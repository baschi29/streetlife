/**
 * 
 */
package infpp.streetlife.model;

import java.util.ArrayList;
import java.io.*;
import java.util.Random;
import java.util.HashSet;

/**
 * Class representing the world all other objects exist in
 * @author Cornelius, Bastian
 */
public class StreetLifeModel implements Model, Serializable{
	
	/**
	 * Version number
	 */
	private static final long serialVersionUID = -1052906946115022938L;
	
	/**
	 * catches the declared lane_widht from the interface
	 */
	private final int laneYDimension = LANE_WIDTH;
	
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
	 * number of lanes used in model
	 */
	private int laneNumber;
	
	/**
	 * Constructor of the class
	 * @param lanes number of lanes
	 * @param length length of the street
	 */
	public StreetLifeModel(int lanes, int length) throws Exception{
		
		this.laneNumber = lanes;
		this.setWidth((lanes * this.laneYDimension) - 1);
		this.setLength(length - 1);
		this.streetObjects = new ArrayList<StreetObject>();
		
		Lane bot = null;
		Lane top = null;
		
		for (int i = 1; i <= lanes; i++) {
			
			Lane current = new Lane(this, i * this.laneYDimension - (this.laneYDimension / 2 + 1), this.laneYDimension, bot, top);
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
			
			ArrayList<HashSet<StreetObject>> collisions = this.findCollisions(obj);
			
			if (collisions.get(0).isEmpty() && collisions.get(1).isEmpty()) {
				this.streetObjects.add(obj);
				return;
			}
			
			obj.setCenterX(this.moduloCircleX(obj.getCenterX() + random.nextInt((int)(this.getLength()))));
			tryCounter += 1;
		}
		
		throw new Exception("Could not add object because of collision. Please try again later");
		
	}
	
	/**
	 * ticks the whole Streetlife for 1 step according to the models rules
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
	 * @param xMovementSet all x coordinates the object is on while moving to desired x
	 * @param yMovementSet all y coordinates the object is on while moving to desired y
	 * @return ArrayList array List containing Hash Set for x collision at position 1 and Hash Set for y collision at position 0
	 */
	@Override
	public ArrayList<HashSet<StreetObject>> findCollisions(StreetObject obj, HashSet<Integer> xMovementSet, HashSet<Integer> yMovementSet) {
		
		ArrayList<HashSet<StreetObject>> collisions = new ArrayList<>();
		HashSet<StreetObject> xCollisions = new HashSet<>();
		HashSet<StreetObject> yCollisions = new HashSet<>();
		collisions.add(xCollisions);
		collisions.add(yCollisions);
		
		if (obj.getHardness() >= 0 && !obj.isDeleted()) {
			
			for (StreetObject cobj: this.streetObjects) {
				
				if ((obj != cobj) && !cobj.isDeleted() && (cobj.getHardness() >= 0)) {
					
					boolean XCollision = cobj.isInsideXDimension(xMovementSet);
					boolean onSameX = cobj.isInsideXDimension(obj.getX());
					boolean YCollision = cobj.isInsideYDimension(yMovementSet);
					boolean onSameY = cobj.isInsideYDimension(obj.getY());
					
					if (XCollision) {
						if (onSameY) {
							xCollisions.add(cobj);
						}
					}
					
					if (YCollision) {
						if (onSameX) {
							yCollisions.add(cobj);
						}
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
	 * @return ArrayList array List containing Hash Set for x collision at position 1 and Hash Set for y collision
	 */
	@Override
	public ArrayList<HashSet<StreetObject>> findCollisions(StreetObject obj) {
		return this.findCollisions(obj, obj.getX(), obj.getY());
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
	 * @return returns the lane at the very bottom of the model aka. the first lane
	 */
	@Override
	public Lane getFirstLane() {
		return this.firstLane;
	}

	/**
	 * @param firstLane the lane at the very bottom of the model aka. the first lane
	 */
	private void setFirstLane(Lane firstLane) {
		this.firstLane = firstLane;
	}


	@Override
	public int getLaneNumber() {
		return laneNumber;
	}
	
}
