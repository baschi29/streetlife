/**
 * 
 */
package infpp.streetlife.controller;

import java.util.ArrayList;
import infpp.streetlife.model.*;

/**
 * @author Cornelius
 * The Controller-interface defines the main method every controller should implement for the model to actually work
 */
public interface Controller {
	
	/**
	 * Starts the simulation
	 */
	abstract public void start();
	
	
	/**
	 * Stops the simulation
	 */
	abstract public void stop();
	
	abstract public void step();
	
	abstract public ArrayList<String> getListOfCars();
	
	abstract public void addMovingObject(String str) throws Exception;
	
	abstract public void deleteObject(StreetObject obj);
	
	abstract public void releaseTheFrogs(int number);
	
}
