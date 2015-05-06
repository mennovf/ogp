package jumpingalien.part3.internal;

import java.awt.Color;

import jumpingalien.common.gui.AlienGameScreen;
import jumpingalien.common.gui.AlienInputMode;
import jumpingalien.common.gui.AlienScreenPanel;
import jumpingalien.common.gui.painters.AbstractAlienPainter;
import jumpingalien.common.gui.painters.AxesPainter;
import jumpingalien.common.gui.painters.DebugInfoPainter;
import jumpingalien.common.gui.painters.HistoryPainter;
import jumpingalien.common.gui.painters.PixelPainter;
import jumpingalien.common.gui.painters.PlayerPainter;
import jumpingalien.common.gui.painters.WorldBorderPainter;
import jumpingalien.part2.internal.GameObjectPainter;
import jumpingalien.part2.internal.GameOverPainter;
import jumpingalien.part2.internal.HealthPainter;
import jumpingalien.part2.internal.ImmunityPainter;
import jumpingalien.part2.internal.Part2WorldInfoProvider;
import jumpingalien.part2.internal.TilePainter;
import ogp.framework.gui.InputMode;
import ogp.framework.gui.MessagePainter;
import ogp.framework.gui.Screen;
import ogp.framework.gui.SolidBackgroundPainter;
import ogp.framework.gui.camera.Camera;
import ogp.framework.gui.camera.SimpleCamera;

public class Part3GameScreen extends
		AlienGameScreen<JumpingAlienGamePart3, JumpingAlienGUIPart3> {

	private Camera mainCamera, zoomCamera;

	public Part3GameScreen(AlienScreenPanel<JumpingAlienGamePart3> panel,
			JumpingAlienGUIPart3 gui,
			Screen<JumpingAlienGamePart3, JumpingAlienGUIPart3> previous) {
		super(panel, gui, previous);
	}

	@Override
	public Part3Options getOptions() {
		return getGUI().getGUIOptions();
	}

	@Override
	protected void setupCameras() {
		if (!getOptions().getDebugShowEntireWorld()) {
			setupDefaultCamera();
		} else {
			setupOverviewCamera();
		}

		if (getOptions().getDebugShowPixels()) {
			int zoom = 20;
			int zoomCameraHeight = 250;
			int zoomCameraWidth = 500;
			zoomCamera = new Camera(new Camera.Rectangle(0, 0, zoomCameraWidth
					/ zoom, zoomCameraHeight / zoom), new Camera.Rectangle(
					getScreenWidth() - zoomCameraWidth, getScreenHeight()
							- zoomCameraHeight, zoomCameraWidth,
					zoomCameraHeight));
			zoomCamera.showBorder(true);
			addCamera(zoomCamera);
		}
	}

	private void setupOverviewCamera() {
		int[] size = getGame().getWorldSize();
		double ratioW = (double) getScreenWidth() / size[0];
		double ratioH = (double) getScreenHeight() / size[1];
		double ratio = Math.min(ratioW, ratioH);

		int scaledScreenWidth = (int) (ratio / ratioW * getScreenWidth());
		int scaledScreenHeight = (int) (ratio / ratioH * getScreenHeight());
		mainCamera = new Camera(new Camera.Rectangle(0, 0, size[0], size[1]),
				new Camera.Rectangle(
						(getScreenWidth() - scaledScreenWidth) / 2,
						(getScreenHeight() - scaledScreenHeight) / 2,
						scaledScreenWidth, scaledScreenHeight));
		addCamera(mainCamera);
	}

	private void setupDefaultCamera() {
		mainCamera = new SimpleCamera(0, 0, getScreenWidth(), getScreenHeight());
		addCamera(mainCamera);
	}

	@Override
	public Camera getMainCamera() {
		return mainCamera;
	}

	@Override
	protected void setupPainters() {
		addPainter(new SolidBackgroundPainter(Color.BLACK, this));

		addPainter(new TilePainter(this, getGame().getMap(), getGame()
				.getWorldInfoProvider()));

		if (getOptions().getDebugShowInfo()) {
			DebugInfoPainter debugInfoPainter = new DebugInfoPainter(this);
			debugInfoPainter.addInfo("Tile size", () -> String.format("%dx%d",
					getGame().getWorldInfoProvider().getTileLength(), getGame()
							.getWorldInfoProvider().getTileLength()));
			debugInfoPainter.addInfo(
					"Visible window",
					() -> getGame()
							.getWorldInfoProvider()
							.getVisibleWindow()
							.map(ar -> String.format("(%d,%d), (%d, %d)",
									ar[0], ar[1], ar[2], ar[3]))
							.orElse("Unknown"));
			addPainter(debugInfoPainter);
		}

		if (getOptions().getDebugShowEntireWorld()) {
			addPainter(new AbstractAlienPainter<AlienGameScreen<?, ?>>(this) {
				@Override
				public void paintInWorld(java.awt.Graphics2D g) {
					((Part2WorldInfoProvider) getGame().getWorldInfoProvider())
							.getVisibleWindow().ifPresent(
									activeRegion -> {
										g.setColor(Color.BLACK);
										g.setXORMode(Color.WHITE);
										g.drawRect(activeRegion[0],
												activeRegion[1],
												activeRegion[2]
														- activeRegion[0] + 1,
												activeRegion[3]
														- activeRegion[1] + 1);
										g.setPaintMode();
									});
				};
			});
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

		addPainter(new GameObjectPainter(this,
				getGame().getAlienInfoProvider(), getGame()
						.getObjectInfoProvider()));

		addPainter(new PlayerPainter(this));
		addPainter(new ImmunityPainter(this, getGame().getAlienInfoProvider()));

		addPainter(new PlayerPainter(this, getGame().getEvilTwinInfoProvider()));
		addPainter(new ImmunityPainter(this, getGame().getEvilTwinInfoProvider()));
		
		addPainter(new WorldBorderPainter(this));

		addPainter(new HealthPainter(this, getGame().getAlienInfoProvider()));

		addPainter(new MessagePainter<Part3GameScreen>(this,
				getGame()::getCurrentMessage));

		addPainter(new GameOverPainter(this, getGame().getWorldInfoProvider()));
	}

	@Override
	public void updateState(double dt) {
		positionMainCamera();
		positionZoomCamera();

	}

	private void positionZoomCamera() {
		if (zoomCamera != null) {
			getGame()
					.getAlienInfoProvider()
					.getAlienXY()
					.ifPresent(
							position -> zoomCamera.moveToWorldLocation(
									position[0] - 5, position[1] - 5));
		}
	}

	private void positionMainCamera() {
		if (!getOptions().getDebugShowEntireWorld()) {
			getGame()
					.getWorldInfoProvider()
					.getVisibleWindow()
					.ifPresent(
							activeRegion -> {
								if (mainCamera != null) {
									mainCamera.moveToWorldLocation(
											activeRegion[0], activeRegion[1]);
								}

							});
		}
	}

	@Override
	protected InputMode<JumpingAlienGamePart3, JumpingAlienGUIPart3> createDefaultInputMode() {
		return new AlienInputMode<>(this, null);
	}
}
