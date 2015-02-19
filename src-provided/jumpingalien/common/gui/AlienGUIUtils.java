package jumpingalien.common.gui;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

public class AlienGUIUtils {

	public static void drawImageInWorld(Graphics2D g, Image image, int left,
			int bottom, boolean hFlipped) {
		AffineTransform tf = new AffineTransform();
		if (hFlipped) {
			left = left + image.getWidth(null);
		}
		tf.translate(left, bottom + image.getHeight(null)); // drawImage works
															// with top left
															// coordinate
		tf.scale((hFlipped ? -1 : 1), -1); // reverse y axis for drawing (avoid
											// image flip)
		g.drawImage(image, tf, null);
	}
}
