package jumpingalien.part1.internal;

import java.awt.Dimension;

import jumpingalien.common.gui.AlienScreenPanel;
import jumpingalien.common.gui.JumpingAlienGUI;
import ogp.framework.gui.GUI;
import ogp.framework.gui.Screen;
import ogp.framework.gui.ScreenPanel;

public class JumpingAlienGUIPart1 extends
		JumpingAlienGUI<JumpingAlienGamePart1> {

	public JumpingAlienGUIPart1(JumpingAlienGamePart1 game) {
		super(game, game.getOptions());
	}

	@Override
	protected String getTitle() {
		return "Jumping Alien (Part 1)";
	}

	@Override
	public Part1Options getGUIOptions() {
		return (Part1Options) super.getGUIOptions();
	}

	@Override
	protected Dimension getDefaultSize() {
		return new Dimension(1100, 800);
	}

	@SuppressWarnings("serial")
	@Override
	protected ScreenPanel<JumpingAlienGamePart1> createScreenPanel() {
		return new AlienScreenPanel<JumpingAlienGamePart1>() {
			@Override
			protected Screen<JumpingAlienGamePart1, ? extends GUI<JumpingAlienGamePart1>> createInitialScreen() {
				return new Part1MainMenu(this, JumpingAlienGUIPart1.this, null);
			}
		};
	}
}
