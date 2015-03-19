package jumpingalien.common.game;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

import jumpingalien.util.ModelException;
import ogp.framework.command.Command;
import ogp.framework.game.Game;
import ogp.framework.messages.Message;
import ogp.framework.messages.MessageType;

public abstract class JumpingAlienGame extends Game {

	private static final int MAX_MISSED_DEADLINES = 4;

	private static final double MAX_TIME_STEP = 0.200;

	private IActionHandler handler;

	private int visibleScreenWidth = -1;
	private int visibleScreenHeight = -1;

	private double elapsedTime = 0;
	private boolean running;

	private final Object facade;

	protected JumpingAlienGame(JumpingAlienGameOptions options, Object facade) {
		super(options);
		this.handler = createActionHandler();
		this.worldInfoProvider = createWorldInfoProvider();
		this.alienInfoProvider = createAlienInfoProvider();
		this.facade = facade;
	}

	protected Object getFacade() {
		return facade;
	}

	@Override
	public JumpingAlienGameOptions getOptions() {
		return (JumpingAlienGameOptions) super.getOptions();
	}
	
	public void restart() {
		start();
	}

	@Override
	public void start() {
		if (visibleScreenWidth < 0 || visibleScreenHeight < 0) {
			throw new IllegalStateException("Visible screen size not set");
		}

		createModel();

		running = true;
	}

	public void setPause(boolean value) {
		running = !value;
	}
	
	public void stop() {
		running = false;
	}

	protected abstract void createModel();

	protected abstract IActionHandler createActionHandler();

	/**
	 * Hack to skip first call to update after game has started (time interval
	 * too large due to initial screen painting)
	 */
	private boolean firstUpdate = true;

	@Override
	protected void doUpdate(double dt) {
		if (isRunning()) {
			if (!firstUpdate) {
				dt = applyTimescale(dt);
				executePendingCommands();
				try {
					advanceTime(dt);
					elapsedTime += dt;
				} catch (ModelException e) {
					addMessage(new Message(MessageType.ERROR, e.getMessage()));
					System.out.println("Could not advance time by dt=" + dt
							+ ": " + e.getMessage());
					e.printStackTrace();
				}
			}
			firstUpdate = false;
		}
	}

	/**
	 * Scale the given time interval based on the game options.
	 * 
	 * If the scaled time in MAX_MISSED_DEADLINES subsequent invocations exceeds
	 * MAX_TIME_STEP, the time scale is adapted.
	 * 
	 * The returned time interval is always guaranteed to be smaller than or
	 * equal to MAX_TIME_STEP.
	 */
	protected double applyTimescale(double dt) {
		double scaledDT = dt / getOptions().getTimescale();
		if (scaledDT > MAX_TIME_STEP) {
			/*
			 * System.out.println(String.format(
			 * "Time interval dt = %.3f limited to %.3f seconds.", scaledDT,
			 * MAX_TIME_STEP));
			 */
			scaledDT = MAX_TIME_STEP;

			deadlineMissed(dt);
		} else {
			deadlineMet(dt);
		}
		return scaledDT;
	}

	private int nbSubsequentDeadlinesMissed = 0;
	private double totalMissedTime;

	private void deadlineMissed(double dt) {
		nbSubsequentDeadlinesMissed++;
		totalMissedTime += dt;
		if (nbSubsequentDeadlinesMissed >= MAX_MISSED_DEADLINES) {
			adjustTimescale(totalMissedTime / nbSubsequentDeadlinesMissed);
			nbSubsequentDeadlinesMissed = 0;
			totalMissedTime = 0;
		}
	}

	private void deadlineMet(double dt) {
		nbSubsequentDeadlinesMissed = 0;
		totalMissedTime = 0;
	}

	private void adjustTimescale(double dt) {
		double newScale = 1.05 * dt / MAX_TIME_STEP; // 5% higher than
														// theoretically
														// necessary timescale
		getOptions().setTimescale(newScale);
		System.out
				.println(String
						.format("Warning: Your advanceTime code seems too slow to ensure dt <= %.3f with the current framerate.\n         In-game time will run slower than real time (1 in-game second = %.2f real-world seconds)",
								MAX_TIME_STEP, newScale));
	}

	public boolean isRunning() {
		return running;
	}

	protected abstract void advanceTime(double dt);

	public double getElapsedTime() {
		return elapsedTime;
	}

	public void setVisibleScreenSize(int width, int height) {
		this.visibleScreenWidth = width;
		this.visibleScreenHeight = height;
	}

	public IActionHandler getActionHandler() {
		return handler;
	}

	public int getVisibleScreenWidth() {
		return visibleScreenWidth;
	}

	public int getVisibleScreenHeight() {
		return visibleScreenHeight;
	}

	private final AlienInfoProvider alienInfoProvider;

	protected abstract AlienInfoProvider createAlienInfoProvider();

	private final WorldInfoProvider worldInfoProvider;

	protected abstract WorldInfoProvider createWorldInfoProvider();

	public AlienInfoProvider getAlienInfoProvider() {
		return alienInfoProvider;
	}

	public WorldInfoProvider getWorldInfoProvider() {
		return worldInfoProvider;
	}

	@Override
	public void addCommand(Command command) {
		super.addCommand(command);
	}

	private final Consumer<ModelException> errorHandler = new Consumer<ModelException>() {

		@Override
		public void accept(ModelException error) {
			addMessage(new Message(MessageType.ERROR, error.getMessage()));
			error.printStackTrace();
		}
	};

	protected <T> Optional<T> catchErrorGet(Supplier<T> action) {
		try {
			return Optional.ofNullable(action.get());
		} catch (ModelException e) {
			errorHandler.accept(e);
			return Optional.empty();
		}
	}

	public <T> void catchErrorAction(Runnable action) {
		try {
			action.run();
		} catch (ModelException e) {
			errorHandler.accept(e);
		}
	}

}
