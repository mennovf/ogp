package ogp.framework.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Optional;
import java.util.function.Supplier;

import ogp.framework.messages.Message;
import ogp.framework.util.GUIUtils;

public class MessagePainter<ScreenType extends Screen<?, ?>> extends
		Painter<ScreenType> {

	private final Supplier<Optional<Message>> messageSupplier;

	public MessagePainter(ScreenType screen, Supplier<Optional<Message>> messageSupplier) {
		super(screen);
		this.messageSupplier = messageSupplier;
	}

	@Override
	public void paintScreenPost(Graphics2D g) {
		Optional<Message> message = messageSupplier.get();
		if (message.isPresent()) {
			paintMessage(g, message.get());
		}
	}

	private void paintMessage(Graphics2D g, Message message) {
		int fontSize = 15;
		Color backgroundColor = getBackgroundColor(message);
		Color foregroundColor = getForegroundColor(message);

		g.setFont(g.getFont().deriveFont((float) fontSize));
		g.setColor(backgroundColor);
		int y = (int) (getScreen().getScreenHeight() - 3 * fontSize);
		g.fillRoundRect(10, y - 10, getScreen().getScreenWidth() - 20,
				fontSize + 20, 4, 4);
		g.setColor(foregroundColor);
		GUIUtils.drawCenteredString(g, message.getMessage(), getScreen()
				.getScreenWidth(), y + fontSize);
	}

	protected Color getForegroundColor(Message message) {
		return Color.BLACK;
	}

	protected Color getBackgroundColor(Message message) {
		switch (message.getType()) {
		case ERROR:
			return new Color(255, 64, 64, 128);
		case INFO:
			return new Color(64, 255, 64, 128);
		case WARNING:
			return new Color(255, 255, 64, 128);
		case DEBUG:
		default:
			return new Color(255, 255, 255, 128);
		}
	}
}
