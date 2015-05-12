package jumpingalien.model.gameobject;

/**
 * An interface for defining movement programmable behaviour.
 * This means a program can control the movement of the object
 * that implements this interface.
 * 
 * @author Rugen Heidbuchel, Menno Vanfrachem
 */
public interface MovementProgrammable {
	
	public void startRun(double direction);
	public void stopRun();
	
	public void startJump();
	public void stopJump();
	
	public void startDuck();
	public void stopDuck();
}
