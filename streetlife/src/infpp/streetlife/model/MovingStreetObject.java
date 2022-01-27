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
	
	/**
	 * Constructor of the Class
	 * @param x x position of the object
	 * @param y y position of the object
	 * @param name name of the object
	 * @param velocity velocity of the object
	 */
	public MovingStreetObject(int x, int y, String name, int hardness, float velocity) throws Exception {
		super(x, y, name, hardness);
		this.setVelocity(velocity);
	}
	
	/**
	 * The move method moves a MovingStreetObject according to their movement rules and velocity
	 * It also checks if a move is valid
	 */
	public abstract void move();

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
}
