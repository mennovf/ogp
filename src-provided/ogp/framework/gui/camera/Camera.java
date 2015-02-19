package ogp.framework.gui.camera;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * <p>
 * This class represents a camera that captures a rectangular region of the game
 * world, and that has its view drawn to a rectangular region of the screen.
 * 
 * <p>
 * The game world is assumed to
 * <ul>
 * <li>have an x-axis increasing from left to right
 * <li>have a y-axis increasing from bottom to top
 * </ul>
 * 
 * <p>
 * The display on screen is assumed to
 * <ul>
 * <li>have an x-axis increasing from left to right
 * <li>have a y-axis increasing from top to bottom (i.e., the opposite direction
 * as game world)
 * </ul>
 * 
 * <p>
 * Region positions are specified as the bottom left coordinate of a rectangle.
 *
 */
public class Camera {

	public static final class Rectangle {
		private final int x, y, width, height;

		public Rectangle(int x, int y, int width, int height) {
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
		}

		public Rectangle withX(int newX) {
			return new Rectangle(newX, y, width, height);
		}

		public Rectangle withY(int newY) {
			return new Rectangle(x, newY, width, height);
		}

		public Rectangle withWidth(int newWidth) {
			return new Rectangle(x, y, newWidth, height);
		}

		public Rectangle withHeight(int newHeight) {
			return new Rectangle(x, y, width, newHeight);
		}

		@Override
		public String toString() {
			return String.format("location (%d,%d), size (%d,%d)", x, y, width,
					height);
		}
	}

	private Rectangle worldRegion, screenRegion;
	private boolean showBorder;
	private Color borderColor = Color.BLACK;

	public Camera(Rectangle worldRegion, Rectangle screenRegion) {
		if (worldRegion.width == 0 || worldRegion.height == 0) {
			throw new IllegalArgumentException("World region too small: "
					+ worldRegion);
		}
		if (screenRegion.width == 0 || screenRegion.height == 0) {
			throw new IllegalArgumentException("Screen region too small: "
					+ screenRegion);
		}
		this.worldRegion = worldRegion;
		this.screenRegion = screenRegion;
	}

	public Rectangle getWorldRegion() {
		return worldRegion;
	}

	public Rectangle getScreenRegion() {
		return screenRegion;
	}

	public void moveToWorldLocation(int x, int y) {
		worldRegion = new Rectangle(x, y, worldRegion.width, worldRegion.height);
	}

	/**
	 * Returns the top left x coordinate of the camera in the world
	 */
	public int getWorldLocationX() {
		return worldRegion.x;
	}

	/**
	 * Sets the top left x coordinate of the camera in the world
	 */
	public void setWorldLocationX(int worldX) {
		worldRegion = worldRegion.withX(worldX);
	}

	/**
	 * Returns the top left y coordinate of the camera in the world
	 */
	public int getWorldLocationY() {
		return worldRegion.y;
	}

	/**
	 * Sets the top left y coordinate of the camera in the world
	 */
	public void setWorldLocationY(int worldY) {
		worldRegion = worldRegion.withY(worldY);
	}

	/**
	 * Returns the width of the camera in the world
	 */
	public int getWorldWidth() {
		return worldRegion.width;
	}

	/**
	 * Sets the width of the camera in the world
	 */
	public void setWorldWidth(int worldWidth) {
		worldRegion = worldRegion.withWidth(worldWidth);
	}

	/**
	 * Returns the height of the camera in the world
	 */
	public int getWorldHeight() {
		return worldRegion.height;
	}

	/**
	 * Sets the height of the camera in the world
	 */
	public void setWorldHeight(int worldHeight) {
		worldRegion = worldRegion.withHeight(worldHeight);
	}

	/**
	 * Returns the top left x coordinate of the camera on the screen
	 */
	public int getScreenLocationX() {
		return screenRegion.x;
	}

	/**
	 * Sets the top left x coordinate of the camera on the screen
	 */
	public void setScreenLocationX(int screenX) {
		screenRegion = screenRegion.withX(screenX);
	}

	/**
	 * Returns the top left y coordinate of the camera on the screen
	 */
	public int getScreenLocationY() {
		return screenRegion.y;
	}

	/**
	 * Sets the top left y coordinate of the camera on the screen
	 */
	public void setScreenLocationY(int screenY) {
		screenRegion = screenRegion.withY(screenY);
	}

	/**
	 * Returns the width of the camera on the screen
	 */
	public int getScreenWidth() {
		return screenRegion.width;
	}

	/**
	 * Sets the width of the camera on the screen
	 */
	public void setScreenWidth(int screenWidth) {
		screenRegion = screenRegion.withWidth(screenWidth);
	}

	/**
	 * Returns the height of the camera on the screen
	 */
	public int getScreenHeight() {
		return screenRegion.height;
	}

	/**
	 * Sets the height of the camera on the screen
	 */
	public void setScreenHeight(int screenHeight) {
		screenRegion = screenRegion.withHeight(screenHeight);
	}

	/**
	 * Returns the scale (number of screen pixels per world pixel) in the
	 * X-direction. The scale is determined by the screen width and world width
	 * of this camera.
	 */
	public float getScaleX() {
		return getScreenWidth() / (float) getWorldWidth();
	}

	/**
	 * Returns the scale (number of screen pixels per world pixel) in the
	 * Y-direction. The scale is determined by the screen height and world
	 * height of this camera.
	 */
	public float getScaleY() {
		return getScreenHeight() / (float) getWorldHeight();
	}

	@Override
	public String toString() {
		return String.format(
				"Camera [worldregion: { %s }, screenregion: { %s }]",
				worldRegion, screenRegion);
	}

	/**
	 * Transform the given graphics object so subsequent painting can be done in
	 * world coordinates.
	 */
	public void applyTransform(Graphics2D g) {
		// sx: screen x
		// wx: world x
		// sox: camera screen location x
		// wox: camera world location x
		// fx: scale x

		// sx = sox + fx * (wx - wox)
		// sy = soy - fy * (wy - woy)
		g.translate(getScreenLocationX(), getScreenLocationY()
				+ getScreenHeight());
		g.scale(getScaleX(), -getScaleY());
		g.translate(-getWorldLocationX(), -getWorldLocationY());

	}

	public void paintBorder(Graphics2D g) {
		if (getShowBorder()) {
			g.setColor(getBorderColor());
			g.drawRect(getScreenLocationX(), getScreenLocationY(),
					getScreenWidth(), getScreenHeight());
		}
	}

	public Color getBorderColor() {
		return borderColor;
	}
	
	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}
	

	public boolean getShowBorder() {
		return showBorder;
	}
	
	public void showBorder(boolean value) {
		this.showBorder = value;
	}
}
