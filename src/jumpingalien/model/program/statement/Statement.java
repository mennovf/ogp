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
	 * Breaks this statement. That means that this statement is forced to finish. If this 
	 * Statement is not breakable, then the break will be propagated to the parent Statement.
	 */
	default void executeBreak(CallStack callStack) {
		this.forceFinish();
		if (!this.isBreakable()) {
			callStack.pop().executeBreak(callStack);
		}
	}
	
	
	default CallStack getOwnCallStack(CallStack callStack) {
		return callStack.append(this);
	}
	
	boolean isWellFormed(CallStack callStack);
	

	/**
	 * Returns whether or not this statement allows an Action as a nested Statement.
	 * 
	 * @return whether or not this statement allows an Action as a nested Statement.
	 */
	@Immutable
	@Basic
	default boolean isActionAllowed() {
		return true;
	}
	
	
	/**
	 * Returns whether or not this Statement is breakable. Breakable means that a break statement
	 * stops propagating it's forceFinish up the stack at this statement.
	 * 
	 * @return whether or not this Statement is breakable.
	 */
	@Immutable
	@Basic
	default boolean isBreakable() {
		return false;
	}
}