package com.kirilov.pdfmanipulator.filebrowser.controller;

import com.kirilov.pdfmanipulator.filebrowser.ui.Browser;
import com.kirilov.pdfmanipulator.mainframe.Browser2WorkplaceMediator;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.tree.TreePath;
import org.apache.log4j.Logger;

/**
 *
 * @author Leni Kirilov
 */
public class FileSelectionListener implements MouseListener, KeyListener {

    private static final Logger logger = Logger.getLogger(FileSelectionListener.class);

    private Browser browser;
    private static FileSelectionListener instance;

    public static FileSelectionListener createInstance(Browser browser) {
        if (instance == null) {
            instance = new FileSelectionListener(browser);
        }
        return instance;
    }

    private FileSelectionListener(Browser browser) {
        this.browser = browser;
    }

    //-------MOUSE METHODS ----------
    private void mouseAction() {
        checkSelection();
    }

    private boolean checkSelection() {
        boolean isSomethingSelected = isSomethingSelected();
        boolean result = isSomethingSelected && selectedOnlyFiles();
        browser.getAddSelectedButton().setEnabled(result);
        return result;
    }

    private boolean isSomethingSelected() {
        int selection = browser.getFileSystemTreePanel().getTree().getSelectionCount();
        return selection > 0 ? true : false;
    }

    private boolean selectedOnlyFiles() {
        TreePath[] paths = browser.getFileSystemTreePanel().getTree().getSelectionPaths();

        File currentFile = null;
        for (TreePath path : paths) {
            currentFile = (File) path.getLastPathComponent();
            if (currentFile.isDirectory() || Browser2WorkplaceMediator.isAlreadyAdded(currentFile)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseAction();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    //================KEY METHODS =============
    private boolean keyAction() {
        return checkSelection();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (keyAction() && e.getKeyCode() == KeyEvent.VK_ENTER) {
            logger.info("//TODO - add a file by pressing Enter");
        }
    }
}
