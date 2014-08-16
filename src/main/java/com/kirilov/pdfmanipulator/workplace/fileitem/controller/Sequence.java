package com.kirilov.pdfmanipulator.workplace.fileitem.controller;

import com.kirilov.pdfmanipulator.workplace.fileitem.ui.FileItem;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Leni Kirilov
 */
public class Sequence {

    private static int nextFreeNumber = 1;
    private static Sequence reusableSequence = new Sequence();
    private HashMap<FileItem, Integer> files = new HashMap<FileItem, Integer>();;

    public static Sequence getInstance() {
        return reusableSequence;
    }

    int addFileItem(FileItem fileItem) {
        files.put(fileItem, nextFreeNumber++);
        return nextFreeNumber - 1;
    }

    void removeFileItem(FileItem fileItem) {
        int numberRemoved = files.remove(fileItem);
        shiftNumbers(numberRemoved);
        nextFreeNumber--;
        updateAllFileItemsLabels();
    }

    void resetSequence() {
        nextFreeNumber = 1;
        files.clear();
    }

    int getNumberOfFilesInSequence() {
        return nextFreeNumber - 1;
    }

    public Collection<File> getFilesSorted() {
        List<File> allFiles = new ArrayList<File>();

        List<Integer> sortedValues = new ArrayList<Integer>(files.values());
        Collections.sort(sortedValues);

        for (int value : sortedValues) {
            for (FileItem key : files.keySet()) {
                if (files.get(key).equals(value)) {
                    allFiles.add(new File(key.getPath()));
                    break;
                }
            }
        }//end for outers

        return allFiles;
    }

    //=========PRIVATE METHODS =============
    private void updateAllFileItemsLabels() {
        for (FileItem fileItem : files.keySet()) {
            fileItem.getSequenceLabel().setText(files.get(fileItem) + " ");
        }
    }

    private void shiftNumbers(int removedNumber) {
        List<Integer> sortedValues = new ArrayList<Integer>(files.values());
        Collections.sort(sortedValues);

        for (int value : sortedValues) {
            if (value <= removedNumber) {
                continue;
            }

            for (FileItem key : files.keySet()) {
                if (files.get(key).equals(value)) {
                    files.put(key, value - 1);
                    break;
                }
            }

        }//end for outers
    }
    //===================TEST MAIN==================
    /*public static void main(String[] args) {
    int NUMBER_OF_TRIES = 100;
    long time = System.currentTimeMillis();

    for (int i = 0; i < NUMBER_OF_TRIES; i++) {

    HashMap<String, Integer> map = new HashMap<String, Integer>();

    for (int j = 1; j < 1000; j++) {
    map.put("file" + j, j);
    }

    int removedValue = 0;

    List<Integer> sortedValues = new ArrayList<Integer>(map.values());
    Collections.sort(sortedValues);
    for (int value : sortedValues) {

    if (value <= removedValue) {
    continue;
    }

    for (String key : map.keySet()) {
    if (map.get(key).equals(value)) {
    map.put(key, value - 1);
    break;
    }
    }
    }
    }
    System.out.println("end : " + ((System.currentTimeMillis() - time) / NUMBER_OF_TRIES));
    }*/
}
