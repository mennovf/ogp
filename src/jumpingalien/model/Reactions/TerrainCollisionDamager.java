package jumpingalien.model.Reactions;

import java.util.Collection;

import jumpingalien.model.Collidable;
import jumpingalien.model.GameObject;
import jumpingalien.model.Tile;

public class TerrainCollisionDamager extends CollisionDamager {
	
	private final Collection<TerrainDamageInfo> infos;

	public TerrainCollisionDamager(GameObject owner, double timeInterval, Collection<TerrainDamageInfo> infos) {
		super(owner, 0, timeInterval);
		this.infos = infos;
	}

	@Override
	public boolean doesReactTo(Collidable obj) {
		if (obj instanceof Tile){
			Tile tile = (Tile)obj;
			for (TerrainDamageInfo info : this.infos){
				if (tile.getType() == info.type){
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void reactTo(Collidable collidable) {
		Tile tile = (Tile)collidable;
		if (canTakeDamage()){
			for (TerrainDamageInfo info : this.infos) {
				if (tile.getType() == info.type && (info.timeIn >= info.timeDelay)){
					owner.takeDamage(info.damage);
					this.timeSince = 0;
					break;
				}
			}
		}
	}

	@Override
	public void advanceTime(double dt) {
		super.advanceTime(dt);
		
		for (TerrainDamageInfo info : this.infos){
			if (this.owner.inContactWithTileOfType(info.type)){
				info.timeIn += dt;
			}
			else {
				info.timeIn = 0;
			}
		}
	}
}
