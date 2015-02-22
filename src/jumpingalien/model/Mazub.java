package jumpingalien.model;

import be.kuleuven.cs.som.annotate.Basic;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;

public class Mazub {
	private Sprite[] sprites;
	private Sprite currentSprite;
	
	private final double vxInit;
	private final double vxMax;
	private final double vxMaxDuck = 1;
	
	private boolean isMoving = false, isJumping = false, isDucking = false;
	private double currentTime = 0;
	private double startMovingTime = 0;
	private double endMovingTime = 0;
	
	// Speed and position use meters.
	private Vector2D<Double> speed, position;
	private double facing;
	private static final Vector2D<Double> acceleration = new Vector2D<>(0.9, -10.0);
	
	public Mazub(double x, double y, Sprite[] sprites, double vxInit, double vxMax, double direction){
		this.position = new Vector2D<>(x, y);
		this.speed = new Vector2D<>(0.0, 0.0);
		this.sprites = sprites;
		this.currentSprite = sprites[0];
		this.vxInit = vxInit;
		this.vxMax = vxMax;
		this.setFacing(direction);
	}
	
	@Basic
	public Sprite getCurrentSprite(){
		return currentSprite;
	}
	
	@Basic
	public Vector2D<Double> getCurrentSpeed(){
		return this.speed;
	}
	
	@Basic
	public Vector2D<Double> getCurrentPosition(){
		return this.position;
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
	 * Totally
	 */
	@Basic
	public void setFacing(double facing) {
		this.facing = facing == 0 ? 1 : facing / Math.abs(facing);
	}
	
	
	/**
	 * @param dt
	 * 			The time that has passed in the game world since last calling this method.
	 * @throws	IllegalArgumentException
	 * 			| (dt < 0) || (dt > 0.2)
	 */
	public void advanceTime(double dt) throws IllegalArgumentException{
		if (dt > 0.2){
			throw new IllegalArgumentException("Delta time may not exceed 0.2s.");
		}
		if (dt < 0){
			throw new IllegalArgumentException("Delta time has to be non-negative.");
		}
		
		//TODO Account for overflow somehow?
		this.currentTime += dt;
		
		updateMovement(dt);
		
		//currentSprite gets determined AFTER Mazub's state has been updated
		this.determineCurrentSprite();
	}

	private void updateMovement(double dt) {
		Vector2D<Double> acc = getAcceleration();
		
		this.position.x += this.speed.x * dt + acc.x * dt * dt / 2.0;
		this.speed.x += acc.x * dt;
		if (this.position.x < 0) {
			this.position.x = 0.0;
		} else if (this.position.x >= 10.24) {
			this.position.x = 10.23;
		}
		if (this.speed.x <= -this.getMaxHorizontalSpeed()) {
			this.speed.x = -this.getMaxHorizontalSpeed();
		} else if (this.speed.x >= this.getMaxHorizontalSpeed()) {
			this.speed.x = this.getMaxHorizontalSpeed();
		}
		
		this.position.y += this.speed.y*dt + acc.y * dt * dt / 2.0;
		this.speed.y += acc.y * dt;
		if (this.position.y <= 0){
			this.position.y = 0.0;
			this.speed.y = 0.0;
			if (isJumping){
				endJump();
			}
		}
		
	}

	private void determineCurrentSprite() {
		//TODO Check of 'm' klopt
		int m = (sprites.length - 9) / 2;
		double timeSinceMoving = this.currentTime - this.endMovingTime;
		boolean recentlyMoved = timeSinceMoving < 1;
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
			int spriteIndex = ((int)((this.currentTime - this.startMovingTime)/0.075)) % m;
			this.currentSprite = this.getFacing() == 1 ?
								 this.sprites[8+spriteIndex] : this.sprites[9+m+spriteIndex];
		}
	}
	
	@Basic
	public int getHeight(){
		return this.currentSprite.getHeight();
	}

	@Basic
	public int getWidth(){
		return this.currentSprite.getWidth();
	}
	
	/**
	 * Starts this Mazub's movement in the given direction.
	 * Nominally
	 * 
	 * @param direction
	 * 			The direction to start moving in.
	 */
	public void startMove(double direction) {
		this.isMoving = true;
		this.setFacing(direction);
		this.speed.x = direction * this.vxInit;
		this.startMovingTime = this.currentTime;
	}
	
	/**
	 * Ends this Mazub's movement.
	 */
	public void endMove() {
		this.isMoving = false;
		this.endMovingTime = this.currentTime;
		this.speed.x = 0.0;
	}
	
	
	/**
	 * Starts the jump of this Mazub.
	 * Defensively
	 */
	public void startJump() {
		if (this.onGround() && !isJumping){
			this.isJumping = true;
			this.speed.y = 8.0;
		}
	}
	
	/**
	 * Ends the jump of this Mazub.
	 * Defensively
	 */
	public void endJump() {
		this.isJumping = false;
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
	
	public double getMaxHorizontalSpeed(){
		return this.isDucking ? this.vxMaxDuck : this.vxMax;
	}
	
	public boolean onGround(){
		return Util.fuzzyEquals(this.position.y, 0.0);
	}
}
