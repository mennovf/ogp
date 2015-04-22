package jumpingalien.model;

import java.util.HashSet;
import java.util.Set;

import jumpingalien.util.Sprite;

/**
 * A class to represent a Slime object in a certain school.
 * 
 * @author Rugen en Menno
 * 
 * @invar The slime must always have a valid school.
 * 			| this.hasProperSchool()
 */
public class Slime extends GameObject {
	
	private double moveTimeLeft = 0;
	
	private School school;
	
	
	/**
	 * The time since the last enemy damage was taken.
	 * By setting this to zero, Slime will not
	 * lose life in the first enemyDamageInterval seconds of the game.
	 */
	private double timeSinceEnemyDamage = 0;

	
	/**
	 * The time since the last water damage was taken.
	 * By setting this to zero, Slime will not
	 * lose life in the first terrainDamageInterval seconds of the game.
	 */
	private double timeSinceWaterDamage = 0;
	
	/**
	 * The time Slime has been in contact with water.
	 * When this becomes bigger than 0.2 Slime has been in contact with
	 * water for 0.2 seconds or more, so damage will be taken.
	 */
	private double timeInContactWithWater = 0;
	
	/**
	 * The time since the last magma damage was taken.
	 * By setting this higher than the threshold Slime will take damage
	 * on first contact.
	 */
	private double timeSinceMagmaDamage = Constants.terrainDamageInterval + 0.1;

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
	 * TODO: dit nakijken 
	 * @effect this.setSchool(school)
	 */
	public Slime(Vector<Double> position, Sprite[] sprites, School school) throws IllegalArgumentException {
		
		// GameObject
		super(100, 100, position, sprites);
		
		this.setSchool(school);
		school.addSlime(this);
	}
	
	
	/**
	 * @return The school of this slime.
	 */
	public School getSchool() {
		return this.school;
	}
	
	
	/**
	 * @param school
	 * 			The school to check.
	 * 
	 * @return Whether this slime can have the given school as it's school.
	 * 			| !(school == null)
	 */
	public static boolean canHaveAsSchool(School school) {
		return !(school == null) /*&& !school.isTerminated()*/;
		//TODO: Check exact definition of isTerminated
	}
	
	
	/**
	 * @return Whether this slime has a proper school.
	 * 
	 * @effect this.canHaveAsSchool(this.getSchool())
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
	 * @throws IllegalArgumentException
	 * 			Throws an IllegalArgumentException when this slime can't have the given school as school.
	 */
	public void setSchool(School school) throws IllegalArgumentException {
		if (!canHaveAsSchool(school)) {
			throw new IllegalArgumentException("Invalid school provided.");
		}
		
		this.school = school;
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
	 */
	@Override
	public void setSpeed(Vector<Double> speed) {
		
		super.setSpeed(new Vector<Double>(Utilities.clipInRange(-Constants.slimeMaxHorizontalSpeed,
											Constants.slimeMaxHorizontalSpeed,
											speed.x), speed.y));
	}
	
	
	@Override
	protected void handleStep(double dt) {
		
		this.timeSinceEnemyDamage += dt;
		this.timeSinceWaterDamage += dt;
		this.timeSinceMagmaDamage += dt;
		
		if (this.inContactWithTileOfType(TileType.WATER)) {
			this.timeInContactWithWater += dt;
		} else {
			this.timeInContactWithWater = 0;
		}

		if (moveTimeLeft <= 0) {
			
			this.stopMove();
			
			double direction = Math.rint(Math.random()) == 0 ? -1.0 : 1.0;
			this.startMove(direction);
			
		} else {
			
			moveTimeLeft -= dt;
		}
	}
	
	private void takeDamage(int amount){
		this.increaseHealth(amount);
		this.getSchool().takeDamageCausedBy(this);
	}
	
	
	@Override
	protected void handleCollisions(Set<GameObject> collidingObjects,
			Set<Tile> collidingTiles) {
		super.handleCollisions(collidingObjects, collidingTiles);
		
		if (!this.onGround()) {
			this.setAcceleration(this.getAcceleration().setY(Constants.gravityAcceleration));
		}

		for (Tile tile : collidingTiles) {
			
			switch (tile.getType()) {
			
			case WATER:
				if (this.timeInContactWithWater > Constants.terrainDamageInterval
						&& this.timeSinceWaterDamage > Constants.terrainDamageInterval) {
					this.takeDamage(Constants.waterDamage);
					this.timeSinceWaterDamage = 0;
				}
				break;
				
			case MAGMA:
				if (this.timeSinceMagmaDamage > Constants.terrainDamageInterval) {
					this.takeDamage(Constants.magmaDamage);
					this.timeSinceMagmaDamage = 0;
				}
				break;
				
			default:
				break;
			}
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
	protected void handleCollision(GameObject object) {
		if ((object instanceof Slime)){
			Slime other = (Slime)object;
			if (other.getSchool().size() > this.getSchool().size()){
				School.switchSchools(this.getSchool(), other.getSchool(), this);
			}
		}
		if ((this.timeSinceEnemyDamage > Constants.enemyDamageInterval) && (object instanceof Mazub) || (object instanceof Shark)){
			this.takeDamage(Constants.slimeEnemyContactDamage);
			this.timeSinceEnemyDamage = 0;
		}
	}

}
