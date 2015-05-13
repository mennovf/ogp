package jumpingalien.model.gameobject.programmable;

/**
 * An interface to indicate the object's running behaviour can be controlled by a program.
 * 
 * @author Rugen Heidbuchel, Menno Vanfrachem
 */
public interface RunProgrammable {

	/**
	 * Starts running in the given direction.
	 * 
	 * @param direction
	 * 			The direction in which to start running.
	 * 			1.0 means to the right, -1.0 means to the left.
	 */
	public void startRun(double direction);
	
	
	/**
	 * Stops the running.
	 */
	public void stopRun();
}
