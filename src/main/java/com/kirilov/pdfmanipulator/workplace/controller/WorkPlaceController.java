package com.kirilov.pdfmanipulator.workplace.controller;

import com.kirilov.pdfmanipulator.workplace.fileitem.ui.FileItem;
import com.kirilov.pdfmanipulator.workplace.ui.WorkPlace;
import java.util.Collection;

public interface WorkPlaceController {

    public void addFile(String path, Collection<FileItem> fileItems, boolean isTemp, String thumbPath);

    public void registerWorkPlace(WorkPlace workPlace);

    public void removeAllFiles(Collection<FileItem> fileItems);

    public void splitSelectedFiles(Collection<FileItem> allFileItems, Collection<FileItem> fileItems);

    public void removeSelectedFiles(Collection<FileItem> allFileItems, Collection<FileItem> selectedFileItems);

    public void mergeSelectedFiles(Collection<FileItem> allFileItems, Collection<FileItem> selectedFileItems);
}
