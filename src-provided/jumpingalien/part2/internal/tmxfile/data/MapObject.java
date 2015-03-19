package jumpingalien.part2.internal.tmxfile.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MapObject {
	private final int x, y;
	private final ImageTile tile;
	private final int id;
	private boolean hflip;
	private boolean vflip;

	private final Map<String, String> attributes = new HashMap<String, String>();

	public MapObject(int id, ImageTile tile, int x, int y) {
		this.id = id;
		this.tile = tile;
		this.x = x;
		this.y = y;
	}

	public void setAttribute(String key, String value) {
		attributes.put(key, value);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public ImageTile getTile() {
		return tile;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return String.format("%d (%s): (%d, %d)", id, tile, x, y);
	}

	public void setHFlip(boolean hflip) {
		this.hflip = hflip;
	}

	public boolean getHflip() {
		return hflip;
	}

	public void setVFlip(boolean vflip) {
		this.vflip = vflip;
	}

	public boolean getVflip() {
		return vflip;
	}

	public Optional<Integer> getIntAttribute(String name) {
		if (attributes.containsKey(name)) {
			return Optional.of(Integer.parseInt(attributes.get(name)));
		}
		return Optional.empty();
	}
}
