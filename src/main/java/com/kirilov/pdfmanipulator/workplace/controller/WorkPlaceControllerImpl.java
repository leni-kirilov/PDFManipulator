package com.kirilov.pdfmanipulator.workplace.controller;

import com.kirilov.pdfmanipulator.fileio.FileUtils;
import com.kirilov.pdfmanipulator.fileio.PDFUtils;
import com.kirilov.pdfmanipulator.mainframe.Browser2WorkplaceMediator;
import com.kirilov.pdfmanipulator.workplace.fileitem.ui.FileItem;
import com.kirilov.pdfmanipulator.workplace.fileitem.controller.FileItemFactory;
import com.kirilov.pdfmanipulator.workplace.fileitem.controller.Sequence;
import com.kirilov.pdfmanipulator.workplace.ui.WorkPlace;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.log4j.Logger;

class WorkPlaceControllerImpl implements WorkPlaceController {
    private static final Logger logger = Logger.getLogger(WorkPlaceControllerImpl.class);

    private static final String MERGED = "_m.pdf";
    private WorkPlace workPlace;
    private String specialFileItem;
    private String specialFileItemThumbPath;

    @Override
    public void addFile(String path, Collection<FileItem> fileItems, boolean isTemp, String thumbPath) {
        logger.info("//TODO - Browser at the moment checks for already added files. "
                + "Should be workplace. Should display message");

        //add new File to UI panel
        FileItem newFileImpl = FileItemFactory.createFileItem(path, isTemp, thumbPath);
        Browser2WorkplaceMediator.getCurrentProgressBar().setInnerProgress(60);
        fileItems.add(newFileImpl);
        
        // after a file is added it's always selected. if we split a file its children are selected as well
        newFileImpl.selectFileItem();

        workPlace.updatePanelContainer();
        workPlace.changeStateButtonsAll(true);
        Browser2WorkplaceMediator.getCurrentProgressBar().setInnerProgress(90);
    }

    @Override
    public void registerWorkPlace(WorkPlace workPlace) {
        this.workPlace = workPlace;
    }

    @Override
    public void removeAllFiles(Collection<FileItem> fileItems) {
        removeFileItems(fileItems);
        fileItems.clear();

        workPlace.updatePanelContainer();
        workPlace.changeStateButtonsAll(false);
        workPlace.changeStateButtonsSelected(false);

        logger.info("//TODO at removeAllFiles - should make 'addSelectionButton enabled true'");
    }

    @Override
    public void removeSelectedFiles(Collection<FileItem> allFileItems, Collection<FileItem> selectedFileItems) {
        removeFileItems(selectedFileItems);

        allFileItems.removeAll(selectedFileItems);
        workPlace.updatePanelContainer();

        workPlace.changeStateButtonsSelected(false);
        logger.info("//TODO at removeSelectedFiles - should make "
                + "'addSelectionButton enabled true if file is now removed'");
    }

    private void removeFileItems(Collection<FileItem> fileItems) {
        for (FileItem fileItem : fileItems) {
            if (fileItem.getPath().equals(specialFileItem)) {
                specialFileItemThumbPath = fileItem.getThumbPath();
                removeFile(fileItem, true);
            } else {
                removeFile(fileItem);
            }
        }
    }

    private void removeFile(FileItem file, boolean keepThumb) {
        file.destroy(keepThumb);
        workPlace.getPanelContainer().remove(file);
    }

    private void removeFile(FileItem file) {
        removeFile(file, false);
    }

    public void splitSelectedFiles(Collection<FileItem> allFileItems, Collection<FileItem> selectedFileItems) {
        List<String> tempFileItemList = new ArrayList<String>();

        List<FileItem> canNotBeSplit = new ArrayList<FileItem>();
        for (FileItem fileItem : selectedFileItems) {
            if (fileItem.getPageCount() < 2) {
                logger.info("File is only a single page." + fileItem.getPath());
                canNotBeSplit.add(fileItem);
                continue;
            }
        }

        selectedFileItems.removeAll(canNotBeSplit);

        for (FileItem file : selectedFileItems) {

            List<String> newSplittedFilesPath = singleFileSplit(file);

            if (newSplittedFilesPath.isEmpty()) {
                logger.info("Split failed for " + file.getPath());
            } else {
                for (String fileSplit : newSplittedFilesPath) {
                    tempFileItemList.add(fileSplit);
                }
            }
        }//end of for

        if (selectedFileItems.isEmpty()) {
            //nothing was split, so nothing to remove
            return;
        }

        removeSelectedFiles(allFileItems, selectedFileItems);

        for (String file : tempFileItemList) {
            addFile(file, allFileItems, true, "");
        }
    }

    private List<String> singleFileSplit(FileItem fileItem) {
        String pathToPDF = fileItem.getPath();
        try {

            //HERE IS THE MAGIC
            String resultDirPath = PDFUtils.splitPDFFile(pathToPDF);
            String fileName = new File(pathToPDF).getName();

            logger.info("//TODO - find a way to keep the way new files names are generated in one place!!!");
            List<String> splits = new ArrayList<String>();
            for (int i = 0; i < PDFUtils.getPagesCountForPDFDocument(fileItem.getPath()); i++) {
                splits.add(resultDirPath + File.separator + FileUtils.getWithoutExtension(fileName) + "-" + i + PDFUtils.PDF_EXTENSION);
            }

            return splits;

        } catch (Exception e) {
            logger.error("", e);
            return null;
        }
    }

    public void mergeSelectedFiles(Collection<FileItem> allFileItems, Collection<FileItem> selectedFileItems) {
        if (selectedFileItems.size() == 1) {
            logger.warn("Cannot merge only 1 pdf - it is already in one piece.");
            return;
        }

        List<File> sortedFiles = (List<File>) Sequence.getInstance().getFilesSorted();
        List<String> filesPath = new ArrayList<String>();
        for (File file : sortedFiles) {
            filesPath.add(file.getAbsolutePath());
        }

        String fileName = FileUtils.getWithoutExtension(new File(filesPath.get(0)).getName()) + MERGED;

        //This file will be removed in a special way, to save the Thumbnail for
        //the new merged file instead of regenerating it again.
        specialFileItem = filesPath.get(0);

        String resultPDFPath = PDFUtils.WORKDIR + File.separator + fileName;
        try {
            PDFUtils.mergePDFFiles(filesPath, resultPDFPath);
        } catch (Exception e) {
            logger.warn("ERROR - merge unsuccessful!",e);
            return;
        }

        removeSelectedFiles(allFileItems, selectedFileItems);
        addFile(resultPDFPath, allFileItems, true, specialFileItemThumbPath);
        specialFileItem = null;
        specialFileItemThumbPath = null;
    }
}