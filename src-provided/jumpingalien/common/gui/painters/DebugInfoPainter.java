package jumpingalien.common.gui.painters;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import jumpingalien.common.gui.AlienGameScreen;
import ogp.framework.gui.PerformanceStats;

public class DebugInfoPainter extends
		AbstractAlienPainter<AlienGameScreen<?, ?>> {

	public DebugInfoPainter(AlienGameScreen<?, ?> screen) {
		super(screen);
		setupDefaultInfo();
	}

	protected void setupDefaultInfo() {
		addInfo("In-game time:", () -> String.format(
				"%.1fs (1s = %.2f real seconds)", getGame().getElapsedTime(),
				getGame().getOptions().getTimescale()));

		addInfo("Performance", () -> getFPSStats().toString());

		addInfo("Screen size", () -> String.format("%dx%d", getScreenWidth(),
				getScreenHeight()));

		addInfo("World size",
				() -> getGame().getWorldInfoProvider().getWorldSize()
						.map(size -> String.format("%dx%d", size[0], size[1]))
						.orElse("Unknown"));

		addInfo("Location",
				() -> getGame().getAlienInfoProvider().getAlienXY()
						.map(loc -> String.format("(%d, %d)", loc[0], loc[1]))
						.orElse("Unknown"));

		addInfo("Size", () -> getGame().getAlienInfoProvider().getAlienSize()
				.map(size -> String.format("(%d, %d)", size[0], size[1]))
				.orElse("Unknown"));

		addInfo("Velocity",
				() -> getGame()
						.getAlienInfoProvider()
						.getAlienVelocity()
						.map(vel -> String.format("(%.3f, %.3f) [m/s]", vel[0],
								vel[1])).orElse("Unknwon"));

		addInfo("Acceleration",
				() -> getGame()
						.getAlienInfoProvider()
						.getAlienAcceleration()
						.map(acc -> String.format("(%.3f, %.3f) [m/s²]",
								acc[0], acc[1])).orElse("Unknown"));
	}

	@Override
	public void paintScreenPost(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.setXORMode(Color.WHITE);
		int lineHeight = 20;
		int lineNb = 1;
		int y = getMainCamera().getScreenLocationY();
		int x = getMainCamera().getScreenLocationX() + 10;

		for (Info info : information) {
			g.drawString(info.toString(), x, y + lineNb++ * lineHeight);
		}

		g.setPaintMode();
	}

	private PerformanceStats getFPSStats() {
		return getScreen().getFPSStats();
	}

	private final List<Info> information = new ArrayList<>();

	private static class Info {

		private Supplier<String> supplier;
		private String title;

		public Info(String title, Supplier<String> supplier) {
			this.title = title;
			this.supplier = supplier;
		}

		@Override
		public String toString() {
			return title + ": " + supplier.get();
		}

	}

	public void addInfo(String title, Supplier<String> info) {
		information.add(new Info(title, info));
	}

}
