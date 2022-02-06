/**
 * 
 */
package infpp.streetlife.view;

import java.util.ArrayList;

import infpp.streetlife.controller.Controller;
import infpp.streetlife.model.Model;
import infpp.streetlife.model.StreetObject;

/**
 * Simple text display on the system console, could be used for debug if gui gets bricked
 * @author Cornelius, Bastian
 * @deprecated not used atm
 * 
 */
public class TextView implements View {
	
	private Model model;
	private Controller controller;
	private int numberLanes;
	private int length;
	
	private ArrayList<StreetObject> modelState;
	private ArrayList<String> possibleCars;
	
	
	public TextView(int numberLanes, int length, ArrayList<StreetObject> modelState) {
		System.out.println("Starting StreetLife ...");
		this.numberLanes = numberLanes;
		this.length = length;

	}

	
	@Override
	public void build() {
		
		String objectString = "";
		
		this.modelState = this.model.getModelState();
		
		for (StreetObject obj : modelState) {
			objectString += obj.toString() + "\n";
		}
		System.out.println("Build complete! Current objects: "
				+ "\n"
				+ objectString);

	}

	public void displayPossibleCars() {
		System.out.print("possible car types: " + this.possibleCars);
	}
	

	@Override
	public void setModel(Model model) {
		this.model = model;
		
	}


	@Override
	public Model getModel() {
		return this.model;
	}


	@Override
	public void setController(Controller cntrl) {
		this.controller = cntrl;
		
	}


	@Override
	public Controller getController() {
		return this.controller;
	}


	@Override
	public void setPossibleCars(ArrayList<String> ArrStr) {
		this.possibleCars = ArrStr;
		
	}


	@Override
	public void removeCurrentObject(StreetObject car1) {
		// nothing here atm TODO
		
	}


	@Override
	public void addCurrentObject(StreetObject car1) {
	// nothing here atm	TODO
		
	}


	@Override
	public void refresh() {
		this.build();
		
	}


	@Override
	public void setSize(int i, int j) {
		// TODO Automatisch generierter Methodenstub
		
	}


	@Override
	public void showException(Exception exc) {
		// TODO Auto-generated method stub
		
	}
	
	
}
