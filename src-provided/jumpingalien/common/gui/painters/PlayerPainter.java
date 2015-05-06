package jumpingalien.common.gui.painters;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

import jumpingalien.common.game.AlienInfoProvider;
import jumpingalien.common.gui.AlienGUIUtils;
import jumpingalien.common.gui.AlienGameScreen;
import jumpingalien.common.sprites.ImageSprite;

public class PlayerPainter extends AbstractAlienPainter<AlienGameScreen<?, ?>> {

	private static final Color SIZE_BORDER = Color.RED;
	private static final Color LOCATION_COLOR = Color.RED;
	private static final Color SIZE_FILL = new Color(0x8800ff00, true);

	private final AlienInfoProvider<?> provider;

	public PlayerPainter(AlienGameScreen<?, ?> screen) {
		super(screen);
		this.provider = screen.getGame().getAlienInfoProvider();
	}

	public PlayerPainter(AlienGameScreen<?, ?> screen,
			AlienInfoProvider<?> provider) {
		super(screen);
		this.provider = provider;
	}

	@Override
	public void paintInWorld(Graphics2D g) {
		provider.getAlienXY().ifPresent(
				xy -> {

					if (getOptions().getDebugShowObjectLocationAndSize()) {
						provider.getAlienSize().ifPresent(
								size -> paintLocationAndSize(g, xy, size,
										getOptions().getDebugShowPixels()));
					}

					provider.getPlayerSprite().ifPresent(
							sprite -> AlienGUIUtils.drawImageInWorld(g,
									((ImageSprite) sprite).getImage(), xy[0],
									xy[1], false));
				});
	}

	protected void paintLocationAndSize(Graphics2D g, int[] xy, int[] size,
			boolean smallStroke) {
		g.setColor(SIZE_FILL);
		g.fillRect(xy[0], xy[1], size[0], size[1]);

		g.setColor(SIZE_BORDER);

		Stroke oldStroke = g.getStroke();
		if (smallStroke) {
			// use a smaller stroke if individual pixels are painted
			g.setStroke(new BasicStroke(0.5f));
		}
		g.drawRect(xy[0], xy[1], size[0], size[1]);
		g.setStroke(oldStroke);

		g.setColor(LOCATION_COLOR);
		if (smallStroke) {
			// only fill 1 pixel if individual pixels are painted
			g.fillRect(xy[0], xy[1], 1, 1);
		} else {
			g.fillRect(xy[0], xy[1], 5, 5);
		}
	}

}
