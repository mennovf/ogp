package jumpingalien.model;

import jumpingalien.util.Sprite;
import be.kuleuven.cs.som.annotate.*;

/**
 * @author Rugen & Menno
 *
 * An abstract class representing an object in the game world.
 * This class provides common features of all the object such as health, etc.
 * 
 * @invar The health of the object is never higher than the max health.
 * 			| this.isValidHealth(this.getHealth())
 */
public abstract class GameObject {
	
	/**
	 * @param pos
	 * 			The position to check
	 * 
	 * @return Whether pos is valid (is inside the bounds of the world)
	 * 			| (pos.x >= 0) && (pos.x <= bounds.x)
	 *			 && (pos.y >= 0) && (pos.y <= bounds.y)
	 */
	public static boolean isValidPosition(Vector2D<Double> pos) {
		return (pos.x >= 0) && (pos.x <= Constants.screenSize.x/100)
			&& (pos.y >= 0) && (pos.y <= Constants.screenSize.y/100);
	}


	/**
	 * @param direction
	 * 			The direction to check
	 * 
	 * @return Whether the direction is valid (either 1 or -1)
	 * 			| direction == 1 || direction == -1
	 */
	public static boolean isValidDirection(double direction) {
		return direction == 1 || direction == -1;
	}


	private final Sprite[] sprites;
	private Vector2D<Double> position;
	private double facing;


	/**
	 * @param	m
	 * 			The value to convert from meters to pixels.
	 * 
	 * @return	m converted to pixels.
	 * 
	 * @post	m has to be positive
	 * 			| m >= 0
	 */
	private static int metersToPixels(double m) {
		return (int)(m / 0.01);
	}


	private final int maxHealth;
	private int health;
	
	
	protected GameObject(int health, int maxHealth, Sprite[] sprites) {
		this.setHealth(health);
		this.maxHealth = maxHealth;
		this.sprites = sprites;
	}
	
	
	@Basic
	public int getMaximumHealth(){
		return this.maxHealth;
	}

	@Basic
	public int getHealth(){
		return this.health;
	}

	/**
	 * @return Whether the object is alive or not.
	 * 			| this.getHealth() > 0
	 */
	public boolean isAlive() {
		return this.getHealth() > 0;
	}
	
	/**
	 * @param diff The amount with which to increase health.
	 * @effect
	 * 			| this.setHealth(this.getHealth() + diff)
	 */
	public void increaseHealth(int diff) {
		this.setHealth(this.getHealth() + diff);
	}
	
	/**
	 * @param health The health to check for validity.
	 * @return Whether the provided health does not exceed the maximum allowed.
	 * 			| (health <= this.maxHealth) && (health >= 0)
	 */
	public boolean isValidHealth(int health){
		return (health <= this.maxHealth) && (health >= 0);
	}
	
	/**
	 * @param health The suggested health for this object.
	 * @post Set this object's health to health if health is smaller than the maximum allowed health
	 * 		 otherwise it sets it to the maximum allowed amount.
	 * 			| if (!this.isValidHealth(health))
	 * 			| then new.getHealth() == health
	 * 			| else if (health > this.getMaximumHealth()
	 * 			|      then new.getHealth() == this.getMaximumHealth()
	 * 			|	   else new.getHealth() == 0
	 */
	public void setHealth(int health){
		this.health = Utilities.clipInRange(0, this.getMaximumHealth(), health);
	}
	

	/**
	 * @return This Mazub's position as a 2D vector in meters.
	 */
	@Basic
	public Vector2D<Double> getPositionInMeters() {
		return this.position;
	}


	/**
	 * @return This Mazub's position in pixels.
	 */
	@Basic
	public Vector2D<Integer> getPosition() {
		Vector2D<Double> pos = getPositionInMeters();
		return new Vector2D<Integer>(metersToPixels(pos.x), metersToPixels(pos.y));
	}


	/**
	 * Sets this Mazub's position to the given position.
	 * 
	 * @param position
	 * 			The position to set.
	 * 
	 * @throws NullPointerException
	 * 			Throws a NullPointerException when the position is null.
	 * 			| position == null
	 * 
	 * @throws IllegalArgumentException
	 * 			Throws an IllegalArgumentException when the position is not valid. See isValidPosition.
	 * 			| !isValidPosition(position)
	 */
	@Basic
	public void setPosition(Vector2D<Double> position) throws NullPointerException,
			IllegalArgumentException {
				if (position == null) {
					throw new NullPointerException("The position can not be null.");
				} else if (!isValidPosition(position)) {
					throw new IllegalArgumentException("The given position is not valid, see isValidPosition.");
				}
				
				this.position = position;
			}


	/**
	 * @return The facing of this Mazub. This is either 1.0 if it's facing right or -1.0 if it's facing left.
	 */
	@Basic
	public double getFacing() {
		return this.facing;
	}


	/**
	 * Sets this Mazub's facing.
	 * 
	 * @param facing
	 * 			The facing to set. This should be either 1 for right facing or -1 for left facing.
	 * 
	 * @pre		The facing should be valid;
	 * 			| Mazub.isValidDirection(facing)
	 * 
	 * @post	The facing will be set to the new facing
	 * 			| new.getFacing() == facing
	 */
	@Basic
	public void setFacing(double facing) {
		assert Mazub.isValidDirection(facing);
		this.facing = facing;
	}

	@Basic
	protected Sprite[] getSprites(){
		return this.sprites;
	}

	public void advanceTime(double dt) {
	}
}
