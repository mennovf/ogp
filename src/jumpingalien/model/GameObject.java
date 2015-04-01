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
	public boolean isValidPosition(Vector<Double> pos) {
		if (this.hasProperWorld()) {
			return (pos.x >= 0) && pos.x <= this.getWorld().getSizeInPixels().x
					&& (pos.y >= 0) && pos.y <= this.getWorld().getSizeInPixels().y;
		}
		return true;
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

	
	private boolean isTerminated;

	private final Sprite[] sprites;
	private Vector<Double> position;
	private double facing;

	private final int maxHealth;
	private int health;
	private Sprite currentSprite;
	
	private World world;
	
	
	/**
	 * Creates a new game object with the given health, maxHealth, position and sprites.
	 * 
	 * @param health
	 * 			The health to set.
	 * 
	 * @param maxHealth
	 * 			The max health to set.
	 * 
	 * @param position
	 * 			The position to set.
	 * 
	 * @param sprites
	 * 			The sprites to set.
	 * 
	 * @throws IllegalArgumentException
	 * 			Throws an IllegalArgumentException when the given position is not valid.
	 * 			| !isValidPosition(position)
	 */
	protected GameObject(int health, int maxHealth, Vector<Double> position, Sprite[] sprites)
				throws IllegalArgumentException {
		
		if (! this.isValidPosition(new Vector<>(position))){
			throw new IllegalArgumentException("Position is not valid.");
		}
		
		// maxHealth has to be set before setHealth because it uses maxHealth.
		this.setFacing(1);
		this.maxHealth = maxHealth;
		this.setHealth(health);
		this.sprites = sprites;
		this.setCurrentSprite(sprites[0]);
		this.setPosition(position);
	}
	
	
	/**
	 * Terminates this game object and removes it from it's game world.
	 * @post This GameObject will be terminated.
	 * 			| new.isTerminated() == true
	 */
	public void terminate() {
		this.removeFromWorld();
		this.isTerminated = true;
	}
	
	
	/**
	 * @return true if this game object is terminated.
	 */
	public boolean isTerminated() {
		return this.isTerminated;
	}
	
	
	/**
	 * @param world
	 * 			The world to check.
	 * 
	 * @return true if this game object can have the given world as it's game world.
	 * 			This game object can not be terminated.
	 * 			| !this.isTerminated()
	 * 			The given world can not be null.
	 * 			| world != null
	 * 			The given world can not be terminated.
	 * 			| !world.isTerminated()
	 */
	public boolean canHaveAsWorld(World world) {
		if (this.isTerminated() || (world == null) || world.isTerminated()) {
			return false;
		}
		return true;
	}
	
	/**
	 * @return true if this game object has a proper world.
	 * 			This game object needs to be able to have it's current world as it's game world.
	 * 			| this.canHaveAsWorld()
	 * 			This game object's world needs to contain this game object.
	 * 			| this.getWorld().containsGameObject(this)
	 */
	public boolean hasProperWorld() {
		return this.canHaveAsWorld(this.getWorld()) && this.getWorld().containsGameObject(this);
	}
	
	/**
	 * @return This game object's game world.
	 */
	@Basic
	public World getWorld() {
		return this.world;
	}
	
	/**
	 * Sets the given world as this game object's game world.
	 * 
	 * @param world
	 * 			The world to set.
	 * 
	 * @post The given world is registered as this game object's game world.
	 * 			| new.getWorld() == world
	 * 
	 * @post The given world contains this game object.
	 * 			| (new world).containsGameObject(new)
	 * 
	 * @throws IllegalArgumentException
	 * 			Throws an IllegalArgumentException if this game object can not have the given world as it's game world.
	 * 			| !this.canHaveAsWorld(world)
	 */
	public void setWorld(World world) throws IllegalArgumentException {
		if (!this.canHaveAsWorld(world)) {
			throw new IllegalArgumentException("This game object can't have the given world as it's game world.");
		}
		this.world = world;
		if (!world.containsGameObject(this)) {
			world.addGameObject(this);
		}
	}
	
	/**
	 * @return true if a world contains this game object.
	 * 			| this.getWorld() != null;
	 */
	public boolean inWorld() {
		return this.getWorld() != null && this.getWorld().containsGameObject(this);
	}
	
	/**
	 * Removes this game object from it's world if it's contained by a world.
	 * @post The GameObject will have no references to the old world and neither will the old world have a reference to this.
	 * 			| new.getWorld() == null && old.getWorld().containsGameObject(new) == false
	 */
	public void removeFromWorld() {
		try {
			this.getWorld().removeGameObject(this);
			this.world = null;
		} catch (NullPointerException exc) {
			assert !this.inWorld();
		}
	}
	
	
	/**
	 * @return The maximum amount of hitpoints this game object can have.
	 */
	@Basic @Immutable
	public int getMaximumHealth(){
		return this.maxHealth;
	}

	
	/**
	 * @return The number of hitpoints this game object has.
	 */
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
	 * @return This GameObject's position as a 2D vector in meters.
	 */
	@Basic
	public Vector<Double> getPositionInMeters() {
		return this.position;
	}


	/**
	 * @return This GameObject's position in pixels.
	 */
	@Basic
	public Vector<Integer> getPosition() {
		Vector<Double> pos = getPositionInMeters();
		return new Vector<Integer>(Utilities.metersToPixels(pos.x), Utilities.metersToPixels(pos.y));
	}


	/**
	 * Sets this GameObject's position to the given position.
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
	 * @post The new position will be equal to position
	 * 			| new.getPositionInMeters() == position
	 */
	@Basic
	public void setPosition(Vector<Double> position) throws NullPointerException,
			IllegalArgumentException {
				if (position == null) {
					throw new NullPointerException("The position can not be null.");
				} else if (!isValidPosition(position)) {
					throw new IllegalArgumentException("The given position is not valid, see isValidPosition.");
				}
				
				this.position = position;
			}


	/**
	 * @return The facing of this GameObject This is either 1.0 if it's facing right or -1.0 if it's facing left.
	 */
	@Basic
	protected double getFacing() {
		return this.facing;
	}


	/**
	 * Sets this GameObject's facing.
	 * 
	 * @param facing
	 * 			The facing to set. This should be either 1 for right facing or -1 for left facing.
	 * 
	 * @pre		The facing should be valid;
	 * 			| GameObject.isValidDirection(facing)
	 * 
	 * @post	The facing will be set to the new facing
	 * 			| new.getFacing() == facing
	 */
	@Basic
	protected void setFacing(double facing) {
		assert GameObject.isValidDirection(facing);
		this.facing = facing;
	}

	@Basic
	protected Sprite[] getSprites(){
		return this.sprites;
	}

	public abstract void advanceTime(double dt);


	/**
	 * @return This GameObject's current sprite.
	 */
	@Basic
	public Sprite getCurrentSprite() {
		return currentSprite;
	}
	
	protected void setCurrentSprite(Sprite s){
		this.currentSprite = s;
	}
}
