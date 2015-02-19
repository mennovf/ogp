package jumpingalien.part1.internal;

import jumpingalien.common.gui.AbstractMainMenu;
import jumpingalien.common.gui.AlienScreenPanel;
import ogp.framework.gui.Screen;

public class Part1MainMenu extends
		AbstractMainMenu<JumpingAlienGamePart1, JumpingAlienGUIPart1> {

	protected Part1MainMenu(AlienScreenPanel<JumpingAlienGamePart1> panel,
			JumpingAlienGUIPart1 gui,
			Screen<JumpingAlienGamePart1, JumpingAlienGUIPart1> previous) {
		super(panel, gui, previous);
	}

	@Override
	protected void startGame() {
		getPanel().switchToScreen(
				new Part1GameScreen(getPanel(), getGUI(), this));
	}

	@Override
	protected void setDebugOptions() {
		getPanel().switchToScreen(
				new Part1DebugMenu(getPanel(), getGUI(), this));
	}
}
