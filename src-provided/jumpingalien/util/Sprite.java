package jumpingalien.util;

public class Sprite {

	private final int width;
	private final int height;
	private final String name;

	public Sprite(String name, int width, int height) {
		this.name = name;
		this.width = width;
		this.height = height;
	}
	
	public String getName() {
		return name;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

	@Override
	public String toString() {
		return String.format("Sprite '%s' (size %dx%d)", name, width, height);
	}

}
