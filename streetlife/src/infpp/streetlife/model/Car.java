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
	
	private static final double laneSwitchQuota = 0.5;
	
	/**
	 * Lane the car is on
	 */
	private Lane lane;
	
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
	 * @param x x position of the car
	 * @param y y position of the car
	 * @param name name of the car
	 * @param velocity velocity of car
	 */
	public Car(Model model, int x, Lane lane, String name, float velocity) throws Exception{
		super(model, x, lane.getY(), name, 2, velocity);
		this.setLane(lane);
		this.setLaneSwitchVelocity((int) (Car.laneSwitchQuota * this.getVelocity()));
		
	}
	
	/**
	 * A car can move from left to right, defined by its velocity
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
	
	@Override
	protected void handleXCollision(StreetObject obj, int xDirection) {
		
		if (!this.isSwitchingLanes()) {
			super.handleXCollision(obj, xDirection);
		}
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

	/**
	 * @return switchingLanes
	 */
	public boolean isSwitchingLanes() {
		return this.switchingLanes;
	}

	/**
	 * @param switchingLanes das zu setzende Objekt switchingLanes
	 */
	public void setSwitchingLanes(boolean switchingLanes) {
		this.switchingLanes = switchingLanes;
	}

	/**
	 * @return laneSwitchVelocity
	 */
	public int getLaneSwitchVelocity() {
		return this.laneSwitchVelocity;
	}

	/**
	 * @param laneSwitchVelocity das zu setzende Objekt laneSwitchVelocity
	 */
	private void setLaneSwitchVelocity(int laneSwitchVelocity) {
		this.laneSwitchVelocity = laneSwitchVelocity;
	}
	
}
