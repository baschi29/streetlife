/**
 * 
 */
package infpp.streetlife;

/**
 * Class for cars. A Car has a position (x,y) and a specified name
 */
public class Car {
	
	/**
	 * x position of the car
	 */
	private int x;
	
	/**
	 * y position of the car
	 */
	private int y;
	
	/**
	 * name of the car
	 */
	private String name;
	
	/**
	 * Constructor of the class
	 * @param x x position of the car
	 * @param y y position of the car
	 * @param name name of the car
	 */
	public Car(int x, int y, String name) {
		this.setX(x);
		this.setY(y);
		this.setName(name);
	}

	/**
	 * @return x x position of the car
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x new x position
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return y y position of the car
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y new y position
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return name name of the car
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name new name of the car
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return description returns the description of the car as a String with name, x- and y-position
	 */
	public String toString() {
		return this.name
				+ " at: x - "
				+ Integer.toString(this.x)
				+ " y - "
				+ Integer.toString(this.y);
	}
}
