package jumpingalien.common.gui;

import jumpingalien.common.game.JumpingAlienGame;
import ogp.framework.gui.ScreenPanel;

@SuppressWarnings("serial")
public abstract class AlienScreenPanel<TGame extends JumpingAlienGame> extends
		ScreenPanel<TGame> {

	public AlienScreenPanel() {
		super();
	}

	@Override
	public void initialize(TGame game) {
		game.setVisibleScreenSize(getWidth(), getHeight());
		super.initialize(game);
	}
}
