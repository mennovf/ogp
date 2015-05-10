package jumpingalien.model.program.statement;

public interface Statement {
	static double defaultTime = 0.001;
	double advanceTime(double dt);
	boolean isFinished();
	void reset();
}