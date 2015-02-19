package jumpingalien.common.gui;

import jumpingalien.common.game.JumpingAlienGame;
import jumpingalien.common.gui.AlienScreenPanel;
import ogp.framework.gui.Screen;
import ogp.framework.gui.menu.MenuOption;
import ogp.framework.gui.menu.MenuScreen;

public abstract class AbstractMainMenu<TGame extends JumpingAlienGame, TGUI extends JumpingAlienGUI<TGame>>
		extends MenuScreen<TGame, TGUI> {

	protected AbstractMainMenu(AlienScreenPanel<TGame> panel, TGUI gui,
			Screen<TGame, TGUI> previous) {
		super(panel, gui, previous);
	}

	@Override
	protected AlienScreenPanel<TGame> getPanel() {
		return (AlienScreenPanel<TGame>) super.getPanel();
	}

	@Override
	protected void registerMenuOptions() {
		addOption(new MenuOption("Start game", this::startGame));

		addOption(new MenuOption("Set debug options", this::setDebugOptions));

		addOption(new MenuOption("Quit (Esc)", this::quit));
	}

	protected abstract void startGame();

	protected abstract void setDebugOptions();

	protected void quit() {
		getGUI().exit();
	}
}
