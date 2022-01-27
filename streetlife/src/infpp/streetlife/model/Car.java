/**
 * 
 */
package infpp.streetlife.model;

/**
 * Class for cars. A Car has a position (x,y), a specified name and a specific velocity. It also states a current lane.
 */
public class Car extends MovingStreetObject {
	
	/**
	 * version number
	 */
	private static final long serialVersionUID = -1398620670097379507L;
	private int lane;	  //current lane of car


	/**
	 * Constructor of the class, hardness for all cars is set to 2
	 * @param x x position of the car
	 * @param y y position of the car
	 * @param name name of the car
	 * @param velocity velocity of car
	 */
	public Car(int x, int y, String name, float velocity) throws Exception{
		super(x, y, name, 2, velocity);
		this.setLane(y); //the lane is the current y position
		
		
	}
	
	/**
	 * A car can move from left to right, defined by its velocity
	 */
	
	public void move() {
		this.setX((int) (this.getX() + this.getVelocity()*20));
	}
	
	public int getLane() {
		return lane;
	}
	/**
	 * sets the current lane of the car. cannot be 0 or negative, as these lanes are highly illegal
	 * @param lane
	 */
	public void setLane(int lane) {
		if (lane >0)
			this.lane = lane;
		else
			throw new ArithmeticException("Lane number should be positive");
	}	
	
}
