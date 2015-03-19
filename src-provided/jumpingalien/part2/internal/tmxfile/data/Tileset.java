package jumpingalien.part2.internal.tmxfile.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Tileset {

	private int firstId;

	public Tileset(int gid) {
		this.firstId = gid;
	}

	private List<ImageTile> tiles = new ArrayList<>();

	public ImageTile newTile(int id) {
		ImageTile result = new ImageTile(firstId + id);
		tiles.add(result);
		return result;
	}

	public int getFirstId() {
		return firstId;
	}

	public int getLastId() {
		return firstId + tiles.size();
	}

	public Optional<ImageTile> findTile(int id) {
		return tiles.stream().filter(t -> t.getId() == id).findAny();
	}

}
