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
	
	
	
	public If(Expression<Boolean> condition, Statement trueBranch, Statement falseBranch) {
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
		return currentBranch().advanceTime(dt, globals, this.getOwnCallStack(callStack));
	}

	
	private Statement currentBranch() {
		return this.conditionEvaluation ? trueBranch : falseBranch;
	}

	
	@Override
	public boolean isFinished() {
		return this.forceFinished || (this.conditionEvaluated && this.currentBranch().isFinished());
	}

	
	@Override
	public void reset() {
		this.currentBranch().reset();
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
			&& this.falseBranch.isWellFormed(this.getOwnCallStack(callStack));
	}
}