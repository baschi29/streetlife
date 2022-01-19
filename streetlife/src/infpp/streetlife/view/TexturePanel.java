package infpp.streetlife.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.TexturePaint;

import javax.swing.JPanel;

/**
 * Class TexturePanel to create a custom swing JPanel for displaying resizable pictures
 * @author Cornelius
 *
 */
public class TexturePanel extends JPanel {

    private TexturePaint paint;

    /**
     * sets the background texture as a new TexturePaint-Object
     * @param tp new texture
     */
    public void setTexture(TexturePaint tp) {
        this.paint = tp;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(paint);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
}