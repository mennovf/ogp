package ogp.framework.gui;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import ogp.framework.game.Game;

public abstract class GUI<TGame extends Game> {

	private final Frame frame;

	private final GUIOptions options;

	private final ScreenPanel<TGame> screenPanel;
	private final TimeKeeper fpsCounter;

	private final TGame game;

	protected GUI(TGame game, GUIOptions options) {
		this.game = game;
		this.options = options;
		this.frame = new Frame();
		this.screenPanel = createScreenPanel();
		this.fpsCounter = new TimeKeeper(options.getTargetFPS());
	}

	public GUIOptions getGUIOptions() {
		return options;
	}

	public PerformanceStats getCurrentStats() {
		return fpsCounter.getStats();
	}

	public Frame getFrame() {
		return frame;
	}

	public ScreenPanel<TGame> getScreenPanel() {
		return screenPanel;
	}

	protected abstract ScreenPanel<TGame> createScreenPanel();

	protected abstract String getTitle();

	public void start() {
		game.load();

		setupFrame();
		GraphicsDevice device = GraphicsEnvironment
				.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		try {
			if (options.isFullScreenEnabled() && device.isFullScreenSupported()) {
				device.setFullScreenWindow(frame);
			}
			frame.setVisible(true);
			screenPanel.initialize(game);
			frame.requestFocus();
			gameLoop();
		} finally {
			device.setFullScreenWindow(null);
		}
	}

	private volatile boolean stopped = false;

	private void gameLoop() {

		fpsCounter.start();

		while (!stopped) {

			double dt = fpsCounter.getTimeToAdvance();

			game.update(dt);

			fpsCounter.gameUpdated();
			
			if (stopped) break;

			screenPanel.update(dt);

			fpsCounter.framePainted();

			try {
				Thread.sleep(fpsCounter.getTimeToSleep());
			} catch (InterruptedException e) {
			}

			fpsCounter.frameDone();
		}
	}

	public void exit() {
		stopped = true;
		frame.setVisible(false);
		frame.dispose();
		System.exit(0);
	}

	protected void setupFrame() {
		frame.setIgnoreRepaint(true);
		frame.setFocusable(true);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				exit();
			}
		});
		frame.setResizable(false);
		frame.setTitle(getTitle());

		frame.add(screenPanel);
		screenPanel.setPreferredSize(getDefaultSize());
		screenPanel.setFocusable(false);
		frame.pack();
	}

	protected abstract Dimension getDefaultSize();

}
