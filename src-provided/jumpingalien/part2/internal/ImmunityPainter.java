package jumpingalien.part2.internal;

import java.awt.Color;
import java.awt.Graphics2D;

import jumpingalien.common.gui.painters.AbstractAlienPainter;

public class ImmunityPainter extends AbstractAlienPainter<Part2GameScreen> {

	private static final Color IMMUNITY_COLOR = new Color(255, 196, 0, 60);

	public ImmunityPainter(Part2GameScreen screen) {
		super(screen);
	}

	@Override
	protected JumpingAlienGamePart2 getGame() {
		return (JumpingAlienGamePart2) super.getGame();
	}

	@Override
	public void paintInWorld(Graphics2D g) {
		AlienInfoProvider2 aip = getGame().getAlienInfoProvider();
		aip.isImmune().ifPresent(
				immune -> {
					if (immune) {
						aip.getAlienXY()
								.ifPresent(
										xy -> {
											aip.getAlienSize().ifPresent(
													size -> paintImmunity(g,
															xy, size));
										});
					}
				});
	}

	protected void paintImmunity(Graphics2D g, int[] xy, int[] size) {
		g.setColor(IMMUNITY_COLOR);
		g.fillOval(xy[0], xy[1], size[0], size[1]);
	}

}
