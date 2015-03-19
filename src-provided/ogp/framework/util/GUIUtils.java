package ogp.framework.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;

public class GUIUtils {

	public static Ellipse2D.Double circleAt(double centerX, double centerY,
			double r) {
		return new Ellipse2D.Double(centerX - r, centerY - r, 2 * r, 2 * r);
	}

	public static void drawCenteredString(Graphics2D g2d, String text,
			double width, double y) {
		Rectangle2D bounds = g2d.getFontMetrics().getStringBounds(text, g2d);
		g2d.drawString(text, (int) (width / 2 - bounds.getCenterX()), (int) y);
	}

	public static double distance(double x1, double y1, double x2, double y2) {
		double dx = x1 - x2;
		double dy = y1 - y2;
		return Math.sqrt(dx * dx + dy * dy);
	}
	
	private static final float STEP_SIZE = 7F/10F;

	public static void shiftHue(BufferedImage img, int amount) {
		float[] hsb = new float[3];
		for (int x = 0; x < img.getWidth(); x++) {
			for (int y = 0; y < img.getHeight(); y++) {
				Color color = new Color(img.getRGB(x, y), true);
				Color.RGBtoHSB(color.getRed(), color.getGreen(),
						color.getBlue(), hsb);
				hsb[0] = (hsb[0] + (STEP_SIZE * amount)) % 1.0F;
				img.setRGB(x, y, Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]));
				img.getAlphaRaster().setPixel(x, y,
						new int[] { color.getAlpha() });
			}
		}
	}

	public static BufferedImage copyImage(BufferedImage source) {
		BufferedImage b = new BufferedImage(source.getWidth(),
				source.getHeight(), source.getType());
		Graphics2D g = b.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setComposite(AlphaComposite.SrcOver);
		g.drawImage(source, 0, 0, null);
		g.dispose();
		return b;
	}

	public static BufferedImage scaleTo(BufferedImage image, int width,
			int height) {
		double ratioX = 1.0, ratioY = 1.0;
		if (width > 0) {
			ratioX = (double) width / image.getWidth();
			if (height > 0) {
				ratioY = (double) height / image.getHeight();
			} else {
				// maintain aspect ratio
				ratioY = ratioX;
			}
		} else {
			if (height > 0) {
				// maintain aspect ratio
				ratioY = (double) height / image.getHeight();
				ratioX = ratioY;
			}
		}

		int newWidth = (int) (ratioX * image.getWidth());
		int newHeight = (int) (ratioY * image.getHeight());
		BufferedImage result = new BufferedImage(newWidth, newHeight,
				image.getType());
		Graphics2D g = result.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(image, 0, 0, newWidth, newHeight, null);
		g.dispose();
		return result;
	}

	public static BufferedImage loadImage(String filename) {
		try {
			InputStream inputStream = openResource(filename);
			BufferedImage result = ImageIO.read(inputStream);
			inputStream.close();
			return result;
		} catch (IOException e) {
			throw new RuntimeException(
					"Could not read file '" + filename + "'", e);
		}
	}

	public static BufferedImage hflip(BufferedImage image) {
		BufferedImage flippedImage = new BufferedImage(image.getWidth(),
				image.getHeight(), image.getType());
		Graphics2D flippedGraphics = flippedImage.createGraphics();
		flippedGraphics.scale(-1, 1);
		flippedGraphics.drawImage(image, -image.getWidth(null), 0, null);
		flippedGraphics.dispose();
		return flippedImage;
	}

	public static InputStream openResource(String filename) throws IOException {
		URL url = toURL(filename);
		return openResource(url);
	}

	public static InputStream openResource(URL url) throws IOException {
		InputStream result;

		URLConnection conn = url.openConnection();
		result = conn.getInputStream();

		return result;
	}

	public static URL toURL(String filename) throws FileNotFoundException {
		URL url = GUIUtils.class.getResource("/" + filename);
		if (url == null) {
			try {
				File file = new File(filename);
				if (file.exists()) {
					url = new File(filename).toURI().toURL();
				} else {
					throw new FileNotFoundException("File not found: "
							+ filename);
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
				return null;
			}
		}
		return url;
	}
}
