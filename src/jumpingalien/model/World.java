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
		return new Vector2D<>(this.nbTiles);
	}
	
	
	/**
	 * @return A 2D vector representing the size of the game world in pixels.
	 */
	public Vector2D<Integer> getSizeInPixels() {
		return new Vector2D<>(this.getNumberOfTiles().x * this.getTileSize(), this.getNumberOfTiles().y * this.getTileSize());
	}
	
	
	/**
	 * @param pixel
	 * 			The pixel to check.
	 * 
	 * @return true if the pixel lies in the game world.
	 */
	public boolean pixelInWorld(Vector2D<Integer> pixel) {
		Vector2D<Integer> worldSize = this.getSizeInPixels();
		return pixel.x >= 0 && pixel.x < worldSize.x
				&& pixel.y >= 0 && pixel.y < worldSize.y;
	}
	
	
	/**
	 * @param tile
	 * 			The tile to check.
	 * 
	 * @return true if the tile lies in the game world.
	 */
	public boolean tileInWorld(Vector2D<Integer> tile) {
		Vector2D<Integer> numberOfTiles = this.getNumberOfTiles();
		return tile.x >= 0 && tile.x < numberOfTiles.x
				&& tile.y >= 0 && tile.y < numberOfTiles.y;
	}
	
	
	/**
	 * @param tileX
	 * 			The x index of the specified tile.
	 * 
	 * @param tileY
	 * 			The y index of the specified tile.
	 * 
	 * @return A 2D vector representing the bottom left pixel position of the specified tile.
	 * 
	 * @pre The given tile must lie in the game world.
	 * 			| this.tileInWorld(tile)
	 * 
	 * geen stijl gespecifieerd -> nominally
	 */
	public Vector2D<Integer> getBottomLeftPixelOfTile(Vector2D<Integer> tile) {
		assert this.tileInWorld(tile);
		return new Vector2D<>(tile.x * this.getTileSize(), tile.y * this.getTileSize());
	}
	
	
	/**
	 * @param pixel
	 * 			The pixel to get the containing tile of.
	 * 
	 * @return A 2D vector representing the position of the tile containing the given pixel.
	 * 
	 * @pre The given pixel must lie in the game world.
	 * 			| this.pixelInWorld(pixel)
	 * 
	 * geen stijl gespecifieerd -> nominally
	 */
	public Vector2D<Integer> getTileContainingPixel(Vector2D<Integer> pixel) {
		assert this.pixelInWorld(pixel);
		return new Vector2D<>(pixel.x / this.getTileSize(), pixel.y / this.getTileSize());
	}
	
	
	/**
	 * @param bottomLeftPixel
	 * 			The position of the bottom left pixel of the rectangle.
	 * 
	 * @param topRightPixel
	 * 			The position of the top right pixel of the rectangle.
	 * 
	 * @return An array of 2D arrays representing the positions of the tiles intersecting with the
	 * 			specified rectangle in pixels.
	 * 
	 * @pre The given pixels must lie inside the game world.
	 * 			| this.pixelInWorld(bottomLeftPixel) && this.pixelInWorld(topRightPixel)
	 * 
	 * geen stijl gespecifieerd -> nominally
	 */
	public int[][] getTilePositionsIn(Vector2D<Integer> bottomLeftPixel, Vector2D<Integer> topRightPixel) {
		
		// Java kan geen array maken van type Vector2D<Integer>[]
		
		assert this.pixelInWorld(bottomLeftPixel) && this.pixelInWorld(topRightPixel);
		
		Vector2D<Integer> bottomLeftTile = this.getTileContainingPixel(bottomLeftPixel);
		Vector2D<Integer> topRightTile = this.getTileContainingPixel(topRightPixel);
		
		int blockWidth = topRightTile.x - bottomLeftTile.x + 1;
		int blockHeight = topRightTile.y - bottomLeftTile.y + 1;
		int numberOfTiles = blockWidth * blockHeight;
		
		int[][] tiles = new int[numberOfTiles][2];
		
		for (int x = bottomLeftTile.x; x <= topRightTile.x; x++) {
			for (int y = bottomLeftTile.y; y <= topRightTile.y; y++) {
				int index = (x - bottomLeftTile.x) * blockWidth + (y - bottomLeftTile.y);
				tiles[index][0] = x;
				tiles[index][1] = y;
			}
		}
		
		return tiles;
	}
	
	
	/**
	 * @return A 2D vector representing the size of the visible window in pixels.
	 */
	@Basic
	public Vector2D<Integer> getVisibleWindowSize() {
		return new Vector2D<>(this.visibleWindowSize);
	}
	
	
	/**
	 * @return A 2D vector representing the position of the target tile.
	 */
	@Basic
	public Vector2D<Integer> getTargetTilePosition() {
		return new Vector2D<>(this.targetTilePosition);
	}
}
