/**
 * 
 */
package infpp.streetlife.model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

/**
 * abstract class for objects on the street. Acts as superclass for every moving object such as cars or animals
 */
public abstract class StreetObject implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 366898580346801333L;

	/**
	 * x position of the object
	 */
	private int x;
	
	/**
	 * y position of the object
	 */
	private int y;
	
	/**
	 * name of the object
	 */
	private String name;
	
	
	/**
	 * image that displays the object on the screen
	 */

	protected BufferedImage img;
	
	/**
	 * Constructor of the class
	 * @param x x position of the object
	 * @param y y position of the object
	 * @param name name of the object
	 */
	public StreetObject(int x, int y, String name) {
		
		this.x = x;
		this.y = y;
		this.name = name;
		
		 try { 
	           img = ImageIO.read(getClass().getResource("/car.png"));
	        } catch(IOException ioe){
	        	System.out.println("Unable to open file");
	        	}
	}

	/**
	 * @return name name of the object
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name new name of the object
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return x x position of the object
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x new x position of the object
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return y y position of the object
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y new y position of the object
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	public BufferedImage getImg() {
		return img;
	}
	
	public void setImg(BufferedImage newImg) {
		this.img = newImg;
	}
	
	
	/**
	 * @return description returns the description of the object as a String
	 * with name, x and y position
	 */
	@Override
	public String toString() {
		return this.name
				+ " at: x = "
				+ Integer.toString(this.x)
				+ " ; y = "
				+ Integer.toString(this.y);
	}
}
