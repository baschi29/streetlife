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

import infpp.streetlife.model.StreetObject;

import java.awt.event.*;

class DrawingSpace extends JPanel
{
    private BufferedImage bg, car;
    //private ArrayList<BufferedImage> addedCars;
    private ArrayList<StreetObject> streetobjs;
    private int bgWidth, bgHeight;
    private int carWidth, carHeight;  
    private int scWidth, scHeight;
    private int posX, posY;
	private TexturePaint paint;

    public DrawingSpace(ArrayList<StreetObject> streetobjs){      
        loadImages();
        init();
        this.streetobjs = streetobjs;
        setPreferredSize(new Dimension(scWidth, scHeight));
    }

    private void init(){
//    	
//    	
//    	
//    	
//    	
//        posX = 0;
//        posY = 0;
//        carWidth = car.getWidth();
//        carHeight = car.getHeight();      
//        bgWidth = bg.getWidth();
//        bgHeight = bg.getHeight();      
//        scWidth = bgWidth;
//        scHeight = bgHeight;        
    }

    private void loadImages(){
        try{ 
           car = ImageIO.read(getClass().getResource("/car.png"));
        }catch(IOException ioe){
        	System.out.println("Unable to open file");
        	}
    }

    

    public void setTexture(TexturePaint tp) {
        this.paint = tp;
    }
    
    
    public int getPosX() {
    	return posX;
    }
    public int getPosY() {
    	return posX;
    }
    
    public void setPosX(int newPos) {
    	this.posX = newPos;
    }
    public void setPosY(int newPos) {
    	this.posY = newPos;
    }
    
    
    @Override public void paintComponent(Graphics g){
        super.paintComponent(g);        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(paint);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        //g.drawImage(bg, 0, 0, bgWidth, bgHeight, null);
        
        for (StreetObject obj : streetobjs) {
        	g2d.drawImage(obj.getImg(), obj.getX()+(obj.getImg().getWidth()/2), obj.getY()*50-(obj.getImg().getHeight()/2), obj.getImg().getWidth(),obj.getImg().getHeight(), null);
    	} 
       
        
        
        
        
        
    }
    
    
}

  
