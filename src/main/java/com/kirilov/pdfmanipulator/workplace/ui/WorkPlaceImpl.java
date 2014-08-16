package com.kirilov.pdfmanipulator.workplace.ui;

import com.kirilov.pdfmanipulator.i18n.Translator;
import com.kirilov.pdfmanipulator.i18n.TranslatorFactory;
import com.kirilov.pdfmanipulator.workplace.controller.WorkPlaceFactory;
import com.kirilov.pdfmanipulator.workplace.controller.WorkPlaceController;
import com.kirilov.pdfmanipulator.workplace.fileitem.ui.FileItem;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import org.apache.log4j.Logger;

/**
 *
 * @author Leni Kirilov
 */
public class WorkPlaceImpl extends WorkPlace {
    private static final Logger logger = Logger.getLogger(WorkPlaceImpl.class);

    private LinkedList<FileItem> fileItems;
    private WorkPlaceController workPlaceController;
    private Translator translator;

    public WorkPlaceImpl(WorkPlaceController workPlaceController, Translator translator) {
        this.workPlaceController = workPlaceController;
        this.workPlaceController.registerWorkPlace(this);
        this.translator = translator;
        initComponents();
        fileItems = new LinkedList<FileItem>();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        splitSelectedButton = new javax.swing.JButton();
        mergeSelectedButton = new javax.swing.JButton();
        uploadSelectedButton = new javax.swing.JButton();
        uploadAllButton = new javax.swing.JButton();
        removeSelectedButton = new javax.swing.JButton();
        removeAllButton = new javax.swing.JButton();
        scrollArea = new javax.swing.JScrollPane();
        panelContainer = new javax.swing.JPanel();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        splitSelectedButton.setText(translator.getTranslation("SPLIT SELECTED"));
        splitSelectedButton.setEnabled(false);
        splitSelectedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                splitSelectedButtonActionPerformed(evt);
            }
        });

        mergeSelectedButton.setText(translator.getTranslation("MERGE SELECTED"));
        mergeSelectedButton.setEnabled(false);
        mergeSelectedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mergeSelectedButtonActionPerformed(evt);
            }
        });

        uploadSelectedButton.setText(translator.getTranslation("UPLOAD SELECTED"));
        uploadSelectedButton.setEnabled(false);
        uploadSelectedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadSelectedButtonActionPerformed(evt);
            }
        });

        uploadAllButton.setText(translator.getTranslation("UPLOAD ALL"));
        uploadAllButton.setEnabled(false);
        uploadAllButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadAllButtonActionPerformed(evt);
            }
        });

        removeSelectedButton.setText(translator.getTranslation("REMOVE SELECTED"));
        removeSelectedButton.setEnabled(false);
        removeSelectedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeSelectedButtonActionPerformed(evt);
            }
        });

        removeAllButton.setText(translator.getTranslation("REMOVE ALL"));
        removeAllButton.setEnabled(false);
        removeAllButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeAllButtonActionPerformed(evt);
            }
        });

        scrollArea.setWheelScrollingEnabled(true);

        panelContainer.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        javax.swing.GroupLayout panelContainerLayout = new javax.swing.GroupLayout(panelContainer);
        panelContainer.setLayout(panelContainerLayout);
        panelContainerLayout.setHorizontalGroup(
            panelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 678, Short.MAX_VALUE)
        );
        panelContainerLayout.setVerticalGroup(
            panelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 314, Short.MAX_VALUE)
        );

        scrollArea.setViewportView(panelContainer);
        scrollArea.getVerticalScrollBar().setUnitIncrement(10);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(splitSelectedButton, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mergeSelectedButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 250, Short.MAX_VALUE)
                .addComponent(uploadSelectedButton, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(uploadAllButton, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addComponent(removeSelectedButton, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(removeAllButton, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                .addContainerGap(460, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollArea, javax.swing.GroupLayout.DEFAULT_SIZE, 658, Short.MAX_VALUE)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {mergeSelectedButton, splitSelectedButton, uploadAllButton, uploadSelectedButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(splitSelectedButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(uploadAllButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mergeSelectedButton)
                    .addComponent(uploadSelectedButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollArea, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(removeSelectedButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removeAllButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {mergeSelectedButton, splitSelectedButton, uploadAllButton, uploadSelectedButton});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {removeAllButton, removeSelectedButton});

    }// </editor-fold>//GEN-END:initComponents

    private void removeSelectedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeSelectedButtonActionPerformed
        workPlaceController.removeSelectedFiles(fileItems, getSelectedFiles());
        logger.info("//TODO - add selected files button should be rechecked");
    }//GEN-LAST:event_removeSelectedButtonActionPerformed

    private void removeAllButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeAllButtonActionPerformed
        workPlaceController.removeAllFiles(fileItems);
        logger.info("//TODO - add selected files button should be rechecked");
    }//GEN-LAST:event_removeAllButtonActionPerformed

    private void splitSelectedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_splitSelectedButtonActionPerformed
        workPlaceController.splitSelectedFiles(fileItems, getSelectedFiles());
        logger.info("//TODO - add selected files button should be rechecked");
    }//GEN-LAST:event_splitSelectedButtonActionPerformed

    private void mergeSelectedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mergeSelectedButtonActionPerformed
        workPlaceController.mergeSelectedFiles(fileItems, getSelectedFiles());
        logger.info("//TODO - add selected files button should be rechecked");
    }//GEN-LAST:event_mergeSelectedButtonActionPerformed

    private void uploadSelectedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadSelectedButtonActionPerformed
        logger.warn("//TODO - implement upload selected");
    }//GEN-LAST:event_uploadSelectedButtonActionPerformed

    private void uploadAllButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadAllButtonActionPerformed
        logger.warn("//TODO - implement upload all");
    }//GEN-LAST:event_uploadAllButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton mergeSelectedButton;
    private javax.swing.JPanel panelContainer;
    private javax.swing.JButton removeAllButton;
    private javax.swing.JButton removeSelectedButton;
    private javax.swing.JScrollPane scrollArea;
    private javax.swing.JButton splitSelectedButton;
    private javax.swing.JButton uploadAllButton;
    private javax.swing.JButton uploadSelectedButton;
    // End of variables declaration//GEN-END:variables

    @Override
    public void addFile(String path) {
        workPlaceController.addFile(path, getFileItems(), false, "");
    }

    @Override
    public void addFile(File pathFile) {
        addFile(pathFile.toString());
    }

    @Override
    public void changeStateButtonsAll(boolean value) {
        removeAllButton.setEnabled(value);
        uploadAllButton.setEnabled(value);
    }

    @Override
    public void changeStateButtonsSelected(boolean value) {
        splitSelectedButton.setEnabled(value);
        mergeSelectedButton.setEnabled(value);
        uploadSelectedButton.setEnabled(value);
        removeSelectedButton.setEnabled(value);
    }

    /**
     * This code should be carefully touched!
     * It is copied and refactored from NetBeans code generator.
     */
    @Override
    public void updatePanelContainer() {
        getPanelContainer().removeAll();

        GroupLayout panelContainerLayout = new GroupLayout(getPanelContainer());
        getPanelContainer().setLayout(panelContainerLayout);

        HorizontalGroup:
        {
            GroupLayout.ParallelGroup paralelHorizontalGroup = panelContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING);
            for (FileItem file : getFileItems()) {
                paralelHorizontalGroup.addComponent(file, GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE);
            }

            panelContainerLayout.setHorizontalGroup(paralelHorizontalGroup);
        }

        VerticalGroup:
        {
            GroupLayout.ParallelGroup paralelVerticalGroup = panelContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING);
            GroupLayout.SequentialGroup sequentialGroup = panelContainerLayout.createSequentialGroup();
            paralelVerticalGroup.addGroup(sequentialGroup);
            for (FileItem file : getFileItems()) {
                sequentialGroup.addComponent(file, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
                sequentialGroup.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED);

            }
            sequentialGroup.addContainerGap(33, Short.MAX_VALUE);
            panelContainerLayout.setVerticalGroup(paralelVerticalGroup);
        }

        scrollArea.setViewportView(getPanelContainer());
        scrollArea.repaint();
    }

    @Override
    public JPanel getPanelContainer() {
        return panelContainer;
    }

    @Override
    public LinkedList<FileItem> getFileItems() {
        return fileItems;
    }

    private List<FileItem> getSelectedFiles() {
        List<FileItem> selectedFiles = new ArrayList<FileItem>();

        for (FileItem file : fileItems) {

            if (file.isSelected()) {
                selectedFiles.add(file);
            }
        }

        return selectedFiles;
    }

    //main test
    public static void main(String[] args) {
        JFrame jf = new JFrame();
        jf.add(WorkPlaceFactory.getWorkPlaceInstance(TranslatorFactory.getTranslator(Locale.getDefault())));

        jf.setVisible(true);
        jf.setSize(500, 500);
    }
}