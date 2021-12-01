package infpp.streetlife;

public class Car {
	private int x;
	private int y;
	private String name;
	
	public Car(int x, int y, String name) {
		this.setX(x);
		this.setY(y);
		this.setName(name);
	}

	/**
	 * @return x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x das zu setzende Attribut x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y das zu setzende Objekt y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name das zu setzende Objekt name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return Description returns the description of the car
	 */
	public String toString() {
		return "Name: "
				+ this.name
				+ " at: x - "
				+ Integer.toString(this.x)
				+ " y - "
				+ Integer.toString(this.y);
	}
}
