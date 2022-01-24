/**
 * 
 */
package infpp.streetlife.view;

import java.util.ArrayList;

import infpp.streetlife.controller.Controller;
import infpp.streetlife.model.Model;
import infpp.streetlife.model.StreetLifeModel;
import infpp.streetlife.model.StreetObject;

public class StreetLifeView implements View {

	private StreetGUI gui;
	private Controller controller;
	private Model model;
	
	
	
	public StreetLifeView(Model model) {
		this.gui = new StreetGUI(model);
		this.setModel(model);
	}
	
	
	@Override
	public void build() {
		gui.setVisible(true);
	}

	@Override
	public void setModel(Model model) {
		this.model = model;
		gui.setModel(model);
	}
	
	@Override
	public Model getModel() {
		return this.model;
	}


	@Override
	public void setController(Controller cntrl) {
		this.controller = cntrl;
		gui.setController(controller);
		
	}


	@Override
	public Controller getController() {
		return this.controller;
	}


	@Override
	public void setPossibleCars(ArrayList<String> ArrStr) {
		this.gui.setPossibleCars(ArrStr);
		
		
	}


	@Override
	public void removeCurrentObject(StreetObject car1) {
		this.gui.removeCurrentCar(car1);
		
	}


	@Override
	public void addCurrentObject(StreetObject car1) {
		this.gui.addCurrentCar(car1);
		
	}
}
