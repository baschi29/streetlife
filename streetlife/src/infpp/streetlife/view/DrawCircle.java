package infpp.streetlife.view;

import javax.swing.*;
import java.awt.*;

public class DrawCircle extends JPanel {

    public DrawCircle(){
       
        setSize(250, 250);
        setVisible(true);
        

    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawRoundRect(40, 50, 90, 90, 200, 200);
    }

    public static void main(String[] args) {

       new DrawCircle();

    }
}
