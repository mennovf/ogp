package ogp.framework.gui;

import java.awt.Graphics2D;

public abstract class Painter<ScreenType> {

	private final ScreenType screen;

	public Painter(ScreenType screen) {
		this.screen = screen;
	}
	
	public ScreenType getScreen() {
		return screen;
	}

	/** Paint in screen coordinates, before the world view is drawn */
	public void  paintScreenPre(Graphics2D g) {
		
	}
	
	/** Paint in screen coordinates, after the world view has been drawn */
	public void  paintScreenPost(Graphics2D g) {
		
	}
	
	/** Paint the world view (in world coordinates) */
	public void  paintInWorld(Graphics2D g) {
		
	}

	
}
