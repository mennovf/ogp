package ogp.framework.command;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Command queue is the link between the main thread (game and display updates)
 * and the AWT event queue thread (reactions to key presses and mouse input).
 * 
 * This class is (supposed to be) thread-safe.
 * 
 * @author koeny
 *
 */
public class CommandQueue {

	private final LinkedBlockingQueue<Command> queue = new LinkedBlockingQueue<Command>();

	public void add(Command command) {
		queue.add(command);
	}

	public List<Command> getAndClearPendingCommands() {
		LinkedList<Command> result = new LinkedList<Command>();
		queue.drainTo(result);
		return result;
	}

	public List<Command> peekPendingCommands() {
		LinkedList<Command> result = new LinkedList<Command>(queue);
		return result;
	}

}
