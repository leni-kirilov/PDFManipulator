package com.kirilov.pdfmanipulator.filebrowser.ui;

import com.kirilov.pdfmanipulator.filebrowser.controller.BrowserFactory;
import com.kirilov.pdfmanipulator.filebrowser.controller.BrowserController;
import com.kirilov.pdfmanipulator.filebrowser.filechooser.FileSystemTreePanel;
import com.kirilov.pdfmanipulator.i18n.Translator;
import com.kirilov.pdfmanipulator.i18n.TranslatorFactory;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Leni Kirilov
 */
public class BrowserImpl extends Browser {

    private BrowserController browserController;
    private Translator translator;

    public BrowserImpl(BrowserController browserController, Translator translator) {
        this.browserController = browserController;
        this.browserController.registerBrowser(this);
        this.translator = translator;

        initComponents();
        this.browserController.addFileListenerToFileTree(fileSystemTreePanel.getTree());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addSelectedButton = new javax.swing.JButton();
        driveComboBox = new javax.swing.JComboBox();
        scrollPane = new javax.swing.JScrollPane();
        //TODO 2 calls to getDrives at init... netbeans locked the code - changed it!
        fileSystemTreePanel = new FileSystemTreePanel(browserController.findSystemDrives()[0]);

        addSelectedButton.setText(translator.getTranslation("ADD SELECTED"));
        addSelectedButton.setEnabled(false);
        addSelectedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSelectedButtonActionPerformed(evt);
            }
        });

        driveComboBox.setModel(new javax.swing.DefaultComboBoxModel(browserController.findSystemDrives()));
        driveComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                driveComboBoxActionPerformed(evt);
            }
        });

        scrollPane.setViewportView(fileSystemTreePanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(addSelectedButton, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
            .addComponent(driveComboBox, 0, 210, Short.MAX_VALUE)
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(addSelectedButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(driveComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void driveComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_driveComboBoxActionPerformed
        FileSystemTreePanel newTreePanel = browserController.refreshFileSystemTreePanel(driveComboBox, scrollPane);
        if (newTreePanel != null) {
            this.fileSystemTreePanel = newTreePanel;
        }
    }//GEN-LAST:event_driveComboBoxActionPerformed

    private void addSelectedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSelectedButtonActionPerformed
        browserController.addFilesToWorkplace();
    }//GEN-LAST:event_addSelectedButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addSelectedButton;
    private javax.swing.JComboBox driveComboBox;
    private com.kirilov.pdfmanipulator.filebrowser.filechooser.FileSystemTreePanel fileSystemTreePanel;
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables

    @Override
    public JButton getAddSelectedButton() {
        return addSelectedButton;
    }

    @Override
    public FileSystemTreePanel getFileSystemTreePanel() {
        return fileSystemTreePanel;
    }
    //test main

    public static void main(String[] args) {
        JFrame jf = new JFrame();
        jf.add(BrowserFactory.getBrowser(TranslatorFactory.getTranslator(Locale.getDefault())));

        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(500, 500);
    }
}
