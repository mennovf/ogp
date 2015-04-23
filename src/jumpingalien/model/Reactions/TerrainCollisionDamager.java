package jumpingalien.model.Reactions;

import jumpingalien.model.Collidable;
import jumpingalien.model.GameObject;
import jumpingalien.model.Tile;
import jumpingalien.model.TileType;

public class TerrainCollisionDamager extends CollisionDamager {
	
	private TileType type;
	private double timeIn;
	private final double timeDelay;

	public TerrainCollisionDamager(GameObject owner, int damage,
			double timeInterval, double timeDelay, TileType type) {
		super(owner, damage, timeInterval);
		this.timeIn = 0;
		this.timeDelay = timeDelay;
		this.type = type;
	}

	@Override
	public boolean doesReactTo(Collidable obj) {
		return (obj instanceof Tile) && (((Tile)obj).getType() == this.type);
	}

	@Override
	protected boolean canTakeDamage(){
		return super.canTakeDamage() && (timeIn >= timeDelay);
	}
	
	@Override
	public void advanceTime(double dt) {
		super.advanceTime(dt);
		
		if (this.owner.inContactWithTileOfType(this.type)){
			this.timeIn += dt;
		}
		else {
			this.timeIn = 0;
		}
	}
}
