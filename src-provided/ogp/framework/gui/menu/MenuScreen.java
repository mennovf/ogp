package ogp.framework.gui.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import ogp.framework.command.Command;
import ogp.framework.command.CommandQueue;
import ogp.framework.command.SimpleCommand;
import ogp.framework.game.Game;
import ogp.framework.gui.GUI;
import ogp.framework.gui.InputMode;
import ogp.framework.gui.MessagePainter;
import ogp.framework.gui.Painter;
import ogp.framework.gui.Screen;
import ogp.framework.gui.ScreenPanel;
import ogp.framework.util.GUIUtils;

public abstract class MenuScreen<TGame extends Game, TGUI extends GUI<TGame>>
		extends Screen<TGame, TGUI> {

	private static final float FONT_SIZE = 26;

	private static final int TOP_MARGIN = 10;

	private final CommandQueue commandQueue = new CommandQueue();

	private int selectedIndex;

	protected MenuScreen(ScreenPanel<TGame> panel, TGUI gui,
			Screen<TGame, TGUI> previous) {
		super(panel, gui, previous);
	}

	@Override
	protected InputMode<TGame, TGUI> createDefaultInputMode() {
		return new InputMode<TGame, TGUI>(this, null) {
			@Override
			public void keyReleased(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_ESCAPE:
					getScreen().close();
					break;
				case KeyEvent.VK_DOWN:
				case KeyEvent.VK_KP_DOWN:
					commandQueue.add(new SimpleCommand("Move down",
							MenuScreen.this::selectNextOption));
					break;
				case KeyEvent.VK_UP:
				case KeyEvent.VK_KP_UP:
					commandQueue.add(new SimpleCommand("Move up",
							MenuScreen.this::selectPreviousOption));
					break;
				case KeyEvent.VK_ENTER:
				case KeyEvent.VK_SPACE:
					commandQueue.add(new SimpleCommand("Select",
							MenuScreen.this::selectCurrentOption));
					break;
				}
			}
		};
	}

	@Override
	public void screenStarted() {
		super.screenStarted();
		options.clear();
		registerMenuOptions();
	}

	@Override
	public void updateState(double dt) {
		for (Command command : commandQueue.getAndClearPendingCommands()) {
			command.execute();
		}
	}

	protected void selectNextOption() {
		selectedIndex = (selectedIndex + 1) % options.size();
	}

	protected void selectPreviousOption() {
		selectedIndex = (selectedIndex + options.size() - 1) % options.size();
	}

	protected void selectCurrentOption() {
		getSelectedOption().select();
	}

	protected MenuOption getSelectedOption() {
		return options.get(selectedIndex);
	}

	private final List<MenuOption> options = new ArrayList<>();

	protected void addOption(MenuOption option) {
		options.add(option);
	}

	protected abstract void registerMenuOptions();

	private Color defaultColor = Color.WHITE;
	private Color selectedColor = Color.ORANGE;

	private boolean drawCentered = false;

	@Override
	protected void setupPainters() {
		addPainter(new Painter<MenuScreen<TGame, TGUI>>(this) {
			@Override
			public void paintScreenPre(Graphics2D g) {

				int y = TOP_MARGIN;

				Font defaultFont = g.getFont().deriveFont(FONT_SIZE);
				for (MenuOption option : options) {
					float fontSize = option.getScale() * FONT_SIZE;
					g.setFont(defaultFont.deriveFont(fontSize));
					y += (int) (fontSize * 1.5);

					if (option.equals(getSelectedOption())) {
						g.setColor(selectedColor);
					} else {
						g.setColor(defaultColor);
					}
					if (drawCentered()) {
						GUIUtils.drawCenteredString(g, option.getString(),
								getScreenWidth(), y);
					} else {
						g.drawString(option.getString(), 10, y);
					}
				}

				MenuOption selected = getSelectedOption();
				if (selected != null && selected.getDescription() != null) {
					int fontSize = 12;
					g.setFont(defaultFont.deriveFont((float) fontSize));
					g.setColor(new Color(255, 255, 255, 128));
					y = (int) (getScreenHeight() - 3 * fontSize);
					g.fillRoundRect(10, y - 10, getScreenWidth() - 20,
							fontSize + 20, 4, 4);
					g.setColor(Color.BLACK);
					GUIUtils.drawCenteredString(g, selected.getDescription(),
							getScreenWidth(), y + fontSize);
				}
			}
		});

		addPainter(new MessagePainter<Screen<?, ?>>(this,
				getGame()::getCurrentMessage));
	}

	private boolean drawCentered() {
		return drawCentered;
	}

	public void setDrawCentered(boolean value) {
		this.drawCentered = value;
	}

}
