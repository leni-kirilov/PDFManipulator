package com.kirilov.pdfmanipulator.filebrowser.controller;

import com.kirilov.pdfmanipulator.filebrowser.ui.Browser;
import com.kirilov.pdfmanipulator.filebrowser.ui.BrowserImpl;
import com.kirilov.pdfmanipulator.i18n.Translator;

/**
*
* @author Leni Kirilov
*/
public class BrowserFactory {

    private static Browser browser;

    public static Browser getBrowser(Translator translator) {
        if (browser == null) {
            initializeBrowser(translator);
        }
        return browser;
    }

    private static void initializeBrowser(Translator translator) {
        BrowserController browserController = new BrowserControllerImpl();
        browser = new BrowserImpl(browserController, translator);
        FileTransferHandler.createInstance(browserController, browser);
    }

    private BrowserFactory() {
        throw new RuntimeException("You should not instantiate me");
    }
}
