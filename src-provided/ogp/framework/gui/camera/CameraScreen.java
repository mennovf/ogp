package ogp.framework.gui.camera;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

import ogp.framework.game.Game;
import ogp.framework.gui.GUI;
import ogp.framework.gui.Painter;
import ogp.framework.gui.Screen;
import ogp.framework.gui.ScreenPanel;

/** A screen with a camera */
public abstract class CameraScreen<TGame extends Game, TGUI extends GUI<TGame>>
		extends Screen<TGame, TGUI> {

	private final List<Camera> cameras = new ArrayList<>();

	protected CameraScreen(ScreenPanel<TGame> panel, TGUI gui,
			Screen<TGame, TGUI> previous) {
		super(panel, gui, previous);
	}

	public abstract Camera getMainCamera();

	@Override
	public void screenStarted() {
		super.screenStarted();
		setupCameras();
	}

	protected void addCamera(Camera camera) {
		cameras.add(camera);
	}

	protected abstract void setupCameras();

	@Override
	public final void paintFrame(Graphics2D g) {
		for (Camera camera : cameras) {
			paintCamera(g, camera);
		}
	}

	private void paintCamera(Graphics2D g, Camera camera) {
		AffineTransform oldTransform = g.getTransform();
		g.setClip(camera.getScreenLocationX(), camera.getScreenLocationY(),
				camera.getScreenWidth() + 1, camera.getScreenHeight() + 1);
		paintScreenPre(g);
		try {
			camera.applyTransform(g);
			paintWorld(g);
		} finally {
			g.setTransform(oldTransform);
		}
		paintScreenPost(g);
		g.setClip(null);
		camera.paintBorder(g);
	}

	private void paintScreenPre(Graphics2D g) {
		for (Painter<?> painter : getPainters()) {
			painter.paintScreenPre(g);
		}
	}

	private void paintWorld(Graphics2D g) {
		for (Painter<?> painter : getPainters()) {
			painter.paintInWorld(g);
		}
	}

	private void paintScreenPost(Graphics2D g) {
		for (Painter<?> painter : getPainters()) {
			painter.paintScreenPost(g);
		}
	}

}
