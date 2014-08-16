package com.kirilov.pdfmanipulator.progressbar;

import java.awt.BorderLayout;
import java.awt.Cursor;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

/**
 *
 * @author Leni Kirilov
 */
public class DoubleProgressBar extends JFrame {

    private JProgressBar upperProgressBar;
    private JProgressBar innerProgressBar;

    public DoubleProgressBar(String frameTitle, int upperMaximum) {
        initFrame(frameTitle);
        this.upperProgressBar = initProgressBar(0, upperMaximum);
        this.innerProgressBar = initProgressBar(0, 100);

        this.add(upperProgressBar, BorderLayout.NORTH);
        this.add(innerProgressBar, BorderLayout.SOUTH);

        reset();
    }

    public JProgressBar getUpperProgressBar() {
        return upperProgressBar;
    }

    public void setUpperMaximum(int max) {
        this.upperProgressBar.setMaximum(max);
    }

    public int getUpperMinimum() {
        return upperProgressBar.getMinimum();
    }

    public int getUpperMaximum() {
        return upperProgressBar.getMaximum();
    }

    public void setUpperProgress(int i) {
        upperProgressBar.setValue(i);
        upperProgressBar.setString(i + " / " + upperProgressBar.getMaximum() + " files converted");
        this.repaint();
        upperProgressBar.repaint();
    }

    public int getInnerMinimum() {
        return innerProgressBar.getMinimum();
    }

    public int getInnerMaximum() {
        return innerProgressBar.getMaximum();
    }

    public void setInnerProgress(final int i) {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                innerProgressBar.setValue(i);
                DoubleProgressBar.this.repaint();
                innerProgressBar.repaint();
            }
        });
    }

    private void initFrame(String title) {
        this.setTitle(title);
        this.setLocationRelativeTo(null);
        this.setSize(300, 69);
        this.setResizable(false);
    }

    private JProgressBar initProgressBar(int min, int max) {
        JProgressBar progressBar = new JProgressBar(min, max);
        progressBar.setStringPainted(true);
//        progressBar.setIndeterminate(true);
        progressBar.setToolTipText("tooltip text");
        return progressBar;
    }

    public void reset() {
        setInnerProgress(0);
        setUpperProgress(0);
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    }

    public static void main(String[] args) throws Exception {
        DoubleProgressBar progressBar = new DoubleProgressBar("File conversion in progress...", 5);
        progressBar.setVisible(true);

        for (int i = progressBar.getUpperMinimum() + 1; i <= progressBar.getUpperMaximum(); i++) {
            for (int j = progressBar.getInnerMinimum(); j <= progressBar.getInnerMaximum(); j++) {
                progressBar.setInnerProgress(j);
                Thread.sleep(50);
            }

            progressBar.setUpperProgress(i);
            Thread.sleep(100);
        }

        progressBar.setCursor(Cursor.getDefaultCursor());
    }
}
