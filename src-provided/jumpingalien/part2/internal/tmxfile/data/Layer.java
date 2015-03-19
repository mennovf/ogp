package jumpingalien.part2.internal.tmxfile.data;

import java.util.ArrayList;
import java.util.List;

public class Layer {

	private final List<ImageTile> tiles = new ArrayList<>();

	private final String name;

	private final int nbTilesY;

	private final int nbTilesX;

	public Layer(String name, int nbTilesX, int nbTilesY) {
		this.name = name;
		this.nbTilesX = nbTilesX;
		this.nbTilesY = nbTilesY;
	}

	public String getName() {
		return name;
	}

	public void addTile(ImageTile tile) {
		tiles.add(tile);
	}

	public ImageTile getTile(int tileX, int tileY) {
		int tileIndex = (nbTilesY - tileY - 1) * nbTilesX + tileX;
		return tiles.get(tileIndex);
	}

}
