package jumpingalien.common.gui;

import ogp.framework.gui.GUIOptions;

public interface JumpingAlienGUIOptions extends GUIOptions {

	@Override
	public default boolean isFullScreenEnabled() {
		return false;
	}
	
	public abstract boolean getDebugShowHistory();

	public abstract boolean getDebugShowPixels();

	public abstract boolean getDebugShowObjectLocationAndSize();

	public abstract boolean getDebugShowAxes();

	public abstract boolean getDebugShowInfo();

}
