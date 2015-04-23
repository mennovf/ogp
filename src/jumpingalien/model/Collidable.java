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
	public boolean isPassable();
}
