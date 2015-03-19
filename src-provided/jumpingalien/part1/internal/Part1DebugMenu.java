package jumpingalien.part1.internal;

import java.util.function.BiConsumer;
import java.util.function.Function;

import jumpingalien.common.gui.AlienScreenPanel;
import ogp.framework.gui.Screen;
import ogp.framework.gui.ScreenPanel;
import ogp.framework.gui.menu.MenuOption;
import ogp.framework.gui.menu.MenuScreen;

public class Part1DebugMenu extends
		MenuScreen<JumpingAlienGamePart1, JumpingAlienGUIPart1> {

	public Part1DebugMenu(ScreenPanel<JumpingAlienGamePart1> panel,
			JumpingAlienGUIPart1 gui,
			Screen<JumpingAlienGamePart1, JumpingAlienGUIPart1> previous) {
		super(panel, gui, previous);
	}

	@Override
	protected AlienScreenPanel<JumpingAlienGamePart1> getPanel() {
		return (AlienScreenPanel<JumpingAlienGamePart1>) super.getPanel();
	}

	@Override
	protected void registerMenuOptions() {

		addDebugOption("Show info", Part1Options::getDebugShowInfo,
				Part1Options::setDebugShowInfo);
		addDebugOption("Show alien location and size",
				Part1Options::getDebugShowObjectLocationAndSize,
				Part1Options::setDebugShowAlienLocationAndSize);
		addDebugOption("Show axes", Part1Options::getDebugShowAxes,
				Part1Options::setDebugShowAxes);
		addDebugOption("Show location history",
				Part1Options::getDebugShowHistory,
				Part1Options::setDebugShowHistory);

		addOption(new MenuOption("Start game", this::startGame));

		addOption(new MenuOption("Return (Esc)", this::close));

	}

	private void addDebugOption(String name,
			Function<Part1Options, Boolean> optionGetter,
			BiConsumer<Part1Options, Boolean> optionSetter) {
		addDebugOption(name, optionGetter, optionSetter, null);
	}

	private void addDebugOption(String name,
			Function<Part1Options, Boolean> optionGetter,
			BiConsumer<Part1Options, Boolean> optionSetter, String description) {
		Part1Options options = getGUI().getGUIOptions();
		MenuOption debugOption = new MenuOption(() -> {
			String state = "Off";
			if (optionGetter.apply(options)) {
				state = "On";
			}
			return String.format("%s: %s", name, state);
		}, () -> {
			optionSetter.accept(options, !optionGetter.apply(options));
		}, description);
		debugOption.setScale(0.7f);
		addOption(debugOption);
	}

	private void startGame() {
		getPanel().switchToScreen(
				new Part1GameScreen(getPanel(), getGUI(), this));
	}
}
