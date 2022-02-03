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
	
	/**
	 * Image for everything else
	 */
	private final String ERROR_PATH = "img/missing_texture.png";
	
	private BufferedImage CarImg;
	private BufferedImage FrogImg;
	private BufferedImage ErrorImg;
	
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
	           ErrorImg = fl.loadImage(ERROR_PATH);
	        } catch(IOException ioe){
	        	System.out.println("Unable to open file");
	        	}
	}
	
	public BufferedImage getImg(String objectName) {
		String str = objectName.trim();
		if (str.equals("Frog")) {
			return FrogImg; 
		}
		else if (str.equals("Fiat")) {
			return CarImg;
		}
		else if (str.equals("Ford") ) {	
			return CarImg;
		}
		else if (str.equals("Ferrari")) {
			return CarImg;
		}
		else {
			System.out.println(objectName);
			System.out.println(objectName.getClass());
			return ErrorImg;
			//throw new IllegalArgumentException("Tried returning Object that isn't predefined");
		}
	}
}
