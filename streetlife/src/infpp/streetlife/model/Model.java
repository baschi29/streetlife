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
	 * @return ArrayList List of all objects
	 */
	abstract public ArrayList<StreetObject> getModelState();
	
	abstract public int getWidth();
	
	abstract public void setWidth(int width);
	
	abstract public int getLength();
	
	abstract public void setLength(int length);
	
	abstract public void move();
	
	abstract public void addObject(StreetObject obj);
	
	abstract public void deleteObject(StreetObject obj);
}
