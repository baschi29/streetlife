
package infpp.streetlife.view;

import infpp.streetlife.controller.Controller;
import infpp.streetlife.model.Model;

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
	
}	
