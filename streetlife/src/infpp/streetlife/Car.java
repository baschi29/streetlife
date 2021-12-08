/**
 * 
 */
package infpp.streetlife;

/**
 * Class for cars
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
		this.velocity = velocity;
	}
	
	public void move() {
		this.setX(this.getX() + velocity);
	}
}
