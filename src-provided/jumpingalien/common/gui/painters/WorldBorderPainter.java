package jumpingalien.common.gui.painters;

import java.awt.Color;
import java.awt.Graphics2D;

import jumpingalien.common.gui.AlienGameScreen;

public class WorldBorderPainter extends
		AbstractAlienPainter<AlienGameScreen<?, ?>> {

	public WorldBorderPainter(AlienGameScreen<?, ?> screen) {
		super(screen);
	}

	@Override
	public void paintInWorld(Graphics2D g) {
		g.setColor(Color.YELLOW);
		getGame().getWorldInfoProvider().getWorldSize()
				.ifPresent(wh -> g.drawRect(0, 0, wh[0], wh[1]));
	}
}
