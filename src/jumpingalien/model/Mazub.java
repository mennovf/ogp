package jumpingalien.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import be.kuleuven.cs.som.annotate.*;
import jumpingalien.util.Sprite;
import jumpingalien.model.Constants;
import jumpingalien.model.Utilities;
import jumpingalien.model.Reactions.GameObjectCollisionDamager;
import jumpingalien.model.Reactions.TerrainCollisionDamager;
import jumpingalien.model.Reactions.TerrainDamageInfo;

/**
 * A class representing a single Mazub.
 * 
 * @invar Mazub's horizontal speed does not exceed the maximum speed.
 * 			| Mazub.isValidSpeed(this.getSpeed())
 * 
 * @author Rugen Heidbuchel & Menno Vanfrachem
 * @version 1.0
 */
public class Mazub extends GameObject {
	
	/**
	 * The horizontal speed Mazub gets when he start
	 * moving.
	 */
	private final double vxInit;
	
	/**
	 * Mazub's maximum horizontal speed when he's not
	 * ducking.
	 */
	private final double vxMax;
	
	
	/**
	 * A boolean to indicate Mazub is moving.
	 */
	private boolean isMoving = false;
	
	/**
	 * A boolean to indicate Mazub is ducking.
	 */
	private boolean isDucking = false;
	
	/**
	 * A boolean to indicate Mazub is ducking but
	 * he wants to stand up.
	 */
	private boolean wantsToStandUp = true;
	
	/**
	 * The time Mazub has been moving without stopping.
	 */
	private double movingTime = 0;
	
	/**
	 * The time since Mazub last moved.
	 */
	private double timeSinceMoving = Constants.mazubAfterMoveStayTime;
	
	
	/**
	 * The amount of time startMove is called.
	 */
	int amountOfTimesStartMoveCalled = 0;
	
	

	/**
	 * @param x
	 * 			The x position of this Mazub.
	 * 
	 * @param y
	 * 			The y position of this Mazub.
	 * 
	 * @param sprites
	 * 			An array of sprites containing Mazubs animations.
	 * 
	 * @param vxInit
	 * 			Mazubs initial speed when starting to walk.
	 * 
	 * @param vxMax
	 * 			Mazubs maximum speed while walking.
	 * 
	 * @param direction
	 * 			The direction Mazub is facing. -1 means he's facing left, +1 means he's facing right.
	 * 
	 * @post The sprites should not be null
	 * 			| sprites != null
	 * 
	 * @post The amount of sprites should be bigger than or 8 and even.
	 * 			| sprites.length > 8 && (sprites.length % 2) == 0
	 * 
	 * @throws NullPointerException
	 * 			Throws a NullPointerException when sprites is null.
	 * 			| sprites == null
	 * 
	 * @throws IllegalArgumentException
	 * 			Throws an IllegalArgumentException when the position and/or direction are not valid.
	 * 			| !Mazub.isValidPosition(new Vector<>(x, y)) || !Mazub.isValidDirection(direction)
	 */
	public Mazub(Vector<Double> position, Sprite[] sprites,
			double vxInit, double vxMax, double direction)
			throws NullPointerException, IllegalArgumentException{
		
		super(100, 500, position, sprites);
		
		if (!Mazub.isValidDirection(direction)) {
			throw new IllegalArgumentException("direction is not valid.");
		}
		assert sprites != null;
		assert sprites.length > 8;
		assert (sprites.length % 2) == 0;
		assert vxInit >= 1.0;
		assert vxMax >= vxInit;
		
		this.setCurrentSprite(sprites[0]);
		this.vxInit = vxInit;
		this.vxMax = vxMax;
		this.setFacing(direction);
		
		this.setAcceleration(this.getAcceleration().setY(Constants.gravityAcceleration));
		
		Collection<Class<? extends GameObject>> damageClasses = new HashSet<Class<? extends GameObject>>();
		damageClasses.add(Shark.class);
		damageClasses.add(Slime.class);
		this.addCollisionDamager(new GameObjectCollisionDamager(this, Constants.mazubEnemyDamage, Constants.enemyDamageInterval, damageClasses));

		Collection<Class<? extends GameObject>> plantClass = new HashSet<Class<? extends GameObject>>();
		plantClass.add(Plant.class);
		this.addCollisionDamager(new GameObjectCollisionDamager(this, Constants.mazubPlantHealthGain, 0, plantClass));

		Collection<TerrainDamageInfo> terrainInfos= new HashSet<>();
		terrainInfos.add(new TerrainDamageInfo(TileType.MAGMA, Constants.magmaDamage, 0));
		terrainInfos.add(new TerrainDamageInfo(TileType.WATER, Constants.waterDamage, Constants.terrainDamageInterval));

		this.addCollisionDamager(new TerrainCollisionDamager(this, Constants.terrainDamageInterval, terrainInfos));
	}
	
