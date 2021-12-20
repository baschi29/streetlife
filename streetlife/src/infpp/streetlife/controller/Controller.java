/**
 * 
 */
package infpp.streetlife.controller;

/**
 * @author Cornelius
 * The Controller-interface defines the main method every controller should implement for the model to actually work
 */
public interface Controller {

	/**
	 * Initiates the simulation without actually starting it
	 */
	abstract public void initiate();
	
	/**
	 * Starts the simulation
	 */
	abstract public void start();
	
	
	/**
	 * Stops the simulation
	 */
	abstract public void stop();
	
}
