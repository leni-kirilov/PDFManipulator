package com.kirilov.pdfmanipulator.workplace.fileitem.controller;

import com.kirilov.pdfmanipulator.workplace.fileitem.ui.FileItem;

public interface FileItemController {

    public void destroy(boolean keepThumb);

    public void thumbButtonActionPerformed();

    public void registerFile(FileItem fileItem);

    public void performSequenceAction();

    public boolean isSelected();

    public int getPageCount();

    public void initFileItemDetails(String thumbPath);

    public String getThumbPath();
}
