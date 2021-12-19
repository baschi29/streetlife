/**
 * 
 */
package infpp.streetlife.view;

import infpp.streetlife.model.Car;

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
	abstract public void build(int numberLanes, int length);
	
	/**
	 * adds a car to the street
	 * @param obj the car
	 */
	abstract public void addObject(Car obj);
	
	/**
	 * updates an object on the screen
	 * @param obj
	 */
	abstract public void updateObject(Car obj);
	
	
	/**
	 * Display the whole street
	 */
	abstract public void display();
	
	/**
	 * Demolishes the car on the street
	 */
	abstract public void erase(Car obj);
}
