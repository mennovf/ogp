package jumpingalien.common.gui;

import jumpingalien.common.game.JumpingAlienGame;
import ogp.framework.gui.GUI;

public abstract class JumpingAlienGUI<TGame extends JumpingAlienGame> extends
		GUI<TGame> {

	public JumpingAlienGUI(TGame game, JumpingAlienGUIOptions options) {
		super(game, options);
	}

	@Override
	public JumpingAlienGUIOptions getGUIOptions() {
		return (JumpingAlienGUIOptions) super.getGUIOptions();
	}

}
