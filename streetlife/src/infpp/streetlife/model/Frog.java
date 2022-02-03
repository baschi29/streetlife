/**
 * 
 */
package infpp.streetlife.model;

import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import infpp.streetlife.FileLoader;

/**
 * Class for frogs. A Car has a position (x,y), a specified name and a specific jump interval and
 * jump range
 */
public class Frog extends MovingStreetObject{
	
	/**
	 * version number
	 */
	private static final long serialVersionUID = -2783805143464437390L;

	/**
	 * Interval in which the frog jumps, defined in movements/jump
	 */
	private int jumpInterval;
	
	/**
	 * Counter variable for counting remaining movements before next jump (counting down)
	 */
	private int intervalCounter;
	
	/**
	 * Range of one jump of the frog
	 */
	private int jumpRange;
	
	
	
	/**
	 * Constructor, hardness for all frogs is set to 1
	 * @param model model the object exists in
	 * @param x initial x position of the frog
	 * @param y initial y position of the frog
	 * @param name name of the frog
	 * @param jumpInterval number of movement calls it takes for the frog to jump
	 * @param jumpRange distance the frog is able to jump
	 */
	public Frog(Model model, int x, int y, String name, int jumpRange, float velocity) throws Exception{
		super(model, x, y, name, 1, velocity);
		this.setJumpRange(jumpRange);
		this.setVelocity(velocity);
		
	}

	/**
	 * @return jumpInterval number of movement calls it takes for the frog to jump
	 */
	public int getJumpInterval() {
		return jumpInterval;
	}

	/**
	 * @param jumpInterval sets the jump interval of the frog, resets IntervalCounter
	 */
	private void setJumpInterval(int jumpInterval) {
		this.jumpInterval = jumpInterval;
		this.setIntervalCounter(jumpInterval);
	}

	/**
	 * @return jumpRange range of one jump of the frog
	 */
	public int getJumpRange() {
		return jumpRange;
	}

	/**
	 * @param jumpRange Range of one jump of the frog
	 */
	private void setJumpRange(int jumpRange) {
		this.jumpRange = jumpRange;
	}
	
	/**
	 * @param velocity sets the velocity of the frog as well as the jumpInterval resulting
	 * from velocity and jumpRange
	 * jumpInterval = jumpRange / velocity
	 * because of the restriction of one jump per move the velocity cannot be higher than jumpRange
	 */
	@Override
	public void setVelocity(float velocity) throws Exception {
		
		if (this.getVelocity() <= this.getJumpRange()) {
			super.setVelocity(velocity);
			this.setJumpInterval((int) ( this.getJumpRange() / this.getVelocity() )); // may round because of typecast
		}
		else {
			super.setVelocity(this.getJumpRange());
			this.setJumpInterval(1);
		}
		
	}

	/**
	 * @return intervalCounter counting down the amount of movements till the next jump
	 */
	public int getIntervalCounter() {
		return intervalCounter;
	}

	/**
	 * @param intervalCounter setting the amount of movements till the next jump
	 */
	private void setIntervalCounter(int intervalCounter) {
		this.intervalCounter = intervalCounter;
	}
	
	/**
	 * A frog moves every jumpInterval movement calls
	 * currently it moves jumpRange units randomly in x or positive y direction
	 * After movement the IntervalCounter gets reset
	 */
	public void calculateMove() {
		
		this.setIntervalCounter(this.getIntervalCounter() - 1);
		
		if (this.getIntervalCounter() == 0) {
			
			Random rd = new Random();
			boolean xMovement = rd.nextBoolean();
			int movementSign = rd.nextInt(2) * 2 - 1; // Randomly -1 or +1
			
			if (xMovement) {
				this.setIntendedCenterX(this.getCenterX() + movementSign * this.getJumpRange()*20);
			}
			else {
				this.setIntendedCenterY(this.getCenterY() + this.getJumpRange());
			}
			
			this.setIntervalCounter(this.getJumpInterval());
		}
		
	}
	
}
