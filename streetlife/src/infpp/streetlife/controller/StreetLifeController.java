/**
 * 
 */
package infpp.streetlife.controller;

import infpp.streetlife.model.Car;
import infpp.streetlife.model.StreetLifeModel;
import infpp.streetlife.view.AsciiView;
import infpp.streetlife.view.View;

/**
 *
 */
public class StreetLifeController implements Controller {

	private int numberTicks;
	private StreetLifeModel model;
	private View view;
	private int numLanes;
	private int sizeStreet;
	
	
	/**
	 * Creates a new Controller
	 * @param numLanes specifies the amount of lanes for the street
	 * @param sizeStreet specifies the length of the Street
	 * @param numTicks specifies the amount of ticks
	 */
	public StreetLifeController(int numLanes, int sizeStreet, int numTicks){

		
		this.numLanes = numLanes;
		this.sizeStreet = sizeStreet;
		this.numberTicks = numTicks;
		
	    try {
			this.initiate();
			
		} catch (Exception e) {
			// fetch any errors during building-phase
			System.out.println("Initiating of the StreetLife failed, see below for more details");
			e.printStackTrace();
		}
	}

	/**
	 * initiates the model with some cars, and adds the view to it
	 */
	@Override
	public void initiate() {	
		this.view = new AsciiView();
		this.model = new StreetLifeModel(numLanes, sizeStreet, view);
		
		Car car1 = new Car(0,1,"F", 1);
		Car car2 = new Car(0,2,"A", 2);
		
		model.addCar(car1);
		model.addCar(car2);
	}
	
	/**
	 * starts the simulation and controls the display of the actions
	 */
	@Override
	public void start() {
		
		try {
			for(int n = 0; n < numberTicks; n ++) {
				model.move();
				model.getView().display();
			}
		} catch (Exception e) {
			// fetches any errors during the running-phase
			System.out.println("Oops! Something unexpected occured and we are deeply sorry. Check the error message for details:");
			e.printStackTrace();
		}
	}

	/**
	 * stops the whole simulation and ends the program //TODO 
	 */
	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}
}
