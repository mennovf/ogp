package jumpingalien.model.program.statement;

import java.util.Map;

import be.kuleuven.cs.som.annotate.*;

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
	 * @param globals TODO
	 * 
	 * @return The amount of time *not* consumed by advancing this statement.
	 */
	double advanceTime(double dt, Map<String, Object> globals, CallStack callStack);

	
	/**
	 * Returns whether this statement has finished executing.
	 */
	@Basic
	boolean isFinished();

	
	/**
	 * Resets the internal state of this statement.
	 * 
	 * @post The statement isn't finished.
	 * 			| new.isFinished() == false
	 */
	void reset();
	
	
	/**
	 * Forces this statement to finish.
	 * 
	 * @post This statement will be finished.
	 * 			| new.isFinished()
	 */
	void forceFinish();
	
	
	/**
	 * Breaks this statement. This means it stops the execution of this statement
	 * and it's parents up until the first loop.
	 */
	default void executeBreak(CallStack callStack) {
		callStack.pop().executeBreak(callStack);
	}
	
	
	default CallStack getOwnCallStack(CallStack callStack) {
		return callStack.append(this);
	}
}