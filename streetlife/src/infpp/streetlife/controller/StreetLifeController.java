/**
 * 
 */
package infpp.streetlife.controller;

import java.util.ArrayList;
import java.awt.event.WindowEvent;
import java.io.*;
import infpp.streetlife.model.*;
import infpp.streetlife.view.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The controller initializes the model and the view and controls the model
 * @author Cornelius, Bastian
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
	private int numLanes;
	
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
	 * the timer used for ticking the model many times
	 */
	private Timer timer;
	
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
		
		try {
			this.initatePossibleCars();
			this.initiateModel();
			this.initiateView(this.default_cars);
			
		} catch (Exception e) {
			// fetch any errors during building-phase
			System.out.println("Initiating of the StreetLife failed, see below for more details");
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates a new Controller from a previous saved file
	 * @param filepath
	 */
	public StreetLifeController(String filepath) {

		try {
			this.loadFromFile(filepath);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		this.numLanes = this.model.getLaneNumber();
		this.sizeStreet = this.model.getLength();
		this.simSpeed = 1;
		this.default_cars = false;
		
		try {
			this.initatePossibleCars();
			this.loadFromFile(filepath);
			this.initiateView(false);
		} catch (Exception e) {
			System.out.println("Initiating of the StreetLife failed, see below for more details");
			e.printStackTrace();
		}
	}

	/**
	 * initates the list of possible cars. Wont be used in the future
	 */
	private void initatePossibleCars() {
		this.possibleCars = new ArrayList<>();
		this.possibleCars.add("Fiat");
		this.possibleCars.add("Ford");
		this.possibleCars.add("BMW");
		this.possibleCars.add("Audi");
		this.possibleCars.add("Ferrari");
		this.possibleCars.add("Nissan");
		this.possibleCars.add("VW");
		this.possibleCars.add("Skoda");
		this.possibleCars.add("Mercedes");
	}
	/**
	 * Initiates the model. Should be called first
	 * @throws Exception 
	 */
	private void initiateModel() throws Exception {
		//this.timer = new Timer();
		this.model = new StreetLifeModel(this.numLanes, this.sizeStreet);
	}
	
	/**
	 * Initiates the view, assuming that the model is already present. Should be called second.
	 * @param default_cars
	 * @throws Exception
	 */
	private void initiateView(boolean default_cars) throws Exception{	
		//this.view = new TextView();
		
		this.view = new StreetLifeView(this.model);
	
		
		this.view.setModel(model);
		this.view.setController(this);
		this.view.setSize(this.sizeStreet,this.numLanes*Model.LANE_WIDTH);
		
		this.view.setPossibleCars(this.possibleCars);
		
		// adds a few cars by default
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

		timer = new Timer();
		TimerTask task = new TimerTask(){
			
			public void run()
			{
				try {
					step();
				} catch (Exception e) {
					e.printStackTrace();
				}
			 }
		};
		
		timer.schedule(task, 0, (this.simSpeed+1) * 200);
		
	}

	/**
	 * stops the simulation
	 */
	@Override
	public void stop() throws Exception{
	
		timer.cancel();
	}
	
	/**
	 * Moves the simulation one step further
	 */
	@Override
	public void step() throws Exception{
		
		try {
				this.model.tick();
				this.view.refresh();
			}
		 catch (Exception e) {
			// fetches any errors during the running-phase
			e.printStackTrace();
			throw new Exception("Failed to execute step. This should not happen. Please check console for error logs.");
		}
	}
	
	@Override
	public void restart() throws Exception {
		this.stop();
		this.start();
		
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
		
		if (str.equals("Frog")) {
			Random random = new Random();
			
			this.addObject(new Frog(this.model, random.nextInt(this.model.getLength()), 0, "Frog", 4, 1));
		}
		else if (str.equals("Fiat")) {
			this.addObject(new Car(this.model, 0, this.model.getFirstLane(), "Fiat", 1));
		}
		else if (str.equals("Ford"))  {
			this.addObject(new Car(this.model, 0, this.model.getFirstLane(), "Ford", 2));
		}
		else if (str.equals("BMW"))  {
			this.addObject(new Car(this.model, 0, this.model.getFirstLane(), "BMW", 5));
		}
		else if (str.equals("Audi"))  {
			this.addObject(new Car(this.model, 0, this.model.getFirstLane(), "Audi", 4));
		}
		else if (str.equals("Ferrari"))  {
			this.addObject(new Car(this.model, 0, this.model.getFirstLane(), "Ferrari", 6));
		}
		else if (str.equals("Nissan"))  {
			this.addObject(new Car(this.model, 0, this.model.getFirstLane(), "Nissan", 1));
		}
		else if (str.equals("VW"))  {
			this.addObject(new Car(this.model, 0, this.model.getFirstLane(), "VW", 3));
		}
		else if (str.equals("Skoda"))  {
			this.addObject(new Car(this.model, 0, this.model.getFirstLane(), "Skoda", 2));
		}
		else if (str.equals("Mercedes"))  {
			this.addObject(new Car(this.model, 0, this.model.getFirstLane(), "Skoda", 5));
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
		this.view.setModel(model);
		System.out.println("model loaded");
		//this.view.refresh();
		ois.close();
		
	}
	
	/**
	 * @return int number of Lanes the model gets initialized with
	 */
	@Override
	public int getLaneNumber() {
		return this.numLanes;
	}

	@Override
	public void setSimSpeed(int newSimSpeed) {
		this.simSpeed = newSimSpeed;
		try {
			this.restart();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	
	

}
