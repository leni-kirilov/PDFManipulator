package com.kirilov.pdfmanipulator.filebrowser.filechooser;

import java.io.File;
import java.io.Serializable;
import javax.swing.tree.TreePath;

public class FileSystemModel extends TreeModelSupport implements Serializable {

    File rootFile;

    public FileSystemModel() {
        this(System.getProperty("user.home"));
    }

    public FileSystemModel(String startPath) {
        rootFile = new File(startPath);
    }

    public FileSystemModel(File startPath) {
        rootFile = startPath;
    }

    @Override
    public Object getRoot() {
        return rootFile;
    }

    @Override
    public Object getChild(Object parent, int index) {
        File directory = (File) parent;
        File[] children = directory.listFiles(AllowedFileFilter.filter);
        return children[index];
    }

    @Override
    public int getChildCount(Object parent) {
        File fileSysEntity = (File) parent;
        if (fileSysEntity.isDirectory()) {
            File[] children = fileSysEntity.listFiles(AllowedFileFilter.filter);
            if (children != null) {
                return children.length;
            }
        }
        return 0;
    }

    @Override
    public boolean isLeaf(Object node) {
        File nod = (File) node;
        return nod.isFile();
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        File directory = (File) parent;
        File fileSysEntity = (File) child;
        String[] children = directory.list();
        int result = -1;

        for (int i = 0; i < children.length; ++i) {
            if (fileSysEntity.getName().equals(children[i])) {
                result = i;
                break;
            }
        }
        return result;
    }
}
