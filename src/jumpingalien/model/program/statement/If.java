package jumpingalien.model.program.statement;

import java.util.Map;

import jumpingalien.model.program.expression.Expression;

/**
 * A class representing an if statement.
 */
public class If implements Statement {
	
	private boolean conditionEvaluated = false;
	private boolean conditionEvaluation = false;
	private boolean forceFinished = false;
	
	private Expression<Boolean> condition;
	private Statement trueBranch;
	private Statement falseBranch;
	
	
	
	/**
	 * Creates a new if statement with the given properties.
	 * 
	 * @param condition
	 * 			The condition of the if statement.
	 * 
	 * @param trueBranch
	 * 			The statement that needs to be executed when the condition
	 * 			evaluates to true.
	 * 
	 * @param falseBranch
	 * 			The statement that needs to be executed when the condition
	 * 			evaluates to false.
	 * 
	 * @throws NullPointerException
	 * 			Throws a NullPointerException when either of the condition or the trueBranch is null.
	 * 			| condition == null || trueBranch == null
	 */
	public If(Expression<Boolean> condition, Statement trueBranch, Statement falseBranch) throws NullPointerException {
		
		if (condition == null) {
			throw new NullPointerException("The condition can not be null.");
		}
		if (trueBranch == null) {
			throw new NullPointerException("The true branch can not be null.");
		}
		
		this.condition = condition;
		this.trueBranch = trueBranch;
		this.falseBranch = falseBranch;
	}

	
	
	@Override
	public double advanceTime(double dt, Map<String, Object> globals, CallStack callStack) {
		if (! this.conditionEvaluated) {
			this.conditionEvaluation = condition.evaluate(globals, this.getOwnCallStack(callStack));
			this.conditionEvaluated = true;
		}
		if (this.currentBranch() == null) {
			return dt;
		}
		return this.currentBranch().advanceTime(dt, globals, this.getOwnCallStack(callStack));
	}

	
	private Statement currentBranch() {
		return this.conditionEvaluation ? trueBranch : falseBranch;
	}

	
	@Override
	public boolean isFinished() {
		return this.forceFinished || (this.conditionEvaluated && (this.currentBranch() == null || this.currentBranch().isFinished()));
	}

	
	@Override
	public void reset() {
		if (this.currentBranch() != null) {
			this.currentBranch().reset();
		}
		this.conditionEvaluated = false;
		this.conditionEvaluation = false;
		this.forceFinished = false;
	}

	
	@Override
	public void forceFinish() {
		this.forceFinished = true;
	}



	@Override
	public boolean isWellFormed(CallStack callStack) {
		return this.trueBranch.isWellFormed(this.getOwnCallStack(callStack))
			&& (this.falseBranch == null || this.falseBranch.isWellFormed(this.getOwnCallStack(callStack)));
	}
}