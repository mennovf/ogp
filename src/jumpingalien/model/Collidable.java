package jumpingalien.model;

/**
 * Provides basic info about the behaviour in collisions.
 * 
 * @author Rugen Heidbuchel, Menno Vanfrachem
 */
public interface Collidable {

	/**
	 * Returns whether or not the object is passable.
	 * 
	 * @return true if the object is passable.
	 */
	boolean isPassable();
	
	
	/**
	 * Returns the bottom left position of the bounding
	 * box of the collidable in pixels.
	 * 
	 * @return The bottom left positition of the bounding
	 * 			box of the collidable in pixels.
	 */
	Vector<Integer> getBoundingBoxPositionInPixels();
	
	
	/**
	 * Returns the size of the bounding box of the collidable
	 * in pixels.
	 * 
	 * @return The size of the bounding box of the collidable
	 * 			in pixels.
	 */
	Vector<Integer> getBoundingBoxSizeInPixels();
}
