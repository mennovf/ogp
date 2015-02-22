package jumpingalien.model;

import be.kuleuven.cs.som.annotate.Basic;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;

public class Mazub {
	private Transform transform;
	private Sprite[] sprites;
	private Sprite currentSprite;
	
	private final double vxInit;
	private final double vxMax;
	private final double vxMaxDuck = 1;
	
	boolean isMoving = false, isJumping = false, isDucking = false;
	double currentTime = 0;
	double startMovingTime = 0;
	double endMovingTime = 0;
	
	// Speed and position use meters.
	private Vector2D<Double> speed, position;
	private static final Vector2D<Double> acceleration = new Vector2D<>(0.9, -10.0);
	
	public Mazub(double x, double y, Sprite[] sprites, double vxInit, double vxMax, Transform.Direction direction){
		this.position = new Vector2D<>(x, y);
		this.speed = new Vector2D<>(0.0, 0.0);
		this.transform = new Transform(x, y, direction);
		this.sprites = sprites;
		this.currentSprite = sprites[0];
		this.vxInit = vxInit;
		this.vxMax = vxMax;
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
		//TODO Add other time related stuff in here
		
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
				this.currentSprite = this.transform.facing == Transform.Direction.RIGHT ?
									 this.sprites[2] : this.sprites[3];
			}
		}
		if (!isMoving && isDucking && (!recentlyMoved)){
			this.currentSprite = this.sprites[1];
		}
		if (isMoving && ! onGround() && !isDucking){
			this.currentSprite = this.transform.facing == Transform.Direction.RIGHT ?
								 this.sprites[4] : this.sprites[5];
		}
		if (isDucking && (isMoving || recentlyMoved)){
			this.currentSprite = this.transform.facing == Transform.Direction.RIGHT ?
								 this.sprites[6] : this.sprites[7];
		}
		if (!(! onGround() || isDucking) && isMoving){
			int spriteIndex = ((int)((this.currentTime - this.startMovingTime)/0.075)) % m;
			this.currentSprite = this.transform.facing == Transform.Direction.RIGHT ?
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
	public void startMove(Transform.Direction direction) {
		this.isMoving = true;
		this.transform.facing = direction;
		this.startMovingTime = this.currentTime;
		this.speed.x = direction == Transform.Direction.RIGHT ? 1.0 : -1.0;
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
			acc.x = (transform.facing == Transform.Direction.RIGHT ? 1.0 : -1.0) * Mazub.acceleration.x;
		}
		if (!onGround()){
			acc.y = Mazub.acceleration.y;
		}
		return acc;
	}
	
	public double getMaxHorizontalSpeed(){
		return this.isDucking ? 1.0 : this.vxMax;
	}
	
	public boolean onGround(){
		return Util.fuzzyEquals(this.position.y, 0.0);
	}
}