package com.kirilov.pdfmanipulator.filebrowser.controller;

import com.kirilov.pdfmanipulator.filebrowser.filechooser.FileSystemTreePanel;
import com.kirilov.pdfmanipulator.filebrowser.ui.Browser;
import com.kirilov.pdfmanipulator.fileio.PDFUtils;
import com.kirilov.pdfmanipulator.mainframe.Browser2WorkplaceMediator;
import com.kirilov.pdfmanipulator.progressbar.DoubleProgressBar;
import java.awt.Cursor;

import java.io.File;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.TreePath;

class BrowserControllerImpl implements BrowserController {

    private Browser browser;
    private FileSelectionListener listener;
    private DoubleProgressBar progressBar;

    @Override
    public void addFilesToWorkplace() {
        browser.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Browser2WorkplaceMediator.workPlace.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        JTree fileTree = browser.getFileSystemTreePanel().getTree();
        TreePath[] selectedPaths = fileTree.getSelectionPaths();
        File file = null;
        progressBar = Browser2WorkplaceMediator.newProgressBar("Adding files to workplace", selectedPaths.length);

        int i = 1;
        for (TreePath path : selectedPaths) {
            file = (File) path.getLastPathComponent();
            String pathStr = file.getPath();
            if (isNewFileValidForAdding(pathStr)) {
                Browser2WorkplaceMediator.addFileToWorkplace(file);
            } else {
                //if it's already added it won't be possible to click or press the add button
                JOptionPane.showMessageDialog(browser, "Cannot add " + pathStr + " because it's secured.");
            }

            progressBar.setInnerProgress(100);
            progressBar.setUpperProgress(i++);
        }
        progressBar.setCursor(Cursor.getDefaultCursor());
        
        browser.getAddSelectedButton().setEnabled(false);


        browser.setCursor(Cursor.getDefaultCursor());
        Browser2WorkplaceMediator.workPlace.setCursor(Cursor.getDefaultCursor());
    }

    private boolean isNewFileValidForAdding(String pathStr) {
        boolean isAlreadyAdded = Browser2WorkplaceMediator.isAlreadyAdded(new File(pathStr));
        boolean isSecurePDF = false;
        if (pathStr.endsWith(".pdf")) {
            isSecurePDF = PDFUtils.isPDFFileSecure(pathStr);
        }

        return !(isAlreadyAdded || isSecurePDF);
    }

    @Override
    public void registerBrowser(Browser browser) {
        this.browser = browser;
        tryToRegisterFileSelectionListener();
    }

    private void tryToRegisterFileSelectionListener() {
        if (this.browser != null) {
            this.listener = FileSelectionListener.createInstance(browser);
        }
    }

    @Override
    public String[] findSystemDrives() {
        File[] roots = File.listRoots();
        String[] drives = new String[roots.length];
        for (int i = 0; i < drives.length; i++) {
            drives[i] = roots[i].getPath();
        }

        if (drives == null || drives.length == 0) {
            return new String[]{"c:/"};
        }

        return drives;
    }

    @Override
    public void addFileListenerToFileTree(JTree tree) {
        tree.addMouseListener(listener);
        tree.addKeyListener(listener);
    }

    @Override
    public FileSystemTreePanel refreshFileSystemTreePanel(JComboBox driveComboBox, JScrollPane scrollPane) {
        String newSelectedItem = (String) driveComboBox.getSelectedItem();
        FileSystemTreePanel fileSystemTreePanel = browser.getFileSystemTreePanel();
        if (newSelectedItem.equals(fileSystemTreePanel.getTree().getModel().getRoot().toString())) {
            //the newly clicked selection is the same as the old selection
            return null;
        }

        fileSystemTreePanel = new FileSystemTreePanel(newSelectedItem);

        JTree tree = fileSystemTreePanel.getTree();
        addFileListenerToFileTree(tree);

        scrollPane.setViewportView(fileSystemTreePanel);
        return fileSystemTreePanel;
    }
}
