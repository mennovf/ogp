package jumpingalien.model;

import java.util.HashSet;
import java.util.Set;

import jumpingalien.model.reactions.TerrainCollisionDamager;
import jumpingalien.model.reactions.TerrainCollisionDamager.TerrainDamageInfo;
import jumpingalien.util.Sprite;

public class Gore extends GameObject {
	
	public Gore(Vector<Double> position, Sprite[] sprites) {
		super(1, 1, position, sprites, true);
		this.setSpeed(Vector.scale(new Vector<>(Math.random() - 0.5, Math.random()), 5.0));
		
		Set<TerrainCollisionDamager.TerrainDamageInfo> infos = new HashSet<>();
		infos.add(new TerrainDamageInfo(TileType.GROUND, -1, 0));
		
		this.addCollisionDamager(new TerrainCollisionDamager(this, 0, infos));
		
		this.setAcceleration(this.getAcceleration().setY(Constants.gravityAcceleration));
	}
	
	public static Gore create(Vector<Double> position, Sprite sprite) {
		
		Sprite[] sprites = new Sprite[2];
		sprites[0] = sprite;
		sprites[1] = sprites[0];
		
		return new Gore(position, sprites);
	}

	@Override
	protected Set<Class<? extends GameObject>> getCollidableObjectClasses() {
		return new HashSet<>();
	}

	@Override
	protected Set<TileType> getCollidableTileTypes() {
		HashSet<TileType> collidables = new HashSet<TileType>();
		collidables.add(TileType.GROUND);
		return collidables;
	}

	/**
	 * Does absolutely nothing.
	 */
	@Override
	protected void handleStep(double dt) {}
	
	/**
	 * Does absolutely nothing.
	 */
	@Override
	public void onRemove() {}
	
}