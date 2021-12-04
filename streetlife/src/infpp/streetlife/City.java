/**
 * 
 */
package infpp.streetlife;

import java.util.Random;

/**
 * Class representing the world all other objects exist in
 */
public class City {
	
	/**
	 * width of the city
	 * Defines the size of the city together with depth
	 */
	private int width;
	
	/**
	 * depth of the city
	 * Defines the size of the city together with depth
	 */
	private int depth;
	
	/**
	 * Car object that is existing in the city
	 */
	private Car car;
	
	/**
	 * Constructor of the class
	 * @param width width of the city
	 * @param depth depth of the city
	 * @param car car object in the city
	 */
	public City(int width, int depth, Car car) {
		this.setWidth(width);
		this.setDepth(depth);
		this.setCar(car);
	}

	/**
	 * @return width widht of city
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width new width of the city
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return depth depth of city
	 */
	public int getDepth() {
		return depth;
	}

	/**
	 * @param depth new depth of the city
	 */
	public void setDepth(int depth) {
		this.depth = depth;
	}

	/**
	 * @return car car in city
	 */
	public Car getCar() {
		return car;
	}

	/**
	 * @param car new car object in the city
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
	 * @return description returns the description of the city as a String with width, depth and the description of the car
	 */
	public String toString() {
		return "Width: "
				+ Integer.toString(this.width)
				+ "; Depth: "
				+ Integer.toString(this.depth)
				+ "\nwith car "
				+ this.car.toString();
	}
}
