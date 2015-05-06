package jumpingalien.part3.internal;

import java.util.function.BiConsumer;
import java.util.function.Function;

import jumpingalien.common.gui.AlienScreenPanel;
import ogp.framework.gui.Screen;
import ogp.framework.gui.ScreenPanel;
import ogp.framework.gui.menu.MenuOption;
import ogp.framework.gui.menu.MenuScreen;

public class Part3DebugMenu extends
		MenuScreen<JumpingAlienGamePart3, JumpingAlienGUIPart3> {

	public Part3DebugMenu(ScreenPanel<JumpingAlienGamePart3> panel,
			JumpingAlienGUIPart3 gui,
			Screen<JumpingAlienGamePart3, JumpingAlienGUIPart3> previous) {
		super(panel, gui, previous);
	}

	@Override
	protected AlienScreenPanel<JumpingAlienGamePart3> getPanel() {
		return (AlienScreenPanel<JumpingAlienGamePart3>) super.getPanel();
	}

	@Override
	protected void registerMenuOptions() {

		addDebugOption("Show info", Part3Options::getDebugShowInfo,
				Part3Options::setDebugShowInfo);
		addDebugOption("Show game object location and size",
				Part3Options::getDebugShowObjectLocationAndSize,
				Part3Options::setDebugShowObjectLocationAndSize);
		addDebugOption("Show game object toString()",
				Part3Options::getDebugShowObjectString,
				Part3Options::setDebugShowObjectString);
		addDebugOption("Show axes", Part3Options::getDebugShowAxes,
				Part3Options::setDebugShowAxes);
		addDebugOption("Show location history",
				Part3Options::getDebugShowHistory,
				Part3Options::setDebugShowHistory);
		addDebugOption("Color overlapping tiles",
				Part3Options::getDebugShowAlienOverlappingTiles,
				Part3Options::setDebugShowAlienOverlappingTiles);
		addDebugOption("Color-code tile types",
				Part3Options::getDebugShowTileTypes,
				Part3Options::setDebugShowTileTypes);
		addDebugOption("Show tile gridlines",
				Part3Options::getDebugShowTileGridlines,
				Part3Options::setDebugShowTileGridlines);
		addDebugOption("Show entire world on screen",
				Part3Options::getDebugShowEntireWorld,
				Part3Options::setDebugShowEntireWorld);

		addOption(new MenuOption("Start game", this::startGame));

		addOption(new MenuOption("Return (Esc)", this::close));

	}

	private void addDebugOption(String name,
			Function<Part3Options, Boolean> optionGetter,
			BiConsumer<Part3Options, Boolean> optionSetter) {
		Part3Options options = getGUI().getGUIOptions();
		MenuOption debugOption = new MenuOption(() -> {
			String state = "Off";
			if (optionGetter.apply(options)) {
				state = "On";
			}
			return String.format("%s: %s", name, state);
		}, () -> {
			optionSetter.accept(options, !optionGetter.apply(options));
		});
		debugOption.setScale(0.7f);
		addOption(debugOption);
	}

	private void startGame() {
		getPanel().switchToScreen(
				new Part3GameScreen(getPanel(), getGUI(), this));
	}
}
