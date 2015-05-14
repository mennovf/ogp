package jumpingalien.model.program;

public interface Program {
	/**
	 * Advance the state controlled by this program during a period of time dt.
	 * 
	 * @param dt
	 * 			The game-time that has passed since the last call of this method.
	 */
	void advanceTime(double dt);
}