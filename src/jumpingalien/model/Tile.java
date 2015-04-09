package jumpingalien.model;

public class Tile {

	private final Vector<Integer> position;
	private final int size;
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
	 */
	public Tile(Vector<Integer> position, int size, TileType type) {
		
		this.position = position;
		this.size = size;
		this.type = type;
	}
	
	
	/**
	 * @return The position of this tile.
	 */
	public Vector<Integer> getPosition() {
		return this.position;
	}
	
	
	/**
	 * @return The position of this tile in pixels
	 */
	public Vector<Integer> getPositionInPixels() {
		return Vector.scale(this.getPosition(), this.getSize());
	}
	
	
	/**
	 * @return The position of this tile in meters.
	 */
	public Vector<Double> getPositionInMeters() {
		return Utilities.pixelsVectorToMeters(this.getPositionInPixels());
	}
	
	
	/**
	 * @return The size of this tile in pixels.
	 */
	public int getSize() {
		return this.size;
	}
	
	
	/**
	 * @return The type of this tile.
	 */
	public TileType getType() {
		return this.type;
	}
}
