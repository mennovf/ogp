package jumpingalien.model.gameobject.programmable;

/**
 * An interface to indicate the object's ducking behaviour can be controlled by a program.
 * 
 * @author Rugen Heidbuchel, Menno Vanfrachem
 */
public interface DuckProgrammable {

	/**
	 * Starts ducking.
	 */
	public void startDuck();
	
	/**
	 * Stops ducking.
	 */
	public void stopDuck();
}
