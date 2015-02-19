package jumpingalien.common.game;

import java.util.function.Consumer;

import ogp.framework.command.Command;

public abstract class AbstractActionHandler<TAlien, TFacade> implements
		IActionHandler {
	/**
	 * 
	 */
	private final JumpingAlienGame game;

	protected AbstractActionHandler(JumpingAlienGame game) {
		this.game = game;
	}

	protected JumpingAlienGame getGame() {
		return game;
	}

	protected abstract TFacade getFacade();

	protected abstract TAlien getAlien();

	protected void addAlienCommand(String name, Consumer<TAlien> action) {
		game.addCommand(new Command(name) {
			@Override
			public void execute() {
				game.catchErrorAction(() -> action.accept(getAlien()));
			}
		});
	}
}
