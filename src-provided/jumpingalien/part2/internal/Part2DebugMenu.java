package jumpingalien.part2.internal;

import java.util.function.BiConsumer;
import java.util.function.Function;

import jumpingalien.common.gui.AlienScreenPanel;
import ogp.framework.gui.Screen;
import ogp.framework.gui.ScreenPanel;
import ogp.framework.gui.menu.MenuOption;
import ogp.framework.gui.menu.MenuScreen;

public class Part2DebugMenu extends
		MenuScreen<JumpingAlienGamePart2, JumpingAlienGUIPart2> {

	public Part2DebugMenu(ScreenPanel<JumpingAlienGamePart2> panel,
			JumpingAlienGUIPart2 gui,
			Screen<JumpingAlienGamePart2, JumpingAlienGUIPart2> previous) {
		super(panel, gui, previous);
	}

	@Override
	protected AlienScreenPanel<JumpingAlienGamePart2> getPanel() {
		return (AlienScreenPanel<JumpingAlienGamePart2>) super.getPanel();
	}

	@Override
	protected void registerMenuOptions() {

		addDebugOption("Show info", Part2Options::getDebugShowInfo,
				Part2Options::setDebugShowInfo);
		addDebugOption("Show game object location and size",
				Part2Options::getDebugShowObjectLocationAndSize,
				Part2Options::setDebugShowObjectLocationAndSize);
		addDebugOption("Show game object toString()",
				Part2Options::getDebugShowObjectString,
				Part2Options::setDebugShowObjectString);
		addDebugOption("Show axes", Part2Options::getDebugShowAxes,
				Part2Options::setDebugShowAxes);
		addDebugOption("Show location history",
				Part2Options::getDebugShowHistory,
				Part2Options::setDebugShowHistory);
		addDebugOption("Color overlapping tiles",
				Part2Options::getDebugShowAlienOverlappingTiles,
				Part2Options::setDebugShowAlienOverlappingTiles);
		addDebugOption("Color-code tile types",
				Part2Options::getDebugShowTileTypes,
				Part2Options::setDebugShowTileTypes);
		addDebugOption("Show tile gridlines",
				Part2Options::getDebugShowTileGridlines,
				Part2Options::setDebugShowTileGridlines);
		addDebugOption("Show entire world on screen",
				Part2Options::getDebugShowEntireWorld,
				Part2Options::setDebugShowEntireWorld);

		addOption(new MenuOption("Start game", this::startGame));

		addOption(new MenuOption("Return (Esc)", this::close));

	}

	private void addDebugOption(String name,
			Function<Part2Options, Boolean> optionGetter,
			BiConsumer<Part2Options, Boolean> optionSetter) {
		Part2Options options = getGUI().getGUIOptions();
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
				new Part2GameScreen(getPanel(), getGUI(), this));
	}
}