	/**
	 * @param speed
	 * 			The speed to check
	 * 
	 * @return Whether speed.x's magnitude doesn't exceed the maximum horizontal speed.
	 * 			| Math.abs(speed.x) <= this.getMaxHorizontalSpeed()
	 */
	public boolean isValidSpeed(Vector<Double> speed) {
		return Math.abs(speed.x) <= this.getMaxHorizontalSpeed();
	}
	
	
	/**
	 * @return The maximum horizontal speed of this Mazub in m/s.
	 */
	private double getMaxHorizontalSpeed(){
		return this.isDucking ? Constants.mazubMaxSpeedDucking : this.vxMax;
	}
	
	
	/**
	 * Overrides the setSpeed method of gameObject to clip the speed within the allowed range.
	 */
	@Override
	public void setSpeed(Vector<Double> speed) {
		super.setSpeed(new Vector<Double>(Utilities.clipInRange(-this.getMaxHorizontalSpeed(),
											this.getMaxHorizontalSpeed(),
											speed.x), speed.y));
	}
	
	
	/**
	 * @return	This Mazub's height in pixels.
	 */
	@Basic
	public int getHeight(){
		return this.getSize().y;
	}

	/**
	 * @return	This Mazub's width in pixels.
	 */
	@Basic
	public int getWidth(){
		return this.getSize().x;
	}
	
	
	@Override
	protected Set<Class<? extends GameObject>> getCollidableObjectClasses() {
		
		HashSet<Class<? extends GameObject>> collidables = new HashSet<Class<? extends GameObject>>();
		collidables.add(Mazub.class);
		collidables.add(Plant.class);
		collidables.add(Slime.class);
		collidables.add(Shark.class);
		
		return collidables;
	}
	
	
	@Override
	protected Set<TileType> getCollidableTileTypes() {
		
		HashSet<TileType> collidables = new HashSet<TileType>();
		collidables.add(TileType.GROUND);
		collidables.add(TileType.WATER);
		collidables.add(TileType.MAGMA);
		
		return collidables;
	}
	
	
	
	
	@Override
	protected void handleStep(double dt) {
		
		if (isDucking && wantsToStandUp && canStand()){
			this.isDucking = false;
		}
		if (isMoving) {
			this.movingTime += dt;
		} else {
			this.timeSinceMoving += dt;
		}
	}
	
	
	@Override
	protected void handleCollisions(Set<GameObject> collidingObjects,
			Set<Tile> collidingTiles) {
		
		super.handleCollisions(collidingObjects, collidingTiles);
		
		if (!this.onGround()) {
			this.setAcceleration(this.getAcceleration().setY(Constants.gravityAcceleration));
		}
	}
	
	
	/**
	 * Determines and sets the new current sprite.
	 */
	@Override
	protected Sprite determineCurrentSprite() {
		
		Sprite[] sprites = this.getSprites();
		Sprite currentSprite = this.getCurrentSprite();
		int m = (sprites.length - 8) / 2 - 1;
		boolean recentlyMoved = timeSinceMoving < Constants.mazubAfterMoveStayTime;
		if (!(isMoving || isDucking)){
			if (!recentlyMoved){
				currentSprite = sprites[0];
			}
			else{
				currentSprite = this.getFacing() == 1 ?
									 sprites[2] : sprites[3];
			}
		}
		if (!isMoving && isDucking && (!recentlyMoved)){
			currentSprite = sprites[1];
		}
		if (isMoving && ! onGround() && !isDucking){
			currentSprite = this.getFacing() == 1 ?
								 sprites[4] : sprites[5];
		}
		if (isDucking && (isMoving || recentlyMoved)){
			currentSprite = this.getFacing() == 1 ?
								 sprites[6] : sprites[7];
		}
		if (!(!this.onGround() || isDucking) && isMoving){
			int animationIndex = ((int)(this.movingTime/0.075)) % (m+1);
			currentSprite = this.getFacing() == 1 ?
								 sprites[8+animationIndex] : sprites[9+m+animationIndex];
		}
		return currentSprite;
	}
	
