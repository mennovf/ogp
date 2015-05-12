package jumpingalien.model.program.statement;

import java.util.Map;

import jumpingalien.model.program.expression.Expression;

/**
 * A class representing the 'Wait' action.
 */
public class Wait implements Statement, Action {
	
	private double waitTime;
	private boolean waitTimeEvaluated = false;
	private final Expression<Double> durationExpression;
	private double timeWaited;
	
	
	
	/**
	 * Constructs a new Wait Statement.
	 * 
	 * @param waitTime
	 * 			The amount of time to wait before finishing.
	 */
	public Wait(Expression<Double> duration) {
		this.durationExpression = duration;
		reset();
	}

	
	
	@Override
	public double advanceTime(double dt, Map<String, Object> globals, CallStack callStack) {
		
		if (!this.waitTimeEvaluated) {
			this.waitTime = this.durationExpression.evaluate(globals, this.getOwnCallStack(callStack));
			this.waitTimeEvaluated = true;
		}
		double timeLeft = this.waitTime - this.timeWaited;

		this.timeWaited += dt;

		if (dt < timeLeft) {
			return 0;
		}
		return (dt - timeLeft);
	}

	
	@Override
	public boolean isFinished() {
		return this.waitTimeEvaluated && (this.timeWaited >= this.waitTime);
	}

	
	@Override
	public void reset() {
		this.timeWaited = 0;
		this.waitTimeEvaluated = false;
	}

	
	@Override
	public void forceFinish() {
		this.waitTimeEvaluated = true;
		this.timeWaited = this.waitTime;
	}
}