package jumpingalien.model;

/**
 * A class to manage settings for the game.
 * 
 * @author Rugen Heidbuchel, Menno Vanfrachem
 */
public class Settings {

	
	// Gore
	
	/**
	 * A boolean value to determine whether gore should be used.
	 */
	public static final boolean goreEnabled = true;
	
	/**
	 * The number of blood particles used in the explosion of a game object.
	 * Note that this number can be overriden by the subclass.
	 * -> Right now this only applies to Slime.
	 */
	public static final int gameObjectNumberOfBloodParticles = 60;
	
	/**
	 * The number of blood particles used in the explosion of a shark.
	 * These blood particles are used next to the shark's gore particles.
	 */
	public static final int sharkNumberOfBloodParticles = 10;
}
