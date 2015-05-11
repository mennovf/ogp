package jumpingalien.model.program.statement;

import jumpingalien.model.program.expression.Expression;

/**
 * A class represnting a while loop.
 * 
 * @author Rugen Heidbuchel, Menno Vanfrachem
 */
public class WhileLoop implements Statement {
	
	/**
	 * The expression to evaluate for the condition
	 */
	private final Expression<Boolean> condition;
	
	/**
	 * The statement to evaluate every iteration.
	 */
	private final Statement statement;
	
	/**
	 * Indicates whether the condition has been evaluated.
	 */
	private boolean conditionEvaluated = false;
	
	/**
	 * The result of the last condition evaluation.
	 */
	private boolean conditionEvaluation = true;
	
	
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
		this.statement = statement;
	}
	
	

	@Override
	public double advanceTime(double dt) {
		double timeLeft = dt;
		while (timeLeft >= Statement.defaultTime) {
			if (!conditionEvaluated && this.conditionEvaluation) {
				this.conditionEvaluation = condition.evaluate();
			}
			if (this.conditionEvaluation) {
				timeLeft = statement.advanceTime(timeLeft);
				if (statement.isFinished()) {
					this.conditionEvaluated = false;
				}
			}
		}
		return timeLeft;
	}

	
	@Override
	public boolean isFinished() {
		return !this.conditionEvaluation;
	}

	
	@Override
	public void reset() {
		this.conditionEvaluated = false;
		this.conditionEvaluation = true;
		statement.reset();
	}

}
