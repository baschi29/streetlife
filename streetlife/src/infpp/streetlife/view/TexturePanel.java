package infpp.streetlife.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.TexturePaint;

import javax.swing.JPanel;

/**
 * Class TexturePanel to create a custom swing JPanel for displaying resizable pictures.
 * Currently unused and replaced by the DrawingSpace
 * @deprecated
 * @author Cornelius
 * @version 1.1
 * @since 2022-01-28
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