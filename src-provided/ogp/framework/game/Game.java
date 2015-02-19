package ogp.framework.game;

import java.util.List;
import java.util.Optional;

import ogp.framework.command.Command;
import ogp.framework.command.CommandQueue;
import ogp.framework.messages.Message;
import ogp.framework.messages.MessageBox;

public abstract class Game {
	
	private final GameOptions options;

	protected Game(GameOptions options) {
		this.options = options;
	}
	
	public GameOptions getOptions() {
		return options;
	}

	private final CommandQueue commandQueue = new CommandQueue();
	
	private final MessageBox messageBox = new MessageBox();

	public abstract void load();
	
	public abstract void start();

	public final void update(double dt) {
		messageBox.advanceTime(dt);
		doUpdate(dt);
	}
	
	protected abstract void doUpdate(double dt);

	protected void addCommand(Command command) {
		commandQueue.add(command);
	}

	public List<Command> peekPendingCommands() {
		return commandQueue.peekPendingCommands();
	}

	protected void executePendingCommands() {
		for (Command cmd : commandQueue.getAndClearPendingCommands()) {
			cmd.execute();
		}
	}
	
	public void addMessage(Message message) {
		messageBox.addMessage(message);
	}
	
	public Optional<Message> getCurrentMessage() {
		return messageBox.getCurrentMessage();
	}


}
