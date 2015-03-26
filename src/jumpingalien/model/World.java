package jumpingalien.model;

import java.util.HashSet;
import java.util.Set;

import jumpingalien.util.ModelException;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class representing a rectangular game world, also responsible of managing all game objects it contains.
 * 
 * @author Rugen and Menno 
 *
 */
public class World {
	
	private boolean isTerminated;

	private final int tileSize;
	private final Vector2D<Integer> nbTiles;
	private Vector2D<Integer> visibleWindowBottomLeft;
	private Vector2D<Integer> visibleWindowTopRight;
	private final Vector2D<Integer> targetTilePosition;
	
	private int[][] geologicalFeatures;
	
	private Mazub mazub;
	private Set<GameObject> objects = new HashSet<>();
	
	@Raw
	public World(int tileSize, int nbTilesX, int nbTilesY,
			int visibleWindowWidth, int visibleWindowHeight, int targetTileX,
			int targetTileY) {
		
		this.tileSize = tileSize;
		this.nbTiles = new Vector2D<>(nbTilesX, nbTilesY);
		this.visibleWindowBottomLeft = new Vector2D<>(0, 0);
		this.visibleWindowTopRight = new Vector2D<>(visibleWindowWidth, visibleWindowHeight);
		this.targetTilePosition = new Vector2D<>(targetTileX, targetTileY);
		this.geologicalFeatures = new int[nbTilesX][nbTilesY];
	}
	
	
	/**
	 * Terminates this game world.
	 * 
	 * @pre This game world will be terminated.
	 * 			| new.isTerminated()
	 */
	public void terminate() {
		//TODO: Remove all game object connections here
		this.isTerminated = true;
	}
	
	
	/**
	 * @return true if this game world is terminated.
	 */
	public boolean isTerminated() {
		return this.isTerminated;
	}
	
	
	/**
	 * @return The size of one tile in the game world in pixels.
	 */
	@Basic
	@Immutable
	public int getTileSize() {
		return this.tileSize;
	}
	
	
	/**
	 * @return A 2D vector containing the number of tiles in the x, respectively y directions.
	 */
	@Basic
	@Immutable
	public Vector2D<Integer> getNumberOfTiles() {
		return new Vector2D<>(this.nbTiles);
	}
	
	
	/**
	 * @return A 2D vector representing the size of the game world in pixels.
	 */
	@Immutable
	public Vector2D<Integer> getSizeInPixels() {
		return new Vector2D<>(this.getNumberOfTiles().x * this.getTileSize(), this.getNumberOfTiles().y * this.getTileSize());
	}
	
	
	/**
	 * @return A 2D vector representing the size of the game world in meters.
	 */
	@Immutable
	public Vector2D<Double> getSizeInMeters() {
		return Utilities.pixelsVectorToMeters(this.getSizeInPixels());
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
	public int[][] getTilePositionsInRectangle(Vector2D<Integer> bottomLeftPixel, Vector2D<Integer> topRightPixel) {
		
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
				int index = (y - bottomLeftTile.y) * blockWidth + (x - bottomLeftTile.x);
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
	@Immutable
	public int[] getVisibleWindow() {
		int[] windowArray = {this.visibleWindowBottomLeft.x, this.visibleWindowBottomLeft.y,
				this.visibleWindowTopRight.x, this.visibleWindowTopRight.y};
		return windowArray;
	}
	
	
	/**
	 * @return A 2D vector representing the position of the target tile.
	 */
	@Basic
	@Immutable
	public Vector2D<Integer> getTargetTilePosition() {
		return new Vector2D<>(this.targetTilePosition);
	}
	
	
	/**
	 * Starts the game in this game world.
	 */
	public void startGame() {
		//TODO: Implement this method
	}
	
	
	/**
	 * @return true if the game has ended.
	 */
	public boolean isGameOver() {
		//TODO: Implement this method
		return false;
	}
	
	
	/**
	 * @return true if the player won the game.
	 */
	public boolean didPlayerWin() {
		//TODO: Implement this method
		return false;
	}
	
	
	/**
	 * @return A 2D array representing the game world's tiles' types.
	 */
	@Basic
	public int[][] getGeologicalFeatures() {
		return this.geologicalFeatures.clone();
	}
	
	
	/**
	 * @param pixel
	 * 			A 2D vector representing the position of the pixel to get the geological feature of.
	 * 
	 * @return The geological feature type of the given pixel.
	 * 
	 * @throws ModelException
	 * 			Throws a ModelException when the given pixel does not lie in the game world.
	 * 			| this.pixelInWorld(pixel)
	 * 
	 * ik neem aan dat dit defensive moet, aangezien Facade een exception verwacht
	 */
	public int getGeologicalFeature(Vector2D<Integer> pixel) throws ModelException {
		if (!this.pixelInWorld(pixel)) {
			throw new ModelException("The pixel has to lie in the game world.");
		}
		Vector2D<Integer> tile = this.getTileContainingPixel(pixel);
		return this.getGeologicalFeatures()[tile.x][tile.y];
	}
	
	
	/**
	 * Sets the geological feature of the given tile to the given type.
	 * 
	 * @param tile
	 * 			A 2D vector representing the position of the specified tile.
	 * 
	 * @param tileType
	 * 			The type to set.
	 * 
	 * @pre The given tile must lie in the game world.
	 * 			| this.tileInWorld(tile)
	 * 
	 * geen stijl gespecifieerd -> nominally
	 */
	@Basic
	public void setGeologicalFeature(Vector2D<Integer> tile, int tileType) {
		assert this.tileInWorld(tile);
		this.geologicalFeatures[tile.x][tile.y] = tileType;
	}
	
	
	/**
	 * @param gameObject
	 * 			The game object to check.
	 * 
	 * @return true if this game world contains the given game object.
	 */
	public boolean containsGameObject(GameObject gameObject) {
		if (gameObject instanceof Mazub) {
			return this.getMazub() == mazub;
		}
		return objects.contains(gameObject);
	}
	
	
	/**
	 * Removes the given game object from this game world.
	 * 
	 * @param gameObject
	 * 			The game object to remove.
	 * 
	 * no style specified -> totally
	 */
	public void removeGameObject(GameObject gameObject) {
		if ((gameObject instanceof Mazub) && this.getMazub() == gameObject) {
			this.mazub = null;
		}
		if (this.objects.contains(gameObject)) {
			this.objects.remove(gameObject);
		}
	}
	
	
	/**
	 * @return This game world's mazub.
	 */
	public Mazub getMazub() {
		return this.mazub;
	}
	
	
	/**
	 * Sets this game world's mazub to the given mazub.
	 * 
	 * @param mazub
	 * 			The mazub to set.
	 * 
	 * @post The given mazub is registered as this game world's mazub.
	 * 			| new.getMazub() == mazub
	 * 
	 * @post This game world is registered as the given mazub's game world.
	 * 			| (new mazub).getWorld() == new
	 * 
	 * @throws IllegalArgumentException
	 * 			Throws an IllegalArgumentException when this game world can not have the given mazub as it's mazub.
	 * 			| !this.canHaveAsMazub(mazub)
	 */
	public void setMazub(@Raw Mazub mazub) throws IllegalArgumentException {
		if (!this.canHaveAsMazub(mazub)) {
			throw new IllegalArgumentException("This game world can not have the given mazub as it's mazub");
		}
		this.mazub = mazub;
		mazub.setWorld(this);
	}
	
	
	/**
	 * @param mazub
	 * 			The mazub to check.
	 * 
	 * @return true if this game world can have the given mazub as it's mazub.
	 * 			This game world can not be terminated.
	 * 			| !this.isTerminated()
	 * 			The given mazub can not be null.
	 * 			| mazub != null
	 * 			The given mazub can not be terminated.
	 * 			| !mazub.isTerminated()
	 */
	public boolean canHaveAsMazub(@Raw Mazub mazub) {
		if (this.isTerminated() || (mazub == null) || mazub.isTerminated()) {
			return false;
		}
		return true;
	}
	
	
	/**
	 * @return true if this game world has a proper mazub.
	 * 			This game world needs to be able to have it's current mazub as it's mazub.
	 * 			| this.canHaveAsMazub(this.getMazub())
	 * 			This game world's mazub has to have this game world as it's game world.
	 * 			| this.getMazub().getWorld() == this
	 */
	public boolean hasProperMazub() {
		return this.canHaveAsMazub(this.getMazub()) && this.getMazub().getWorld() == this;
	}
	
	
	/**
	 * @param cls
	 * 			The class of which to return GameObjects
	 * 
	 * @return The collection of all object of with class "type" in this game world.
	 */
	public <T extends GameObject> Set<T> getGameObjectWithClass(Class<T> cls) {
		Set<T> objects = new HashSet<T>();
		for (GameObject obj : this.objects){
			if (obj.getClass() == cls){
				objects.add(cls.cast(obj));
			}
		}
		return objects;
	}
	
	
	/**
	 * Adds the given GameObject to the collection of GameObjects in this game world.
	 * 
	 * @param object
	 * 			The GameObject to add.
	 * 
	 * @post The given GameObject will be added to the collection of GameObjects in this game world.
	 * 			| new.containsGameObject(object)
	 * 
	 * @throw IllegalArgumentException
	 * 			Throws an IllegalArgumentException if the given GameObject is null or terminated.
	 * 			| object == null || object.isTerminated()
	 */
	public void addGameObject(GameObject object) throws IllegalArgumentException {
		if (object == null || object.isTerminated()) {
			throw new IllegalArgumentException("The GameObject can't be null or terminated.");
		}
		this.objects.add(object);
	}
	
	
	/**
	 * Advances the time in this game world with the given time interval and updates position, speed and acceleration of all game objects in this game world.
	 * 
	 * @param dt
	 * 			The time that has passed in the game world since last calling this method.
	 * 
	 * @throws	IllegalArgumentException
	 * 			| (dt < 0) || (dt > Constants.maxTimeInterval) || dt.isNan()
	 */
	public void advanceTime(double dt) {
		
		// check for exceptions
		if (Double.isNaN(dt)) {
			throw new IllegalArgumentException("Delta time can not be NaN.");
		}
		if (dt > Constants.maxTimeInterval){
			throw new IllegalArgumentException(String.format("Delta time may not exceed %.5fs.", Constants.maxTimeInterval));
		}
		if (dt < 0){
			throw new IllegalArgumentException("Delta time has to be non-negative.");
		}
		
		this.getMazub().advanceTime(dt);
		for (GameObject object : this.objects) {
			object.advanceTime(dt);
		}
	}
}
