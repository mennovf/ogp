package ogp.framework.gui;

/**
 * This class keeps track of how many time has passed between two frames, and
 * counts the framerate.
 * 
 * Not thread safe.
 */
public class TimeKeeper {

	private static final int UPDATE_PERIOD_NS = 500_000_000; // nanoseconds

	private long lastGameUpdateNS, lastFramePaintedNS, lastFrameDoneNS;
	private long totalTimeSinceLastUpdateNS;

	private long frameCount;
	private final double targetFPS;
	private double fps;

	private double timeToAdvanceS;

	private long totalPaintTimeNS, totalGameUpdateTimeNS, totalSleepTimeNS;
	private double avgGameUpdateTimeMS, avgPaintTimeMS, avgSleepTimeMS;

	public TimeKeeper(double targetFPS) {
		this.targetFPS = targetFPS;
	}

	public void start() {
		timeToAdvanceS = 1.0 / targetFPS;
		lastFrameDoneNS = now();
	}

	public double getTimeToAdvance() {
		return timeToAdvanceS;
	}

	public PerformanceStats getStats() {
		return new PerformanceStats(avgGameUpdateTimeMS, avgPaintTimeMS,
				avgSleepTimeMS, fps, targetFPS);
	}

	public long getTimeToSleep() {
		double targetTimeBetweenFramesNS = 1e9 / targetFPS;
		double elapsedForFrameNS = lastFramePaintedNS - lastFrameDoneNS;
		// return 95% of theoretical time to sleep; we may not have accounted for some time
		long sleepTimeMS = (long) (0.95 * (targetTimeBetweenFramesNS - elapsedForFrameNS) / 1e6);

		if (sleepTimeMS <= 0) {
			return 5; // give some time to other threads anyway
		} else {
			return sleepTimeMS;
		}
	}

	public void gameUpdated() {
		lastGameUpdateNS = now();
		totalGameUpdateTimeNS += lastGameUpdateNS - lastFrameDoneNS;
	}

	public void framePainted() {
		lastFramePaintedNS = now();
		totalPaintTimeNS += lastFramePaintedNS - lastGameUpdateNS;
	}

	public void frameDone() {
		long now = now();
		totalSleepTimeNS += now - lastFramePaintedNS;
		long elapsedSinceLastFrameNS = now - lastFrameDoneNS;
		lastFrameDoneNS = now;

		totalTimeSinceLastUpdateNS += elapsedSinceLastFrameNS;
		timeToAdvanceS = elapsedSinceLastFrameNS / 1e9;
		frameCount++;
		if (totalTimeSinceLastUpdateNS >= UPDATE_PERIOD_NS) {
			fps = frameCount / (totalTimeSinceLastUpdateNS / 1e9);

			avgGameUpdateTimeMS = totalGameUpdateTimeNS / (frameCount * 1e6);
			avgPaintTimeMS = totalPaintTimeNS / (frameCount * 1e6);
			avgSleepTimeMS = totalSleepTimeNS / (frameCount * 1e6);

			totalTimeSinceLastUpdateNS = totalGameUpdateTimeNS = totalPaintTimeNS = totalSleepTimeNS = 0;
			frameCount = 0;
		}
	}

	private long now() {
		return System.nanoTime();
	}

}
