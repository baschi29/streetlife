/**
 * 
 */
package infpp.streetlife.model;

import java.util.ArrayList;

import infpp.streetlife.view.View;

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
		streetObjects = new ArrayList<StreetObject>();
		
	}

	
	/**
	 * @return width width of street
	 */
	public int getWidth() {
		return width;
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
		return length;
	}

	/**
	 * @param length new length of the street
	 */
	public void setLength(int length) {
		this.length = length;
	}
	
	//TODO: making StreeLifeModel more generic -> eg. to add frogs
	/**
	 * 
	 * @param car adds a car to the street
	 */
	public void addObject(StreetObject obj) {
		streetObjects.add(obj);
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


	public void setView(View view) {
		// TODO Auto-generated method stub
		
	}
}
