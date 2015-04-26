package jumpingalien.model.reactions;

import jumpingalien.model.TileType;

/**
 * A class containing the info for a TerrainCollisionDamager.
 */
public class TerrainDamageInfo {
		public final TileType type;
		public double timeIn;
		public final double timeDelay;
		public final int damage;
		
		/**
		 * The constructor for TerrainDamageInfo.
		 * 
		 * @param type
		 * 			The type of terrain to react to.
		 * 
		 * @param damage
		 * 			The amount of damage to take when the condition are met.
		 * 
		 * @param delay
		 * 			The amount of time the owner has to be in contact with type before
		 * 			starting to receive damage.
		 * 
		 * @post Sets the values to the parameters
		 * 			| new.type == type
		 * 			| new.damage == damage
		 * 			| new.timeIn == 0
		 * 			| new.timeDelay == delay
		 */
		public TerrainDamageInfo(TileType type, int damage, double delay){
			this.type = type;
			this.damage = damage;
			this.timeIn = 0;
			this.timeDelay = delay;
		}
	}