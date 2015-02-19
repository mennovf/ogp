package ogp.framework.gui;

public class PerformanceStats {

	public final double avgGameUpdateTime;
	public final double avgPaintTime;
	
	public final double fps;
	
	public final double targetFPS;
	
	public final double avgSleepTime;
	
	public PerformanceStats(double game, double paint, double sleep, double fps, double targetFPS) {
		this.avgGameUpdateTime = game;
		this.avgPaintTime = paint;
		this.avgSleepTime = sleep;
		this.fps = fps;
		this.targetFPS = targetFPS;
	}
	
	@Override
	public String toString() {
		return String.format("Game update %3.0fms | Paint %3.0fms | Sleep %3.0fms | FPS %.1f (target %.0f)", avgGameUpdateTime, avgPaintTime, avgSleepTime, fps, targetFPS);
	}
	
}
