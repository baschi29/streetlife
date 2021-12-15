/**
 * 
 */
package infpp.streetlife.model;

/**
 * Class for frogs. A Car has a position (x,y), a specified name and a specific jump interval and
 * jump range
 */
public class Frog extends StreetObject{
	
	/**
	 * Interval in which the frog jumps, defined in jumps/movement
	 */
	private float jumpInterval;
	
	/**
	 * Counter variable for counting remaining movements before next jump (counting down)
	 */
	private int intervalCounter;
	
	/**
	 * Range of one jump of the frog
	 */
	private int jumpRange;
	
	public Frog(int x, int y, String name, float jumpInterval, int jumpRange) {
		super(x, y, name);
		this.setJumpInterval(jumpInterval);
		this.setJumpRange(jumpRange);
	}

	/**
	 * @return jumpInterval
	 */
	public float getJumpInterval() {
		return jumpInterval;
	}

	/**
	 * @param jumpInterval das zu setzende Objekt jumpInterval
	 */
	public void setJumpInterval(float jumpInterval) {
		this.jumpInterval = jumpInterval;
	}

	/**
	 * @return jumpRange
	 */
	public int getJumpRange() {
		return jumpRange;
	}

	/**
	 * @param jumpRange das zu setzende Objekt jumpRange
	 */
	public void setJumpRange(int jumpRange) {
		this.jumpRange = jumpRange;
	}

	/**
	 * @return intervalCounter
	 */
	public int getIntervalCounter() {
		return intervalCounter;
	}

	/**
	 * @param intervalCounter das zu setzende Objekt intervalCounter
	 */
	public void setIntervalCounter(int intervalCounter) {
		this.intervalCounter = intervalCounter;
	}
	
}
