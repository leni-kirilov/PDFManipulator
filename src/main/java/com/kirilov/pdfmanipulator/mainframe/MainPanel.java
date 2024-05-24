package com.kirilov.pdfmanipulator.mainframe;

import com.kirilov.pdfmanipulator.filebrowser.controller.BrowserFactory;
import com.kirilov.pdfmanipulator.filebrowser.ui.Browser;
import com.kirilov.pdfmanipulator.i18n.Translator;
import com.kirilov.pdfmanipulator.i18n.TranslatorFactory;
import com.kirilov.pdfmanipulator.workplace.controller.WorkPlaceFactory;
import com.kirilov.pdfmanipulator.workplace.ui.WorkPlace;
import java.awt.Dimension;

import java.io.File;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author Leni Kirilov
 */
public class MainPanel extends JPanel {

    private static final Logger logger = Logger.getLogger(MainPanel.class);

    static {
        PropertyConfigurator.configure(new File("src/main/java/com/kirilov/pdfmanipulator/mainframe/log_config.prop").getAbsolutePath());
    }

    public MainPanel() {
        logger.info("//TODO - Make as an applet!");
        initComponents();
    }

    private void initComponents() {

        Translator translator = TranslatorFactory.getTranslator(Locale.ENGLISH);

        browserPanel = BrowserFactory.getBrowser(translator);
        workPlacePanel = WorkPlaceFactory.getWorkPlaceInstance(translator);


        Browser2WorkplaceMediator.browser = browserPanel;
        Browser2WorkplaceMediator.workPlace = workPlacePanel;
        Browser2WorkplaceMediator.connectTransferHandlerToWorkplace();

        splitPane = new javax.swing.JSplitPane();
        setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        splitPane.setLeftComponent(browserPanel);
        splitPane.setRightComponent(workPlacePanel);

        splitPane.setContinuousLayout(true);
        splitPane.setOneTouchExpandable(true);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(splitPane, GroupLayout.DEFAULT_SIZE, 952, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(splitPane, GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE));
    }
    //
    // Variables declaration - do not modify
    private Browser browserPanel;
    private JSplitPane splitPane;
    private WorkPlace workPlacePanel;
    // End of variables declaration

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                JFrame frame = new JFrame();
                frame.add(new MainPanel());

                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setMinimumSize(new Dimension(1000, 500));
//        frame.setResizable(false);
                frame.setLocationRelativeTo(null);
                frame.setTitle("PDFManipulator");

                String title = System.getProperty("window.title");
                if (title != null && title != "") {
                    frame.setTitle(title);
                }
            }
        });
    }
}
