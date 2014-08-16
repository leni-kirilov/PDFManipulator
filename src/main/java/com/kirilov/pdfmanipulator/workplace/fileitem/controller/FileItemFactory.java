package com.kirilov.pdfmanipulator.workplace.fileitem.controller;

import com.kirilov.pdfmanipulator.workplace.fileitem.ui.FileItem;
import com.kirilov.pdfmanipulator.workplace.fileitem.ui.FileItemImpl;

public class FileItemFactory {

//    private static FileItem createFileItem(String path, boolean isTemp) {
//        return createFileItem(path, false, "");
//    }

//    private static FileItem createFileItem(String path) {
//        return createFileItem(path, false);
//    }

    public static FileItem createFileItem(String path, boolean isTemp, String thumbPath) {
        FileItemController fileController = new FileItemControllerImpl();
        FileItem fileItem = new FileItemImpl(fileController, path, isTemp, thumbPath);
        return fileItem;
    }

    private FileItemFactory() {
        throw new RuntimeException("You should not instanciate me");
    }
}
