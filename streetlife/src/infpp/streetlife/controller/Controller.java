/**
 * 
 */
package infpp.streetlife.controller;

import java.util.*;
import infpp.streetlife.model.*;

/**
 * @author Cornelius, Basti
 * The Controller-interface defines the main method every controller should implement for the model to actually work
 */
public interface Controller {
	
	/**
	 * Starts the simulation
	 */
	abstract public void start() throws Exception;
	
	
	/**
	 * Stops the simulation
	 */
	abstract public void stop() throws Exception;
	
	/**
	 * Moves the simulation one step further
	 */
	abstract public void step() throws Exception;
	
	/**
	 * Returns the list of all predefined car templates
	 * @return ArrayList list of predefined car templates
	 */
	abstract public ArrayList<String> getListOfCars();
	
	/**
	 * @param str Name of predefined object to be added
	 */
	abstract public void addMovingObject(String str) throws Exception;
	
	/**
	 * @param obj object to be deleted from the model
	 */
	abstract public void deleteObject(StreetObject obj) throws Exception;
	
	/**
	 * Releases a number of frogs randomly at the bottom of the street
	 * @param number number of frogs to be added
	 */
	abstract public void releaseTheFrogs(int number) throws Exception;
	
	/**
	 * Saves the model to a given file
	 * @param path path to file
	 */
	abstract public void saveToFile(String path) throws Exception;
	
	/**
	 * Loads a model from a given file
	 * @param path path to file
	 */
	abstract public void loadFromFile(String path) throws Exception;
	
}
