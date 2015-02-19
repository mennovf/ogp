package ogp.framework.gui.camera;


/**
 * A simple camera located at a particular point in the world, and with the
 * given screen size. This camera displays the world, unscaled, at the screen
 * origin.
 * 
 */
public class SimpleCamera extends Camera {

	public SimpleCamera(int worldX, int worldY, int screenWidth,
			int screenHeight) {
		super(new Rectangle(worldX, worldY, screenWidth, screenHeight),
				new Rectangle(0, 0, screenWidth, screenHeight));
	}
}
