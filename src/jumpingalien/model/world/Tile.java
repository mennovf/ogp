package jumpingalien.model.world;

import jumpingalien.model.Collidable;
import jumpingalien.model.Utilities;
import jumpingalien.model.Vector;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class representing a tile in the game world.
 * It contains information about the position, the size
 * and the type of the tile.
 * 
 * @invar The position of a tile will always be positive.
 * 			| this.getPositionInTiles() >= 0
 * 
 * @invar The size of a tile will always be bigger than zero.
 * 			| this.getSizeInPixels() > 0
 * 
 * @author Rugen Heidbuchel, Menno Vanfrachem
 */
@Value
public class Tile implements Collidable {

	/**
	 * The position of the tile in tiles (so not meters, nor pixels).
	 */
	private final Vector<Integer> position;
	
	/**
	 * The size of the tile in pixels.
	 */
	private final int size;
	
	/**
	 * The type of the tile.
	 */
	private final TileType type;
	
	/**
	 * Creates a tile with the given position, size and tile type.
	 * 
	 * @param position
	 * 			The position of the tile. The unit is size.
	 * 
	 * @param size
	 * 			The size of the tile and unit of position in pixels.
	 * 
	 * @param type
	 * 			The type of the tile.
	 * 
	 * @post The tile will have position as it's position.
	 * 			| new.getPosition() == position
	 * 
	 * @post The tile will have size as it's size.
	 * 			| new.getSizeInPixels() == size
	 * 
	 * @post The tile will have type as it's type.
	 * 			| new.getType() == type.
	 */
	public Tile(Vector<Integer> position, int size, TileType type) throws IllegalArgumentException {
		
		if (position.x < 0 || position.y < 0) {
			throw new IllegalArgumentException("The position has to be positive.");
		}
		
		if (size <= 0) {
			throw new IllegalArgumentException("The size should be bigger than zero.");
		}
		
		this.position = position;
		this.size = size;
		this.type = type;
	}
	
	
	/**
	 * Returns the position of this tile in tiles.
	 * 
	 * @return The position of this tile in tiles.
	 */
	@Basic @Immutable
	public Vector<Integer> getPositionInTiles() {
		return this.position;
	}
	
	
	/**
	 * Returns the position of this tile in pixels.
	 * 
	 * @return The position of this tile in pixels.
	 * 			| Vector.scale(this.getPosition(), this.getSize())
	 */
	@Immutable
	public Vector<Integer> getPositionInPixels() {
		return Vector.scale(this.getPositionInTiles(), this.getSideSizeInPixels());
	}
	
	
	/**
	 * Returns the position of this tile in meters.
	 * 
	 * @return The position of this tile in meters.
	 * 			| Utilities.pixelsVectorToMeters(this.getPositionInPixels())
	 */
	@Immutable
	public Vector<Double> getPositionInMeters() {
		return Utilities.pixelsVectorToMeters(this.getPositionInPixels());
	}
	
	
	/**
	 * Returns the position of the top right pixel of this tile in pixels.
	 * 
	 * @return The position of the top right pixel of this tile in pixels.
	 * 			| Vector.add(this.getPositionInPixels(), new Vector<>(this.getSize(), this.getSize()))
	 */
	@Immutable
	public Vector<Integer> getTopRightPixel() {
		return Vector.add(this.getPositionInPixels(), this.getSizeInPixels());
	}
	
	
	/**
	 * Returns the size of this tile in pixels.
	 * 
	 * @return The size of this tile in pixels.
	 */
	@Basic @Immutable
	public int getSideSizeInPixels() {
		return this.size;
	}
	
	
	/**
	 * Returns the size of this tile in pixels.
	 * 
	 * @return A 2D vector representing the size of this tile in pixels.
	 * 			| new Vector<>(this.getSizeInPixels(), this.getSizeInPixels())
	 */
	@Immutable
	public Vector<Integer> getSizeInPixels() {
		return new Vector<>(this.getSideSizeInPixels(), this.getSideSizeInPixels());
	}
	
	
	/**
	 * Returns the type of this tile.
	 * 
	 * @return The type of this tile.
	 */
	@Basic @Immutable
	public TileType getType() {
		return this.type;
	}
	
	
	/**
	 * Returns whether this tile is passable.
	 * 
	 * @return true if this tile is passable.
	 * 			| this.getType().isPassable()
	 */
	@Override
	@Immutable
	public boolean isPassable() {
		return this.getType().isPassable();
	}
}
