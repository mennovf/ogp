package jumpingalien.common.gui;

import jumpingalien.common.game.IActionHandler;
import jumpingalien.common.game.JumpingAlienGame;
import ogp.framework.gui.Screen;
import ogp.framework.gui.camera.CameraScreen;

public abstract class AlienGameScreen<TGame extends JumpingAlienGame, TGUI extends JumpingAlienGUI<TGame>>
		extends CameraScreen<TGame, TGUI> {

	protected AlienGameScreen(AlienScreenPanel<TGame> panel, TGUI gui,
			Screen<TGame, TGUI> previous) {
		super(panel, gui, previous);
	}

	public JumpingAlienGUIOptions getOptions() {
		return getGUI().getGUIOptions();
	}

	public IActionHandler getActionHandler() {
		return getGame().getActionHandler();
	}

	@Override
	public void screenStarted() {
		super.screenStarted();
		getGame().start();
	}

	@Override
	public void screenStopped() {
		super.screenStopped();
		getGUI().exit();
	}
}
