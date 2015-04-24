package jumpingalien.model.Reactions;

import jumpingalien.model.TileType;

public class TerrainDamageInfo {
		public TileType type;
		public double timeIn;
		public final double timeDelay;
		public final int damage;
		
		public TerrainDamageInfo(TileType type, int damage, double delay){
			this.type = type;
			this.damage = damage;
			this.timeIn = 0;
			this.timeDelay = delay;
		}
	}