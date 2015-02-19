package ogp.framework.messages;

public class Message {

	private static final double DEFAULT_DISPLAY_TIME = 2.0; // seconds
	
	private final MessageType type;
	private final String message;

	private final double timeToDisplay; // seconds

	public Message(MessageType type, String message) {
		this(type, message, DEFAULT_DISPLAY_TIME);
	}

	public Message(MessageType type, String message, double timeToDisplay) {
		this.type = type;
		this.message = message;
		this.timeToDisplay = timeToDisplay;
	}

	public String getMessage() {
		return message;
	}

	public double getTimeToDisplay() {
		return timeToDisplay;
	}

	public MessageType getType() {
		return type;
	}

	@Override
	public String toString() {
		return String.format("[%s] %s", type, message);
	}

}
