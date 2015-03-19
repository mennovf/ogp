package jumpingalien.model;

public class World {

	private int tileSize;
	private Vector2D<Integer> nbTiles;
	private Vector2D<Integer> visibleWindowSize;
	private Vector2D<Integer> targetTilePosition;
	
	public World(int tileSize, int nbTilesX, int nbTilesY,
			int visibleWindowWidth, int visibleWindowHeight, int targetTileX,
			int targetTileY) {
		
		this.tileSize = tileSize;
		this.nbTiles.x = nbTilesX;
		this.nbTiles.y = nbTilesY;
		this.visibleWindowSize.x = visibleWindowWidth;
		this.visibleWindowSize.y = visibleWindowHeight;
		this.targetTilePosition.x = targetTileX;
		this.targetTilePosition.y = targetTileY;
	}
	
	
}
