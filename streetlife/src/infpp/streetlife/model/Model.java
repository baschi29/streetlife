/**
 * 
 */
package infpp.streetlife.model;

import java.util.ArrayList;

/**
 * @author basti
 *
 */
public interface Model {

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
	 * Moves the whole Streetlife for 1 step according to the models rules
	 */
	abstract public void move();
	
	/**
	 * @param obj object to be added to the model
	 */
	abstract public void addObject(StreetObject obj) throws Exception;
	
	/**
	 * @param obj object to be deleted from the model
	 */
	abstract public void deleteObject(StreetObject obj) throws Exception;
	
	/**
	 * @return savedFrogs number of Frogs saved by reaching a safe space
	 */
	abstract public int getSavedFrogs();
	
	/**
	 * @return description returns the description of the street as a String with width, length and the description of the car
	 */
	abstract public String toString();
}
