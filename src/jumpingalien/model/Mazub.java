package jumpingalien.model;

import org.junit.experimental.theories.Theory;

import be.kuleuven.cs.som.annotate.Basic;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;

/**
 * A class representing a single Mazub.
 * 
 * @invar Mazub's bottom-left position remains within the bounds of the world.
 * 			| Mazub.isValidPosition(this.getPosition())
 * 
 * @invar Mazub's horizontal speed does not exceed the maximum speed.
 * 			| Mazub.isValidSpeed(this.getSpeed())
 * 
 * @author Rugen Heidbuchel & Menno Vanfrachem
 * @version 1.0
 */
public class Mazub {
	private Sprite[] sprites;
	private Sprite currentSprite;
	
	private final double vxInit;
	private final double vxMax;
	private final double vxMaxDuck = 1;
	
	private boolean isMoving = false, isDucking = false, hasMoved = false;
	private double movingTime = 0;
	private double timeSinceMoving = 0;
	
	private Vector2D<Double> speed, position;
	private double facing;
	private static final Vector2D<Double> acceleration = new Vector2D<>(0.9, -10.0);
	
	private static final Vector2D<Double> bounds = new Vector2D<>(10.24, 7.68);
	
	public Mazub(double x, double y, Sprite[] sprites, double vxInit, double vxMax, double direction) {
		this.setPosition(new Vector2D<>(x, y));
		this.setSpeed(new Vector2D<>(0.0, 0.0));
		this.sprites = sprites;
		this.setCurrentSprite(sprites[0]);
		this.vxInit = vxInit;
		this.vxMax = vxMax;
		this.setFacing(direction);
	}
	
	/**
	 * @param pos
	 * @return Whether pos is valid (is inside the bounds of the world)
	 * 			| (pos.x >= 0) && (pos.x <= bounds.x)
	 *			 && (pos.y >= 0) && (pos.y <= bounds.y)
	 */
	public static boolean isValidPosition(Vector2D<Double> pos){
		return (pos.x >= 0) && (pos.x <= bounds.x)
			&& (pos.y >= 0) && (pos.y <= bounds.y);
	}
	
	
	/**
	 * @param speed
	 * @return Whether speed.x's magnitude doesn't exceed the maximum horizontal speed.
	 * 			| Math.abs(speed.x) < this.getMaxHorizontalSpeed()
	 */
	public boolean isValidSpeed(Vector2D<Double> speed){
		return Math.abs(speed.x) < this.getMaxHorizontalSpeed();
	}
	/**
	 * Returns this Mazub's current sprite.
	 * 
	 * @return This Mazub's current sprite as a Sprite.
	 */
	@Basic
	public Sprite getCurrentSprite() {
		return currentSprite;
	}
	
	/**
	 * Sets this Mazub's current sprite to the given sprite.
	 * 
	 * @param currentSprite
	 * 			The current sprite to set.
	 */
	@Basic
	private void setCurrentSprite(Sprite currentSprite) {
		this.currentSprite = currentSprite;
	}
	
	/**
	 * @return The maximum horizontal speed of this Mazub.
	 */
	private double getMaxHorizontalSpeed(){
		return this.isDucking ? this.vxMaxDuck : this.vxMax;
	}
	
	/**
	 * Returns this Mazub's speed in meters/second.
	 * 
	 * @return This Mazub's speed as a 2D vector.
	 */
	@Basic
	public Vector2D<Double> getSpeed(){
		return this.speed;
	}
	
	/**
	 * Sets this Mazub's speed to the given speed.
	 * 
	 * @param speed
	 * 			The speed to set.
	 */
	@Basic
	private void setSpeed(Vector2D<Double> speed) {
		this.speed = speed;
	}
	
	/**
	 * @return A 2-dimensional vector of this Mzaub's acceleration in m/s.
	 */
	public Vector2D<Double> getAcceleration(){
		Vector2D<Double> acc = new Vector2D<>(0.0, 0.0);
		if (isMoving){
			acc.x = this.getFacing() * Mazub.acceleration.x;
		}
		if (!onGround()){
			acc.y = Mazub.acceleration.y;
		}
		return acc;
	}
	
