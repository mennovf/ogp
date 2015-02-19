package jumpingalien.common.gui;

import java.awt.event.KeyEvent;

import jumpingalien.common.game.IActionHandler;
import jumpingalien.common.game.JumpingAlienGame;
import ogp.framework.gui.InputMode;

public class AlienInputMode<TG extends JumpingAlienGame, TGUI extends JumpingAlienGUI<TG>>
		extends InputMode<TG, TGUI> {
	/**
	 * 
	 */
	private final IActionHandler handler;
	private final InputAction jump, moveLeft, moveRight, duck;

	public AlienInputMode(AlienGameScreen<TG, TGUI> screen,
			AlienInputMode<TG, TGUI> previous) {
		super(screen, previous);
		this.handler = screen.getActionHandler();
		jump = new InputAction(handler::startJump, handler::endJump);
		moveLeft = new InputAction(handler::startMoveLeft, handler::endMoveLeft);
		moveRight = new InputAction(handler::startMoveRight,
				handler::endMoveRight);
		duck = new InputAction(handler::startDuck, handler::endDuck);
	}

	@Override
	public AlienGameScreen<TG, TGUI> getScreen() {
		return (AlienGameScreen<TG, TGUI>) super.getScreen();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		super.keyReleased(e);
		switch (e.getKeyCode()) {
		case KeyEvent.VK_ESCAPE:
			getScreen().close();
			break;
		case KeyEvent.VK_SPACE:
		case KeyEvent.VK_UP:
		case KeyEvent.VK_KP_UP:
			jump.end();
			break;
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_KP_LEFT:
			moveLeft.end();
			break;
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_KP_RIGHT:
			moveRight.end();
			break;
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_KP_DOWN:
			duck.end();
			break;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
		switch (e.getKeyCode()) {
		case KeyEvent.VK_P:
			getScreen().getGame().setPause(true);
			getScreen().switchInputMode(new PausedInputMode(getScreen(), this));
			break;
		case KeyEvent.VK_SPACE:
		case KeyEvent.VK_UP:
		case KeyEvent.VK_KP_UP:
			jump.start();
			break;
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_KP_LEFT:
			moveLeft.start();
			break;
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_KP_RIGHT:
			moveRight.start();
			break;
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_KP_DOWN:
			duck.start();
			break;
		}
	}

	private class PausedInputMode extends AlienInputMode<TG, TGUI> {

		public PausedInputMode(AlienGameScreen<TG, TGUI> screen,
				AlienInputMode<TG, TGUI> previous) {
			super(screen, previous);
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				getScreen().close();
			}
			getScreen().getGame().setPause(false);
			leaveInputMode();
		}

	}
}
