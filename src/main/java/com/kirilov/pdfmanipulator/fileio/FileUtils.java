package com.kirilov.pdfmanipulator.fileio;

import java.io.File;

/**
 *
 * @author Leni Kirilov
 */
public final class FileUtils {


    public static String getWithoutExtension(String fileNameWithExtension){
        int indexOfLastPoint = fileNameWithExtension.lastIndexOf(".");
        String fileNameWithoutExtension = fileNameWithExtension.substring(0, indexOfLastPoint);
        return fileNameWithoutExtension;
    }
    public static String getWithoutExtension(File file){
        return getWithoutExtension(file.getName());
    }
}
