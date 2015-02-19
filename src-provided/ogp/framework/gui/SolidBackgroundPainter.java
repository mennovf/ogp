package ogp.framework.gui;

import java.awt.Color;
import java.awt.Graphics2D;

public class SolidBackgroundPainter extends Painter<Screen<?, ?>> {

	private final Color color;

	public SolidBackgroundPainter(Color color, Screen<?, ?> screen) {
		super(screen);
		this.color = color;
	}

	@Override
	public void paintScreenPre(Graphics2D g) {
		g.setColor(color);
		g.fillRect(0, 0, getScreen().getScreenWidth(), getScreen()
				.getScreenHeight());
	}

}
