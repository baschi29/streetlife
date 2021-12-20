/**
 * 
 */
package infpp.streetlife.model;

import java.util.ArrayList ;
import java.util.Random;
import infpp.streetlife.view.View;

/**
 * Class representing the world all other objects exist in
 */
public class StreetLifeModel{
	
	private int width;
	private int length;
	private View view;
	private ArrayList<Car> cars;
	
	/**
	 * Constructor of the class
	 * @param width width of the street = number of lanes
	 * @param length length of the street
	 * @view the gui the model should be displayed in
	 */
	public StreetLifeModel(int width, int length, View view) {
		this.setWidth(width);
		this.setLength(length);
		cars = new ArrayList<Car>();
		
		//setting the view, building it and displaying it for the first time
		this.view = view;
		view.build(width, length);
		view.display();
		
	}

	/**
	 * @return width width of street
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width new width of the street
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return length length of street
	 */
	public int getLength() {
		return length;
	}

	/**
	 * @param length new length of the street
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * 
	 * @param car adds a car to the street
	 */
	public void addCar(Car car) {
		cars.add(car);
	}
	/**
	 * 
	 * @return View the view connected to the model
	 */
	public View getView() {
		return view;
	}

	
	/**
	 * moves the whole citylife for 1 step
	 */
	public void move() {
		for (Car car : cars) {
			car.move();
			view.updateObject(car);
		}
	}
	
	
	//TODO: Methode sinnvoll? Müsste doch auch alles über eine move Methode machbar sein, Zweck?
	/**
	 * Randomly moves everything
	 */
	public void moveRandom() {
		
		Random rd = new Random();
		int movementSign = rd.nextInt(2) * 2 - 1; // Randomly -1 or +1
		for (Car car : cars) {
		
			car.move(movementSign);
		
		}
		
	}
	
	/**
	 * @return description returns the description of the street as a String with width, length and the description of the car
	 */
	public String toString() {
		String carString = "";
		
		for (Car car : cars) {
			carString += car.toString() + "\n";
		}
		return "Width: "
				+ Integer.toString(this.width)
				+ "; Length: "
				+ Integer.toString(this.length)
				+ carString;
	}
}
