/**
 * 
 */
package infpp.streetlife.controller;

import java.util.ArrayList;
import java.util.*;
import java.io.*;
import infpp.streetlife.model.*;
import infpp.streetlife.view.*;

/**
 *
 */
public class StreetLifeController implements Controller {

	private int numberTicks;
	private Model model;
	private View view;
	private int numLanes;
	private int sizeStreet;
	private ArrayList<String> possibleCars;
	
	/**
	 * Creates a new Controller
	 * @param numLanes specifies the amount of lanes for the street
	 * @param sizeStreet specifies the length of the Street
	 * @param numTicks specifies the amount of ticks
	 */
	public StreetLifeController(int numLanes, int sizeStreet, int numTicks){

		
		this.numLanes = numLanes;
		this.sizeStreet = sizeStreet;
		this.numberTicks = numTicks;
		this.possibleCars = new ArrayList<>();
		this.possibleCars.add("Fiat");
		this.possibleCars.add("Ford");
		this.possibleCars.add("Ferrari");
		
	    try {
			this.initiate();
			
		} catch (Exception e) {
			// fetch any errors during building-phase
			System.out.println("Initiating of the StreetLife failed, see below for more details");
			e.printStackTrace();
		}
	}

	/**
	 * initiates the model with some cars, and adds the view to it
	 */
	
	private void initiate() {	
		//this.view = new AsciiView();
		//this.view = new TextView();
		this.model = new StreetLifeModel(numLanes, sizeStreet);
		this.view = new StreetLifeView(model);
	
		
		this.view.setModel(model);
		this.view.setController(this);
		// this.model.setView(view);
		this.view.build();
		this.view.setPossibleCars(this.possibleCars);
		
		Car car1 = new Car(0,1,"Fiat", 1);
		Car car2 = new Car(0,2,"Audi", 2);
		Frog frog1 = new Frog(10, 4, "Frog", 4, 1);
		
		this.addObject(car1);
		this.addObject(car2);
		this.addObject(frog1);
		
		//building it and displaying it for the first time
		//this.view.build(this.model.getWidth(), this.model.getLength(), this.model.getModelState());
		//this.view.display(this.model.getModelState());
	}
	


	/**
	 * starts the simulation and controls the display of the actions
	 */
	@Override
	public void start() {
		
		
	}

	/**
	 * stops the whole simulation and ends the program //TODO 
	 */
	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public void step() {
		
		try {
				this.model.move();
				//this.view.display(this.model.getModelState());
			}
		 catch (Exception e) {
			// fetches any errors during the running-phase
			System.out.println("Oops! Something unexpected occured and we are deeply sorry. Check the error message for details:");
			e.printStackTrace();
		}
	}
	

	@Override
	public ArrayList<String> getListOfCars() {
		return this.possibleCars;
	}

	@Override
	public void addMovingObject(String str) throws Exception{
		
		if (str == "Frog") {
			this.addObject(new Frog(10, 4, "Frog", 4, 1));
		}
		else if (str == "Fiat") {
			this.addObject(new Car(0,1,"Fiat", 1));
		}
		else if (str == "Ford") {
			this.addObject(new Car(0,2,"Ford", 2));
		}
		else if (str == "Ferrari") {
			this.addObject(new Car(0,3,"Ferrari", 5));
		}
		else {
			throw new IllegalArgumentException("Tried adding Object that isn't predefined");
		}
		
	}

	private void addObject(StreetObject car1) {
		this.model.addObject(car1);
		this.view.addCurrentObject(car1);
		
	}
	
	
	@Override
	public void deleteObject(StreetObject obj) {
		this.model.deleteObject(obj);
		this.view.removeCurrentObject(obj);
		
	}

	@Override
	public void releaseTheFrogs(int number) {
		for (int i = 0; i<number; i++) {
			try {
				this.addMovingObject("Frog");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * Saves the model to a given file
	 * @param path path to file
	 */
	@Override
	public void saveToFile(String path) throws Exception {
		
		OutputStream os = new FileOutputStream(path);
		ObjectOutputStream oss = new ObjectOutputStream(os);
		oss.writeObject(this.model);
		oss.close();
		
	}
	
	/**
	 * Loads a model state from a given file
	 * @param path path to file
	 */
	@Override
	public void loadFromFile(String path) throws Exception {
		
		InputStream is = new FileInputStream(path);
		ObjectInputStream ois = new ObjectInputStream(is);
		this.model = (Model) ois.readObject();
		ois.close();
		
	}

}
