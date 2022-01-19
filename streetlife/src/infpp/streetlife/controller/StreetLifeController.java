/**
 * 
 */
package infpp.streetlife.controller;

import infpp.streetlife.model.*;
import infpp.streetlife.view.*;

/**
 *
 */
public class StreetLifeController implements Controller {

	private int numberTicks;
	private Model model; //TODO Basti repariert, Basti tauscht aus
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
	
	private void initiate() {	
		//this.view = new AsciiView();
		//this.view = new TextView();
		this.view = new StreetLifeView();
		this.model = new StreetLifeModel(numLanes, sizeStreet);
		
		Car car1 = new Car(0,1,"Fiat", 1);
		Car car2 = new Car(0,2,"Audi", 2);
		Frog frog1 = new Frog(10, 4, "Frog", 4, 1);
		
		this.model.addObject(car1);
		this.model.addObject(car2);
		this.model.addObject(frog1);
		
		
		this.view.setModel(model);
		this.view.setController(this);
		// this.model.setView(view);
		this.view.build();
		
		//building it and displaying it for the first time
		//this.view.build(this.model.getWidth(), this.model.getLength(), this.model.getModelState());
		//this.view.display(this.model.getModelState());
	}
	
	/**
	 * starts the simulation and controls the display of the actions
	 */
	@Override
	public void start() {
		
		try {
			for(int n = 0; n < numberTicks; n ++) {
				this.model.move();
				//this.view.display(this.model.getModelState());
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

	@Override
	public void addCar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delCar() {
		// TODO Auto-generated method stub
		
	}
}
