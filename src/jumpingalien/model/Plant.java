package jumpingalien.model;

import java.util.HashSet;
import java.util.Set;

import jumpingalien.model.Reactions.PlantMazubCollisionDamager;
import jumpingalien.util.Sprite;

public class Plant extends GameObject {
	
	private double directionTime = Constants.plantMoveTime;
	
	/**
	 * Creates a plant with the given position and sprites.
	 * 
	 * @param position
	 * 			The position of the plant in meters.
	 * 
	 * @param sprites
	 * 			The sprites of the plant.
	 * 
	 * @effect The GameObject constructor will be called.
	 * 			| super(1, 1, position, sprites, true);
	 */
	public Plant(Vector<Double> position, Sprite[] sprites){
		
		super(1, 1, position, sprites, true);
		this.setSpeed(this.getSpeed().setX(Constants.plantSpeed));
		
		this.addCollisionDamager(new PlantMazubCollisionDamager(this, -1, 0));
	}
	
	
	@Override
	protected Set<Class<? extends GameObject>> getCollidableObjectClasses() {
		
		//TODO: Check whether it does not give problems returning an empty set here
		//		because the interaction of a plant with Mazub will be handled by Mazub.
		
		return new HashSet<Class<? extends GameObject>>();
	}
	
	
	@Override
	protected Set<TileType> getCollidableTileTypes() {
		
		HashSet<TileType> collidables = new HashSet<TileType>();
		collidables.add(TileType.GROUND);
		
		return collidables;
	}
	
	
	@Override
	protected void handleStep(double dt) {
		
		double timeLeft = Constants.plantMoveTime - directionTime;
		directionTime = (directionTime + dt) % Constants.plantMoveTime;
		
		if (dt > timeLeft){
			this.setSpeed(this.getSpeed().setX(this.getFacing() * Constants.plantSpeed));
			this.setFacing(this.getFacing() * -1);
		}
	}
}