package jumpingalien.model;

public class Tile {

	private final Vector<Integer> position;
	private final TileType type;
	
	/**
	 * Creates a tile with the given tile type.
	 * 
	 * @param type
	 * 			The type of the tile.
	 */
	public Tile(Vector<Integer> position, TileType type) {
		
		this.position = position;
		this.type = type;
	}
	
	
	/**
	 * @return The position of this tile.
	 */
	public Vector<Integer> getPosition() {
		return this.position;
	}
	
	
	/**
	 * @return The position of this tile in meters.
	 */
	public Vector<Double> getPositionInMeters() {
		return Utilities.pixelsVectorToMeters(this.getPosition());
	}
	
	
	/**
	 * @return The type of this tile.
	 */
	public TileType getType() {
		return this.type;
	}
}
