package jumpingalien.model.program.statement;

import java.util.Map;

import jumpingalien.model.program.expression.Expression;

/**
 * A class represnting a while loop.
 * 
 * @author Rugen Heidbuchel, Menno Vanfrachem
 */
public class WhileLoop extends Loop {
	
	/**
	 * The expression to evaluate for the condition
	 */
	private final Expression<Boolean> condition;
	
	/**
	 * The statement to evaluate every iteration.
	 */
	private final Statement body;
	
	/**
	 * Indicates whether the condition has been evaluated.
	 */
	private boolean conditionEvaluated = false;
	
	/**
	 * The result of the last condition evaluation.
	 */
	private boolean conditionEvaluation = true;
	
	/**
	 * A boolean value to indicate this loop has been force finished.
	 */
	private boolean forceFinished = false;
	
	
	/**
	 * Creates a new while loop with the given condition expression
	 * and the given statement.
	 * 
	 * @param condition
	 * 			The condition expression. The loop will finish once this evaluates to false.
	 * 
	 * @param statement
	 * 			The statement to evaluate every iteration.
	 * 
	 * @throws NullPointerException
	 * 			Throws a NullPointerException when the given condition expression is null.
	 * 			| condition == null
	 * 			Throws a NullPointerException when the given statement is null.
	 * 			| statement == null
	 */
	public WhileLoop(Expression<Boolean> condition, Statement statement) throws NullPointerException {
		
		if (condition == null) {
			throw new NullPointerException("The given condition expression can not be null.");
		}
		if (statement == null) {
			throw new NullPointerException("The given loop statement can not be null.");
		}
		
		this.condition = condition;
		this.body = statement;
	}
	
	

	@Override
	public double advanceTime(double dt, Map<String, Object> globals, CallStack callStack) {
		double timeLeft = dt;
		while (timeLeft >= Statement.defaultTime && ! this.isFinished()) {
			if (!conditionEvaluated) {
				this.conditionEvaluation = condition.evaluate(globals, this.getOwnCallStack(callStack));
				this.conditionEvaluated = true;
				timeLeft -= Statement.defaultTime;
			} else if (this.conditionEvaluation && !body.isFinished()) {
				timeLeft = body.advanceTime(timeLeft, globals, this.getOwnCallStack(callStack));
				if (body.isFinished()) {
					this.conditionEvaluated = false;
					body.reset();
				}
			} else {
				break;
			}
		}
		return timeLeft;
	}

	
	@Override
	public boolean isFinished() {
		return this.forceFinished || (this.conditionEvaluated && !this.conditionEvaluation);
	}

	
	@Override
	public void reset() {
		this.conditionEvaluated = false;
		this.conditionEvaluation = true;
		this.forceFinished = false;
		body.reset();
	}


	@Override
	public void forceFinish() {
		this.forceFinished = true;
	}



	@Override
	public boolean isWellFormed(CallStack callStack) {
		return body.isWellFormed(this.getOwnCallStack(callStack));
	}

}
