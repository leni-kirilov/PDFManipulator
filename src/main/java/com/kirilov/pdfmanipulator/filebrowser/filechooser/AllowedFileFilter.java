package com.kirilov.pdfmanipulator.filebrowser.filechooser;

import java.io.File;
import java.io.FileFilter;

/**
 *
 * @author Leni Kirilov
 */
public final class AllowedFileFilter implements FileFilter {

    private final static String[] ALLOWED_FILE_EXTENSIONS = {
        ".jpg", ".jpeg", ".pdf",
        ".bmp", ".png", ".gif", ".tiff"
    };
    public static FileFilter filter = new AllowedFileFilter();

    /**
     * At the moment hidden folders are not displayed.
     * Hidden files are displayed.
     * 
     * @param pathname
     * @return
     */
    @Override
    public boolean accept(File pathname) {
        if (pathname.isDirectory() && !pathname.isHidden()) {
            return true;
        }

        String fileName = pathname.getName();

        boolean result = false;
        for (String fileExtension : ALLOWED_FILE_EXTENSIONS) {
            result |= fileName.endsWith(fileExtension);
        }

        return result;
    }
}
