/**
 * 
 */
package infpp.streetlife.model;

/**
 * Class for cars. A Car has a position (x,y), a specified name and a specific velocity. It also states a current lane.
 */
public class Car extends StreetObject {
	
	private int velocity; //car velocity in pixel per movement (ppm)
	private int lane;	  //current lane of car
	private int oldPos;   //remembering "old position" of car for the view
	



	/**
	 * Constructor of the class
	 * @param x x position of the car
	 * @param y y position of the car
	 * @param name name of the car
	 * @param velocity velocity of car
	 */
	public Car(int x, int y, String name, int velocity) {
		super(x, y, name);
		this.setLane(y); //the lane is the current y position
		this.setVelocity(velocity);
	}
	
	/**
	 * A car can move from left to right, defined by its velocity
	 */
	
	public void move() {
		this.setOldPos(this.getX());
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
	 * @param velocity velocity
	 */
	public void setVelocity(int velocity) {
		if (velocity>0) 
			this.velocity = velocity;
		
		else
			throw new ArithmeticException("Velocity should be positive");
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
	
	/**
	 * 
	 * @return old position as integer
	 */
	public int getOldPos() {
		return oldPos;
	}
	/**
	 * 
	 * @param oldPos the old Pos (for movement for example) -> private!
	 */
	private void setOldPos(int oldPos) {
		this.oldPos = oldPos;
	}
}
