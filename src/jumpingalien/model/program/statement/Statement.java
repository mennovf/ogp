package jumpingalien.model.program.statement;

/**
 * An interface for statements.
 */
public interface Statement {
	/**
	 * The default amount of time a statement 'consumes'.
	 */
	public static double defaultTime = 0.001;
	
	
	/**
	 * Advances the time and executes statements if dt is big enough.
	 * 
	 * @param dt
	 * 			The amount of time to advance.
	 * 
	 * @return
	 * 			The amount of time *not* consumed by advancing this statement.
	 */
	double advanceTime(double dt);

	/**
	 * Returns whether this statement has finished executing.
	 * 
	 * @return whether this statement has finished executing.
	 */
	boolean isFinished();

	/**
	 * Resets the internal state of this statement.
	 * 
	 * @post The statement isn't finished.
	 * 			| new.isFinished() == false
	 */
	void reset();
}