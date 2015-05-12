package jumpingalien.model.program.statement;

import java.util.Map;
import java.util.Stack;

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
	double advanceTime(double dt, Map<String, Object> globals, Stack<Statement> callStack);

	
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
	 * Breaks this statement. This means it stops the execution of this statement
	 * and it's parents up until the first loop.
	 */
	default void executeBreak(Stack<Statement> callStack) {
		//TODO: Implement default break behaviour here
	}
}