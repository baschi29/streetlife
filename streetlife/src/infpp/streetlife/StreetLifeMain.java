/**
 * 
 */
package infpp.streetlife;

import infpp.streetlife.controller.Controller;
import infpp.streetlife.controller.StreetLifeController;

/**
 * Class for starting the StreetLifeController
 */
public class StreetLifeMain {

	/**
	 * Creates an instance of StreetLifeController and starts it.
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		int NUMBER_OF_LANES = 5;
		int NUMBER_OF_TICKS = 5;
		int SIZE_OF_STREET = 20;
		
		Controller controller = new StreetLifeController(NUMBER_OF_LANES,SIZE_OF_STREET,NUMBER_OF_TICKS);
		controller.start();

	}

}
