/**
 * 
 */
package infpp.streetlife.model;

/**
 * Class for cars. A Car has a position (x,y), a specified name and a specific velocity. It also states a current lane.
 * @author Cornelius, Bastian
 */
public class Car extends MovingStreetObject {
	
	/**
	 * version number
	 */
	private static final long serialVersionUID = -1398620670097379507L;
	
	/**
	 * defines the percentage of the x velocity used for the lane switching velocity
	 */
	private static final double laneSwitchQuota = 0.5;
	
	/**
	 * Lane the car is on
	 */
	private Lane lane;
	
	/**
	 * velocity used for switching lanes, calculated via laneSwitchQuota and x velocity
	 */
	private int laneSwitchVelocity;
	
	/**
	 * Represents if the car wants to switch lanes
	 * 1 to top lane
	 * 0 no lane switch
	 * -1 to bot lane
	 */
	private int laneSwitchIntention = 0;
	
	/**
	 * States if the car is currently switching lanes
	 */
	private boolean switchingLanes = false;
	
	/**
	 * Constructor of the class, hardness for all cars is set to 2
	 * @param model model the object exists in
	 * @param x x position of the car
	 * @param lane lane the car starts on
	 * @param name name of the car
	 * @param velocity x velocity of car
	 */
	public Car(Model model, int x, Lane lane, String name, float velocity) throws Exception{
		super(model, x, lane.getY(), name, 2, velocity);
		this.setLane(lane);
		this.setLaneSwitchVelocity((int) (Car.laneSwitchQuota * this.getVelocity()));
		
	}
	
	/**
	 * A car can move from left to right, defined by its velocity
	 * If a car has the isSwitchingLanes status it moves in y direction to its desired lane
	 */
	public void calculateMove() {
		
		this.setIntendedX((int) (this.getX() + this.getVelocity()*20));
		this.calculateLaneSwitching();
		
		if (this.isSwitchingLanes()) {
			
			this.setIntendedY(this.getY() + this.getLaneSwitchIntention() * this.getLaneSwitchVelocity());
			
			if (this.getIntendedY() > this.getLane().getTopLane().getY()) {
				this.setIntendedY(this.getLane().getTopLane().getY());
			}
			
			else if (this.getIntendedY() < this.getLane().getBotLane().getY()) {
				this.setIntendedY(this.getLane().getBotLane().getY());
			}
		}
		
	}
	
	/**
	 * Calculates if the car wants to switch lanes
	 * Cars want to switch lanes if they get slowed down or when there is a lane below them
	 */
	private void calculateLaneSwitching() {
		
		if (!this.isSwitchingLanes()) {
			
			if (this.isxSlowedDown() && this.getLane().hasTopLane()) {
				this.setLaneSwitchIntention(1);
			}
			
			else if (!this.isxSlowedDown() && this.getLane().hasBotLane()) {
				this.setLaneSwitchIntention(-1);
			}
			
			if (this.getLaneSwitchIntention() != 0 && this.getModel().findCollisions(this, 0, this.getLaneSwitchIntention() * this.getLane().getYExtension()).isEmpty()) {
				this.setSwitchingLanes(true);
			}
		}
		
		else {
			
			if (this.getLane().hasTopLane() && this.getY() == this.getLane().getTopLane().getY()) {
				
				this.setLaneSwitchIntention(0);
				this.setSwitchingLanes(false);
				this.setLane(this.getLane().getTopLane());
			}
			
			else if (this.getLane().hasBotLane() && this.getY() == this.getLane().getBotLane().getY()) {
				this.setLaneSwitchIntention(0);
				this.setSwitchingLanes(false);
				this.setLane(this.getLane().getBotLane());
			}
		}
	}
	
	/**
	 * hot fix for weird lane switching collision bugs
	 * if a car switches lanes there is currently no collision detection in x direction
	 */
	@Override
	protected void handleXCollision(StreetObject obj, int xDirection) {
		
		if (!this.isSwitchingLanes()) {
			super.handleXCollision(obj, xDirection);
		}
	}
	
	/**
	 * @return lane current lane of the car
	 */
	public Lane getLane() {
		return lane;
	}

	/**
	 * @param lane new lane of the car
	 */
	private void setLane(Lane lane) {
		this.lane = lane;
	}

	/**
	 * @return laneSwitchIntention intention of the car to switch lanes
	 */
	public int getLaneSwitchIntention() {
		return this.laneSwitchIntention;
	}

	/**
	 * @param laneSwitchIntention intention of the car to switch lanes
	 */
	private void setLaneSwitchIntention(int laneSwitchIntention) {
		this.laneSwitchIntention = laneSwitchIntention;
	}

	/**
	 * @return switchingLanes returns if the car is currently switching lanes
	 */
	public boolean isSwitchingLanes() {
		return this.switchingLanes;
	}

	/**
	 * @param switchingLanes sets if the car is currently switching lanes
	 */
	private void setSwitchingLanes(boolean switchingLanes) {
		this.switchingLanes = switchingLanes;
	}

	/**
	 * @return laneSwitchVelocity velocity of the car while changing lanes
	 */
	public int getLaneSwitchVelocity() {
		return this.laneSwitchVelocity;
	}

	/**
	 * @param laneSwitchVelocity velocity of the car while changing lanes
	 */
	private void setLaneSwitchVelocity(int laneSwitchVelocity) {
		this.laneSwitchVelocity = laneSwitchVelocity;
	}
	
}
