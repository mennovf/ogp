package ogp.framework.gui;

import java.awt.Frame;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import ogp.framework.game.Game;

public abstract class Screen<TGame extends Game, TGUI extends GUI<TGame>> {

	private final ScreenPanel<TGame> panel;
	private final Frame frame;
	private InputMode<TGame, TGUI> currentInputMode;
	private final TGUI gui;
	
	private final Screen<TGame, TGUI> previous;
	
	private final List<Painter<?>> painters = new ArrayList<>();

	protected Screen(ScreenPanel<TGame> panel, TGUI gui,
			Screen<TGame, TGUI> previous) {
		this.panel = panel;
		this.gui = gui;
		this.frame = gui.getFrame();
		this.previous = previous;
	}
	
	protected abstract void setupPainters();
	
	protected void addPainter(Painter<?> painter) {
		painters.add(painter);
	}
	
	protected List<Painter<?>> getPainters() {
		return painters;
	}
	
	protected ScreenPanel<TGame> getPanel() {
		return panel;
	}

	public PerformanceStats getFPSStats() {
		return gui.getCurrentStats();
	}

	public TGame getGame() {
		return panel.getGame();
	}

	protected TGUI getGUI() {
		return gui;
	}

	public Screen<TGame, TGUI> getPrevious() {
		return previous;
	}

	public void close() {
		if (previous != null) {
			getPanel().switchToScreen(previous);
		} else {
			getGUI().exit();
		}
	}
	
	public final void onScreenStarted() {
		switchInputMode(createDefaultInputMode());
		screenStarted();
		setupPainters();
	}
	
	public final void onScreenStopped() {
		switchInputMode(null);
		screenStopped();
	}

	protected void screenStarted() {
	}

	protected void screenStopped() {
	}

	public int getScreenWidth() {
		return getPanel().getWidth();
	}

	public int getScreenHeight() {
		return getPanel().getHeight();
	}

	protected abstract InputMode<TGame, TGUI> createDefaultInputMode();

	public InputMode<TGame, TGUI> getCurrentInputMode() {
		return currentInputMode;
	}

	public void switchInputMode(InputMode<TGame, TGUI> newMode) {
		if (currentInputMode != null) {
			frame.removeKeyListener(currentInputMode);
			frame.removeMouseListener(currentInputMode);
			frame.removeMouseMotionListener(currentInputMode);
		}
		currentInputMode = newMode;
		if (newMode != null) {
			frame.addKeyListener(newMode);
			frame.addMouseListener(newMode);
			frame.addMouseMotionListener(newMode);
		}
	}

	public void paintFrame(Graphics2D g) {
		for (Painter<?> p : getPainters()) {
			p.paintScreenPre(g);
		}
		for (Painter<?> p : getPainters()) {
			p.paintInWorld(g);
		}
		for (Painter<?> p : getPainters()) {
			p.paintScreenPost(g);
		}
	}

	public abstract void updateState(double dt);
}
