package com.kirilov.pdfmanipulator.mainframe;

import java.awt.Graphics;
import javax.swing.JApplet;

@Deprecated
public class MainApplet extends JApplet {

    @Override
    public void init() {
        super.init();
        setSize(1000, 500);
        add(new MainPanel());
//        try{
//            new File("bla.txt").createNewFile();
//        }catch(IOException e){
//            e.printStackTrace();
//        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.drawString("test string", 100, 100);
    }
}
