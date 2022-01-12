/**
 * 
 */
package infpp.streetlife.view;

import java.util.ArrayList;

import infpp.streetlife.model.StreetObject;

public class StreetLifeView implements View {

	private StreetGUI gui;
	
	@Override
	public void build(int numberLanes, int length, ArrayList<StreetObject> modelState) { //ist ne initiale Größe überhaupt noch notwendig? Würde bei 0 anfangen
		
		gui = new StreetGUI(modelState);
		gui.setVisible(true);
		

	}

	@Override
	public void display(ArrayList<StreetObject> modelState) {
		// TODO Automatisch generierter Methodenstub

	}
}
