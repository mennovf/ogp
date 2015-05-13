package jumpingalien.model.gameobject;

import java.util.HashSet;
import java.util.Set;

import jumpingalien.common.sprites.ImageSprite;
import jumpingalien.model.Constants;
import jumpingalien.model.Vector;
import jumpingalien.model.program.Program;
import jumpingalien.model.reactions.PlantMazubCollisionDamager;
import jumpingalien.model.world.TileType;
import jumpingalien.util.Sprite;

/**
 * A class representing a Plant, food for Mazub.
 * 
 * @author Rugen Heidbuchel & Menno Vanfrachem
 * 
 * @invar Plants will either have 1 or 0 health.
 * 			| plant.getHealth() == 0 || plant.getHealht() == 1
 * 
 * @invar See GameObject.
 */
public class Plant extends GameObject implements RunProgrammable {
	
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
	 * 
	 * @effect The plant will start moving to the right with a speed of Constants.plantSpeed.
	 * 			| plant.setSpeed(this.getSpeed().setX(Constants.plantSpeed))
	 */
	public Plant(Vector<Double> position, Sprite[] sprites){
		
		this(position, sprites, null);
	}
	
	
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
	 * 
	 * @effect The plant will start moving to the right with a speed of Constants.plantSpeed.
	 * 			| plant.setSpeed(this.getSpeed().setX(Constants.plantSpeed))
	 */
	public Plant(Vector<Double> position, Sprite[] sprites, Program program){
		
		super(1, 1, position, sprites, program, true);
		this.setSpeed(this.getSpeed().setX(Constants.plantSpeed));
		
		this.addCollisionDamager(new PlantMazubCollisionDamager(this, -1, 0));
	}
	
	
	@Override
	protected Sprite[] getGoreSprites() {
	
		int numberOfGoreSprites = 8;
		Sprite[] spriteSet = new Sprite[numberOfGoreSprites];
		
		for (int i = 1; i <= numberOfGoreSprites; i++) {
			spriteSet[i-1] = ImageSprite.createSprite("levels/gore/plant/plantGore_" + i + ".png");
		}
		
		return spriteSet;
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
			this.startRun(this.getFacing() * -1);
		}
	}


	@Override
	public void startRun(double direction) {
		this.setFacing(direction);
		this.setSpeed(this.getSpeed().setX(this.getFacing() * Constants.plantSpeed));
	}


	@Override
	public void stopRun() {
		this.setSpeed(this.getSpeed().setX(0.0));
	}
}