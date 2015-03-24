package jumpingalien.model;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class representing a rectangular game world, also responsible of managing all game objects it contains.
 * 
 * @author Rugen and Menno 
 *
 */
public class World {

	private int tileSize;
	private Vector2D<Integer> nbTiles;
	private Vector2D<Integer> visibleWindowSize;
	private Vector2D<Integer> targetTilePosition;
	
	public World(int tileSize, int nbTilesX, int nbTilesY,
			int visibleWindowWidth, int visibleWindowHeight, int targetTileX,
			int targetTileY) {
		
		this.tileSize = tileSize;
		this.nbTiles = new Vector2D<>(nbTilesX, nbTilesY);
		this.visibleWindowSize = new Vector2D<>(visibleWindowWidth, visibleWindowHeight);
		this.targetTilePosition = new Vector2D<>(targetTileX, targetTileY);
	}
	
	
	/**
	 * @return The size of one tile in the game world in pixels.
	 */
	@Basic
	public int getTileSize() {
		return this.tileSize;
	}
	
	
	/**
	 * @return A 2D vector containing the number of tiles in the x, respectively y directions.
	 */
	@Basic
	public Vector2D<Integer> getNumberOfTiles() {
		return this.nbTiles;
	}
	
	
	/**
	 * @return A 2D vector representing the size of the game world in pixels.
	 */
	public Vector2D<Integer> getSizeInPixels() {
		return new Vector2D<Integer>(this.getNumberOfTiles().x * this.getTileSize(), this.getNumberOfTiles().y * this.getTileSize());
	}
	
	
	/**
	 * @return A 2D vector representing the size of the visible window in pixels.
	 */
	@Basic
	public Vector2D<Integer> getVisibleWindowSize() {
		return this.visibleWindowSize;
	}
}
