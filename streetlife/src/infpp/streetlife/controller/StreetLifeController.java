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
		
	    this.initiate();
	}

	@Override
	public void initiate() {
		this.view = new AsciiView();
		this.model = new StreetLifeModel(numLanes, sizeStreet, view);
		
		Car car1 = new Car(0,1,"F", 1);
		Car car2 = new Car(0,2,"A", 2);
		
		model.addCar(car1);
		model.addCar(car2);
	}
	
	@Override
	public void start() {
		
		for(int n = 0; n < numberTicks; n ++) {
			model.move();
			model.getView().display();
		}
	}


	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}
}
