/**
 * 
 */
package infpp.streetlife.model;

import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collections;

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
	 * the center x coordinate the object intends to be on after movement
	 */
	private int intendedX;
	
	/**
	 * the center y coordinate the object intends to be on after movement
	 */
	private int intendedY;
	
	/**
	 * true if the object wasn't able to move at full speed at the previous movement
	 */
	private boolean xSlowedDown = false;
	
	/**
	 * Constructor of the Class
	 * @param x x center position of the object
	 * @param y y center position of the object
	 * @param name name of the object
	 * @param hardness hardness of the object
	 * @param velocity velocity of the object
	 */
	public MovingStreetObject(Model model, int x, int y, String name, int hardness, float velocity) throws Exception {
		super(model, x, y, name, hardness);
		this.setIntendedX(x);
		this.setIntendedY(y);
		this.setVelocity(velocity);
	}
	
	/**
	 * A tick of a MovingStreetObject causes it to calculate it's next move
	 */
	public void tick() {
		
		if (!this.isDeleted()) {
			this.calculateMove();
			this.manageCollisions();
			this.manageBorders();
			this.move();
		}
		
	}
	
	/**
	 * Calculates the intended new x and y center positions for the object after the movement
	 */
	public abstract void calculateMove();
	
	/**
	 * The move method moves a MovingStreetObject to their intended x and y center positions
	 */
	public void move() {
		
		this.setX(this.getIntendedX());
		this.setY(this.getIntendedY());
	}
	
	/**
	 * Checks if the new intended position of an object leads to a collision
	 * If there is one, the collision is dealed with based on the hardness level of the involving objects:
	 * Objects with lower or the same hardness level will move till they reach the point exactly before the collision
	 * Objects with higher hardness level will move like there is no collision, the other object will get removed
	 */
	private void manageCollisions() {
		
		int xCenterMovement = this.getIntendedX() - this.getCenterX();
		int yCenterMovement = this.getIntendedY() - this.getCenterY();
		int xDirection = (int) Math.signum(xCenterMovement);
		int yDirection = (int) Math.signum(yCenterMovement);
		
		HashSet<Integer> xMovementSet = new HashSet<>();
		
		for (int i = 0; i <= xCenterMovement * xDirection; i++) {
			
			for (int xpos : this.getX()) {
				xMovementSet.add(this.getModel().moduloCircleX(xpos + i * xDirection));
			}
		}
		
		HashSet<Integer> yMovementSet = new HashSet<>();
		
		for (int i = 0; i <= yCenterMovement * yDirection; i++) {
			
			for (int ypos : this.getY()) {
				yMovementSet.add(ypos + i * yDirection);
			}
		}
		
		this.setxSlowedDown(false);
		
		ArrayList<HashSet<StreetObject>> collisions = this.getModel().findCollisions(this, xMovementSet, yMovementSet);
		
		// handling of x collisions
		for (int i = 0; i < collisions.size(); i++) {
			
			for (StreetObject cobj : collisions.get(i)) {
				
				if (this.getHardness() <= cobj.getHardness()) {
					
					if (i == 0) {
						this.handleXCollision(cobj, xDirection);
						this.setxSlowedDown(true);
					}
					
					if (i == 1) {
						this.handleYCollision(cobj, yDirection);
					}
				}
				
				else if (this.getHardness() > cobj.getHardness()) {
					cobj.setDeleted(true);
				}
			}
		}
	}
	
	/**
	 * Handles X Collision with an object with same or higher hardness
	 * @param obj object the x collision is with
	 * @param xDirection x direction in which the current object moves
	 */
	private void handleXCollision(StreetObject obj, int xDirection) {
		
		if (!this.isInsideXDimension(obj.getX())) {
			if (Math.signum(xDirection) < 0) {
				this.setIntendedX(Collections.max(obj.getX()) + (this.getXDimension() / 2 + 1));
			}
			else {
				this.setIntendedX(Collections.min(obj.getX()) - (this.getXDimension() / 2 + 1));
			}
		}
	}
	
	/**
	 * Handles Y Collision with and object with same or higher hardness
	 * @param obj object the y collision is with
	 * @param yDirection y direction in which the current object moves
	 */
	protected void handleYCollision(StreetObject obj, int yDirection) {
		
		if (!this.isInsideYDimension(obj.getY())) {
			if (Math.signum(yDirection) < 0) {
				this.setIntendedY(Collections.max(obj.getY()) + (this.getYDimension() / 2 + 1));
			}
			else {
				this.setIntendedY(Collections.min(obj.getY()) - (this.getYDimension() / 2 + 1));
			}
		}
	}
	
	/**
	 * Manages the x and y borders of the street for moving street objects
	 * Objects leaving the street on the right or left reappear at the other site
	 * Objects leaving in y direction get deleted from the model. If the object is a frog it will get added to the saved frogs count
	 */
	private void manageBorders() {
		
		this.setIntendedX(this.getModel().moduloCircleX(this.getIntendedX()));
		
		if ((this.getIntendedY() > this.getModel().getWidth()) || (this.getIntendedY() < 0)) {
			
			if (this instanceof Frog) {
				this.getModel().incrementSavedFrogs();
			}
			
			this.setDeleted(true);
		}
	}
	
	/**
	 * @return velocity velocity of the moving object
	 */
	public float getVelocity() {
		return velocity;
	}

	/**
	 * @param velocity sets the velocity of the moving object
	 */
	public void setVelocity(float velocity) throws Exception{
		if (velocity >= 0) {
			this.velocity = velocity;
		}
		else {
			throw new ArithmeticException("Velocity should be positive or null");
		}
	}

	/**
	 * @return intendedX intended x center position after movement
	 */
	public int getIntendedX() {
		return intendedX;
	}

	/**
	 * @param intendedX sets intended x center position after movement
	 */
	public void setIntendedX(int intendedX) {
		this.intendedX = intendedX;
	}

	/**
	 * @return intendedY intended y center position after movement
	 */
	public int getIntendedY() {
		return intendedY;
	}

	/**
	 * @param intendedY sets intended y center position after movement
	 */
	public void setIntendedY(int intendedY) {
		this.intendedY = intendedY;
	}

	/**
	 * @return xSlowedDown returns if the object wasn't able to move at full speed at the previous movement
	 */
	public boolean isxSlowedDown() {
		return this.xSlowedDown;
	}

	/**
	 * @param xSlowedDown sets if the object wasn't able to move at full speed at the previous movement
	 */
	public void setxSlowedDown(boolean xSlowedDown) {
		this.xSlowedDown = xSlowedDown;
	}
	
}
