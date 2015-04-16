package jumpingalien.model;

import java.util.HashSet;
import java.util.Set;

import jumpingalien.util.Sprite;
import jumpingalien.util.Util;
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
	
	private boolean isTerminated;

	private final Sprite[] sprites;
	private Sprite currentSprite;
	private Motion motion;
	private double facing;

	private final int maxHealth;
	private int health;
	
	private double deathTime = 0.0;
	
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
		
		if (!this.isValidPosition(position)){
			throw new IllegalArgumentException("Position is not valid.");
		}
		
		// maxHealth has to be set before setHealth because it uses maxHealth.
		this.setFacing(1);
		this.maxHealth = maxHealth;
		this.health = 1;
		this.setHealth(health);
		this.sprites = sprites;
		this.setCurrentSprite(sprites[0]);
		this.motion = new Motion(this, position, new Vector<Double>(0.0, 0.0), new Vector<Double>(0.0, 0.0));
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
	 * @return Whether the object is alive or not. The object is considered dead when it's health
	 * 		   is zero and has been zero for longer than Constants.deathTime.
	 * 			| !((this.isHealthZero()) && (this.deathTime > Constants.deathTime))
	 */
	public boolean isAlive() {
		return !(this.isHealthZero() && (this.deathTime >= Constants.deathTime));
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
	 * 		 otherwise it sets it to the maximum allowed amount. If the health of this object is equal to zero
	 * 		 then setHealth does nothing.
	 * 			| if (!this.isValidHealth(health) && (this.getHealth() != 0))
	 * 			| then new.getHealth() == health
	 * 			| else if (health > this.getMaximumHealth()
	 * 			|      then new.getHealth() == this.getMaximumHealth()
	 * 			|	   else new.getHealth() == 0
	 */
	public void setHealth(int health){
		if (! this.isHealthZero()){
			this.health = Utilities.clipInRange(0, this.getMaximumHealth(), health);
		}
	}
	

	/**
	 * @return This GameObject's position as a 2D vector in meters.
	 */
	@Basic
	public Vector<Double> getPositionInMeters() {
		return this.motion.getPosition();
	}


	/**
	 * @return This GameObject's position in pixels.
	 */
	@Basic
	public Vector<Integer> getPosition() {
		return Utilities.metersVectorToPixels(this.getPositionInMeters());
	}


	/**
	 * Sets this game object's position to the given position in meters.
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
				
		this.motion.setPosition(position);
	}
	
	
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
			return (pos.x >= 0) && pos.x < this.getWorld().getSizeInMeters().x
					&& (pos.y >= 0) && pos.y < this.getWorld().getSizeInMeters().y;
		}
		return (pos.x >= 0) && (pos.y >= 0);
	}
	
	
	/**
	 * @return Whether this game object is standing on impassable terrain.
	 */
	public boolean onGround() {
		
		//TODO: Mazub should be able to fall through the ground and die.
		
		//TODO: Test this method
		if (Util.fuzzyEquals(this.getPosition().y, 0.0)) {
			return true;
		}
		
		Set<Tile> collidingTiles = world.getTilesCollidingWithObject(this);
		
		for (Tile tile : collidingTiles) {
			if (!tile.getType().passable) {
				Vector<Integer> tilePos = tile.getPosition();
				int tileSize = this.getWorld().getTileSize();
				Vector<OverlapDirection> overlapDir = this.getWorld().getKindOfOverlap(
						this.getPosition(), Vector.add(this.getPosition(), this.getSize()),
						tilePos, Vector.add(tilePos, new Vector<>(tileSize, tileSize)));
				
				if (overlapDir.y == OverlapDirection.LOW || overlapDir.y == OverlapDirection.NONE) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	
	/**
	 * @return A set of classes with which the game object can collide.
	 */
	@Immutable
	protected abstract Set<Class<? extends GameObject>> getCollidableObjectClasses();
	
	
	/**
	 * Returns whether collisions for this game object should be detected
	 * for the given object class.
	 * 
	 * @param object
	 * 			The object to check.
	 * 
	 * @return Whether or not the object should collide with the given object class.
	 */
	@Basic
	public boolean collidesWithGameObjectClass(Class<? extends GameObject> objectClass) {
		
		return this.getCollidableObjectClasses().contains(objectClass);
	}
	
	
	/**
	 * @return A set of tile types the game object can collide with.
	 */
	@Immutable
	protected abstract Set<TileType> getCollidableTileTypes();
	
	
	/**
	 * Returns whether collisions for this game object should be detected
	 * for the given tile type.
	 * 
	 * @param type
	 * 			The tile type to check.
	 * 
	 * @return Whether or not the object should collide with the given tile type.
	 */
	@Basic
	public boolean collidesWithTileType(TileType type) {
		
		return this.getCollidableTileTypes().contains(type);
	}
	
	
	/**
	 * @return The speed of this game object in m/s.
	 */
	@Basic
	public Vector<Double> getSpeed() {
		
		return this.motion.getSpeed();
	}
	
	
	/**
	 * Sets the speed of this game object.
	 * 
	 * @param speed
	 * 			The speed to set.
	 * 
	 * @post The speed of this game object will be the given speed.
	 * 			| new.getSpeed() == speed
	 */
	@Basic
	protected void setSpeed(Vector<Double> speed) {
		
		this.motion.setSpeed(speed);
	}
	
	
	/**
	 * @return The acceleration of this game object in m/(s^2).
	 */
	@Basic
	public Vector<Double> getAcceleration() {
		
		return this.motion.getAcceleration();
	}
	
	
	/**
	 * Sets the acceleration of this game object.
	 * 
	 * @param acceleration
	 * 			The acceleration to set.
	 * 
	 * @post The acceleration of this game object will be the given acceleration.
	 * 			| new.getAcceleration() == acceleration
	 */
	@Basic
	public void setAcceleration(Vector<Double> acceleration) {
		
		this.motion.setAcceleration(acceleration);
	}


	/**
	 * @return The facing of this GameObject This is either 1.0 if it's facing right or -1.0 if it's facing left.
	 */
	@Basic
	public double getFacing() {
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
	

	@Basic
	protected Sprite[] getSprites(){
		return this.sprites;
	}


	/**
	 * @return This GameObject's current sprite.
	 */
	@Basic
	public Sprite getCurrentSprite() {
		
		return currentSprite;
	}
	
	
	/**
	 * Set the current sprite of this game object.
	 * 
	 * @param s
	 * 			The sprite to set.
	 * 
	 * @post This game object will have the given sprite as it's current sprite.
	 * 			| new.getCurrentSprite() == s
	 */
	@Basic
	protected void setCurrentSprite(Sprite s){
		this.currentSprite = s;
	}
	
	
	/**
	 * Determines and sets the new currentSprite of this game object.
	 * The standard implementation takes sprite 0 for left facing and
	 * sprite 1 for right facing.
	 */
	protected void determineCurrentSprite() {
		this.setCurrentSprite(this.getSprites()[this.getFacing() == 1.0 ? 1 : 0]);
	}
	
	
	/**
	 * @return The size of this game object in pixels.
	 */
	@Basic
	public Vector<Integer> getSize() {
		
		Sprite sprite = this.getCurrentSprite();
		return new Vector<Integer>(sprite.getWidth(), sprite.getHeight());
	}
	
	
	/**
	 * @return The size of this game object in meters.
	 */
	public Vector<Double> getSizeInMeters() {
		return Utilities.pixelsVectorToMeters(this.getSize());
	}
	
	
	/**
	 * Advances the time of this game object and adjusts it's position,
	 * speed and acceleration accordingly. Small steps will be performed
	 * to handle collisions as well.
	 * 
	 * @param dt
	 * 			The time to advance.
	 */
	public void advanceTime(double dt) {
		
		double time = 0.0;
		while (time < dt) {
			
			double stepTime = this.motion.step(dt - time);
			time += stepTime;
			
			this.handleStep(stepTime);
			
			if (this.isHealthZero()) {
				deathTime += stepTime;
			}
			
			Set<GameObject> collidingObjects = this.getWorld().getObjectsCollidingWithObject(this);
			Set<Tile> collidingTiles = this.getWorld().getTilesCollidingWithObject(this);
			
			this.handleTerrain(collidingTiles);
			this.handleCollisions(collidingObjects, collidingTiles);
			this.determineCurrentSprite();
		}
	}
	
	
	/**
	 * Handles the basic collisions with terrain.
	 * 
	 * @param collidingTiles
	 * 			The tiles with which the game object is colliding.
	 */
	private void handleTerrain(Set<Tile> collidingTiles) {
		
		Set<Tile> hardOnes = new HashSet<Tile>();
		
		for (Tile tile: collidingTiles) {
			if (!tile.getType().passable) {
				
				if (!correctOverlapWithTile(tile)) {
					System.out.println("Incorrect overlap detected...");
					continue;
				}
				
				Vector<Integer> overlap = getKindOfOverlapWithTile(tile);
				
				if (overlap.x == 0 || overlap.y == 0) {
					continue;
				}
				
				if (Math.abs(overlap.x) == 1 && Math.abs(overlap.y) == 1) {
					hardOnes.add(tile);
					continue;
				}
				
//				System.out.println();
//				System.out.println(overlap.x);
//				System.out.println(overlap.y);
				
				if (Math.abs(overlap.x) == 1) {
					this.setPosition(this.getPositionInMeters().addX(overlap.x * Constants.metersPerPixel));
					this.setSpeed(this.getSpeed().setX(0.0));
				} else if (Math.abs(overlap.y) == 1 || overlap.y == 2) {
					int correction = (overlap.y == 1 || overlap.y == 2) ? -1 : 0;
					this.setPosition(this.getPositionInMeters().addY((overlap.y + correction) * Constants.metersPerPixel));
					this.setSpeed(this.getSpeed().setY(0.0));
				} else {
					System.out.println("this is fucked up...");
				}
			}
		}
		
//		for (Tile tile : collidingTiles) {
//			
//			if (!tile.getType().passable) {
//				
//				Vector<OverlapDirection> overlapDir = this.getWorld().getKindOfOverlap(
//						this.getPosition(), Vector.add(this.getPosition(), this.getSize()),
//						tile.getPositionInPixels(), Vector.add(tile.getPositionInPixels(),
//								new Vector<>(this.getWorld().getTileSize(), this.getWorld().getTileSize())));
//				
//				if (overlapDir.y == OverlapDirection.HIGH) {
//					this.setPosition(this.getPositionInMeters().setY(tile.getPositionInMeters().y - this.getSizeInMeters().y));
//					this.setSpeed(this.getSpeed().setY(0.0));
//				}
//				
//				if ((overlapDir.x == OverlapDirection.LOW) && (tile.getPositionInPixels().y - this.getPosition().y > 1)) {
//					this.setPosition(this.getPositionInMeters().setX(tile.getPositionInMeters().x + this.getWorld().getTileSizeInMeters()));
//					this.setSpeed(this.getSpeed().setX(0.0));
//					this.setAcceleration(this.getAcceleration().setX(0.0));
//				}
//				
//				if ((overlapDir.x == OverlapDirection.HIGH) && (tile.getPositionInPixels().y - this.getPosition().y > 1)) {
//					this.setPosition(this.getPositionInMeters().setX(tile.getPositionInMeters().x - this.getSizeInMeters().x));
//					this.setSpeed(this.getSpeed().setX(0.0));
//					this.setAcceleration(this.getAcceleration().setX(0.0));
//				}
//				
//				if (overlapDir.y == OverlapDirection.LOW) {
//					this.setPosition(this.getPositionInMeters().setY(tile.getPositionInMeters().y + this.getWorld().getTileSizeInMeters() - Constants.metersPerPixel));
//					this.setSpeed(this.getSpeed().setY(0.0));
//					this.setAcceleration(this.getAcceleration().setY(0.0));
//				}
//			}
//		}
	}
	
	
	/**
	 * The position of the center of this gameObject in pixels based on it's position and it's dimensions.
	 * If the calculated pixel value isn't an integer, the x and y components are floored.
	 * @return The position of the center of this GameObject.
	 */
	public Vector<Integer> getCenterInPixels(){
		Vector<Integer> size = this.getSize();
		return Vector.add(this.getPosition(), new Vector<Integer>((int)(size.x * 0.5), (int)(size.y * 0.5)));
	}
	
	/**
	 * Handles a time step of dt and updates properties accordingly.
	 * 
	 * @param dt
	 * 			The length of the time step.
	 */
	protected abstract void handleStep(double dt);
	
	
	/**
	 * Handles the collisions for this game object and updates motion and other properties accordingly.
	 * 
	 * @param collidingObjects
	 * 			The objects this game object collides with.
	 * 
	 * @param collidingTiles
	 * 			The tiles this game object collides with.
	 */
	protected abstract void handleCollisions(Set<GameObject> collidingObjects, Set<Tile> collidingTiles);
	
	
	/**
	 * Returns a 2D vector representing the kind of overlap of the given tile
	 * with this game object. When the overlap comes from lower coördinates
	 * (on the left or on the bottom) a positive overlap value is returned.
	 * When the overlap comes from higher coördinates (on the right and on 
	 * the top) a negative value is returned.
	 * 
	 * @param tile
	 * 			The tile to get the kind of overlap with.
	 * 
	 * @return A 2D vector representing the kind of overlap.
	 */
	private Vector<Integer> getKindOfOverlapWithTile(Tile tile) {
		
		Vector<Integer> tilePos = tile.getPositionInPixels();
		Vector<Integer> selfPos = this.getPosition();
		int tileSize = tile.getSize();
		Vector<Integer> selfSize = this.getSize();
		
		Vector<Integer> overlap = new Vector<>(0, 0);
		
		if (!correctOverlapWithTile(tile)) {
			return overlap;
		}
		
		if (tilePos.x <= selfPos.x) {
			overlap = overlap.setX(Math.min(selfSize.x, tilePos.x + tileSize - selfPos.x));
		} else {
			overlap = overlap.setX(Math.min(selfSize.x, tilePos.x - selfPos.x - selfSize.x));
		}
		
		if (tilePos.y <= selfPos.y) {
			overlap = overlap.setY(Math.min(selfSize.y, tilePos.y + tileSize - selfPos.y));
		} else {
			overlap = overlap.setY(Math.min(selfSize.y, tilePos.y - selfPos.y - selfSize.y));
		}
		
		return overlap;
	}
	
	
	/**
	 * Returns whether this game object overlaps correctly with the given tile
	 * for terrain handling calculations. This means the tile can only
	 * overlap with the outer border op pixels of the game object because
	 * the time steps make sure steps of one pixel are taken.
	 * 
	 * @param tile
	 * 			The tile to test overlapping with.
	 * 
	 * @return True if this game object overlaps correctly with the given tile.
	 */
	private boolean correctOverlapWithTile(Tile tile) {
		
		//TODO: Implement this.
		
		return true;
	}
	
	/**
	 * Returns whether this object's health is equal to zero.
	 * 
	 * @return Whether this object's health is equal to zero.
	 */
	public boolean isHealthZero(){
		return this.getHealth() == 0;
	}
}
