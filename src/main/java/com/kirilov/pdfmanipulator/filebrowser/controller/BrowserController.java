package com.kirilov.pdfmanipulator.filebrowser.controller;

import com.kirilov.pdfmanipulator.filebrowser.filechooser.FileSystemTreePanel;
import com.kirilov.pdfmanipulator.filebrowser.ui.Browser;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTree;

public interface BrowserController {

    public void addFilesToWorkplace();

    public void addFileListenerToFileTree(JTree tree);

    public FileSystemTreePanel refreshFileSystemTreePanel(JComboBox driveComboBox, JScrollPane scrollPane);

    public String[] findSystemDrives();

    public void registerBrowser(Browser browser);
}
