package infpp.streetlife.view;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.TexturePaint;
import java.util.ArrayList;

import javax.swing.JPanel;

import infpp.streetlife.model.MovingStreetObject;
import infpp.streetlife.model.StreetObject;

import java.awt.event.*;

/**
 * A custom JPanel for display of moving images over a background. Controls the correct tiling of the background and movement of the objects "on top"
 * @author Cornelius, Bastian
 * @version 1.0
 * @since 2022-01-28
 */
class DrawingSpace extends JPanel
{

    private ArrayList<StreetObject> streetobjs;
	private TexturePaint paint;

	//Creates a new DrawingSpacePanel, needs an array of objects that should be displayed
    public DrawingSpace(ArrayList<StreetObject> streetobjs){      
       
        this.streetobjs = streetobjs;
    }

    /**
     * Sets the texture used for the background
     * @param tp background texture
     */
    public void setTexture(TexturePaint tp) {
        this.paint = tp;
    }
    
    /**
     * adds a new Car to the DrawingSpace and repaints
     * @param car
     */
    public void addCar(StreetObject car) {
    	this.streetobjs.add(car);
    	repaint();
    }
    
    /**
     * repaints the DrawingSpace, essentially refreshes everything once
     */
    public void refresh() {
    	repaint();
    }
    
    /**
     * paints everything on the Panel, automatically called during build process
     * @param g used graphics
     */
    @Override public void paintComponent(Graphics g){
        super.paintComponent(g);    
        
        final int LANE_SIZE = 10; //mp for lane size ("Space between the cars"), so that the cars dont overlap each other.
		final int OFFSET_Y = 100; //offset for the whole street, so that the cars are properly on the canvas
        
        //set Background
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(paint);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        
        //Draws a static background image (bg), deprecated
        //g.drawImage(bg, 0, 0, bgWidth, bgHeight, null);
        
        
        //draw lane stripes
        int laneNumber = getHeight() % 5;
        
        Graphics2D lanes = (Graphics2D) g;
        lanes.setPaint(Color.WHITE);
        
        //start pos of the stripes
    	int startx = 0;
    	int starty = (getHeight()-OFFSET_Y/2-5) ;
    	
    	//width of lanes, distance between the stripes
    	int lane_width = 5 * LANE_SIZE;
    	int stripe_gap = 40;
    	
    	//length and width of the stripes
    	int length = 30; //getWidth();
    	int width = LANE_SIZE/2;
        
    	//first stripe is always without breaks
    	lanes.fillRect(startx ,starty,getWidth(),width);
    	
    	//paint the dotted stripes on the canvas
    	for(int y = 1; y < laneNumber+2; y++) {
        	
    		for(int x = 0; x < getWidth(); x += stripe_gap) {
    			lanes.fillRect(startx + x ,starty - y*lane_width ,length,width);
    		}	
    	
    	}
        
        
        
        for (StreetObject obj : streetobjs) {
        	
        	//only paint images that are acutally moving objects, nothing else
        	if (obj instanceof MovingStreetObject) {
        		//calcs the size of the image, essentially finding the middle for accurate movement.
        		//then set the object on the new x/y values
        		
        		
        		
        		int currentY = (int) this.getSize().getHeight()-OFFSET_Y;
        		g2d.drawImage(obj.getImg(), obj.getX()+(obj.getImg().getWidth()/2), currentY - (obj.getY()*LANE_SIZE-(obj.getImg().getHeight()/2)), obj.getImg().getWidth(),obj.getImg().getHeight(), null);
        	}
        	
    	} 
       
     
    }
    
    
}

  
