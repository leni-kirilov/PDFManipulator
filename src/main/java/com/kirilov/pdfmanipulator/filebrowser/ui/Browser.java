package com.kirilov.pdfmanipulator.filebrowser.ui;

import com.kirilov.pdfmanipulator.filebrowser.filechooser.FileSystemTreePanel;
import javax.swing.JButton;
import javax.swing.JPanel;

public abstract class Browser extends JPanel {

    public abstract JButton getAddSelectedButton();

    public abstract FileSystemTreePanel getFileSystemTreePanel();
}
