package jumpingalien.model.gameobject;

/**
 * An interface to indicate the object's jump behaviour can be controlled by a program.
 * 
 * @author Rugen Heidbuchel, Menno Vanfrachem
 */
public interface JumpProgrammable {

	/**
	 * Starts a jump.
	 */
	public void startJump();
	
	
	/**
	 * Stops a jump.
	 */
	public void stopJump();
}
