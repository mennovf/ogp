package jumpingalien.part2.internal;

import java.awt.Dimension;

import jumpingalien.common.gui.AlienScreenPanel;
import jumpingalien.common.gui.JumpingAlienGUI;
import jumpingalien.part2.internal.JumpingAlienGUIPart2;
import ogp.framework.gui.GUI;
import ogp.framework.gui.Screen;
import ogp.framework.gui.ScreenPanel;

public class JumpingAlienGUIPart2 extends
		JumpingAlienGUI<JumpingAlienGamePart2> {

	public JumpingAlienGUIPart2(JumpingAlienGamePart2 game) {
		super(game, game.getOptions());
	}

	@Override
	public Part2Options getGUIOptions() {
		return (Part2Options) super.getGUIOptions();
	}

	@Override
	protected String getTitle() {
		return "Jumping Alien (Part 2)";
	}

	@Override
	protected Dimension getDefaultSize() {
		return new Dimension(1024, 768);
	}

	@SuppressWarnings("serial")
	@Override
	protected ScreenPanel<JumpingAlienGamePart2> createScreenPanel() {
		return new AlienScreenPanel<JumpingAlienGamePart2>() {
			@Override
			protected Screen<JumpingAlienGamePart2, ? extends GUI<JumpingAlienGamePart2>> createInitialScreen() {
				return new Part2MainMenu(this, JumpingAlienGUIPart2.this,
						null);
			}
		};
	}
}
