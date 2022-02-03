/**
 * 
 */
package infpp.streetlife.model;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * @author Cornelius, Bastian
 *
 */
public interface Model {
	
	/**
	 * declares the width of the lanes used, should not be edited if you don't know what you do
	 */
	public final int LANE_WIDTH = 5;
	
	/**
	 * Returns the Model State in Form of a List of all objects
	 * @return ArrayList List of all objects present
	 */
	abstract public ArrayList<StreetObject> getModelState();
	
	/**
	 * @return width width of the street
	 */
	abstract public int getWidth();
	
	/**
	 * @return length length of street
	 */
	abstract public int getLength();
	
	/**
	 * ticks the whole Streetlife for 1 step according to the models rules, includes movement
	 */
	abstract public void tick();
	
	/**
	 * @param obj object to be added to the model
	 */
	abstract public void addObject(StreetObject obj) throws Exception;
	
	/**
	 * @param obj object to be deleted from the model
	 */
	abstract public void deleteObject(StreetObject obj) throws Exception;
	
	/**
	 * resets the number of saved frogs to 0
	 */
	public void resetSavedFrogs();
	
	/**
	 * increments the number of saved frogs by 1
	 */
	public void incrementSavedFrogs();
	
	/**
	 * @return savedFrogs number of Frogs saved by reaching a safe space
	 */
	abstract public int getSavedFrogs();
	
	/**
	 * @return description returns the description of the street as a String with width, length and the description of the car
	 */
	abstract public String toString();
	
	/**
	 * Detects if there is an collision with an existing object on the current position
	 * and on the way the x and y movements describe
	 * Objects with a negative hardness level are unable to collide
	 * @param obj object to check for collisions
	 * @param xMovement xMovement of the object
	 * @param yMovement yMovement of the object
	 * @return ArrayList array List containing Hash Set for x collision at position 1 and Hash Set for y collision
	 */
	public ArrayList<HashSet<StreetObject>> findCollisions(StreetObject obj, HashSet<Integer> xMovementSet, HashSet<Integer> yMovementSet);
	
	/**
	 * Detects if there is an collision with an existing object on the current position
	 * x and y movements are therefore set to 0
	 * @param obj object to check for collisions
	 * @return ArrayList array List containing Hash Set for x collision at position 1 and Hash Set for y collision
	 */
	public ArrayList<HashSet<StreetObject>> findCollisions(StreetObject obj);
	
	/**
	 * Will always return a positive value to enable circling through the canvas
	 * @param old old x or y position to perform modulo on
	 * @return new new x or y position after performing modulo
	 */
	public int moduloCircleX(int old);
	
	public Lane getFirstLane();
	
}
