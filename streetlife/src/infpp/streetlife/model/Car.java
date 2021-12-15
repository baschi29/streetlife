/**
 * 
 */
package infpp.streetlife.model;

/**
 * Class for cars. A Car has a position (x,y), a specified name and a specific velocity
 */
public class Car extends StreetObject {
	
	/**
	 * velocity of car in pixel/movement (ppm)
	 */
	private int velocity;
	
	/**
	 * Constructor of the class
	 * @param x x position of the car
	 * @param y y position of the car
	 * @param name name of the car
	 */
	public Car(int x, int y, String name, int velocity) {
		super(x, y, name);
		this.setVelocity(velocity);
	}
	
	/**
	 * A car can move from left to right, defined by its velocity
	 */
	
	public void move() {
		this.setX(this.getX() + this.getVelocity());
	}
	
	
	/**
	 * A car can move from left to right, specified by amount in pixel
	 */
	
	public void move(int amount) {
		this.setX(this.getX() + amount);
	}

	/**
	 * @return velocity
	 */
	public int getVelocity() {
		return velocity;
	}

	/**
	 * @param velocity das zu setzende Objekt velocity
	 */
	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}
}
