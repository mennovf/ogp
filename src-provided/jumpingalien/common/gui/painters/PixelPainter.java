package jumpingalien.common.gui.painters;

import java.awt.Graphics2D;
import java.awt.TexturePaint;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import jumpingalien.common.gui.AlienGameScreen;

public class PixelPainter extends AbstractAlienPainter<AlienGameScreen<?, ?>> {

	private static final int PIXEL_COLOR_2 = 0xaaffffff;
	private static final int PIXEL_COLOR_1 = 0xaa2C9ABF;
	private final BufferedImage texture;

	public PixelPainter(AlienGameScreen<?, ?> screen) {
		super(screen);
		texture = new BufferedImage(2, 2, BufferedImage.TYPE_INT_ARGB);
		texture.setRGB(0, 0, PIXEL_COLOR_1);
		texture.setRGB(1, 1, PIXEL_COLOR_1);
		texture.setRGB(1, 0, PIXEL_COLOR_2);
		texture.setRGB(0, 1, PIXEL_COLOR_2);
	}

	@Override
	public void paintInWorld(Graphics2D g) {
		g.setPaint(new TexturePaint(texture, new Rectangle2D.Double(0, 0, 2, 2)));
		g.fillRect(0, 0, getScreenWidth(), getScreenHeight());
	}

}
