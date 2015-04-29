package jumpingalien.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import jumpingalien.util.ModelException;
import jumpingalien.util.Sprite;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class representing a rectangular game world, also responsible of managing all game objects it contains.
 * 
 * @author Rugen Heidbuchel, Menno Vanfrachem
 */
public class World {

	/**
	 * The size of a tile in pixels.
	 */
	private final int tileSize;
	
	/**
	 * The position of the bottom left pixel of the visible window in pixels.
	 */
	private Vector<Integer> visibleWindowBottomLeft;
	
	/**
	 * The position of the top right pixel of the visible window in pixels.
	 */
	private Vector<Integer> visibleWindowTopRight;
	
	/**
	 * The position of the target tile in tiles.
	 */
	private final Vector<Integer> targetTilePosition;
	
	/**
	 * A 2 dimensional array containing the types of tiles in the game world.
	 */
	private TileType[][] tiles;
	
	
	/**
	 * The Mazub of this game world.
	 */
	private Mazub mazub;
	
	/**
	 * A set of game objects of this game world.
	 */
	private Set<GameObject> objects = new HashSet<>();
	
	
	
	/**
	 * Creates a new world with the given parameters.
	 * 
	 * @param tileSize
	 * 			The size of a tile in pixels.
	 * 
	 * @param nbTilesX
	 * 			The number of tiles in the x dimension.
	 * 
	 * @param nbTilesY
	 * 			The number of tiles in the y dimension.
	 * 
	 * @param visibleWindowWidth
	 * 			The width of the visible window in pixels.
	 * 
	 * @param visibleWindowHeight
	 * 			The height of the visisble window in pixels.
	 * 
	 * @param targetTileX
	 * 			The x position of the target tile in tiles.
	 * 
	 * @param targetTileY
	 * 			The y position of the target tile in tiles.
	 * 
	 * @post The tile size of this world will be tileSize.
	 * 			| new.getTileSize() == tileSize
	 * 
	 * @post The number of tiles will be nbTilesX in the x
	 * 			dimension and nbTilesY in the y dimension.
	 * 			| new.getNumberOfTiles() == new Vector<>(nbTilesX, nbTilesY)
	 * 
	 * @post The position of the bottom left pixel of the
	 * 			visible window will be set to [0, 0].
	 * 			The position of the top right pixel of the
	 * 			visible window will be set to
	 * 			[visibleWindowWidth, visibleWindowHeight]
	 * 			| new.getVisibleWindow() ==
	 * 			|	{0, 0, visibleWindowWidth, visibleWindowHeight}
	 * 
	 * @post The entire world will be filled with AIR tiles.
	 * 			| for each tileType in new.getTileTypes:
	 * 			|	tileType == TileType.AIR
	 * 
	 * @throws IllegalArgumentException
	 * 			Throws an IllegalArgumentException when the
	 * 			number of tiles in either dimension is less
	 * 			than or equal to zero.
	 * 			| nbTilesX <= 0 || nbTilesY <= 0
	 */
	@Raw
	public World(int tileSize, int nbTilesX, int nbTilesY,
			int visibleWindowWidth, int visibleWindowHeight, int targetTileX,
			int targetTileY) throws IllegalArgumentException {
		
		if (nbTilesX <= 0 || nbTilesY <= 0) {
			throw new IllegalArgumentException("The number of tiles provided should be bigger"
					+ "than zero in both dimensions.");
		}
		
		this.tileSize = tileSize;
		this.visibleWindowBottomLeft = new Vector<>(0, 0);
		this.visibleWindowTopRight = new Vector<>(visibleWindowWidth, visibleWindowHeight);
		this.targetTilePosition = new Vector<>(targetTileX, targetTileY);
		this.tiles = new TileType[nbTilesX][nbTilesY];
		
		for (int x = 0; x < nbTilesX; x++) {
			for (int y = 0; y < nbTilesY; y++) {
				this.tiles[x][y] = TileType.AIR;
			}
		}
	}
	
	
	/**
	 * Returns the size of one tile in the game world in pixels.
	 * 
	 * @return The size of one tile in the game world in pixels.
	 */
	@Basic
	@Immutable
	public int getTileSize() {
		return this.tileSize;
	}
	
	
	/**
	 * Returns the size of one tile in the game world in meters.
	 * 
	 * @return The size of one tile in the game world in meters.
	 */
	@Immutable
	public double getTileSizeInMeters() {
		return Utilities.pixelsToMeters(this.getTileSize());
	}
	
	
	/**
	 * Returns the number of tiles in this game world in the x and y dimensions.
	 * 
	 * @return A 2D vector containing the number of tiles in the x, respectively y dimensions.
	 * 			| new Vector<>(this.getTileTypes().length, this.getTileTypes()[0].length)
	 */
	@Immutable
	public Vector<Integer> getNumberOfTiles() {
		return new Vector<>(this.getTileTypes().length, this.getTileTypes()[0].length);
	}
	
	
	/**
	 * Returns the total size of the world in pixels.
	 * 
	 * @return A 2D vector representing the size of the game world in pixels.
	 * 			| new Vector<>(this.getNumberOfTiles().x * this.getTileSize(),
	 * 			|	this.getNumberOfTiles().y * this.getTileSize())
	 */
	@Immutable
	public Vector<Integer> getSizeInPixels() {
		return new Vector<>(this.getNumberOfTiles().x * this.getTileSize(), this.getNumberOfTiles().y * this.getTileSize());
	}
	
	
	/**
	 * Returns the total size of the world in meters.
	 * 
	 * @return A 2D vector representing the size of the game world in meters.
	 * 			| Utilities.pixelsVectorToMeters(this.getSizeInPixels())
	 */
	@Immutable
	public Vector<Double> getSizeInMeters() {
		return Utilities.pixelsVectorToMeters(this.getSizeInPixels());
	}
	
	
	/**
	 * Returns whether the given pixel's position lies
	 * inside the game world.
	 * 
	 * @param pixel
	 * 			The pixel to check.
	 * 
	 * @return true if the pixel lies in the game world.
	 * 			| worldSize = this.getSizeInPixels()
	 * 			| pixel.x >= 0 && pixel.x < worldSize.x
	 * 			|	&& pixel.y >= 0 && pixel.y < worldSize.y
	 */
	public boolean pixelInWorld(Vector<Integer> pixel) {
		Vector<Integer> worldSize = this.getSizeInPixels();
		return pixel.x >= 0 && pixel.x < worldSize.x
				&& pixel.y >= 0 && pixel.y < worldSize.y;
	}
	
	
	/**
	 * Returns whether the given tile position lies in the game world.
	 * 
	 * @param tile
	 * 			The tile position to check.
	 * 
	 * @return true if the tile lies in the game world.
	 * 			| numberOfTiles = this.getNumberOfTiles()
	 * 			| tile.x >= 0 && tile.x < numberOfTiles.x
	 * 			|	&& tile.y >= 0 && tile.y < numberOfTiles.y
	 */
	public boolean tilePositionInWorld(Vector<Integer> tile) {
		Vector<Integer> numberOfTiles = this.getNumberOfTiles();
		return tile.x >= 0 && tile.x < numberOfTiles.x
				&& tile.y >= 0 && tile.y < numberOfTiles.y;
	}
	
	
	/**
	 * Returns the position of the bottom left pixel of the tile at the given tile position.
	 * 
	 * @param tile
	 * 			The position of the tile to check in tiles.
	 * 
	 * @return A 2D vector representing the bottom left pixel position of the specified tile.
	 * 			| new Vector<>(tile.x * this.getTileSize(), tile.y * this.getTileSize())
	 * 
	 * @pre The given tile must lie in the game world.
	 * 			| this.tileInWorld(tile)
	 */
	public Vector<Integer> getBottomLeftPixelOfTile(Vector<Integer> tile) {
		assert this.tilePositionInWorld(tile);
		return new Vector<>(tile.x * this.getTileSize(), tile.y * this.getTileSize());
	}
	
	
	/**
	 * Returns the position of the tile containing the given pixel's
	 * position in tiles.
	 * 
	 * @param pixel
	 * 			The pixel to get the containing tile of.
	 * 
	 * @return A 2D vector representing the position of the tile containing the given pixel.
	 * 			| new Vector<>(pixel.x / this.getTileSize(), pixel.y / this.getTileSize())
	 * 
	 * @pre The given pixel must lie in the game world.
	 * 			| this.pixelInWorld(pixel)
	 */
	public Vector<Integer> getTileContainingPixel(Vector<Integer> pixel) {
		assert this.pixelInWorld(pixel);
		return new Vector<>(pixel.x / this.getTileSize(), pixel.y / this.getTileSize());
	}
	
	
	/**
	 * Returns a list containing the positions of the tiles the given rectangle intersects with.
	 * The positions are expressed in tiles.
	 * 
	 * @param bottomLeftPixel
	 * 			The position of the bottom left pixel of the rectangle.
	 * 
	 * @param topRightPixel
	 * 			The position of the top right pixel of the rectangle.
	 * 
	 * @return An array of 2D vectors representing the positions of the tiles intersecting with the
	 * 			specified rectangle in tiles.
	 * 
	 * @pre The bottom left pixel must lie inside the game world.
	 * 			| this.pixelInWorld(bottomLeftPixel)
	 */
	public ArrayList<Vector<Integer>> getTilePositionsInRectangle(Vector<Integer> bottomLeftPixel, Vector<Integer> topRightPixel) {
		assert this.pixelInWorld(bottomLeftPixel);
		
		Vector<Integer> topRightRectPixel = Vector.add(topRightPixel, new Vector<>(-1, -1));
		
		Vector<Integer> bottomLeftTile = this.getTileContainingPixel(bottomLeftPixel);
		Vector<Integer> topRightTile = this.pixelInWorld(topRightRectPixel) ?
				this.getTileContainingPixel(topRightRectPixel) :
				this.getTileContainingPixel(Utilities.clipVectorInRange(new Vector<>(0, 0), this.getSizeInPixels(), topRightPixel));
		
		int blockWidth = topRightTile.x - bottomLeftTile.x + 1;
		int blockHeight = topRightTile.y - bottomLeftTile.y + 1;
		int numberOfTiles = blockWidth * blockHeight;
		
		ArrayList<Vector<Integer>> positions = new ArrayList<Vector<Integer>>(numberOfTiles);
		
		for (int x = bottomLeftTile.x; x <= topRightTile.x; x++) {
			for (int y = bottomLeftTile.y; y <= topRightTile.y; y++) {
				positions.add(new Vector<Integer>(x, y));
			}
		}
		
		return positions;
	}
	
	
	/**
	 * Returns the rectangle of the visible window.
	 * 
	 * @return An array representing the location of the bottom left and top right corners of the visible window in pixels.
	 * 		   The array uses the following format {bottomLeft.x, bottomLeft.y, topRight.x, topRight.y};
	 */
	@Basic
	@Immutable
	public int[] getVisibleWindow() {
		Vector<Integer> size = new Vector<>(visibleWindowTopRight.x - visibleWindowBottomLeft.x,
											visibleWindowTopRight.y - visibleWindowBottomLeft.y);
		Vector<Integer> pos = new Vector<>(mazub.getCenterInPixels().x - size.x / 2,
										   mazub.getCenterInPixels().y - size.y / 2);
		//Correction for the edges of the map
		pos = Utilities.clipVectorInRange(new Vector<>(0, 0), Vector.add(Vector.add(this.getSizeInPixels(), Vector.scale(size, -1)), new Vector<>(-1, -1)), pos);
		
		Vector<Integer> topRight = Vector.add(pos, size);
		int[] windowArray = {pos.x, pos.y, topRight.x, topRight.y};
		return windowArray;
	}
	
	
	/**
	 * Returns the position of the target tile in tiles.
	 * 
	 * @return A 2D vector representing the position of the target tile.
	 */
	@Basic
	@Immutable
	public Vector<Integer> getTargetTilePosition() {
		return this.targetTilePosition;
	}
	
	
	/**
	 * Returns whether the game has ended.
	 * 
	 * @return true if the game has ended.
	 * 			This means the player is dead or the player reached the target tile.
	 */
	public boolean isGameOver() {
		return this.getMazub().isHealthZero() || this.didPlayerWin();
	}
	
	
	/**
	 * Returns whether the player won the game.
	 * 
	 * @return true if the player won the game.
	 */
	public boolean didPlayerWin() {
		if (mazub.isHealthZero()) {
			return false;
		}
		ArrayList<Vector<Integer>> tilePositions = this.getTilePositionsInRectangle(
				mazub.getPositionInPixels(), mazub.getTopRightPixel());
		Vector<Integer> targetTilePos = this.getTargetTilePosition();
		for (Vector<Integer> pos : tilePositions) {
			if ((pos.x == targetTilePos.x) && (pos.y == targetTilePosition.y)) {
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Returns a grid representing the tile types of the tiles in 
	 * the game world.
	 * 
	 * @return A 2D array representing the game world's tiles' types.
	 */
	@Basic
	public TileType[][] getTileTypes() {
		return this.tiles.clone();
	}
	
	
	/**
	 * Returns the type of the tile that contains the given pixel's location.
	 * 
	 * @param pixel
	 * 			A 2D vector representing the position of the pixel to get the tile type of.
	 * 
	 * @return The type of the tile that contains the given pixel.
	 * 			| tilePosition = this.getTileContainingPixel(pixel)
	 * 			| this.getTileTypes()[tilePosition.x][tilePosition.y]
	 * 
	 * @throws ModelException
	 * 			Throws a ModelException when the given pixel does not lie in the game world.
	 * 			| !this.pixelInWorld(pixel)
	 */
	public TileType getTileTypeOfPixel(Vector<Integer> pixel) throws ModelException {
		if (!this.pixelInWorld(pixel)) {
			throw new ModelException("The pixel has to lie in the game world.");
		}
		Vector<Integer> tilePosition = this.getTileContainingPixel(pixel);
		return this.getTileTypes()[tilePosition.x][tilePosition.y];
	}
	
	
	/**
	 * Returns the type of the tile at the given tile position.
	 * 
	 * @param position
	 * 			The position of the tile.
	 * 
	 * @return The type of the tile at the given tile position.
	 * 			| this.getTileTypes()[position.x][position.y]
	 * 
	 * @throws ModelException
	 * 			Throws a model exception when the given tile position
	 * 			does not lie in the game world.
	 * 			| !this.tilePositionInWorld(position)
	 */
	public TileType getTileTypeOfTile(Vector<Integer> position) throws ModelException {
		if (!this.tilePositionInWorld(position)) {
			throw new ModelException("The tile position has to lie in the game world.");
		}
		return this.getTileTypes()[position.x][position.y];
	}
	
	
	/**
	 * Sets the type of the given tile position.
	 * 
	 * @param position
	 * 			The position of the tile to set in tiles.
	 * 
	 * @param type
	 * 			The type to set.
	 * 
	 * @pre The given tile position must lie in the game world.
	 * 			| this.tilePositionInWorld(position)
	 * 
	 * @post The tile at the given tile position will be the given tile.
	 * 			| new.getTile(new.getBottomLeftPixelOfTile(position)) == tile
	 */
	@Basic
	public void setTileType(Vector<Integer> position, TileType type) {
		assert this.tilePositionInWorld(position);
		this.tiles[position.x][position.y] = type;
	}
	
	
	/**
	 * Returns whether this game world contains the given
	 * game object.
	 * 
	 * @param gameObject
	 * 			The game object to check.
	 * 
	 * @return true if this game world contains the given game object.
	 */
	public boolean containsGameObject(GameObject gameObject) {
		//TODO: Deze documentatie moet nagekeken worden voor de @return en @Basic
		if (gameObject instanceof Mazub) {
			return this.getMazub() == (Mazub) gameObject;
		}
		return objects.contains(gameObject);
	}
	
	
	/**
	 * Removes the given game object from this game world.
	 * 
	 * @param gameObject
	 * 			The game object to remove.
	 * 
	 * @post This world will no longer hold a reference to gameObject
	 * 			| new.containsGameObject(gameObject) == false
	 */
	public void removeGameObject(GameObject gameObject) {
		if ((gameObject instanceof Mazub) && this.getMazub() == gameObject) {
			this.mazub = null;
		} else if (this.objects.contains(gameObject)) {
			this.objects.remove(gameObject);
		}
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
	 * @post The object will have this world as it's world.
	 * 			| (new object).getWorld() == new
	 * 
	 * @throw IllegalArgumentException
	 * 			Throws an IllegalArgumentException if the given GameObject is null or terminated.
	 * 			| object == null || object.isTerminated()
	 */
	public void addGameObject(GameObject object) throws IllegalArgumentException {
		if (object == null) {
			throw new IllegalArgumentException("The GameObject can't be null or terminated.");
		}
		
		if (object instanceof Mazub) {
			if (this.getMazub() != null) {
				throw new IllegalArgumentException("This game world already has a mazub.");
			}
			this.setMazub((Mazub) object);
		} else {
			this.objects.add(object);
			object.setWorld(this);
		}
	}
	
	
	/**
	 * Returns the mazub of this game world.
	 * 
	 * @return This game world's mazub.
	 */
	@Basic
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
	 * Returns whether this game world can have the given mazub as it's mazub.
	 * 
	 * @param mazub
	 * 			The mazub to check.
	 * 
	 * @return true if this game world can have the given mazub as it's mazub.
	 * 			The given mazub can not be null.
	 * 			| mazub != null
	 */
	public boolean canHaveAsMazub(@Raw Mazub mazub) {
		return mazub != null;
	}
	
	
	/**
	 * Return whether this world has a proper mazub.
	 * 
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
	 * Returns a set of all the GameObjects in this world including Mazub.
	 * @return A set of all the GameObjects in this world including Mazub.
	 */
	public Set<GameObject> getGameObjects() {
		Set<GameObject> objs = new HashSet<GameObject>(this.objects);
		if (this.hasProperMazub()){
			objs.add(this.getMazub());
		}
		return objs;
	}
	
	/**
	 * Returns a set containing the game objects in this world of the 
	 * given class.
	 * 
	 * @param cls
	 * 			The class of which to return game objects.
	 * 
	 * @return The collection of all object with class "type" in this game world.
	 */
	@Basic
	public <T extends GameObject> Set<T> getGameObjectsWithClass(Class<T> cls) {
		Set<T> objects = new HashSet<T>();
		for (GameObject obj : this.getGameObjects()){
			if (obj.getClass() == cls){
				objects.add(cls.cast(obj));
			}
		}
		return objects;
	}
	
	
	/**
	 * Returns a set of all game objects colliding with the given game object.
	 * 
	 * @param object
	 * 			The object to get colliding objects with.
	 * 
	 * @return A set containing all objects the given object collides with.
	 */
	public Set<GameObject> getObjectsCollidingWithObject(GameObject object) {
		
		HashSet<GameObject> collidingObjects = new HashSet<GameObject>();
		
		if (object == null) {
			return collidingObjects;
		}
		
		for (GameObject obj : this.getGameObjects()) {
			
			if (obj != object && object.collidesWithGameObjectClass(obj.getClass())
					&& object.doesOverlapWith(obj)) {
				
				collidingObjects.add(obj);
			}
		}
		
		return collidingObjects;
	}
	
	
	/**
	 * Returns a set of all tiles colliding with the given game object.
	 * 
	 * @param object
	 * 			The object to get colliding tiles with.
	 * 
	 * @return A set containing all tiles the given object collides with.
	 */
	public Set<Tile> getTilesCollidingWithObject(GameObject object) {
		
		Set<Tile> collidingTiles = new HashSet<Tile>();
		
		if (object == null) {
			return collidingTiles;
		}
		
		ArrayList<Vector<Integer>> positions = this.getTilePositionsInRectangle(object.getPositionInPixels(),
				Vector.add(object.getPositionInPixels(), object.getSizeInPixels()));
		
		for (Vector<Integer> position : positions) {
			TileType type = this.getTileTypeOfTile(position);
			if (object.collidesWithTileType(type)) {
				Tile tile = new Tile(position, this.getTileSize(), type);
				collidingTiles.add(tile);
			}
		}
		
		return collidingTiles;
	}
	
	
	/**
	 * Advances the time in this game world with the given time interval and updates
	 * position, speed and acceleration of all game objects in this game world.
	 * 
	 * @param dt
	 * 			The time that has passed in the game world since last calling this method.
	 * 
	 * @post All properties of this world and the game objects in this world
	 * 			will be updated accordingly.
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
		
		//Remove dead objects from the world
		Set<GameObject> deaths = new HashSet<>();
		for (GameObject object : this.objects) {
			if (!object.isAlive()){
				deaths.add(object);
			}
		}
		
		for (GameObject object : deaths) {
			if (!(object instanceof Gore)) {
				for (Sprite sprite : object.getGoreSprites()) {
					this.addGameObject(Gore.create(Utilities.pixelsVectorToMeters(object.getCenterInPixels()), sprite));
				}
			}
		}
		
		this.objects.removeAll(deaths);
	}
}
