package jumpingalien.model;

/**
 * @author Rugen en Menno
 * 
 * An enum representing the type of a tile in the game world.
 */
public enum TileType {
	AIR		(0, true),
	GROUND	(1, false),
	WATER	(2, true),
	MAGMA	(3, true);
	
	/**
	 * An id number for the tile type.
	 */
	public final int number;
	
	/**
	 * A boolean that indicates whether the type of terrain is passable or not.
	 */
	public final boolean passable;
	
	
	/**
	 * Constructs a tile type with the given number and passibility.
	 * 
	 * @param number
	 * 			The id number of the tile type.
	 * 
	 * @param passable
	 * 			Whether the tile type is passable or not.
	 */
	TileType(int number, boolean passable) {
		this.number = number;
		this.passable = passable;
	}
	
	
	/**
	 * Creates a new tile type from a tile id number.
	 * 
	 * @param number
	 * 			The tile id number.
	 * 
	 * @return The tile type with the given id number.
	 * 
	 * @throws IllegalArgumentException
	 * 			Throws an IllegalArgumentException when there is no tile type
	 * 			with the given id number.
	 */
	public static TileType tileTypeForNumber(int number) throws IllegalArgumentException {
		
		for (TileType type : TileType.values()) {
			
			if (type.number == number) {
				return type;
			}
		}
		
		throw new IllegalArgumentException("The given number does not conform to a known tile type.");
	}
}
