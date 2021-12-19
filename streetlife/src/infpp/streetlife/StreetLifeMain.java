/**
 * 
 */
package infpp.streetlife;

import infpp.streetlife.controller.Controller;
import infpp.streetlife.controller.StreetLifeController;
import infpp.streetlife.model.Car;
import infpp.streetlife.model.StreetLifeModel;
import infpp.streetlife.view.AsciiView;
import infpp.streetlife.view.View;

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
		
		Car car1 = new Car(0,1,"Fiat", 1);
		Car car2 = new Car(0,2,"Audi", 2);
		
		
		View view = new AsciiView();
		StreetLifeModel model = new StreetLifeModel(NUMBER_OF_LANES, 10, view);
		model.addCar(car1);
		model.addCar(car2);
		Controller controller = new StreetLifeController(NUMBER_OF_LANES,model);
		controller.start();

	}

}
