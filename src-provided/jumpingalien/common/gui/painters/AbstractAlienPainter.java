package jumpingalien.common.gui.painters;

import jumpingalien.common.game.JumpingAlienGame;
import jumpingalien.common.gui.JumpingAlienGUIOptions;
import jumpingalien.common.gui.AlienGameScreen;
import ogp.framework.gui.Painter;
import ogp.framework.gui.camera.Camera;

public abstract class AbstractAlienPainter<ScreenType extends AlienGameScreen<?, ?>>
		extends Painter<ScreenType> {

	private final Camera mainCamera;
	private final JumpingAlienGame game;

	public AbstractAlienPainter(ScreenType screen) {
		super(screen);
		this.mainCamera = screen.getMainCamera();
		this.game = screen.getGame();
	}

	protected JumpingAlienGUIOptions getOptions() {
		return getScreen().getOptions();
	}

	protected Camera getMainCamera() {
		return mainCamera;
	}

	protected JumpingAlienGame getGame() {
		return game;
	}

	protected int getScreenWidth() {
		return getScreen().getScreenWidth();
	}

	protected int getScreenHeight() {
		return getScreen().getScreenHeight();
	}

}
