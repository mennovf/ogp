package jumpingalien.model.command;

import java.util.LinkedList;

public class CommandQueue {

	@FunctionalInterface
	public static interface Command {
		void execute();
	}

	private LinkedList<Command> queue = new LinkedList<Command>();
	
	public void add(Command command) {
		queue.add(command);
	}
	
	public void execute() {
		while (this.queue.size() != 0) {
			Command command = this.queue.removeFirst();
			command.execute();
		}
	}
}