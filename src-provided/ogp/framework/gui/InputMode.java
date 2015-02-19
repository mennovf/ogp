package ogp.framework.gui;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import ogp.framework.game.Game;

public class InputMode<TGame extends Game, TGUI extends GUI<TGame>> implements KeyListener,
		MouseListener, MouseMotionListener {

	private final Screen<TGame, TGUI> screen;
	private final InputMode<TGame, TGUI> previous;

	public InputMode(Screen<TGame, TGUI> screen, InputMode<TGame, TGUI> previous) {
		this.screen = screen;
		this.previous = previous;
	}

	public Screen<TGame, TGUI> getScreen() {
		return screen;
	}

	public void leaveInputMode() {
		if (previous == null) {
			getScreen().switchInputMode(getScreen().createDefaultInputMode());
		} else {
			getScreen().switchInputMode(previous);
		}
	}

	public void paintOverlay(Graphics2D g) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}
}
