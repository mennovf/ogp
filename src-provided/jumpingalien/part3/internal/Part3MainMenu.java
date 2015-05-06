package jumpingalien.part3.internal;

import jumpingalien.common.gui.AlienScreenPanel;
import ogp.framework.gui.Screen;
import ogp.framework.gui.menu.MenuOption;
import ogp.framework.gui.menu.MenuScreen;

public class Part3MainMenu extends
		MenuScreen<JumpingAlienGamePart3, JumpingAlienGUIPart3> {

	public <T extends AlienScreenPanel<JumpingAlienGamePart3>> Part3MainMenu(
			T panel, JumpingAlienGUIPart3 gui,
			Screen<JumpingAlienGamePart3, JumpingAlienGUIPart3> previous) {
		super(panel, gui, previous);
	}

	@Override
	protected AlienScreenPanel<JumpingAlienGamePart3> getPanel() {
		return (AlienScreenPanel<JumpingAlienGamePart3>) super.getPanel();
	}

	@Override
	protected void registerMenuOptions() {
		addOption(new MenuOption("Start game", this::startGame));
		addOption(new MenuOption(
				"Start game with helpful debug visualisations",
				this::startGameWithDebug));

		addOption(new MenuOption(() -> "Change world: " + getCurrentMap(),
				this::selectMap));

		addOption(new MenuOption("Set debug options", this::setDebugOptions));

		addOption(new MenuOption("Quit (Esc)", this::quit));
	}

	@Override
	public void screenStarted() {
		super.screenStarted();
		String[] maps = getMaps();
		for (int i = 0; i < maps.length; i++) {
			if (maps[i].equals(getGame().getMapFile())) {
				currentIndex = i;
				break;
			}
		}
	}

	private int currentIndex = 0;
	private String[] mapFilenames;

	private String[] getMaps() {
		if (mapFilenames == null) {
			mapFilenames = getGame().getAvailableMaps();
		}
		return mapFilenames;
	}

	private String getCurrentMap() {
		return getMaps()[currentIndex];
	}

	private void selectMap() {
		currentIndex = (currentIndex + 1) % getMaps().length;
	}

	private void startGame() {
		if (getGame().setMapFile(getCurrentMap())) {
			getPanel().switchToScreen(
					new Part3GameScreen(getPanel(), getGUI(), this));
		}
	}

	private void startGameWithDebug() {
		if (getGame().setMapFile(getCurrentMap())) {

			getGame().getOptions().setDebugShowInfo(true);
			getGame().getOptions().setDebugShowObjectLocationAndSize(true);
			getGame().getOptions().setDebugShowAlienOverlappingTiles(true);
			getGame().getOptions().setDebugShowObjectString(true);
			getGame().getOptions().setDebugShowTileGridlines(true);

			getPanel().switchToScreen(
					new Part3GameScreen(getPanel(), getGUI(), this));
		}
	}

	private void setDebugOptions() {
		if (getGame().setMapFile(getCurrentMap())) {
			getPanel().switchToScreen(
					new Part3DebugMenu(getPanel(), getGUI(), this));
		}
	}

	private void quit() {
		getGUI().exit();
	}
}
