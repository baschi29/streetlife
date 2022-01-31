/**
 * 
 */
package infpp.streetlife.controller;

import java.util.ArrayList;
import java.io.*;
import infpp.streetlife.model.*;
import infpp.streetlife.view.*;

/**
 *
 */
public class StreetLifeController implements Controller {
	
	

	/**
	 * StreetLifeModel the controller uses
	 */
	private Model model;
	
	/**
	 * StreetLifeView the controller uses
	 */
	private View view;
	
	/**
	 * Number of Lanes the model is initialized with
	 */
	private int numLanes; //TODO Basti
	
	/**
	 * Size of the Street (x direction)
	 */
	private int sizeStreet;
	
	/**
	 * speed of the simulation, 0 = slowest, 1 = faster, 2 = faster, etc
	 */
	private int simSpeed;
	
	/**
	 * defines if default car-setup should be used or if the street should start empty
	 */
	
	private boolean default_cars;
	/**
	 * List of predefined Cars that can be added to the model
	 */
	private ArrayList<String> possibleCars;
	
	/**
	 * Creates a new Controller
	 * @param numLanes specifies the amount of lanes for the street
	 * @param sizeStreet specifies the length of the Street
	 * @param numTicks specifies the amount of ticks
	 */
	public StreetLifeController(int numLanes, int sizeStreet, int simSpeed, boolean default_cars){

		
		this.numLanes = numLanes;
		this.sizeStreet = sizeStreet;
		this.simSpeed = simSpeed;
		this.default_cars = default_cars;
		
		
		this.possibleCars = new ArrayList<>();
		this.possibleCars.add("Fiat");
		this.possibleCars.add("Ford");
		this.possibleCars.add("Ferrari");
		
		try {
			this.initiate(default_cars);
			
		} catch (Exception e) {
			// fetch any errors during building-phase
			System.out.println("Initiating of the StreetLife failed, see below for more details");
			e.printStackTrace();
		}
	}

	/**
	 * initiates the model with some cars, and adds the view to it
	 */
	
	private void initiate(boolean default_cars) throws Exception{	
		//this.view = new TextView();
		this.model = new StreetLifeModel(this.numLanes, this.sizeStreet);
		this.view = new StreetLifeView(this.model);
	
		
		this.view.setModel(model);
		this.view.setController(this);
		this.view.setSize(this.sizeStreet,this.numLanes*this.model.LANE_WIDTH);
		
		this.view.setPossibleCars(this.possibleCars);
		
		if(default_cars) {
			try {
			this.addMovingObject("Fiat");
			this.addMovingObject("Ford");
			this.addMovingObject("Ferrari");
			}
			catch (Exception exc) {
				ErrorDialog dia = new ErrorDialog(exc);
				dia.setVisible(true);
			}
		}
		//the build of the view should be the last action, so there are no conflicts!
		this.view.build();
	}
	


	/**
	 * starts the simulation
	 */
	@Override
	public void start() throws Exception{
		
		
	}

	/**
	 * stops the simulation
	 */
	@Override
	public void stop() throws Exception{
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Moves the simulation one step further
	 */
	@Override
	public void step() throws Exception{
		
		try {
				this.model.tick();
			}
		 catch (Exception e) {
			// fetches any errors during the running-phase
			e.printStackTrace();
			throw new Exception("Failed to execute step. Please check console for error logs.");
		}
	}
	
	/**
	 * Returns the list of all predefined car templates
	 * @return ArrayList list of predefined car templates
	 */
	@Override
	public ArrayList<String> getListOfCars() {
		return this.possibleCars;
	}
	
	/**
	 * @param str Name of predefined object to be added
	 */
	@Override
	public void addMovingObject(String str) throws Exception{
		
		if (str == "Frog") {
			this.addObject(new Frog(this.model, 10, 0, "Frog", 4, 1));
		}
		else if (str == "Fiat") {
			this.addObject(new Car(this.model, 0, this.model.getFirstLane(), "Fiat", 1));
		}
		else if (str == "Ford") {
			this.addObject(new Car(this.model, 0, this.model.getFirstLane(), "Ford", 2));
		}
		else if (str == "Ferrari") {
			this.addObject(new Car(this.model, 0, this.model.getFirstLane(), "Ferrari", 5));
		}
		else {
			throw new IllegalArgumentException("Tried adding Object that isn't predefined");
		}
		
	}
	
	/**
	 * @param obj object to be added to the model
	 */
	private void addObject(StreetObject obj) throws Exception{
		
		this.model.addObject(obj);
		this.view.addCurrentObject(obj);
		
	}
	
	/**
	 * @param obj object to be deleted from the model
	 */
	@Override
	public void deleteObject(StreetObject obj) throws Exception{
		
		this.model.deleteObject(obj);
		this.view.removeCurrentObject(obj);
		
	}
	
	/**
	 * Releases a number of frogs randomly at the bottom of the street
	 * @param number number of frogs to be added
	 */
	@Override
	public void releaseTheFrogs(int number) throws Exception{
		
		for (int i = 0; i<number; i++) {
			this.addMovingObject("Frog");
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
	 * Loads a model from a given file
	 * @param path path to file
	 */
	@Override
	public void loadFromFile(String path) throws Exception {
		
		InputStream is = new FileInputStream(path);
		ObjectInputStream ois = new ObjectInputStream(is);
		this.model = (Model) ois.readObject();
		ois.close();
		
	}

	@Override
	public int getLaneNumber() {
		return this.numLanes;
	}

}
