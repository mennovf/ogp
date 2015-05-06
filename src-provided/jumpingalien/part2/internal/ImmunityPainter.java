package jumpingalien.part2.internal;

import java.awt.Color;
import java.awt.Graphics2D;

import jumpingalien.common.gui.AlienGameScreen;
import jumpingalien.common.gui.painters.AbstractAlienPainter;

public class ImmunityPainter extends
		AbstractAlienPainter<AlienGameScreen<?, ?>> {

	private static final Color IMMUNITY_COLOR = new Color(255, 196, 0, 60);
	private AlienInfoProvider2<?> alienInfoProvider;

	public ImmunityPainter(AlienGameScreen<?, ?> screen,
			AlienInfoProvider2<?> alienInfoProvider) {
		super(screen);
		this.alienInfoProvider = alienInfoProvider;
	}

	@Override
	public void paintInWorld(Graphics2D g) {
		alienInfoProvider
				.isImmune()
				.ifPresent(
						immune -> {
							if (immune) {
								alienInfoProvider
										.getAlienXY()
										.ifPresent(
												xy -> {
													alienInfoProvider
															.getAlienSize()
															.ifPresent(
																	size -> paintImmunity(
																			g,
																			xy,
																			size));
												});
							}
						});
	}

	protected void paintImmunity(Graphics2D g, int[] xy, int[] size) {
		g.setColor(IMMUNITY_COLOR);
		g.fillOval(xy[0], xy[1], size[0], size[1]);
	}

}
