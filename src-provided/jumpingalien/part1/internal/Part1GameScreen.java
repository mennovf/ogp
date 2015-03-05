package jumpingalien.part1.internal;

import java.awt.Color;
import java.util.Optional;

import jumpingalien.common.gui.AlienInputMode;
import jumpingalien.common.gui.AlienGameScreen;
import jumpingalien.common.gui.AlienScreenPanel;
import jumpingalien.common.gui.painters.AxesPainter;
import jumpingalien.common.gui.painters.DebugInfoPainter;
import jumpingalien.common.gui.painters.HistoryPainter;
import jumpingalien.common.gui.painters.PixelPainter;
import jumpingalien.common.gui.painters.PlayerPainter;
import jumpingalien.common.gui.painters.WorldBorderPainter;
import ogp.framework.gui.MessagePainter;
import ogp.framework.gui.Screen;
import ogp.framework.gui.SolidBackgroundPainter;
import ogp.framework.gui.camera.Camera;
import ogp.framework.gui.camera.Camera.Rectangle;

public class Part1GameScreen extends
		AlienGameScreen<JumpingAlienGamePart1, JumpingAlienGUIPart1> {

	private static final int DEBUG_PIXELS_ZOOM = 8;

	private Camera mainCamera;

	public Part1GameScreen(AlienScreenPanel<JumpingAlienGamePart1> panel,
			JumpingAlienGUIPart1 gui,
			Screen<JumpingAlienGamePart1, JumpingAlienGUIPart1> previous) {
		super(panel, gui, previous);
	}

	@Override
	public Part1Options getOptions() {
		return (Part1Options) super.getOptions();
	}

	@Override
	protected void setupCameras() {
		Optional<int[]> worldSize = getGame().getWorldInfoProvider()
				.getWorldSize();
		if (!worldSize.isPresent()) {
			throw new IllegalStateException("World size must be set!");
		}
		int worldWidth = worldSize.get()[0];
		int worldHeight = worldSize.get()[1];

		if (getOptions().getDebugShowPixels()) {
			mainCamera = new Camera(new Rectangle(0, 0, worldWidth
					/ DEBUG_PIXELS_ZOOM, worldHeight / DEBUG_PIXELS_ZOOM),
					new Rectangle(0, 0, getScreenWidth(), getScreenHeight()));
		} else {
			int widthOnScreen = getScreenWidth();
			int heightOnScreen = getScreenHeight();

			double scale = 1.0;

			if (widthOnScreen < worldWidth) {
				scale = (double) widthOnScreen / worldWidth;
			}

			if (heightOnScreen < worldHeight) {
				scale = Math.min(scale, (double) heightOnScreen / worldHeight);
			}

			heightOnScreen = (int) (scale * worldHeight);
			widthOnScreen = (int) (scale * worldWidth);
			int screenX = (getScreenWidth() - widthOnScreen) / 2;
			int screenY = (getScreenHeight() - heightOnScreen) / 2;

			mainCamera = new Camera(
					new Rectangle(0, 0, worldWidth, worldHeight),
					new Rectangle(screenX, screenY, widthOnScreen,
							heightOnScreen));
		}
		addCamera(mainCamera);
	}

	@Override
	protected void setupPainters() {
		addPainter(new SolidBackgroundPainter(Color.BLACK, this));

		if (getOptions().getDebugShowInfo()) {
			addPainter(new DebugInfoPainter(this));
		}

		if (getOptions().getDebugShowAxes()) {
			addPainter(new AxesPainter(this));
		}

		if (getOptions().getDebugShowPixels()) {
			addPainter(new PixelPainter(this));
		}

		if (getOptions().getDebugShowHistory()) {
			addPainter(new HistoryPainter(this));
		}

		addPainter(new PlayerPainter(this));

		addPainter(new WorldBorderPainter(this));

		addPainter(new MessagePainter<Part1GameScreen>(this,
				getGame()::getCurrentMessage));
	}

	@Override
	public void updateState(double dt) {
		if (getOptions().getDebugShowPixels()) {
			getGame()
					.getAlienInfoProvider()
					.getAlienXY()
					.ifPresent(
							xy -> mainCamera.moveToWorldLocation(xy[0], xy[1]));
		}
	}

	@Override
	protected AlienInputMode<JumpingAlienGamePart1, JumpingAlienGUIPart1> createDefaultInputMode() {
		return new AlienInputMode<>(this, null);
	}

	@Override
	public Camera getMainCamera() {
		return mainCamera;
	}
}
