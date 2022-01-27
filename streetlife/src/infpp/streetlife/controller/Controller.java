/**
 * 
 */
package infpp.streetlife.controller;

import java.util.*;
import infpp.streetlife.model.*;

/**
 * @author Cornelius
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
	
	abstract public void step() throws Exception;
	
	abstract public ArrayList<String> getListOfCars();
	
	abstract public void addMovingObject(String str) throws Exception;
	
	abstract public void deleteObject(StreetObject obj) throws Exception;
	
	abstract public void releaseTheFrogs(int number) throws Exception;
	
	abstract public void saveToFile(String path) throws Exception;
	
	abstract public void loadFromFile(String path) throws Exception;
	
}
