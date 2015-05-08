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
	
//	public static void main(String[] args) {
//		LinkedList<String> heys = new LinkedList<String>();
//		CommandQueue queue = new CommandQueue();
//		queue.add(() -> {heys.add("Hello");});
//		queue.add(() -> {heys.add("World");});
//		
//		try {
//			queue.execute();
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalArgumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InvocationTargetException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		for (String word : heys) {
//			System.out.print(word);
//		}
//	}
}