package ogp.framework.command;

public class SimpleCommand extends Command {

	private final Runnable action;

	public SimpleCommand(String name, Runnable action) {
		super(name);
		this.action = action;
	}

	@Override
	public void execute() {
		action.run();
	}
}
