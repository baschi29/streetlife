/**
 * 
 */
package infpp.streetlife.model;

/**
 * @author basti
 *
 */
public class Lane extends StreetObject{

	/**
	 * version number
	 */
	private static final long serialVersionUID = -8513366365658877769L;
	
	/**
	 * Lane below the current lane
	 */
	private Lane botLane;
	
	/**
	 * Lane above the current lane
	 */
	private Lane topLane;
	
	/**
	 * Constructor
	 * hardness set to -1 as there is no collision with other objects
	 * Lanes stretch across the whole model, but their x coordinate is set to 0
	 * @param model the model the object exists in
	 * @param y y position of the object
	 * @param name name of the object
	 */
	public Lane(Model model, int y, int yExtension, Lane botLane, Lane topLane) {
		super(model, 0, y, "Lane", -1);
		this.setBotLane(botLane);
		this.setTopLane(topLane);
		this.setYExtension(yExtension);
	}
	
	/**
	 * ticks do nothing for lanes
	 */
	@Override
	public void tick() {}

	/**
	 * @return botLane Lane below the current lane
	 */
	public Lane getBotLane() {
		return this.botLane;
	}

	/**
	 * @param botLane Lane below the current lane
	 */
	public void setBotLane(Lane botLane) {
		this.botLane = botLane;
	}

	/**
	 * @return topLane Lane above the current lane
	 */
	public Lane getTopLane() {
		return this.topLane;
	}

	/**
	 * @param topLane Lane above the current lane
	 */
	public void setTopLane(Lane topLane) {
		this.topLane = topLane;
	}

}
