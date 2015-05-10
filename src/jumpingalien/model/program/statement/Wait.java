package jumpingalien.model.program.statement;

public class Wait implements Statement {
	
	private final double waitTime;
	private double timeWaited;
	
	public Wait(double waitTime) {
		this.waitTime = waitTime;
		reset();
	}

	@Override
	public double advanceTime(double dt) {
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