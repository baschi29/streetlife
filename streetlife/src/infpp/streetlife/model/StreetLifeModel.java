/**
 * 
 */
package infpp.streetlife.model;

import java.util.ArrayList;

/**
 * Class representing the world all other objects exist in
 */
public class StreetLifeModel implements Model{
	
	private int width;
	private int length;
	private ArrayList<StreetObject> streetObjects;
	
	/**
	 * Constructor of the class
	 * @param width width of the street = number of lanes
	 * @param length length of the street
	 * @view the gui the model should be displayed in
	 */
	public StreetLifeModel(int width, int length) {
		this.setWidth(width);
		this.setLength(length);
		this.streetObjects = new ArrayList<StreetObject>();
		
	}

	
	/**
	 * @return width width of street
	 */
	public int getWidth() {
		return this.width;
	}

	/**
	 * @param width new width of the street
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return length length of street
	 */
	public int getLength() {
		return this.length;
	}

	/**
	 * @param length new length of the street
	 */
	public void setLength(int length) {
		this.length = length;
	}
	
	/**
	 * 
	 * @param car adds a car to the street
	 */
	public void addObject(StreetObject obj) {
		this.streetObjects.add(obj);
	}
	
	/**
	 * moves the whole streetlife for 1 step
	 */
	public void move() {
		
		for (StreetObject obj : streetObjects) {
	
			if (obj instanceof MovingStreetObject) {
				((MovingStreetObject) obj).move();
			}
		}
	}
	
	/**
	 * @return description returns the description of the street as a String with width, length and the description of the car
	 */
	public String toString() {
		String objectString = "";
		
		for (StreetObject obj : streetObjects) {
			objectString += obj.toString() + "\n";
		}
		return "Width: "
				+ Integer.toString(this.width)
				+ "; Length: "
				+ Integer.toString(this.length)
				+ "\n"
				+ objectString;
	}

	@Override
	public ArrayList<StreetObject> getModelState() {
		return this.streetObjects;
	}

	@Override
	public void deleteObject(StreetObject obj) {
		this.streetObjects.remove(obj);
		
	}
}
