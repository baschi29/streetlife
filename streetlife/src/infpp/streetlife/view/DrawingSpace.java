package infpp.streetlife.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import infpp.streetlife.model.Car;
import infpp.streetlife.model.MovingStreetObject;
import infpp.streetlife.model.StreetObject;

import java.awt.event.*;

/**
 * A custom JPanel
 * @author Cornelius
 *
 */
class DrawingSpace extends JPanel
{

    private ArrayList<StreetObject> streetobjs;
  
	private TexturePaint paint;

    public DrawingSpace(ArrayList<StreetObject> streetobjs){      
       
        this.streetobjs = streetobjs;
        //setPreferredSize(this.getParent().getSize());
    }


    public void setTexture(TexturePaint tp) {
        this.paint = tp;
    }
    
    
    
    @Override public void paintComponent(Graphics g){
        super.paintComponent(g);        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(paint);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        //g.drawImage(bg, 0, 0, bgWidth, bgHeight, null);
        
        for (StreetObject obj : streetobjs) {
        	if (obj instanceof MovingStreetObject) {
        		if (obj instanceof Car)
        		g2d.drawImage(obj.getImg(), obj.getX()+(obj.getImg().getWidth()/2), obj.getY()*50-(obj.getImg().getHeight()/2), obj.getImg().getWidth(),obj.getImg().getHeight(), null);
        	}
    	} 
       
        
        
        
        
        
    }
    
    
}

  
