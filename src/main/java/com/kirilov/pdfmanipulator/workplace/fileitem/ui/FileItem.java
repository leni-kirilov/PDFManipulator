package com.kirilov.pdfmanipulator.workplace.fileitem.ui;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class FileItem extends JPanel {

    public abstract String getThumbPath();

    public abstract String getPath();

    public abstract String getOriginalPath();

    public abstract JButton getThumbButton();

    public abstract JLabel getSizeLabel();

    public abstract JLabel getFileNameLabel();

    public abstract JLabel getPageCountLabel();

    public abstract JLabel getSequenceLabel();

    public abstract JCheckBox getSelectedCheckBox();

    public abstract int getPageCount();

    public abstract void destroy(boolean keepThumb);

    public abstract boolean isSelected();

    public abstract boolean isTemp();

    public abstract void setTemp();

    public abstract void setPath(String newFilePath);

    public abstract void selectFileItem();
}
