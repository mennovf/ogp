package jumpingalien.model.reactions;

import java.util.Collection;

import jumpingalien.model.Collidable;
import jumpingalien.model.GameObject;
import jumpingalien.model.Tile;
import jumpingalien.model.Utilities;

/**
 * Manages the administration for collisions with terrain.
 */
public class TerrainCollisionDamager extends CollisionDamager {
	
	private final Collection<TerrainDamageInfo> infos;

	/**
	 * The constructor for TerrrainCollisionDamager.
	 * 
	 * @param infos
	 * 			A collection of TerrainDamageInfos to react to.
	 * 
	 * @param timeInterval
	 * 			The amount of time there minimally has to be between taking instances of damage.
	 * 
	 * @param owner
	 * 			The owner of this damager. If the owner calls reactTo for a collision with an
	 * 			object, the owner will take damage if all the conditions are met.
	 * 
	 * @effect Calls the constructor of CollisionDamager.
	 * 			| super(owner, 0, timeInterval)
	 * 
	 * @post This.info will be equal to the provided infos.
	 * 			| this.infos == infos
	 */
	public TerrainCollisionDamager(GameObject owner, double timeInterval, Collection<TerrainDamageInfo> infos) {
		super(owner, 0, timeInterval);
		this.infos = infos;
	}

	
	/**
	 * Returns whether or not this TerrainCollisionDamager will react to collidable.
	 * 
	 * @return Whether or not this TerrainCollisionDamager will react to collidable.
	 * 			| (obj instanceof Tile) && ((Tile)obj).getType() == (any of the types in this.infos)
	 */
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


	/**
	 * Apply damage to the owner if the conditions are met.
	 * 
	 * @param obj
	 * 			The object to react to.
	 * 
	 * @post If the owner can take damage, it takes damage.
	 * 			| if canTakeDamage() && ((Tile)collidable).getType() == (one of the info's types) && (that info's timeIn >= it's timeDelay))
	 * 			| then (new this.owner).getHealth() == (old this.owner).getHealht() + this.damage
	 * 
	 * @post timeSince will be reset to zero if damage is applied
	 * 			| then this.timeSince = 0
	 */
	@Override
	public void reactTo(Collidable collidable) {
		Tile tile = (Tile)collidable;
		if (canTakeDamage()){
			for (TerrainDamageInfo info : this.infos) {
				if (tile.getType() == info.type
					&& (this.timeSince - info.timeDelay + info.timeIn >= (info.timeDelay + this.timeInterval))){
					owner.takeDamage(info.damage);
					this.timeSince -= this.timeInterval;
					break;
				}
			}
		}
	}


	/**
	 * Advances the internal timers.
	 * 
	 * @param dt
	 * 			The amount of time with which to progress the internal timers.
	 * 
	 * @effect Calls advanceTime of CollisionDamagers.
	 * 			| super.advanceTime(dt)
	 * 
	 * @post If the owner is currently in contact with one of the info's TileType
	 * 		 that info's timeIn will get incremented by dt. If it's not in contact with
	 * 		 an info's TileType that info's timeIn will be set to 0.
	 * 			| for every info in this.infos
	 * 			| if this.owner.inContactWithTileOfType(info.type)
	 * 			| then info.timeIn += dt
	 * 			| else info.timeIn = 0
	 */
	@Override
	public void advanceTime(double dt) {
		super.advanceTime(dt);

		boolean inContact = false;

		for (TerrainDamageInfo info : this.infos){
			if (this.owner.inContactWithTileOfType(info.type)){
				info.timeIn = Utilities.clipInRange(0.0, info.timeDelay, info.timeIn + dt);
				inContact = true;
			} else {
				info.timeIn = 0;
			}
		}
		
		if (!inContact) {
			this.timeSince = Utilities.clipInRange(0.0, this.timeInterval, this.timeSince);
		}
	}
}
