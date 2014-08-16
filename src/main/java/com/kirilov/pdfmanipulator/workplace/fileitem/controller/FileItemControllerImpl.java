package com.kirilov.pdfmanipulator.workplace.fileitem.controller;

import com.kirilov.pdfmanipulator.fileio.FileUtils;
import com.kirilov.pdfmanipulator.fileio.ImageUtils;
import com.kirilov.pdfmanipulator.fileio.PDFUtils;
import com.kirilov.pdfmanipulator.mainframe.Browser2WorkplaceMediator;
import com.kirilov.pdfmanipulator.workplace.WorkPlace2FileItemMediator;
import com.kirilov.pdfmanipulator.workplace.fileitem.ui.FileItem;

import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import javax.swing.ImageIcon;
import org.apache.log4j.Logger;

class FileItemControllerImpl implements FileItemController {

    private static final Logger logger = Logger.getLogger(FileItemControllerImpl.class);
    private static final int PDF_PAGE_HEIGHT = 750;
    private static final int PDF_PAGE_WIDHT = 575;
    private static final int PDF_THUMB_PAGE_HEIGHT = 77;
    private static final int PDF_THUMB_PAGE_WIDHT = 55;
    //
    private static final String RESIZED = "_r.jpg";
    private static final String CONVERTED = "_c.pdf";
    private static final String THUMB = "_t.jpg";
    //
    private static final int FILENAME_LIMIT = 70;
    private static final NumberFormat FORMATTER;
    private static final Sequence FILE_ITEM_SEQUENCE = Sequence.getInstance();
    //
    private FileItem fileItem;
    private File file;
    private String thumbPath;
    private boolean isInSequence = false;
    private int pageCount;

    static {
        FORMATTER = NumberFormat.getInstance();
        FORMATTER.setMaximumFractionDigits(2);
    }

    @Override
    public void performSequenceAction() {
        if (isInSequence) {
            FILE_ITEM_SEQUENCE.removeFileItem(fileItem);
            fileItem.getSequenceLabel().setText(" ");
            isInSequence = false;
        } else {
            int number = FILE_ITEM_SEQUENCE.addFileItem(fileItem);
            fileItem.getSequenceLabel().setText(number + " ");
            isInSequence = true;
        }

        updateStateButtonsSelected();
    }

    @Override
    public void destroy(boolean keepThumb) {
        if (isInSequence) {
            FILE_ITEM_SEQUENCE.removeFileItem(fileItem);
        }

        if (!keepThumb) {
            deleteTempFile(thumbPath);
        }

        if (fileItem.isTemp()) {
            deleteTempFile(file);
        }

        PDFUtils.removePDDocument(file.getAbsolutePath());
    }

    @Override
    public void registerFile(FileItem fileItem) {
        this.fileItem = fileItem;
        this.file = new File(this.fileItem.getPath());
    }

    public void initFileItemDetails(String thumbPath) {
        if(thumbPath.isEmpty()){
            fileItem.getThumbButton().setIcon(getThumb());
        }else{
            this.thumbPath = thumbPath;
            fileItem.getThumbButton().setIcon(new ImageIcon(this.thumbPath));
        }
        Browser2WorkplaceMediator.getCurrentProgressBar().setInnerProgress(60);
        fileItem.getFileNameLabel().setText(extractName());
        Browser2WorkplaceMediator.getCurrentProgressBar().setInnerProgress(70);
        fileItem.getPageCountLabel().setText(calculatePageCount());
        fileItem.getSizeLabel().setText(calculateSize());
        Browser2WorkplaceMediator.getCurrentProgressBar().setInnerProgress(80);
    }

    /**
     * Opens the associated program with PDF file extension
     */
    @Override
    public void thumbButtonActionPerformed() {
       String osName = System.getProperty("os.name");
       if(osName.contains("Windows")){
           openInWindowsOS();
       }else if(osName.contains("Mac")){
           openInMacOS();
       }
    }

