
package infpp.streetlife.view;

import infpp.streetlife.controller.Controller;
import infpp.streetlife.model.Model;
import infpp.streetlife.model.StreetLifeModel;

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
	
	abstract public void setModel(StreetLifeModel model);
	
	abstract public Model getModel();
	
	abstract public void setController(Controller cntrl);
	
	abstract public Controller getController();
	
}	
