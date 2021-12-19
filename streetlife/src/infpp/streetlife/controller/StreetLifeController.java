/**
 * 
 */
package infpp.streetlife.controller;

import infpp.streetlife.model.StreetLifeModel;
import infpp.streetlife.view.View;

/**
 *
 */
public class StreetLifeController implements Controller {

	private int numberTicks;
	private StreetLifeModel model;
	private View view;
	
	
	/**
	 * Creates a new Controller
	 * @param numTicks specifies the amount of ticks
	 */
	public StreetLifeController(int numTicks, StreetLifeModel model){
		this.numberTicks = numTicks;
		this.model = model;
		
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
