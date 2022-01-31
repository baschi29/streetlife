/**
 * 
 */
package infpp.streetlife;

import javax.swing.JDialog;

import infpp.streetlife.controller.Controller;
import infpp.streetlife.controller.StreetLifeController;
import infpp.streetlife.view.StartUpDialog;

import java.awt.EventQueue;

/**
 * Class for starting the StreetLifeController
 * @author Cornelius
 */
public class StreetLifeMain {

	/**
	 * Creates an instance of the StartUp-Dialog and sets its visibility to true.
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
		public void run() {

		
		try {
		StartUpDialog dia = new StartUpDialog();
		dia.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dia.setVisible(true);
		}
		catch (Exception e) {
			System.out.println("Error during startup!");
			e.printStackTrace();
		}
		}
	});
}
	
	/**
	 * starts the StreetLife-Simulation with default parameters. Useful for debug.
	 */
	public static void startStreetLife() {
		Controller controller = new StreetLifeController(5,1000,1,true);
		try {
			controller.start();
		}
		catch (Exception e) {
			System.out.println("Error starting the controller");
			e.printStackTrace();
		}
	}
	
	/**
	 * actually starts the StreetLife-Simulation with parameters
	 * @param number_of_lanes	the amount of lanes the street should use
	 * @param size_of_street	the length of the street, equal to the size of the window
	 * @param sim_speed			the speed the simulation should run
	 * @param default_cars		true if default cars should be added
	 */
	
	public static void startStreetLife(int number_of_lanes,int size_of_street,int sim_speed, boolean default_cars) {
		
		Controller controller = new StreetLifeController(number_of_lanes,size_of_street,sim_speed,default_cars);
		try {
			controller.start();
		}
		catch (Exception e) {
			System.out.println("Error starting the controller");
			e.printStackTrace();
		}
	}

}
