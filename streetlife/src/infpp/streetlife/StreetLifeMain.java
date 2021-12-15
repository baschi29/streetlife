/**
 * 
 */
package infpp.streetlife;

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
		
		StreetLifeController controller = new StreetLifeController();
		controller.start();

	}

}
