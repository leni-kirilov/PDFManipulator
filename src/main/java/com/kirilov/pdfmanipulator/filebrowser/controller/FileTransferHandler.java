package com.kirilov.pdfmanipulator.filebrowser.controller;

import com.kirilov.pdfmanipulator.filebrowser.ui.Browser;

import javax.swing.TransferHandler;

/**
 *
 * @author Leni Kirilov
 */
public class FileTransferHandler extends TransferHandler {

    static FileTransferHandler fileTransferHandler;

    static TransferHandler createInstance(BrowserController browserController, Browser browser) {
        if (fileTransferHandler == null) {
            fileTransferHandler = new FileTransferHandler(browserController, browser);
        }
        return fileTransferHandler;
    }

    public static TransferHandler getInstance() {
        return fileTransferHandler;
    }
    //----------NON-STATIC methods
    private BrowserController browserController;
    private Browser browser;

    private FileTransferHandler(BrowserController browserController, Browser browser) {
        this.browserController = browserController;
        this.browser = browser;
    }

    @Override
    public boolean canImport(TransferSupport support) {
        return browser.getAddSelectedButton().isEnabled();
    }

    @Override
    public boolean importData(TransferSupport support) {
        browserController.addFilesToWorkplace();
        return true;
    }
}
