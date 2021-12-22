/**
 * 
 */
package infpp.streetlife.model;

import java.util.ArrayList ;

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
}
