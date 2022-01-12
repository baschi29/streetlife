/**
 * 
 */
package infpp.streetlife.view;

import java.util.ArrayList;

import infpp.streetlife.model.StreetObject;

public class StreetLifeView implements View {

	StreetLifeGUI gui = new StreetLifeGUI();
	
	@Override
	public void build(int numberLanes, int length, ArrayList<StreetObject> modelState) {
		
		
		gui.setVisible(true);

	}

	@Override
	public void display(ArrayList<StreetObject> modelState) {
		// TODO Automatisch generierter Methodenstub

	}
}
