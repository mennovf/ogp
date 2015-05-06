package jumpingalien.part2.internal;

import java.awt.Graphics2D;

import jumpingalien.common.gui.AlienGameScreen;
import jumpingalien.common.gui.painters.AbstractAlienPainter;
import jumpingalien.common.sprites.ImageSprite;

public class HealthPainter extends AbstractAlienPainter<AlienGameScreen<?, ?>> {

	private static final int H_MARGIN = 30;
	private static final int WIDTH = 30;
	private static final int V_MARGIN = 30;

	private final AlienInfoProvider2<?> alienInfoProvider;

	public HealthPainter(AlienGameScreen<?, ?> screen,
			AlienInfoProvider2<?> alienInfoProvider) {
		super(screen);
		this.alienInfoProvider = alienInfoProvider;
	}

	@Override
	public void paintScreenPost(Graphics2D g) {
		alienInfoProvider.getAlienHealth().ifPresent(
				health -> paintHealth(g, health));
	}

	private void paintHealth(Graphics2D g, Integer health) {
		int count = 0;
		int origHealth = health;
		if (origHealth > 0) {
			while (health > 0) {
				count += 1;
				int digit = health % 10;
				health = health / 10;
				ImageSprite image = Resources.NUMBER_SPRITES[digit];
				g.drawImage(image.getImage(), getScreenWidth() - H_MARGIN
						- count * WIDTH, V_MARGIN, null);
			}
		} else {
			count += 1;
			ImageSprite image = Resources.NUMBER_SPRITES[0];
			g.drawImage(image.getImage(), getScreenWidth() - H_MARGIN - count
					* WIDTH, V_MARGIN, null);
		}

		if (origHealth >= 66) {
			g.drawImage(Resources.HEALTH_FULL.getImage(), getScreenWidth()
					- H_MARGIN - count * WIDTH
					- Resources.HEALTH_FULL.getImage().getWidth(), V_MARGIN,
					null);
		} else if (origHealth >= 33) {
			g.drawImage(Resources.HEALTH_HALF.getImage(), getScreenWidth()
					- H_MARGIN - count * WIDTH
					- Resources.HEALTH_HALF.getImage().getWidth(), V_MARGIN,
					null);
		} else {
			g.drawImage(Resources.HEALTH_EMPTY.getImage(), getScreenWidth()
					- H_MARGIN - count * WIDTH
					- Resources.HEALTH_EMPTY.getImage().getWidth(), V_MARGIN,
					null);
		}
	};
}
