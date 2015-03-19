package jumpingalien.common.sprites;

import java.awt.image.BufferedImage;

import jumpingalien.util.Sprite;
import ogp.framework.util.GUIUtils;

public class ImageSprite extends Sprite {

	public static ImageSprite createSprite(String fileName) {
		return new ImageSprite(fileName, GUIUtils.loadImage(fileName));
	}

	public static ImageSprite createHFlippedSprite(String fileName) {
		return new ImageSprite(fileName, GUIUtils.hflip(GUIUtils
				.loadImage(fileName)));
	}

	public static ImageSprite createSprite(String name, BufferedImage image) {
		return new ImageSprite(name, image);
	}

	private final BufferedImage image;

	public ImageSprite(String name, BufferedImage image) {
		super(name, image.getWidth(), image.getHeight());
		this.image = image;
	}

	public BufferedImage getImage() {
		return image;
	}

	public ImageSprite scaleToHeight(int newHeight) {
		return new ImageSprite(getName(),
				GUIUtils.scaleTo(image, -1, newHeight));
	}

	/**
	 * Rescale without maintaining aspect ratio
	 * 
	 * @param newWidth
	 *            new width, or -1 to keep original width
	 * @param newHeight
	 *            new height, or -1 to keep original height
	 * 
	 * @return
	 */
	public ImageSprite resizeTo(int newWidth, int newHeight) {
		if (newWidth < 0) {
			newWidth = image.getWidth();
		}
		if (newHeight < 0) {
			newHeight = image.getHeight();
		}

		return new ImageSprite(getName(), GUIUtils.scaleTo(image, newWidth,
				newHeight));
	}

	public Sprite scaleToWidth(int newWidth) {
		return new ImageSprite(getName(), GUIUtils.scaleTo(image, newWidth, -1));
	}

	public ImageSprite shiftHue(int amount) {
		BufferedImage shiftedImage = GUIUtils.copyImage(image);
		GUIUtils.shiftHue(shiftedImage, amount);
		return new ImageSprite(getName(), shiftedImage);
	}

}
