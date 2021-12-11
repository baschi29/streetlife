/**
 * 
 */
package infpp.streetlife;

/**
 * Class for objects on the street. Acts as parent class for every moving object
 */
public abstract class StreetObject {
	
	/**
	 * x position of the object
	 */
	private int x;
	
	/**
	 * y position of the object
	 */
	private int y;
	
	/**
	 * name of the object
	 */
	private String name;
	
	/**
	 * Constructor of the class
	 * @param x x position of the object
	 * @param y y position of the object
	 * @param name name of the object
	 */
	public StreetObject(int x, int y, String name) {
		
		this.x = x;
		this.y = y;
		this.name = name;
	}

	/**
	 * @return name name of the object
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name new name of the object
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return x x position of the object
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x new x position of the object
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return y y position of the object
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y new y position of the object
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * @return description returns the description of the object as a String with name, x and y position
	 */
	public String toString() {
		return this.name
				+ " at: x - "
				+ Integer.toString(this.x)
				+ " y - "
				+ Integer.toString(this.y);
	}
}
