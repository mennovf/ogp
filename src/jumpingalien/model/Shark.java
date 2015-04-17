package jumpingalien.model;

import java.util.HashSet;
import java.util.Set;

import jumpingalien.util.Sprite;

/**
 * @author Rugen en Menno
 * 
 * A public class representing a shark game object.
 */
public class Shark extends GameObject {
	
	private double moveTimeLeft = 0;
	private double movePeriodCount = 0;

	/**
	 * Creates a shark with the given position and sprites.
	 * 
	 * @param position
	 * 			The position in the game world in pixels.
	 * 
	 * @param sprites
	 * 			The sprite list.
	 */
	public Shark(Vector<Double> position, Sprite[] sprites) {
		
		// GameObject
		super(100, 100, position, sprites);
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
		
		super.setSpeed(new Vector<Double>(Utilities.clipInRange(-Constants.sharkMaxHorizontalSpeed,
											Constants.sharkMaxHorizontalSpeed,
											speed.x), speed.y));
	}
	
	
	/**
	 * Returns whether this sharks bottom perimeter overlaps with water.
	 * 
	 * @return true if this sharks bottom perimeter overlaps with water.
	 */
	private boolean inWater() {
		
		Set<Tile> collidingTiles = this.getWorld().getTilesCollidingWithObject(this);
		
		for (Tile tile : collidingTiles) {
			
			if (tile.getType() == TileType.WATER) {
				
				Vector<Integer> overlap = this.getKindOfOverlapWithTile(tile);
				
				if (overlap.y > 0) {
					return true;
				}
			}
		}
		
		return false;
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
	
	
	@Override
	protected void handleCollisions(Set<GameObject> collidingObjects,
			Set<Tile> collidingTiles) {
		super.handleCollisions(collidingObjects, collidingTiles);
		
		if (!(this.onGround() || this.inWater())) {
			this.setAcceleration(this.getAcceleration().setY(Constants.gravityAcceleration));
		}
	}
	
	
	@Override
	protected void handleCollision(GameObject object) {
		// TODO Auto-generated method stub
		
	}
	
	
	/**
	 * Starts moving in the given direction.
	 * 
	 * @param direction
	 * 			The direction to start moving in.
	 * 			1.0 means to the right, -1.0 means to the left.
	 */
	private void startMove(double direction) {
		
		this.setFacing(direction);
		this.setAcceleration(this.getAcceleration().setX(Constants.sharkHorizontalAcceleration * direction));
		moveTimeLeft = Constants.slimeMinMoveTime + Math.random() *
				(Constants.slimeMaxMoveTime - Constants.slimeMinMoveTime);
		
		if (movePeriodCount > 4) {
			
			if (Math.rint(Math.random()) == 0) {
				
				this.setSpeed(this.getSpeed().setY(Constants.sharkInitialJumpSpeed));
				
				movePeriodCount = 0;
			}
		}
		
		movePeriodCount += 1;
	}
	
	
	/**
	 * Stops the movement of this shark.
	 */
	private void stopMove() {
		
		this.setSpeed(this.getSpeed().setX(0.0));
	}
}
