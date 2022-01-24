
package infpp.streetlife.view;

import java.util.ArrayList;

import infpp.streetlife.controller.Controller;
import infpp.streetlife.model.Model;
import infpp.streetlife.model.StreetObject;

/**
 * @author Cornelius
 *
 */
public interface View {
	
	/**
	 * Builds an empty street
	 * @param numberLanes the number on lanes on street
	 * @param length the length in pixel
	 */
	abstract public void build();
	
	abstract public void setModel(Model model);
	
	abstract public Model getModel();
	
	abstract public void setController(Controller cntrl);
	
	abstract public Controller getController();
	
	abstract public void setPossibleCars(ArrayList<String> ArrStr);

	public abstract void removeCurrentObject(StreetObject car1);

	public abstract void addCurrentObject(StreetObject car1);
	
}	
