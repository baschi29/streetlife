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
	
	private Lane lane;
	
	private int laneSwitchIntention = 0;

	/**
	 * Constructor of the class, hardness for all cars is set to 2
	 * @param x x position of the car
	 * @param y y position of the car
	 * @param name name of the car
	 * @param velocity velocity of car
	 */
	public Car(Model model, int x, Lane lane, String name, float velocity) throws Exception{
		super(model, x, lane.getY(), name, 2, velocity);
		this.setLane(lane);
		
	}
	
	/**
	 * A car can move from left to right, defined by its velocity
	 */
	public void calculateMove() {
		this.setIntendedX((int) (this.getX() + this.getVelocity()*20));
	}
	

	/**
	 * @return lane
	 */
	public Lane getLane() {
		return lane;
	}

	/**
	 * @param lane das zu setzende Objekt lane
	 */
	public void setLane(Lane lane) {
		this.lane = lane;
	}

	/**
	 * @return laneSwitchIntention
	 */
	public int getLaneSwitchIntention() {
		return this.laneSwitchIntention;
	}

	/**
	 * @param laneSwitchIntention das zu setzende Objekt laneSwitchIntention
	 */
	public void setLaneSwitchIntention(int laneSwitchIntention) {
		this.laneSwitchIntention = laneSwitchIntention;
	}
	
}
