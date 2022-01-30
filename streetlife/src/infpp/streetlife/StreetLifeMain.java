/**
 * 
 */
package infpp.streetlife;

import infpp.streetlife.controller.Controller;
import infpp.streetlife.controller.StreetLifeController;

/**
 * Class for starting the StreetLifeController
 * @author Cornelius
 */
public class StreetLifeMain {

	/**
	 * Creates an instance of StreetLifeController and starts it.
	 * @param args command line arguments, [number_of_lanes,size_of_street,number_of_ticks]
	 */
	public static void main(String[] args) {
		int NUMBER_OF_LANES ; //number of lanes the street should have
		int SIZE_OF_STREET;   // size or length of displayed street
		int NUMBER_OF_TICKS;  // number of ticks the simulation should run for, equals the sim time
		
		
		if (args.length == 0) { //if no args are given, use some default values
			NUMBER_OF_LANES = 8;
			SIZE_OF_STREET = 1200;
			NUMBER_OF_TICKS = 10;
			
		}
		else {
			NUMBER_OF_LANES = Integer.parseInt(args[0]);
			SIZE_OF_STREET = Integer.parseInt(args[1]);
			NUMBER_OF_TICKS = Integer.parseInt(args[2]);
			
		}
			
			Controller controller = new StreetLifeController(NUMBER_OF_LANES,SIZE_OF_STREET,NUMBER_OF_TICKS);
			try {
				controller.start();
			}
			catch (Exception e) {
				System.out.println("Error starting the controller");
				e.printStackTrace();
			}

	}

}
