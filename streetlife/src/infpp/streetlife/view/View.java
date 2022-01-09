
package infpp.streetlife.view;

import infpp.streetlife.model.StreetObject;

import java.util.ArrayList;

/**
 * @author Cornelius
 *
 */
public interface View {

	/**
	 * Builds an empty street
	 * @param numberLanes the number on lanes on street
	 * @param length the length in pixel
	 */
	abstract public void build(int numberLanes, int length, ArrayList<StreetObject> modelState);
	
	/**
	 * Display the whole street
	 */
	abstract public void display(ArrayList<StreetObject> modelState);
	
}	
