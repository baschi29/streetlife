/**
 * 
 */
package infpp.streetlife.view;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import infpp.streetlife.FileLoader;
import infpp.streetlife.controller.Controller;
import infpp.streetlife.model.Car;
import infpp.streetlife.model.Frog;
import infpp.streetlife.model.Model;
import infpp.streetlife.model.StreetObject;

/**
 * The StreetLifeView implements the interface, manages the in/outputs and controls the gui. Basically used for connecting the gui to the view-interface
 * @author Cornelius, Bastian
 * @version 1.4
 * @since 2022-01-28
 *
 */
public class StreetLifeView implements View {

	private StreetGUI gui;
	private Controller controller;
	private Model model;
	
	/**
	 * Image of the Car, so that it doesn't look like a frog
	 */
	private final String CAR_PATH = "img/car.png";
	
	/**
	 * Image of the Frog, so that it doesn't look like a car
	 */
	private final String FROG_PATH = "img/frog_small.png";
	
	private BufferedImage CarImg;
	private BufferedImage FrogImg;
	
	public StreetLifeView(Model model) {
		try {
			this.gui = new StreetGUI(model);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setModel(model);
		this.initiateImages();
		this.gui.setView(this);
	}
	
	
	@Override
	public void build() {
		this.gui.setLaneNumber(this.controller.getLaneNumber());
		gui.setVisible(true);
	}

	@Override
	public void setModel(Model model) {
		this.model = model;
		gui.setModel(model);
	}
	
	@Override
	public Model getModel() {
		return this.model;
	}


	@Override
	public void setController(Controller cntrl) {
		this.controller = cntrl;
		gui.setController(controller);
		
	}


	@Override
	public Controller getController() {
		return this.controller;
	}


	@Override
	public void setPossibleCars(ArrayList<String> ArrStr) {
		this.gui.setPossibleCars(ArrStr);
		
		
	}


	@Override
	public void removeCurrentObject(StreetObject car1) {
		this.gui.removeCurrentCar(car1);
		
	}


	@Override
	public void addCurrentObject(StreetObject car1) {
		this.gui.addCurrentCar(car1);
		
	}


	@Override
	public void refresh() {
		this.gui.refresh();
		
	}


	@Override
	public void setSize(int i, int j) {
		int actual_j = j*20;
		this.gui.setSize(i, actual_j);
		this.gui.setSizeDisplay(actual_j, i, this.controller.getLaneNumber());
		
	}
	
	private void initiateImages() {
		try { 
			 FileLoader fl = new FileLoader();
	           CarImg = fl.loadImage(CAR_PATH);
	           FrogImg = fl.loadImage(FROG_PATH);
	        } catch(IOException ioe){
	        	System.out.println("Unable to open file");
	        	}
	}
	
	public BufferedImage getImg(String objectName) {
		if (objectName == "Frog") {
			return FrogImg; 
		}
		else if (objectName == "Fiat") {
			return CarImg;
		}
		else if (objectName == "Ford") {	
			return CarImg;
		}
		else if (objectName == "Ferrari") {
			return CarImg;
		}
		else {
			throw new IllegalArgumentException("Tried returning Object that isn't predefined");
		}
	}
}
