package com.kirilov.pdfmanipulator.mainframe;

import com.kirilov.pdfmanipulator.filebrowser.controller.FileTransferHandler;
import com.kirilov.pdfmanipulator.filebrowser.ui.Browser;
import com.kirilov.pdfmanipulator.progressbar.DoubleProgressBar;

import com.kirilov.pdfmanipulator.workplace.fileitem.ui.FileItem;
import com.kirilov.pdfmanipulator.workplace.ui.WorkPlace;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;

/**
 *
 * @author Leni Kirilov
 */
public final class Browser2WorkplaceMediator {

    public static Browser browser;
    public static WorkPlace workPlace;
    private static DoubleProgressBar currentProgressBar = new DoubleProgressBar("", 0);

    public static List<String> getAllFilesPaths() {
        List<String> files = new ArrayList<String>();
        for (FileItem fileItem : workPlace.getFileItems()) {
            files.add(fileItem.getPath());
        }

        return files;
    }

    public static List<String> getAllFilesOriginalPaths() {
        List<String> files = new ArrayList<String>();
        for (FileItem fileItem : workPlace.getFileItems()) {
            files.add(fileItem.getOriginalPath());
        }

        return files;
    }

    public static void addFileToWorkplace(String filePath) {
        workPlace.addFile(filePath);
    }

    public static void addFileToWorkplace(File file) {
        addFileToWorkplace(file.getAbsolutePath());
    }

    public static boolean isAlreadyAdded(File file) {
        return isAlreadyAdded(file.getAbsolutePath());
    }

    public static boolean isAlreadyAdded(String fileAbsolutePath) {
        List<String> files = getAllFilesOriginalPaths();

        for (String filePath : files) {
            if (filePath.equals(fileAbsolutePath)) {
                return true;
            }
        }//end of for

        return false;
    }

    public static DoubleProgressBar newProgressBar(final String frameTitle, final int upperMax) {
        currentProgressBar.reset();
        currentProgressBar.setTitle(frameTitle);
        currentProgressBar.setUpperMaximum(upperMax);
        currentProgressBar.setVisible(true);
        currentProgressBar.setAlwaysOnTop(true);
//        currentProgressBar = new DoubleProgressBar(frameTitle, upperMax);
        return getCurrentProgressBar();
    }

    public static DoubleProgressBar getCurrentProgressBar() {
        return currentProgressBar;
    }

    public static void connectTransferHandlerToWorkplace() {
        workPlace.setTransferHandler(FileTransferHandler.getInstance());
    }
}
