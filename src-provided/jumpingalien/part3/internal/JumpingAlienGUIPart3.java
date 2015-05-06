package jumpingalien.part3.internal;

import java.awt.Dimension;

import jumpingalien.common.gui.AlienScreenPanel;
import jumpingalien.common.gui.JumpingAlienGUI;
import ogp.framework.gui.GUI;
import ogp.framework.gui.Screen;
import ogp.framework.gui.ScreenPanel;

public class JumpingAlienGUIPart3 extends
		JumpingAlienGUI<JumpingAlienGamePart3> {

	public JumpingAlienGUIPart3(JumpingAlienGamePart3 game) {
		super(game, game.getOptions());
	}

	@Override
	public Part3Options getGUIOptions() {
		return (Part3Options) super.getGUIOptions();
	}

	@Override
	protected String getTitle() {
		return "Jumping Alien (Part 3)";
	}

	@Override
	protected Dimension getDefaultSize() {
		return new Dimension(1024, 768);
	}

	@SuppressWarnings("serial")
	@Override
	protected ScreenPanel<JumpingAlienGamePart3> createScreenPanel() {
		return new AlienScreenPanel<JumpingAlienGamePart3>() {
			@Override
			protected Screen<JumpingAlienGamePart3, ? extends GUI<JumpingAlienGamePart3>> createInitialScreen() {
				return new Part3MainMenu(this, JumpingAlienGUIPart3.this,
						null);
			}
		};
	}
}