    private void openInWindowsOS(){
         try{
            Process p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler \"" + file.toString()+ "\"");
            p.waitFor();
        } catch (IOException e) {
            logger.warn("", e);
        } catch (InterruptedException e) {
            logger.warn("", e);
        }
    }
    
    private void openInMacOS() {
        try {
            Process p = Runtime.getRuntime().exec("open " + file.toString());
        } catch (IOException e) {
            logger.warn("", e);
        }
    }

    public boolean isSelected() {
        return isInSequence;
    }
    //============INIT METHODS    \/   \/   \/===========

    private ImageIcon getThumb() {
        logger.warn("//TODO - Make adding files a seperate thread process so that the application doesn't hang!");

        //if image - convert to pdf
        convertImageToPDF();
        logger.info("//TODO - after conversion, too strange name for file... be careful for name collisions");

        //make thumb out of PDF
        String largeThumbFileName = FileUtils.getWithoutExtension(new File(fileItem.getPath())) + "_.JPG";
        File largeThumbFile = PDFUtils.createThumbnailFromPDFFirstPage(largeThumbFileName, fileItem.getPath());

        if (largeThumbFile != null) {

            String smallThumbPath = FileUtils.getWithoutExtension(largeThumbFile.getAbsolutePath()) + THUMB;
            //resize thumb to fit little icon
            ImageUtils.resizeImageToJPG(largeThumbFile.getAbsolutePath(), smallThumbPath, PDF_THUMB_PAGE_WIDHT, PDF_THUMB_PAGE_HEIGHT);
            deleteTempFile(largeThumbFile);

            thumbPath = smallThumbPath;
            return new ImageIcon(smallThumbPath);

        } else {
            return new ImageIcon(getClass().getResource("/com/kirilov/pdfmanipulator/workplace/fileitem/pdfToSplit.pdf_.JPG_resized_smallll.jpg"));
        }
    }

    private void convertImageToPDF() {
        if (!fileItem.getPath().endsWith(PDFUtils.PDF_EXTENSION)) {

            String imageFilePath = fileItem.getPath();

            //resize image to fit 1 PDF page
            imageFilePath = PDFUtils.WORKDIR + File.separator + FileUtils.getWithoutExtension(file) + RESIZED;
            ImageUtils.resizeImageToJPG(fileItem.getPath(), imageFilePath, PDF_PAGE_WIDHT, PDF_PAGE_HEIGHT);

            //convert to PDF with only 1 page
            PDFUtils.createPDFFromJPG(FileUtils.getWithoutExtension(imageFilePath) + CONVERTED, imageFilePath);

            deleteTempFile(imageFilePath);
            imageFilePath = FileUtils.getWithoutExtension(imageFilePath) + CONVERTED;
            fileItem.setPath(imageFilePath);
            registerFile(fileItem);
            fileItem.setTemp();
        }
    }

    private String extractName() {
        String fileName = file.getName();
        if (fileName.length() > FILENAME_LIMIT) {
            return fileName.substring(0, FILENAME_LIMIT - 2) + "..." + fileName.substring(fileName.lastIndexOf("."));
        } else {
            return fileName;
        }
    }

    private String calculatePageCount() {
        if (pageCount == 0) {
            int pagesCount = PDFUtils.getPagesCountForPDFDocument(fileItem.getPath());
            pageCount = pagesCount;
        }

        if (pageCount != -1) {
            return pageCount + " pages";
        } else {
            return "1 page";
        }
    }

    private String calculateSize() {
        double sizeInB = file.length();

        if (sizeInB > 500) {
            double sizeInKB = sizeInB / 1024;
            if (sizeInKB > 1024) {
                double sizeInMB = sizeInKB / 1024;
                return FORMATTER.format(sizeInMB) + " MB";
            } else {
                return FORMATTER.format(sizeInKB) + " KB";
            }

        } else {
            return "< 1 KB";
        }
    }

    // ------------------INIT methods end ^^^-------------
    private void updateStateButtonsSelected() {
        if (FILE_ITEM_SEQUENCE.getNumberOfFilesInSequence() > 0) {
            WorkPlace2FileItemMediator.changeStateButtonsSelected(true);
        } else {
            WorkPlace2FileItemMediator.changeStateButtonsSelected(false);
        }
    }

    private void deleteTempFile(String tempFilePath) {
        logger.debug("Trying to delete= " + tempFilePath);
        deleteTempFile(new File(tempFilePath));
    }

    private void deleteTempFile(File tempFile) {
        if (tempFile.delete()) {
            logger.info("Successful delete of " + tempFile);
        } else {
            logger.info("Failed delete of " + tempFile);
        }
    }

    public int getPageCount() {
       return pageCount;
    }

    @Override
    public String getThumbPath(){
        return thumbPath;
    }
}
