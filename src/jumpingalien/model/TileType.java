package jumpingalien.model;

import be.kuleuven.cs.som.annotate.*;

/**
 * An enum representing the type of a tile in the game world.
 * 
 * @author Rugen Heidbuchel, Menno Vanfrachem
 */
@Value
public enum TileType implements Collidable {
	AIR		(0, true),
	GROUND	(1, false),
	WATER	(2, true),
	MAGMA	(3, true);
	
	/**
	 * An id number for the tile type.
	 */
	private final int number;
	
	/**
	 * A boolean that indicates whether the type of terrain is passable or not.
	 */
	private final boolean passable;
	
	
	/**
	 * Constructs a tile type with the given number and passability.
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
	
	
	/**
	 * Returns the id number of this tile type.
	 * 
	 * @return The id number of this tile type.
	 */
	@Basic @Immutable
	public int getNumber() {
		return this.number;
	}
	
	
	/**
	 * Returns whether this tile type is passable.
	 * 
	 * @return true if this tile type is passable.
	 */
	@Override
	@Basic @Immutable
	public boolean isPassable() {
		return this.passable;
	}
}
