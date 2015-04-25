package jumpingalien.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import be.kuleuven.cs.som.annotate.*;
import jumpingalien.model.Reactions.GameObjectCollisionDamager;
import jumpingalien.model.Reactions.TerrainCollisionDamager;
import jumpingalien.model.Reactions.TerrainDamageInfo;
import jumpingalien.util.Sprite;

/**
 * A class to represent a Slime object in a certain school.
 * 
 * @author Rugen en Menno
 * 
 * @invar The slime must always have a valid school.
 * 			| this.hasProperSchool()
 * 
 * @invar See GameObject.
 */
public class Slime extends GameObject {
	
	/**
	 * The time left in the current movement period of this slime.
	 */
	private double moveTimeLeft = 0;
	
	/**
	 * The school this slime is in.
	 */
	private School school;
	
	
	/**
	 * Creates a new slime with the given positions, sprites and school.
	 * 
	 * @param position
	 * 			The position of the slime.
	 * 
	 * @param sprites
	 * 			The sprites of the slime.
	 * 
	 * @param school
	 * 			The school of the slime.
	 * 
	 * @effect Sets the school of the slime.
	 * 			| this.setSchool(school)
	 * 
	 * @effect Calls GameObject's constructor.
	 * 			| super(Constants.slimeBeginHealth, Constants.slimeMaxHealth, position, sprites)
	 */ 
	public Slime(Vector<Double> position, Sprite[] sprites, School school) throws IllegalArgumentException {
		
		super(Constants.slimeBeginHealth, Constants.slimeMaxHealth, position, sprites);
		
		this.setSchool(school);
		
		Collection<Class<? extends GameObject>> damageClasses = new HashSet<Class<? extends GameObject>>();
		damageClasses.add(Mazub.class);
		damageClasses.add(Shark.class);
		this.addCollisionDamager(new GameObjectCollisionDamager(this, Constants.slimeEnemyContactDamage, Constants.enemyDamageInterval, damageClasses));

		Collection<TerrainDamageInfo> terrainInfos= new HashSet<>();
		terrainInfos.add(new TerrainDamageInfo(TileType.MAGMA, Constants.magmaDamage, 0));
		terrainInfos.add(new TerrainDamageInfo(TileType.WATER, Constants.waterDamage, Constants.terrainDamageInterval));
		this.addCollisionDamager(new TerrainCollisionDamager(this, Constants.terrainDamageInterval, terrainInfos));
	}
	
	
	/**
	 * Returns the school of this slime.
	 * 
	 * @return The school of this slime.
	 */
	@Basic
	public School getSchool() {
		return this.school;
	}
	
	
	/**
	 * Returns whether this slime can have the given
	 * school as it's school.
	 * 
	 * @param school
	 * 			The school to check.
	 * 
	 * @return Whether this slime can have the given school as it's school.
	 * 			| school != null
	 */
	public static boolean canHaveAsSchool(School school) {
		return school != null;
	}
	
	
	/**
	 * Returns whether this slime has a proper school.
	 * 
	 * @return Whether this slime has a proper school.
	 * 			| this.canHaveAsSchool(this.getSchool())
	 */
	public boolean hasProperSchool() {
		return canHaveAsSchool(this.getSchool());
	}
	
	
	/**
	 * Sets the school of this slime.
	 * 
	 * @param school
	 * 			The school to set.
	 * 
	 * @post This slime will have the given school as it's school.
	 * 			| new.getSchool() == (new school)
	 * 
	 * @post The new school will contain this slime.
	 * 			| new.getSchool().containsSlime(new)
	 * 
	 * @post If this slime already had a school, the old school
	 * 			will no longer contain this slime.
	 * 			| !old.getSchool().containsSlime(new)
	 * 
	 * @throws IllegalArgumentException
	 * 			Throws an IllegalArgumentException when this slime can't have the given school as school.
	 */
	@Raw
	public void setSchool(School school) throws IllegalArgumentException {
		if (!canHaveAsSchool(school)) {
			throw new IllegalArgumentException("Invalid school provided.");
		}
		
		if (this.hasProperSchool()) {
			this.getSchool().removeSlime(this);
		}
		
		this.school = school;
		if (!school.containsSlime(this)) {
			school.addSlime(this);
		}
	}
	
	
	@Override
	protected Set<Class<? extends GameObject>> getCollidableObjectClasses() {
		
		HashSet<Class<? extends GameObject>> collidables = new HashSet<Class<? extends GameObject>>();
		collidables.add(Mazub.class);
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
	
	
	/**
	 * Overrides the setSpeed method of gameObject to clip the speed within the allowed range.
	 * 
	 * @post The horizontal speed of this slime (in absolute value) will not be bigger than the
	 * 			maximum allowed horizontal speed.
	 * 			| Math.abs(new.getSpeed().x) <= Constants.slimeMaxHorizontalSpeed
	 */
	@Override
	public void setSpeed(Vector<Double> speed) {
		super.setSpeed(new Vector<Double>(Utilities.clipInRange(-Constants.slimeMaxHorizontalSpeed,
											Constants.slimeMaxHorizontalSpeed,
											speed.x), speed.y));
	}
	
	
	@Override
	protected void handleStep(double dt) {
		
		if (moveTimeLeft <= 0) {
			
			this.stopMove();
			
			double direction = Math.rint(Math.random()) == 0 ? -1.0 : 1.0;
			this.startMove(direction);
			
		} else {
			
			moveTimeLeft -= dt;
		}
	}
	
	
	
	/**
	 * Causes this Slime to take lose an amount of hitpoints specified by damage.
	 * Every slime part of the school to which this Slime belongs, also loses 1 health.
	 * 
	 * @param amount
	 * 			The amount of damage taken.
	 * 
	 * @effect Lowers the health of this object by damage.
	 * 			| this.increaseHealth(damage).
	 * 
	 * @effect  Deal 1 point of damage to the slimes belonging to this slime's school.
	 * 			| this.getSchool().takeDamageCausedBy(this)
	 */
	@Override
	public void takeDamage(int amount){
		super.takeDamage(amount);
		this.getSchool().takeDamageCausedBy(this);
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
	 * Starts the movement of this slime in the given direction.
	 * 
	 * @param direction
	 * 			The direction of the movement. 1.0 for movement to the right,
	 * 			-1.0 for movement to the left.
	 */
	private void startMove(double direction) {
		
		this.setFacing(direction);
		this.setAcceleration(this.getAcceleration().setX(Constants.slimeHorizontalAcceleration * direction));
		moveTimeLeft = Constants.slimeMinMoveTime + Math.random() *
				(Constants.slimeMaxMoveTime - Constants.slimeMinMoveTime);
	}
	
	
	/**
	 * Stops the movement of this slime.
	 */
	private void stopMove() {
		
		this.setSpeed(this.getSpeed().setX(0.0));
	}


	@Override
	protected void handleCollision(Collidable collidable) {
		super.handleCollision(collidable);
		if ((collidable instanceof Slime)){
			Slime other = (Slime)collidable;
			if (other.getSchool().size() > this.getSchool().size()){
				School.switchSchoolsOfSlimeTo(this, other.getSchool());
			}
		}
	}

}
