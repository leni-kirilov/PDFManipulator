package com.kirilov.pdfmanipulator.workplace.ui;

import com.kirilov.pdfmanipulator.workplace.fileitem.ui.FileItem;
import java.io.File;
import java.util.Collection;
import javax.swing.JPanel;

public abstract class WorkPlace extends JPanel {

    public abstract void addFile(String path);

    public abstract void addFile(File pathFile);

    public abstract void changeStateButtonsAll(boolean value);

    public abstract void changeStateButtonsSelected(boolean value);

    public abstract JPanel getPanelContainer();

    public abstract Collection<FileItem> getFileItems();

    public abstract void updatePanelContainer();
}
