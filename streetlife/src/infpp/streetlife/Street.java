/**
 * 
 */
package infpp.streetlife;

import java.util.Random;

/**
 * Class representing the world all other objects exist in
 */
public class Street{
	
	/**
	 * width of the street (y)
	 * Defines the size of the street together with length
	 */
	private int width;
	
	/**
	 * length of the street (x)
	 * Defines the size of the street together with length
	 */
	private int length;
	
	/**
	 * Car object that is existing in the street
	 */
	private Car car;
	
	/**
	 * Constructor of the class
	 * @param width width of the street
	 * @param length length of the street
	 * @param car car object in the street
	 */
	public Street(int width, int length, Car car) {
		this.setWidth(width);
		this.setLength(length);
		this.setCar(car);
	}

	/**
	 * @return width widht of street
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
	 * @return car car in street
	 */
	public Car getCar() {
		return car;
	}

	/**
	 * @param car new car object in the street
	 */
	public void setCar(Car car) {
		this.car = car;
	}
	
	/**
	 * Randomly moves the car 1 step further
	 */
	public void move() {
		
		Random rd = new Random();
		boolean xMovement = rd.nextBoolean();
		int movementDirection = rd.nextInt(2) * 2 - 1; // Randomly -1 or +1
		
		if (xMovement) {
			this.car.setX(movementDirection + this.car.getX()); 
		}
		else {
			this.car.setY(movementDirection + this.car.getY());
		}
	}
	
	/**
	 * @return description returns the description of the street as a String with width, length and the description of the car
	 */
	public String toString() {
		return "Width: "
				+ Integer.toString(this.width)
				+ "; Length: "
				+ Integer.toString(this.length)
				+ "\nwith car "
				+ this.car.toString();
	}
}
