/**
 * 
 */
package infpp.streetlife.model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import javax.imageio.ImageIO;
import java.util.HashSet;

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
	 * x position of the center of the object
	 */
	private int x;
	
	/**
	 * all x coordinates the object occupies
	 */
	private HashSet<Integer> xSet;
	
	/**
	 * y position of the center of the object
	 */
	private int y;
	
	/**
	 * all y coordinates the object occupies
	 */
	private HashSet<Integer> ySet;
	
	/**
	 * amount of pixel the object extents symmetrically in y direction
	 * defaults to 0
	 */
	private int yDimension = 0;
	
	/**
	 * amount of pixel the object extents symmetrically in x direction
	 * defaults to 0
	 */
	private int xDimension = 0;
	
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
	 * @return x x center position of the object
	 */
	public int getCenterX() {
		return this.x;
	}
	
	/**
	 * @return xSet all x coordinates of the object
	 */
	public HashSet<Integer> getX() {
		return this.xSet;
	}

	/**
	 * @param x new x center position of the object
	 */
	public void setX(int x) {
		this.x = x;
		
		int halfxDimension = this.getXDimension() / 2;
		this.xSet = new HashSet<>();
		
		for (int i = -halfxDimension; i <= halfxDimension; i++) {
			this.xSet.add(this.getCenterX() + i);
		}
	}

	/**
	 * @return y y center position of the object
	 */
	public int getCenterY() {
		return y;
	}

	/**
	 * @return ySet all y coordinates of the object
	 */
	public HashSet<Integer> getY() {
		return this.ySet;
	}
	
	/**
	 * @param y new y center position of the object
	 */
	public void setY(int y) {
		this.y = y;
		
		int halfYDimension = this.getYDimension() / 2;
		this.ySet = new HashSet<>();
		
		for (int i = -halfYDimension; i <= halfYDimension; i++) {
			this.ySet.add(this.getCenterY() + i);
		}
	}
	
	/**
	 * @return img image associated with the object, may be used by view
	 */
	public BufferedImage getImg() {
		return img;
	}
	
	/**
	 * @param newImg image associated with the object, may be used by view
	 */
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
	 * @return deleted true if the object has the deleted Status
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
	 * @param hardness hardness of the object
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
	 * @return yDimension amount of pixel the object extents symmetrically in y direction
	 */
	public int getYDimension() {
		return this.yDimension;
	}

	/**
	 * @param yDimension new yDimension value
	 */
	public void setYDimension(int yDimension) {
		this.yDimension = yDimension;
		this.setY(this.getCenterY());
	}

	/**
	 * @return xDimension amount of pixel the object extents symmetrically in x direction
	 */
	public int getXDimension() {
		return xDimension;
	}

	/**
	 * @param xDimension new xDimension value
	 */
	public void setXDimension(int xDimension) {
		this.xDimension = xDimension;
		this.setX(this.getCenterX());
	}
	
	/**
	 * Checks if the x coordinates given with the set overlap with the x coordinates of the object
	 * @param set x coordinates used for checking for overlap
	 * @return insideXDimension true if some x coordinates are the same
	 */
	public boolean isInsideXDimension(HashSet<Integer> set) {
		return !calculateIntersection(this.getX(), set).isEmpty();
	}
	
	/**
	 * Checks if the y coordinates given with the set overlap with the y coordinates of the object
	 * @param set y coordinates used for checking for overlap
	 * @return insideYDimension true if some y coordinates are the same
	 */
	public boolean isInsideYDimension(HashSet<Integer> set) {
		return !calculateIntersection(this.getY(), set).isEmpty();
	}
	
	/**
	 * Help method to realise non mutating intersection on HashSets
	 * @param s1 first HashSet
	 * @param s2 second HashSet
	 * @return s3 intersection of s1 and s2
	 */
	private HashSet<Integer> calculateIntersection(HashSet<Integer> s1, HashSet<Integer> s2) {
		HashSet<Integer> intersection = new HashSet<>(s2);
		intersection.retainAll(s1);
		return intersection;
	}
}
