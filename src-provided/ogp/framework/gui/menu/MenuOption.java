package ogp.framework.gui.menu;

import java.util.function.Supplier;

public class MenuOption {

	private final Supplier<String> supplier;
	private final Runnable action;
	private float scale = 1.0f;

	private final String description;

	public MenuOption(String text, Runnable action) {
		this(text, action, null);
	}

	public MenuOption(String text, Runnable action, String description) {
		this(() -> text, action, description);
	}

	public MenuOption(Supplier<String> textSupplier, Runnable action) {
		this(textSupplier, action, null);
	}

	public MenuOption(Supplier<String> textSupplier, Runnable action,
			String description) {
		this.supplier = textSupplier;
		this.action = action;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void select() {
		action.run();
	}

	public String getString() {
		return supplier.get();
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public float getScale() {
		return scale;
	}

}
