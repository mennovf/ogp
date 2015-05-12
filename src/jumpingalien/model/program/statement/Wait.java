package jumpingalien.model.program.statement;

import java.util.Map;

/**
 * A class representing the 'Wait' action.
 */
public class Wait implements Statement {
	
	private final double waitTime;
	private double timeWaited;
	
	/**
	 * Constructs a new Wait Statement.
	 * 
	 * @param waitTime
	 * 			The amount of time to wait before finishing.
	 */
	public Wait(double waitTime) {
		this.waitTime = waitTime;
		reset();
	}

	@Override
	public double advanceTime(double dt, Map<String, Object> globals, CallStack callStack) {
		double timeLeft = waitTime - timeWaited;

		this.timeWaited += dt;

		if (dt < timeLeft) {
			return 0;
		}
		return (dt - timeLeft);
	}

	@Override
	public boolean isFinished() {
		return (this.timeWaited >= this.waitTime);
	}

	@Override
	public void reset() {
		this.timeWaited = 0;
	}
}