	/**
	 * Starts this Mazub's movement in the given direction.
	 * 
	 * @param direction
	 * 			The direction to start moving in.
	 * 
	 * @pre		Direction should be valid.
	 * 			| Mazub.isValidDirection(direction)
	 * 
	 * @post	The horizontal speed shall be set to +- vxInit.
	 * 			| new.getSpeed().x == direction * this.vxInit
	 * 
	 * @post	isMoving shall be set to true.
	 * 			| new.isMoving == true
	 */
	public void startMove(double direction) {
		assert Mazub.isValidDirection(direction);
		this.isMoving = true;
		this.setFacing(direction);
		this.setSpeed(this.getSpeed().setX(direction * this.vxInit));
		if (!this.isDucking) {
			this.setAcceleration(this.getAcceleration().setX(direction * Constants.mazubHorizontalAcceleration));
		}
		this.movingTime = 0;
		this.amountOfTimesStartMoveCalled += 1;
	}
	
	/**
	 * Ends this Mazub's movement.
	 * 
	 * @post Horizontal speed will be zero
	 * 			| new.getSpeed().x == 0
	 */
	public void endMove() {
		this.amountOfTimesStartMoveCalled -= 1;
		if (amountOfTimesStartMoveCalled == 0){
			this.isMoving = false;
			this.setSpeed(this.getSpeed().setX(0.0));
			this.setAcceleration(this.getAcceleration().setX(0.0));
			this.timeSinceMoving = 0;
		}
	}
	
	
	/**
	 * Starts the jump of this Mazub.
	 * 
	 * @post Mazub's vertical speed will be different from zero
	 * 			| new.getSpeed().y != 0
	 */
	public void startJump() {
		if (onGround()){
			this.setSpeed(this.getSpeed().setY(Constants.mazubInitialJumpSpeed));
		}
	}
	
	/**
	 * Ends the jump of this Mazub.
	 * 
	 * @post	If the speed of this Mazub is bigger than 0, the vertical speed of this Mazub will be 0.
	 * 			| if (this.getSpeed().y > 0)
	 * 			|	new.getSpeed().y == 0
	 * 
	 * @post	If the speed of this Mazub is not bigger than 0, the vertical speed will remain the same.
	 * 			| if (this.getSpeed().y <= 0)
	 * 			|	new.getSpeed().y == this.getSpeed().y
	 */
	public void endJump() {
		if (this.getSpeed().y > 0) {
			this.setSpeed(this.getSpeed().setY(0.0));
		}
	}
	
	
	/**
	 * Starts the duck of this Mazub.
	 * 
	 * @post The velocity will be smaller than or equal to the maximum duck velocity.
	 * 			| new.getSpeed().x <= this.getMaxSpeedWhileDucking()
	 */
	public void startDuck() {
		this.isDucking = true;
		this.wantsToStandUp = false;
	}
	
	/**
	 * Ends the duck of this Mazub.
	 */
	public void endDuck() {
		this.wantsToStandUp = true;
		if (this.canStand()){
			this.isDucking = false;
		}
	}

	/**
	 * Determines whether or not Mazub can stand up currently.
	 * @return Whether Mazub can stand up currently.
	 * 
	 */
	private boolean canStand() {
		//Set up for cleanup
		boolean oldIsDucking = this.isDucking;
		Sprite oldSprite = this.getCurrentSprite();

		this.isDucking = false;
		this.setCurrentSprite(this.determineCurrentSprite());
		
		Set<Collidable> collidables = new HashSet<>();
		collidables.addAll(this.getWorld().getTilesCollidingWithObject(this));
		collidables.addAll(this.getWorld().getObjectsCollidingWithObject(this));
		
		boolean canStand = true;
		for (Collidable collidable : collidables) {
			Vector<Integer> overlap = this.getKindOfOverlapWith(collidable);
			if (overlap.y < 0 && 4*Math.abs(overlap.x) > Math.abs(overlap.y)) {
				if (collidable instanceof Tile) {
					Tile tile = (Tile) collidable;
					if (tile.getType() == TileType.GROUND) {
						canStand = false;
						break;
					}
				} else {
					GameObject object = (GameObject) collidable;
					if (!object.isPassable()) {
						canStand = false;
						break;
					}
				}
			}
		}
		
		//Cleanup
		this.isDucking = oldIsDucking;
		this.setCurrentSprite(oldSprite);

		return canStand;
	}
}
