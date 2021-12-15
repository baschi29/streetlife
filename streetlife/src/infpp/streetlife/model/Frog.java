/**
 * 
 */
package infpp.streetlife.model;

import java.util.Random;

/**
 * Class for frogs. A Car has a position (x,y), a specified name and a specific jump interval and
 * jump range
 */
public class Frog extends StreetObject{
	
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
	 * Constructor
	 * @param x initial x position of the frog
	 * @param y initial y position of the frog
	 * @param name name of the frog
	 * @param jumpInterval number of movement calls it takes for the frog to jump
	 * @param jumpRange distance the frog is able to jump
	 */
	public Frog(int x, int y, String name, int jumpInterval, int jumpRange) {
		super(x, y, name);
		this.setJumpInterval(jumpInterval);
		this.setJumpRange(jumpRange);
		this.setIntervalCounter(jumpInterval);
	}

	/**
	 * @return jumpInterval number of movement calls it takes for the frog to jump
	 */
	public int getJumpInterval() {
		return jumpInterval;
	}

	/**
	 * @param jumpInterval sets the jump interval of the frog
	 */
	public void setJumpInterval(int jumpInterval) {
		this.jumpInterval = jumpInterval;
	}

	/**
	 * @return jumpRange 
	 */
	public int getJumpRange() {
		return jumpRange;
	}

	/**
	 * @param jumpRange distance the frog is able to jump
	 */
	public void setJumpRange(int jumpRange) {
		this.jumpRange = jumpRange;
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
	
	//TODO: vernünftige Move Methode für Frosch schreiben
	/**
	 * A frog moves every <jumpInterval> movement calls
	 * currently it moves <jumpRange> units randomly in x or y direction
	 * After movement the IntervalCounter gets reset
	 */
	public void move() {
		this.setIntervalCounter(this.getIntervalCounter() - 1);
		
		if (this.getIntervalCounter() == 0) {
			
			Random rd = new Random();
			boolean xMovement = rd.nextBoolean();
			int movementSign = rd.nextInt(2) * 2 - 1; // Randomly -1 or +1
			
			if (xMovement) {
				this.setX(movementSign * this.getJumpRange());
			}
			else {
				this.setY(movementSign * this.getJumpRange());
			}
			
			this.setIntervalCounter(this.getJumpInterval());
		}
	}
	
}
