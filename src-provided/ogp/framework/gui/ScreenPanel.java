package ogp.framework.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import ogp.framework.game.Game;

@SuppressWarnings("serial")
public abstract class ScreenPanel<T extends Game> extends Canvas {

	private BufferStrategy bufferStrategy;

	private Screen<T, ? extends GUI<T>> currentScreen;

	private T game;

	public ScreenPanel() {
		setIgnoreRepaint(true);
	}

	public T getGame() {
		return game;
	}

	public void initialize(T game) {
		this.game = game;
		createBufferStrategy(2);
		bufferStrategy = getBufferStrategy();
		switchToScreen(createInitialScreen());
	}

	protected abstract Screen<T, ? extends GUI<T>> createInitialScreen();

	public void switchToScreen(Screen<T, ? extends GUI<T>> newScreen) {
		if (currentScreen != null) {
			currentScreen.onScreenStopped();
		}
		currentScreen = newScreen;
		if (newScreen != null) {
			newScreen.onScreenStarted();
		}
	}

	public void update(double dt) {
		updateCurrentScreen(dt);
		paintCurrentScreen();
	}

	protected void updateCurrentScreen(double dt) {
		if (currentScreen != null) {
			currentScreen.updateState(dt);
		}
	}

	protected void paintCurrentScreen() {
		Graphics2D g = null;
		try {
			g = (Graphics2D) bufferStrategy.getDrawGraphics();
			g.setBackground(getBackgroundColor());
			g.clearRect(0, 0, getWidth(), getHeight());
			if (currentScreen != null) {
				currentScreen.paintFrame(g);
			}
			bufferStrategy.show();
			Toolkit.getDefaultToolkit().sync();
		} finally {
			if (g != null)
				g.dispose();
		}
	}

	protected Color getBackgroundColor() {
		return Color.BLACK;
	}
}
