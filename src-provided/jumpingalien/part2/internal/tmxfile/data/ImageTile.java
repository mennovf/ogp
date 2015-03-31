package jumpingalien.part2.internal.tmxfile.data;

import java.awt.Image;
import java.io.File;

import ogp.framework.util.GUIUtils;

/**
 * This class is only for internal use by the GUI
 * 
 * @author koeny
 *
 */
public class ImageTile {
	

	/**
	 * This class is only for internal use by the GUI
	 * 
	 * @author koeny
	 *
	 */
	public static enum TileType {
		AIR(0), SOLID(1), WATER(2), MAGMA(3);

		private final int value;

		TileType(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		public static TileType fromValue(int value) {
			for (TileType type : values()) {
				if (type.getValue() == value) {
					return type;
				}
			}
			throw new IllegalArgumentException("Tile type with value " + value
					+ " not defined.");
		}
	}

	public static TileType getTileTypeFor(String fileName) {
		if (fileName.startsWith("grass") || fileName.startsWith("castle")) {
			return TileType.SOLID;
		} else if (fileName.startsWith("liquidWater")) {
			return TileType.WATER;
		} else if (fileName.startsWith("liquidLava")) {
			return TileType.MAGMA;
		} else {
			return TileType.AIR;
		}
	}


	private final int id;
	private File file;
	private int height;
	private int width;
	private Image image;

	public ImageTile(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public File getFile() {
		return file;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setImage(int width, int height, File file) {
		this.width = width;
		this.height = height;
		this.file = file;
	}

	public TileType getType() {
		return getTileTypeFor(file.getName());
	}

	public Image getImage() {
		// lazy loading
		if (image == null) {
			image = GUIUtils.loadImage(file.getAbsolutePath());
		}
		return image;
	}

	@Override
	public String toString() {
		return String.format("%d (%s): %dx%d", id, file, width, height);
	}

	public String getFilename() {
		return file.getPath();
	}
	
	public String getOSIndependentFilename() {
        return file.getPath().replace("\\","/");
	}
}
