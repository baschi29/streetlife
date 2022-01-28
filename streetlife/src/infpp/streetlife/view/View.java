
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
	 */
	abstract public void build();
	
	/**
	 * setting the model, that should be displayed
	 * @param model
	 */
	abstract public void setModel(Model model);
	
	/**
	 * returns the displayed model
	 * @return displayed model
	 */
	abstract public Model getModel();
	
	/**
	 * sets the controller, that controls the view. Used for transferring back the action after the ActionListener firing
	 * @param cntrl
	 */
	abstract public void setController(Controller cntrl);
	
	/**
	 * returns the controller, that controls the view.
	 * @return controller
	 */
	abstract public Controller getController();
	
	/**
	 * sets the possible car types that _could_ be added
	 * @param ArrStr
	 */
	abstract public void setPossibleCars(ArrayList<String> ArrStr);

	/**
	 * adds a car to the view. Note that this doesn't affect the model
	 * @param car1
	 */
	public abstract void addCurrentObject(StreetObject car1);
	
	/**
	 * removes a car from the view. Note that this doesn't affect the model
	 * @param car1
	 */
	public abstract void removeCurrentObject(StreetObject car1);

	/**
	 * refreshes the whole view, essentially getting things up-to-date and repaint the window
	 */
	public abstract void refresh();
	
}	
