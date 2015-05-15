package jumpingalien.model;

import be.kuleuven.cs.som.annotate.Basic;

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
	@Basic
	Vector<Integer> getPositionInPixels();
	
	
	/**
	 * Returns the size of the bounding box of the collidable
	 * in pixels.
	 * 
	 * @return The size of the bounding box of the collidable
	 * 			in pixels.
	 */
	@Basic
	Vector<Integer> getSizeInPixels();
	
	
	/**
	 * Returns the center of the collidable's bounding box in pixels.
	 * 
	 * @return The center of the collidable's bounding box in pixels.
	 * 			| new Vector<>(getPositionInPixels().x + getSizeInPixels().x/2, getPositionInPixels().y + getSizeInPixels().y/2)
	 */
	default Vector<Integer> getCenterInPixels() {
		return new Vector<>(getPositionInPixels().x + getSizeInPixels().x/2, getPositionInPixels().y + getSizeInPixels().y/2);
	}
}