	/**
	 * Returns this Mazub's position in meters.
	 * 
	 * @return This Mazub's position as a 2D vector.
	 */
	@Basic
	public Vector2D<Double> getPosition(){
		return this.position;
	}
	
	/**
	 * Sets this Mazub's position to the given position.
	 * 
	 * @param position
	 * 			The position to set.
	 */
	@Basic
	private void setPosition(Vector2D<Double> position) {
		this.position = position;
	}
	
	/**
	 * Returns the facing of this Mazub.
	 * 
	 * @return The facing of this Mazub. This is either 1.0 if it's facing right or -1.0 if it's facing left.
	 */
	@Basic
	public double getFacing() {
		return this.facing;
	}
	
	/**
	 * Sets this Mazub's facing to -1.0 if facing < 0 (left facing) or to 1.0 if facing >= 0 (right facing).
	 * 
	 * @param facing
	 * 			The facing to set.
	 * 
	 * @post	If facing is negative, this Mazub's facing is set to -1.0.
	 * 			| if (facing < 0)
	 * 			|	new.facing == -1
	 * 
	 * @post	If facing is not negative, this Mazub's facing is set to 1.0.
	 * 			| if (facing >= 0)
	 * 			|	new.facing == 1
	 * 
	 * @throws IllegalArgumentException
	 * 
	 * Nominally
	 */
	@Basic
	public void setFacing(double facing) throws IllegalArgumentException {
		if (!(facing == -1 || facing == 1)) {
			throw new IllegalArgumentException("Facing should be -1 or 1.");
		}
		this.facing = facing;
	}
	
	/**
	 * Returns this Mazub's height in pixels.
	 * 
	 * @return	This Mazub's height in pixels.
	 */
	@Basic
	public int getHeight(){
		return this.currentSprite.getHeight();
	}

	/**
	 * Returns this Mazub's width in pixels.
	 * 
	 * @return	This Mazub's width in pixels.
	 */
	@Basic
	public int getWidth(){
		return this.currentSprite.getWidth();
	}
	
	/**
	 * @return Returns whether this Mazub is touching the ground or not.
	 * 			| if (this.getCurrentPosition().y == 0.0)
	 * 			| then true
	 * 			| else false
	 */
	public boolean onGround(){
		return Util.fuzzyEquals(this.position.y, 0.0);
	}
	
	
	
	
	/**
	 * @param dt
	 * 			The time that has passed in the game world since last calling this method.
	 * 
	 * @throws	IllegalArgumentException
	 * 			| (dt < 0) || (dt > 0.2)
	 */
	public void advanceTime(double dt) throws IllegalArgumentException{
		
		// check for exceptions
		if (Double.isNaN(dt)) {
			throw new IllegalArgumentException("Delta time can not be NaN.");
		}
		if (dt > 0.2){
			throw new IllegalArgumentException("Delta time may not exceed 0.2s.");
		}
		if (dt < 0){
			throw new IllegalArgumentException("Delta time has to be non-negative.");
		}
		
		// update times
		if (isMoving) {
			this.movingTime += dt;
		} else {
			this.timeSinceMoving += dt;
		}
		
		this.updateMovement(dt);
		
		//currentSprite gets determined AFTER Mazub's state has been updated
		this.determineCurrentSprite();
	}

