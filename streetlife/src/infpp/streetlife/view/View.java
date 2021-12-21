
package infpp.streetlife.view;

import infpp.streetlife.model.Car;

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
	abstract public void build(int numberLanes, int length, ArrayList<Car> modelState);
	
	/**
	 * adds a car to the street
	 * @param obj the car
	 */
	abstract public void addObject(Car obj);
	
	/**
	 * updates an object on the screen
	 * @param obj the car to be updated
	 */
	abstract public void updateObject(Car obj);
	
	/**
	 * Demolishes the car on the street
	 */
	abstract public void erase(Car obj);

	
	/**
	 * Display the whole street
	 */
	abstract public void display(ArrayList<Car> modelState);
	
}	
