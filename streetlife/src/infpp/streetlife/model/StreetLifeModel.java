/**
 * 
 */
package infpp.streetlife.model;

import java.util.ArrayList;
import java.io.*;
import java.util.Random;
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
	 * catches the declared lane_widht from the interface
	 */
	private final int laneYExtension = LANE_WIDTH;
	
	private Lane firstLane;
	
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
	 * @param lanes number of lanes
	 * @param length length of the street
	 */
	public StreetLifeModel(int lanes, int length) throws Exception{
		
		this.setWidth((lanes * this.laneYExtension) - 1);
		this.setLength(length - 1);
		this.streetObjects = new ArrayList<StreetObject>();
		
		Lane bot = null;
		Lane top = null;
		
		for (int i = 1; i <= lanes; i++) {
			
			Lane current = new Lane(this, i * this.laneYExtension - (this.laneYExtension / 2 + 1), this.laneYExtension, bot, top);
			this.addObject(current);
			
			if (bot != null) {
				bot.setTopLane(current);
			}
			else {
				this.setFirstLane(current);
			}
			
			bot = current;
			
		}
		System.out.println(this.toString());
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
		
		int tryCounter = 0;
		Random random = new Random();
		
		while (tryCounter <= 10) {
			
			HashSet<StreetObject> collisions = this.findCollisions(obj);
			
			if (collisions.isEmpty()) {
				this.streetObjects.add(obj);
				return;
			}
			
			obj.setX(obj.getX() + random.nextInt((int)(this.getLength() / 40)));
			tryCounter += 1;
		}
		
		throw new Exception("Could not add object because of collision. Please try again later");
		
	}
	
	/**
	 * ticks the whole Streetlife for 1 step according to the models rules
	 * all new positions of all objects get calculated, verified and then set
	 */
	@Override
	public void tick() {
		
		for (StreetObject obj : this.streetObjects) {	
			obj.tick();
		}
		
		this.deleteDeletionPendingObjects();
	}
	
	/**
	 * Detects if there is an collision with an existing object on the current position
	 * and on the way the x and y movements describe
	 * Objects with a negative hardness level are only able to collide with objects the same hardness
	 * @param obj object to check for collisions
	 * @param xMovement xMovement of the object
	 * @param yMovement yMovement of the object
	 * @return collisions set of other object with collision
	 */
	@Override
	public HashSet<StreetObject> findCollisions(StreetObject obj, int xMovement, int yMovement) {
		
		HashSet<StreetObject> collisions = new HashSet<>();
		int xDirection = (int) Math.signum(xMovement);
		int yDirection = (int) Math.signum(yMovement);
		
		if (obj.getHardness() >= 0 && !obj.isDeleted()) {
			
			for (StreetObject cobj: this.streetObjects) {
				
				if ((obj != cobj) && !cobj.isDeleted() && (cobj.getHardness() >= 0)) {
					boolean xCollision = false;
					boolean yCollision = false;
					
					for (int i = 0; i <= Math.abs(xMovement); i++) {
						if (this.moduloCircleX(obj.getX() + xDirection * i) == cobj.getX()) {
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
			
		}
		
		return collisions;
	}
	
	/**
	 * Detects if there is an collision with an existing object on the current position
	 * x and y movements are therefore set to 0
	 * @param obj object to check for collisions
	 * @return collisions set of other objects with collision
	 */
	@Override
	public HashSet<StreetObject> findCollisions(StreetObject obj) {
		return this.findCollisions(obj, 0, 0);
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
	
	/**
	 * Will always return a positive value to enable circling through the canvas
	 * @param old old x or y position to perform modulo on
	 * @return new new x or y position after performing modulo
	 */
	@Override
	public int moduloCircleX(int old) {
		return ((old % this.getLength() + this.getLength()) % this.getLength());
	}

	/**
	 * resets the number of saved frogs to 0
	 */
	@Override
	public void resetSavedFrogs() {
		this.setSavedFrogs(0);
		
	}

	/**
	 * increments the number of saved frogs by 1
	 */
	@Override
	public void incrementSavedFrogs() {
		this.setSavedFrogs(this.getSavedFrogs() + 1);
	}


	/**
	 * @return firstLane
	 */
	@Override
	public Lane getFirstLane() {
		return this.firstLane;
	}


	/**
	 * @param firstLane das zu setzende Objekt firstLane
	 */
	private void setFirstLane(Lane firstLane) {
		this.firstLane = firstLane;
	}
	
}