	/**
	 * Updates this Mazub's position and speed using the given time interval.
	 * 
	 * @param dt
	 * 			The passed time interval since the last update in seconds.
	 */
	private void updateMovement(double dt) {
		
		// Set some variables so we need to write less. The variables are references because Vector2D is a class, so setting also works.
		Vector2D<Double> acc = this.getAcceleration();
		Vector2D<Double> speed = this.getSpeed();
		Vector2D<Double> position = this.getPosition();
		
		// Update x for position and speed
		position.x += speed.x * dt + acc.x * dt * dt / 2.0;
		speed.x += acc.x * dt;
		
		// Keep x position in bounds
		position.x = clipInRange(0, bounds.x, position.x);
		
		// Keep horizontal speed in bounds
		speed.x = clipInRange(-this.getMaxHorizontalSpeed(),
										this.getMaxHorizontalSpeed(),
										speed.x);
		
		position.y += speed.y * dt + acc.y * dt * dt / 2.0;
		speed.y += acc.y * dt;
		
		// Keep y position in bounds
		position.y = clipInRange(0, bounds.y, position.y);
		
	}
	
	
	/**
	 * Clips the value to the given range.
	 * 
	 * @param min
	 * 			The minimum of the range.
	 * 
	 * @param max
	 * 			The maximum of the range.
	 * 
	 * @param value
	 * 			The value to clip.
	 * 
	 * @return	The value clipped to the given range. If it's bigger than max, max will be returned. If it's smaller than min, min will be returned.
	 * 			| if (value < min)
	 * 			|	return min
	 * 			| if (value > max)
	 * 			|	return max
	 * 			| else
	 * 			|	return value
	 * 
	 * @pre		Max should be bigger than min.
	 * 			| max > min
	 */
	private static double clipInRange(double min, double max, double value) {
		
		assert max > min;
		
		if (value < min) {
			return min;
		} else if (value > max) {
			return max;
		}
		
		return value;
	}

	private void determineCurrentSprite() {
		
		int m = (sprites.length - 9) / 2;
		boolean recentlyMoved = timeSinceMoving < 1 && this.hasMoved;
		if (!(isMoving || isDucking)){
			if (!recentlyMoved){
				this.currentSprite = this.sprites[0];
			}
			else{
				this.currentSprite = this.getFacing() == 1 ?
									 this.sprites[2] : this.sprites[3];
			}
		}
		if (!isMoving && isDucking && (!recentlyMoved)){
			this.currentSprite = this.sprites[1];
		}
		if (isMoving && ! onGround() && !isDucking){
			this.currentSprite = this.getFacing() == 1 ?
								 this.sprites[4] : this.sprites[5];
		}
		if (isDucking && (isMoving || recentlyMoved)){
			this.currentSprite = this.getFacing() == 1 ?
								 this.sprites[6] : this.sprites[7];
		}
		if (!(! onGround() || isDucking) && isMoving){
			int spriteIndex = ((int)(this.movingTime/0.075)) % m;
			this.currentSprite = this.getFacing() == 1 ?
								 this.sprites[8+spriteIndex] : this.sprites[9+m+spriteIndex];
		}
	}
	
	/**
	 * Starts this Mazub's movement in the given direction.
	 * Nominally
	 * 
	 * @param direction
	 * 			The direction to start moving in.
	 * 
	 * @pre		Direction should be -1 or 1.
	 * 			| direction == -1 || direction == 1
	 * 
	 * @post	The horizontal speed shall be set to +- vxInit.
	 * 			| new.getSpeed().x == direction * this.vxInit
	 * 
	 * @post	isMoving shall be set to true.
	 * 			| new.isMoving == true
	 */
	public void startMove(double direction) {
		assert direction == -1 || direction == 1;
		this.isMoving = true;
		this.setFacing(direction);
		this.speed.x = direction * this.vxInit;
		this.movingTime = 0;
	}
	
	/**
	 * Ends this Mazub's movement.
	 */
	public void endMove() {
		this.isMoving = false;
		this.speed.x = 0.0;
		this.hasMoved = true;
		this.timeSinceMoving = 0;
	}
	
	
	/**
	 * Starts the jump of this Mazub.
	 * 
	 * @pre		This Mazub should not be in the air.
	 * 			| this.onGround()
	 */
	public void startJump() {
		this.speed.y = 8.0;
	}
	
	/**
	 * Ends the jump of this Mazub.
	 * 
	 * @pre		The vertical speed of this Mazub should be bigger than 0.
	 * 			| this.speed.y > 0
	 * 
	 * @post	The vertical speed of this Mazub will be 0.
	 * 			| new.speed.y == 0
	 */
	public void endJump() {
		if (this.speed.y > 0) {
			this.speed.y = 0.0;
		}
	}
	
	
	/**
	 * Defensively
	 */
	public void startDuck() {
		this.isDucking = true;
	}
	
	/**
	 * Defensively
	 */
	public void endDuck() {
		this.isDucking = false;
	}
}
