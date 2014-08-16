package com.kirilov.pdfmanipulator.filebrowser.filechooser.junk;

import java.io.File;
import java.io.FileFilter;
import java.util.Enumeration;
import javax.swing.tree.DefaultMutableTreeNode;

@Deprecated
class FileNode extends DefaultMutableTreeNode {

    public FileNode(final File f) {
        super(f);
        if (f != null) {
            if (f.isDirectory()) {
                setAllowsChildren(true);
            } else {
                setAllowsChildren(false);
            }
        }
    }

    @Override
    public File getUserObject() {
        return (File) super.getUserObject();
    }

    @Override
    public String toString() {
        if (userObject == null) {
            return null;
        }
        if (userObject instanceof File) {
            File uObj = (File) userObject;
            String name = uObj.getName();
            int index = name.indexOf(".java");
            if (index != -1) {
                name = name.substring(0, index);
            }
            return name;
        }
        return userObject.toString();
    }

//    public static void main(String[] args) {
////        new FileSystemTreePanel("d:\\").setVisible(true);
//        DefaultMutableTreeNode node = createFileTree(new File("d:\\Lenonardo\\"), null);
//        System.out.println(node.toString());
//        System.out.println();
//    }

    public static FileNode createFileTree(final File root, FileFilter filt) {
        FileNode node = new FileNode(root);
        if (filt == null) {
            filt = new MyFileFilter();
        }

        if (root.isDirectory()) {
            File[] files = root.listFiles(filt);
            if (files != null) {
                for (File file : files) {
                    FileNode nod = createFileTree(file, filt);
                    if (nod.getChildCount() > 0 || file.isFile()) {
                        node.add(nod);
                    }
                }
            }
        }
        return node;
    }

//    public static FileNode removeEmptyDir(FileNode node) {
//        File[] files = node.getUserObject().listFiles();
//    }
    static class MyFileFilter implements FileFilter {

        public boolean accept(File pathname) {
            String fileName = pathname.getName();
            boolean result = false;

            if (fileName.endsWith(".jpg")
                    || fileName.endsWith(".pdf")
                    || fileName.endsWith(".bmp")
                    || fileName.endsWith(".png")
                    || !fileName.contains(".")) {
                result = true;
            }

            return result;
        }
    }
}
