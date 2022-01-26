/**
 * 
 */
package infpp.streetlife.model;

import java.util.ArrayList;
import java.io.*;

/**
 * Class representing the world all other objects exist in
 */
public class StreetLifeModel implements Model, Serializable{
	
	/**
	 * Version number
	 */
	private static final long serialVersionUID = -1052906946115022938L;
	
	/**
	 * Width of the street
	 */
	private int width;
	
	/**
	 * length of the street
	 */
	private int length;
	
	/**
	 * number of frogs that are counted as saved because they reached a safe space
	 */
	private int savedFrogs;
	
	/**
	 * Array containing all objects that are currently present in the model
	 */
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
	 * @return width width of the street
	 */
	public int getWidth() {
		return this.width;
	}

	/**
	 * @param width new width of the street
	 */
	private void setWidth(int width) {
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
	private void setLength(int length) {
		this.length = length;
	}
	
	/**
	 * @param obj object to be added to the model
	 */
	public void addObject(StreetObject obj) {
		this.streetObjects.add(obj);
	}
	
	/**
	 * moves the whole Streetlife for 1 step according to the models rules
	 * Objects leaving the street on the right or left reappear at the other site
	 * Objects leaving in y direction get deleted from the model. If the object is a frog it will get added to the saved frogs count
	 */
	public void move() {
		
		for (StreetObject obj : this.streetObjects) {
	
			if (obj instanceof MovingStreetObject) {
				
				((MovingStreetObject) obj).move();
				
				if (obj.getX() > this.getLength()) {
					obj.setX(obj.getX() - this.getLength());
				}
				
				else if (obj.getX() < 0) {
					obj.setX(obj.getX() + this.getLength());
				}
				
				if ((obj.getY() > this.getWidth()) || (obj.getY() < 0)) {
					
					if (obj instanceof Frog) {
						this.setSavedFrogs(this.getSavedFrogs() + 1);
					}
					
					obj.setDeleted(true);
				}
			}
		}
		
		this.deleteDeletionPendingObjects();
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
	
	/**
	 * Returns the Model State in Form of a List of all objects
	 * @return ArrayList List of all objects present
	 */
	@Override
	public ArrayList<StreetObject> getModelState() {
		return this.streetObjects;
	}
	
	/**
	 * @param obj object to be deleted from the model
	 */
	@Override
	public void deleteObject(StreetObject obj) {
		this.streetObjects.remove(obj);
		
	}
	
	/**
	 * deletes all objects that are marked as deleted (deleted = true)
	 */
	private void deleteDeletionPendingObjects() {
		
		ArrayList<StreetObject> toDelete = new ArrayList<>();
		
		for (StreetObject obj: this.streetObjects) {
			
			if (obj.isDeleted()) {
				toDelete.add(obj);
			}
		}
		
		for (StreetObject obj: toDelete) {
			this.streetObjects.remove(obj);
		}
		System.out.println(this.streetObjects.toString());
	}

	/**
	 * @return savedFrogs number of Frogs saved by reaching a safe space
	 */
	public int getSavedFrogs() {
		return this.savedFrogs;
	}


	/**
	 * @param savedFrogs set new value for the number of saved frogs
	 */
	private void setSavedFrogs(int savedFrogs) {
		this.savedFrogs = savedFrogs;
	}

}
