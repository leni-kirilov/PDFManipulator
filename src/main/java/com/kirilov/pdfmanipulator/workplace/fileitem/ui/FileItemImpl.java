package com.kirilov.pdfmanipulator.workplace.fileitem.ui;

import com.kirilov.pdfmanipulator.mainframe.Browser2WorkplaceMediator;
import com.kirilov.pdfmanipulator.workplace.fileitem.controller.FileItemController;
import javax.swing.JCheckBox;
import org.apache.log4j.Logger;

/**
 *
 * @author Leni Kirilov
 */
public class FileItemImpl extends FileItem {

    private static final Logger logger = Logger.getLogger(FileItemImpl.class);
    /**
     * To be used when the file is created by the PDFManipulator program and will be deleted upon destroy()
     */
    private boolean isTemp;
    private String path;
    private final String originalPath;
    private FileItemController fileController;

    public FileItemImpl(FileItemController fileController, String path, boolean isTemp) {
        this(fileController, path, isTemp, "");
    }

    public FileItemImpl(FileItemController fileController, String path, boolean isTemp, String thumbPath) {
        this.fileController = fileController;
        this.path = path;
        this.originalPath = path;
        this.fileController.registerFile(this);
        this.isTemp = isTemp;
        Browser2WorkplaceMediator.getCurrentProgressBar().setInnerProgress(20);
        initComponents();
        Browser2WorkplaceMediator.getCurrentProgressBar().setInnerProgress(40);
        this.fileController.initFileItemDetails(thumbPath);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        selectedCheckBox = new javax.swing.JCheckBox();
        thumbButton = new javax.swing.JButton();
        fileNameLabel = new javax.swing.JLabel();
        pageCountLabel = new javax.swing.JLabel();
        documentTypeComboBox = new javax.swing.JComboBox();
        inputField = new javax.swing.JTextField();
        sequenceLabel = new javax.swing.JLabel();
        sizeLabel = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        selectedCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectedCheckBoxActionPerformed(evt);
            }
        });

        thumbButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/kirilov/pdfmanipulator/workplace/fileitem/pdfToSplit.pdf_.JPG_resized_smallll.jpg"))); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("com/kirilov/pdfmanipulator/i18n/bundles/translations_en"); // NOI18N
        thumbButton.setToolTipText(bundle.getString("PRESS TO DISPLAY FILE")); // NOI18N
        thumbButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thumbButtonActionPerformed(evt);
            }
        });

        fileNameLabel.setText("");

        pageCountLabel.setText("");

        documentTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Document Type 1.Document Type 1.Document Type 1.50", "Document Type 1.Document Type 1.Document Type 1.50", "Document Type 1.Document Type 1.Document Type 1.50", "Document Type 1.Document Type 1.Document Type 1.50" }));
        documentTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                documentTypeComboBoxActionPerformed(evt);
            }
        });

        inputField.setText(bundle.getString("ENTER GBL NUMBER HERE")); // NOI18N

        sequenceLabel.setText(" ");

        sizeLabel.setText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sequenceLabel)
                    .addComponent(selectedCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(thumbButton, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pageCountLabel)
                        .addGap(31, 31, 31)
                        .addComponent(sizeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addComponent(documentTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inputField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(fileNameLabel))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(thumbButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(selectedCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sequenceLabel)
                .addContainerGap(27, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(fileNameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pageCountLabel)
                    .addComponent(sizeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputField, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(documentTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void thumbButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thumbButtonActionPerformed
        fileController.thumbButtonActionPerformed();
    }//GEN-LAST:event_thumbButtonActionPerformed

    private void documentTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_documentTypeComboBoxActionPerformed
        logger.warn("//TODO - documentTypeComboBoxActionPerformed not implemented");
    }//GEN-LAST:event_documentTypeComboBoxActionPerformed

    private void selectedCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectedCheckBoxActionPerformed
        fileController.performSequenceAction();
    }//GEN-LAST:event_selectedCheckBoxActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox documentTypeComboBox;
    private javax.swing.JLabel fileNameLabel;
    private javax.swing.JTextField inputField;
    private javax.swing.JLabel pageCountLabel;
    private javax.swing.JCheckBox selectedCheckBox;
    private javax.swing.JLabel sequenceLabel;
    private javax.swing.JLabel sizeLabel;
    private javax.swing.JButton thumbButton;
    // End of variables declaration//GEN-END:variables

    @Override
    public void destroy(boolean keepThumb) {
        fileController.destroy(keepThumb);
    }

    //===========GETTER METHODS ============
    @Override
    public String getPath() {
        return path;
    }

    @Override
    public void setPath(String newFilePath) {
        path = newFilePath;
    }

    @Override
    public javax.swing.JLabel getFileNameLabel() {
        return fileNameLabel;
    }

    @Override
    public javax.swing.JLabel getPageCountLabel() {
        return pageCountLabel;
    }

    @Override
    public javax.swing.JLabel getSequenceLabel() {
        return sequenceLabel;
    }

    @Override
    public javax.swing.JLabel getSizeLabel() {
        return sizeLabel;
    }

    @Override
    public javax.swing.JButton getThumbButton() {
        return thumbButton;
    }

    @Override
    public JCheckBox getSelectedCheckBox() {
        return selectedCheckBox;
    }

    @Override
    public boolean isSelected() {
        return fileController.isSelected();
    }

    @Override
    public String getOriginalPath() {
        return originalPath;
    }

    @Override
    public boolean isTemp() {
        return isTemp;
    }

    @Override
    public void setTemp() {
        isTemp = true;
    }

    @Override
    public int getPageCount() {
        return fileController.getPageCount();
    }

    @Override
    public String getThumbPath(){
        return fileController.getThumbPath();
    }

    /**
     * this is used for the UNDO feauture
     */
    @Override
    public void selectFileItem(){
        fileController.performSequenceAction();
        selectedCheckBox.setSelected(true);
    }
}
