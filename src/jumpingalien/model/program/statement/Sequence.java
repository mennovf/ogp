package jumpingalien.model.program.statement;

import java.util.Map;

import be.kuleuven.cs.som.annotate.*;


/**
 * A class handling the execution of a list of statements.
 */
public class Sequence implements Statement {
	
	/**
	 * The list of statements.
	 */
	private Statement[] statements;
	
	/**
	 * The index of the current statement.
	 */
	private int currentStatementIndex;
	
	/**
	 * A boolean to indicate this statement has been force finished.
	 */
	private boolean forceFinished = false;
	
	
	
	/**
	 * Constructs a new Statements object.
	 * 
	 * @param statements
	 * 			The statements to control.
	 * 
	 * @throws NullPointerException
	 * 			Throws a NullPointerException when the given statements list is null.
	 */
	public Sequence(Statement[] statements) throws NullPointerException {
		
		if (statements == null) {
			throw new NullPointerException("The given statements list is null.");
		}
		
		this.statements = statements;
		reset();
	}

	
	
	/**
	 * Returns The statement being executed.
	 */
	@Basic
	private Statement currentStatement() {
		return this.statements[this.currentStatementIndex];
	}

	
	@Override
	public double advanceTime(double dt, Map<String, Object> globals, CallStack callStack) {
		double timeLeft = dt;
		if (!this.forceFinished) {
			while (timeLeft >= Statement.defaultTime && ! this.isFinished()) {
				timeLeft = this.currentStatement().advanceTime(timeLeft, globals, this.getOwnCallStack(callStack));
		        if (this.currentStatement().isFinished()) {
		                this.currentStatementIndex += 1;
		        }
			}
		}
		return timeLeft;
	}

	
	@Override
	public boolean isFinished() {
		return this.forceFinished || (currentStatementIndex == this.statements.length);
	}

	
	@Override
	public void reset() {
		for (Statement statement: this.statements) {
			statement.reset();
		}
		this.currentStatementIndex = 0;
		this.forceFinished = false;
	}

	
	@Override
	public void forceFinish() {
		this.forceFinished = true;
	}



	@Override
	public boolean isWellFormed(CallStack callStack) {
		for (Statement statement : this.statements) {
			if (!statement.isWellFormed(this.getOwnCallStack(callStack))) {
				return false;
			}
		}
		return true;
	}
}