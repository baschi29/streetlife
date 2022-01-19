/**
 * 
 */
package infpp.streetlife.view;

import infpp.streetlife.controller.Controller;
import infpp.streetlife.model.Model;
import infpp.streetlife.model.StreetLifeModel;

public class StreetLifeView implements View {

	private StreetGUI gui;
	private Controller controller;
	private Model model;
	
	
	
	public StreetLifeView() {
		this.gui = new StreetGUI();
	}
	
	
	@Override
	public void build() {
		gui.setVisible(true);
	}

	@Override
	public void setModel(StreetLifeModel model) {
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
		// TODO Auto-generated method stub
		return this.controller;
	}
}
