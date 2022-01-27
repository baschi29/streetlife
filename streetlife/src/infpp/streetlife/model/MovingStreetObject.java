/**
 * 
 */
package infpp.streetlife.model;

/**
 * Class for moving Street Objects. Every moving Street object has a velocity and a move() method.
 */
public abstract class MovingStreetObject extends StreetObject{
	
	/**
	 * Version number
	 */
	private static final long serialVersionUID = -7084546346725325553L;
	
	/**
	 * velocity in pixel/movement
	 */
	private float velocity;
	
	private int intendedX;
	
	private int intendedY;
	
	/**
	 * Constructor of the Class
	 * @param x x position of the object
	 * @param y y position of the object
	 * @param name name of the object
	 * @param velocity velocity of the object
	 * @param hardness hardness of the object
	 */
	public MovingStreetObject(int x, int y, String name, int hardness, float velocity) throws Exception {
		super(x, y, name, hardness);
		this.setIntendedX(x);
		this.setIntendedY(y);
		this.setVelocity(velocity);
	}
	
	/**
	 * Calculates the intended new x and y positions for the object after the movement
	 */
	public abstract void calculateMove();
	
	/**
	 * The move method moves a MovingStreetObject to their intended x and y positions
	 * It is the responsibility of the model to verify if the intended positions are valid
	 */
	public void move() {
		
		this.setX(this.getIntendedX());
		this.setY(this.getIntendedY());
		
	}

	/**
	 * @return velocity velocity of the moving object
	 */
	public float getVelocity() {
		return velocity;
	}

	/**
	 * @param velocity sets the velocity of the moving object
	 * negative velocities are highly illegal
	 */
	public void setVelocity(float velocity) throws Exception{
		if (velocity >= 0) {
			this.velocity = velocity;
		}
		else {
			throw new ArithmeticException("Velocity should be positive or null");
		}
	}

	/**
	 * @return intendedX intended x position after movement
	 */
	public int getIntendedX() {
		return intendedX;
	}

	/**
	 * @param intendedX sets intended x position after movement
	 */
	public void setIntendedX(int intendedX) {
		this.intendedX = intendedX;
	}

	/**
	 * @return intendedY intended y position after movement
	 */
	public int getIntendedY() {
		return intendedY;
	}

	/**
	 * @param intendedY sets intended y position after movement
	 */
	public void setIntendedY(int intendedY) {
		this.intendedY = intendedY;
	}
	
}
