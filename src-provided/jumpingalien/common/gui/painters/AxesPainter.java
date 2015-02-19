package jumpingalien.common.gui.painters;

import java.awt.Color;
import java.awt.Graphics2D;

import jumpingalien.common.gui.AlienGameScreen;

public class AxesPainter extends AbstractAlienPainter<AlienGameScreen<?, ?>> {

	private static final int AXES_LENGTH = 100;
	private static final int AXES_WIDTH = 6;
	private static final int AXES_ARROW_SIZE = 12;

	private static final Color AXES_COLOR = Color.YELLOW;

	public AxesPainter(AlienGameScreen<?, ?> screen) {
		super(screen);
	}

	@Override
	public void paintInWorld(Graphics2D g) {
		g.setColor(AXES_COLOR);
		g.fillRect(-AXES_WIDTH / 2, -AXES_WIDTH / 2, AXES_WIDTH, AXES_LENGTH);
		g.fillRect(-AXES_WIDTH / 2, -AXES_WIDTH / 2, AXES_LENGTH, AXES_WIDTH);
		g.fillPolygon(new int[] { -AXES_ARROW_SIZE, 0, AXES_ARROW_SIZE },
				new int[] { AXES_LENGTH - AXES_WIDTH / 2,
						AXES_LENGTH - AXES_WIDTH / 2 + AXES_ARROW_SIZE,
						AXES_LENGTH - AXES_WIDTH / 2 }, 3);
		g.fillPolygon(new int[] { AXES_LENGTH - AXES_WIDTH / 2,
				AXES_LENGTH - AXES_WIDTH / 2 + AXES_ARROW_SIZE,
				AXES_LENGTH - AXES_WIDTH / 2 }, new int[] { -AXES_ARROW_SIZE,
				0, AXES_ARROW_SIZE }, 3);
	}
}
