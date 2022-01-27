/**
 * 
 */
package infpp.streetlife.model;

import java.util.ArrayList;
import java.io.*;
import java.util.HashSet;

/**
 * @author Basti, Cornelius
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
	@Override
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
	@Override
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
	@Override
	public void addObject(StreetObject obj) throws Exception{
		this.streetObjects.add(obj);
	}
	
	/**
	 * moves the whole Streetlife for 1 step according to the models rules
	 * all new positions of all objects get calculated, verified and then set
	 */
	@Override
	public void move() {
		
		for (StreetObject obj : this.streetObjects) {
	
			if (obj instanceof MovingStreetObject) {
				
				MovingStreetObject mobj = (MovingStreetObject) obj;
				mobj.calculateMove();
				this.manageBorders(mobj);
				this.manageCollisions(mobj);
				mobj.move();
				
			}
		}
		
		this.deleteDeletionPendingObjects();
	}
	
	/**
	 * Manages the x and y borders of the street for moving street objects
	 * Objects leaving the street on the right or left reappear at the other site
	 * Objects leaving in y direction get deleted from the model. If the object is a frog it will get added to the saved frogs count
	 * @param obj object which x and y positions should get verified according to the border laws
	 */
	private void manageBorders(MovingStreetObject obj) {
		
		if (obj.getIntendedX() > this.getLength()) {
			obj.setIntendedX(obj.getIntendedX() - this.getLength());
		}
		
		else if (obj.getIntendedX() < 0) {
			obj.setIntendedX(obj.getIntendedX() + this.getLength());
		}
		
		if ((obj.getIntendedY() > this.getWidth()) || (obj.getIntendedY() < 0)) {
			
			if (obj instanceof Frog) {
				this.setSavedFrogs(this.getSavedFrogs() + 1);
			}
			
			obj.setDeleted(true);
			
		}
		
	}
	
	/**
	 * Checks if the new intended position of an object leads to a collision
	 * If there is one, the collision is dealed with based on the hardness level of the involving objects:
	 * Objects with lower or the same hardness level will move till they reach the point exactly before the collision
	 * Objects with higher hardness level will move like there is no collision, the other object will get removed
	 * @param obj object to be checked for collisions
	 */
	private void manageCollisions(MovingStreetObject obj) {
		
		int xMovement = obj.getIntendedX() - obj.getX();
		int yMovement = obj.getIntendedY() - obj.getY();
		int xDirection = (int) Math.signum(xMovement);
		int yDirection = (int) Math.signum(yMovement);
		
		HashSet<StreetObject> collisions = this.findCollisions(obj, xMovement, yMovement);
		
		for (StreetObject cobj: collisions) {
			
			if (obj.getHardness() <= cobj.getHardness()) {
				
				if (Math.abs(obj.getX() - cobj.getX()) <= Math.abs(xMovement)) {
					
					obj.setIntendedX(cobj.getX() - xDirection);
					
				}
				
				else if (Math.abs(obj.getY() - cobj.getY()) <= Math.abs(yMovement)) {
					
					obj.setIntendedY(cobj.getY() - yDirection);
					
				}
				
			}
			
			if (obj.getHardness() > cobj.getHardness()) {
				
				cobj.setDeleted(true);
				
			}
			
		}
		
		
	}
	
	//TODO
	private HashSet<StreetObject> findCollisions(StreetObject obj, int xMovement, int yMovement) {
		
		HashSet<StreetObject> collisions = new HashSet<>();
		int xDirection = (int) Math.signum(xMovement);
		int yDirection = (int) Math.signum(yMovement);
		
		for (StreetObject cobj: this.streetObjects) {
			
			if ((obj != cobj) && !cobj.isDeleted()) {
				
				boolean xCollision = false;
				boolean yCollision = false;
				
				for (int i = 0; i <= Math.abs(xMovement); i++) {
					
					if ((obj.getX() + xDirection * i) == cobj.getX()) {
						xCollision = true;
						break;
					}
					
				}
				
				for (int i = 0; i <= Math.abs(yMovement); i++) {
					
					if ((obj.getY() + yDirection * i) == cobj.getY()) {
						yCollision = true;
						break;
					}
					
				}
				
				if (xCollision && yCollision) {
					collisions.add(cobj);
				}
				
			}
			
		}
		
		return collisions;
	}
	
	/**
	 * @return description returns the description of the street as a String with width, length and the description of the car
	 */
	@Override
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
	public void deleteObject(StreetObject obj) throws Exception {
		
		try {
			this.streetObjects.remove(obj);
		}
		catch (Exception e) {
			throw new IllegalArgumentException("Tried deleting non existant Object");
		}
		
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
			try {
				this.deleteObject(obj);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * @return savedFrogs number of Frogs saved by reaching a safe space
	 */
	@Override
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
