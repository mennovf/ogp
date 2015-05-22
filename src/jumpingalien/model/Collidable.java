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


	/**
	 * Returns the kind of overlap with the given collidable.
	 * 
	 * @param collidable
	 * 			| The collidable to get the kind of overlap with.
	 * 
	 * @return A 2D vector representing the kind of overlap.
	 * 			When collidable is a Tile object:
	 * 			| this.getKindOfOverlapWith((Tile) collidable)
	 * 			When collidable is a GameObject object:
	 * 			| this.getKindOfOverlapWith((GameObject) collidable)
	 * 
	 * @throws IllegalArgumentException
	 * 			Throws an IllegalArgumentException when the collidable's type is unknown.
	 * 			The type should be Tile or GameObject.
	 * 			| !(collidable instanceof Tile || collidable instanceof GameObject)
	 */
	default Vector<Integer> getKindOfOverlapWith(Collidable collidable) throws IllegalArgumentException {
		return this.getKindOfOverlapWithRect(collidable.getPositionInPixels(),
				collidable.getSizeInPixels());
	}
	
	
	/**
	 * Returns a 2D vector representing the kind of overlap of the given position and size
	 * with this game object. When the overlap comes from lower coördinates
	 * (on the left or on the bottom) a positive overlap value is returned.
	 * When the overlap comes from higher coördinates (on the right and on 
	 * the top) a negative value is returned.
	 * 
	 * @param otherPos
	 * 			The position of the other object.
	 * 
	 * @param otherSize
	 * 			The size of the other object.
	 * 
	 * @return A 2D vector representing the kind of overlap.
	 */
	default Vector<Integer> getKindOfOverlapWithRect(Vector<Integer> otherPos, Vector<Integer> otherSize) {
		Vector<Integer> selfPos = this.getPositionInPixels();
		Vector<Integer> selfSize = this.getSizeInPixels();
		
		Vector<Integer> overlap = new Vector<>(0, 0);
		
		if (otherPos.x <= selfPos.x) {
			overlap = overlap.setX(Math.min(selfSize.x, otherPos.x + otherSize.x - selfPos.x));
		} else {
			overlap = overlap.setX(Math.min(selfSize.x, otherPos.x - selfPos.x - selfSize.x));
		}
		
		if (otherPos.y <= selfPos.y) {
			overlap = overlap.setY(Math.min(selfSize.y, otherPos.y + otherSize.y - selfPos.y));
		} else {
			overlap = overlap.setY(Math.min(selfSize.y, otherPos.y - selfPos.y - selfSize.y));
		}
		
		return overlap;
	}
	
	
	/**
	 * Returns whether this game object overlaps with the given collidable.
	 * 
	 * @param collidable
	 * 			The collidable to check overlap with.
	 * 
	 * @return true if this game object overlaps with the given collidable.
	 * 			| !(collidable.getPositionInPixels().x + collidable.getSize().x <= self.getPositionInPixels().x
				|	|| collidable.getPositionInPixels().x >= self.getPositionInPixels().x + self.getSize().x
				|	|| collidable.getPositionInPixels().y + collidable.getSize().y <= self.getPositionInPixels().y
				|	|| collidable.getPositionInPixels().y >= self.getPositionInPixels().y + self.getSize().y)
	 */
	default boolean doesOverlapWith(Collidable collidable) {
		return this.doesOverlapWithRect(collidable.getPositionInPixels(), collidable.getSizeInPixels());
	}
	
	
	/**
	 * Returns whether this game object overlaps with the given rectangle.
	 * 
	 * @param pos
	 * 			The bottom left position of the rectangle.
	 * 
	 * @param size
	 * 			The size of the rectangle.
	 * 
	 * @return true if this game object and the rectangle overlap.
	 */
	default boolean doesOverlapWithRect(Vector<Integer> pos, Vector<Integer> size) {
		
		Vector<Integer> selfPos = this.getPositionInPixels();
		Vector<Integer> selfSize = this.getSizeInPixels();
		
		return !(pos.x + size.x <= selfPos.x
				|| pos.x >= selfPos.x + selfSize.x
				|| pos.y + size.y <= selfPos.y
				|| pos.y >= selfPos.y + selfSize.y);
	}
}
