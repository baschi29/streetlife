/**
 * 
 */
package infpp.streetlife.controller;

/**
 * @author Cornelius
 *
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
