package ogp.framework.messages;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

public class MessageBox {

	private final Queue<Message> messages = new LinkedList<Message>();

	public void addMessage(Message message) {
		messages.add(message);
	}

	public Optional<Message> getCurrentMessage() {
		if (messages.isEmpty()) {
			return Optional.empty();
		} else {
			return Optional.of(messages.peek());
		}
	}

	private double currentMessageActive = 0;

	public void advanceTime(double dt) {
		Optional<Message> optMessage = getCurrentMessage();
		if (optMessage.isPresent()) {
			currentMessageActive += dt;
			while (optMessage.isPresent()
					&& currentMessageActive >= optMessage.get()
							.getTimeToDisplay()) {
				messages.poll();
				currentMessageActive = currentMessageActive
						- optMessage.get().getTimeToDisplay();
				optMessage = getCurrentMessage();
			}
			if (!optMessage.isPresent()) {
				currentMessageActive = 0;
			}
		}
	}
}
