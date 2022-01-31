/**
 * 
 */
package infpp.streetlife.model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import javax.imageio.ImageIO;

import infpp.streetlife.FileLoader;

/**
 * abstract class for objects on the street. Acts as superclass for every moving object such as cars or animals
 */

public abstract class StreetObject implements Serializable {
	
	/**
	 * Version number
	 */
	private static final long serialVersionUID = 366898580346801333L;
	
	/**
	 * Holds the model the object exists in
	 */
	private Model model;
	
	/**
	 * Default Resource used for the car
	 */
	private final String CAR_PATH = "img/car.png";
	
	/**
	 * x position of the object
	 */
	private int x;
	
	/**
	 * y position of the object
	 */
	private int y;
	
	/**
	 * amount of pixel the object extents symmetrically in y
	 * defaults to 0
	 */
	private int yExtension = 0;
	
	/**
	 * name of the object
	 */
	private String name;
	
	/**
	 * hardness of an objects represents how an object interacts with other objects on collision
	 * objects with higher hardness "remove" objects with lower hardness, objects with the same or higher hardness are avoided
	 */
	private int hardness;
	
	/**
	 * marks the object as deleted, deletion happens, when method deleteDeletionPendingObjects() is called in StreetLifeModel
	 */
	private boolean deleted;
	
	/**
	 * image that displays the object on the screen
	 */

	protected BufferedImage img;
	
	/**
	 * Constructor of the class
	 * @param model the model the object exists in
	 * @param x x position of the object
	 * @param y y position of the object
	 * @param name name of the object
	 * @param hardness hardness of the object
	 */
	public StreetObject(Model model, int x, int y, String name, int hardness) {
		
		this.setModel(model);
		this.setX(x);
		this.setY(y);
		this.setName(name);
		this.setHardness(hardness);
		
		 try { 
			 FileLoader fl = new FileLoader();
	           img = fl.loadImage(CAR_PATH);
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

	/**
	 * @return deleted
	 */
	public boolean isDeleted() {
		return this.deleted;
	}

	/**
	 * @param deleted sets the object as deleted (or reverts deletion before object gets removed completely)
	 */
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	/**
	 * @return hardness hardness of the object
	 */
	public int getHardness() {
		return hardness;
	}

	/**
	 * @param hardness das zu setzende Objekt hardness
	 */
	private void setHardness(int hardness) {
		this.hardness = hardness;
	}
	
	/**
	 * ticks the object one time
	 */
	public abstract void tick();

	/**
	 * @return model model the objects exists in
	 */
	public Model getModel() {
		return model;
	}

	/**
	 * @param model sets a new model for the object
	 */
	public void setModel(Model model) {
		this.model = model;
	}

	/**
	 * @return yExtension amount of pixel the object extents symmetrically in y direction
	 */
	public int getYExtension() {
		return this.yExtension;
	}

	/**
	 * @param yExtension new yExtension value
	 */
	public void setYExtension(int yExtension) {
		this.yExtension = yExtension;
	}
	
}
