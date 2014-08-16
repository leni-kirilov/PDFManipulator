package com.kirilov.pdfmanipulator.filebrowser.filechooser;

import java.awt.BorderLayout;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.File;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.TransferHandler;
import javax.swing.tree.TreeModel;
import org.apache.log4j.Logger;

public class FileSystemTreePanel extends JPanel {

    private static final Logger logger = Logger.getLogger(FileSystemTreePanel.class);
    private JTree tree;

    public FileSystemTreePanel() {
        this(new FileSystemModel());
    }

    public FileSystemTreePanel(File startPath) {
        this(new FileSystemModel(startPath));
    }

    public FileSystemTreePanel(String startPath) {
        this(new FileSystemModel(startPath));
    }

    public FileSystemTreePanel(FileSystemModel model) {
        tree = new JTree((TreeModel) model) {

            @Override
            public String convertValueToText(Object value, boolean selected,
                    boolean expanded, boolean leaf, int row,
                    boolean hasFocus) {
                return ((File) value).getName();
            }
        };

        addDragFromJTree();

//        tree.setLargeModel(true);
        tree.setRootVisible(false);
        tree.setShowsRootHandles(true);
        tree.putClientProperty("JTree.lineStyle", "Angled");

        setLayout(new BorderLayout());
        add(tree, BorderLayout.CENTER);
        logger.warn("//TODO - make directories appear first, then files and not by alphabetical order");
    }

    private void addDragFromJTree() {
        tree.setDragEnabled(true);
        tree.setTransferHandler(new TransferHandler() {

            @Override
            public int getSourceActions(JComponent c) {
                return COPY;
            }

            @Override
            protected Transferable createTransferable(JComponent c) {
                String result = ((JTree) c).getSelectionPath().getLastPathComponent().toString();
                return new StringSelection(result);
            }

            @Override
            protected void exportDone(JComponent c, Transferable t, int action) {
            }
        });
    }

    public JTree getTree() {
        return tree;
    }
}
