package jumpingalien.model;

import java.util.HashSet;
import java.util.Set;

import jumpingalien.util.Sprite;

public class Shark extends GameObject {

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
	
	
	@Override
	protected void handleStep(double dt) {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	protected void handleCollisions(Set<GameObject> collidingObjects,
			Set<Tile> collidingTiles) {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public void advanceTime(double dt) {
		// TODO Auto-generated method stub
		
	}
}
