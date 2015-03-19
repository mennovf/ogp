package jumpingalien.part2.internal.tmxfile.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Map {

	private final List<Tileset> tilesets = new ArrayList<>();
	private final List<Layer> layers = new ArrayList<>();
	private final List<ObjectGroup> objectGroups = new ArrayList<>();

	private final int nbTilesY;
	private final int nbTilesX;
	private final int tileSizeX;
	private final int tileSizeY;

	private String background;
	private int initialX;
	private int initialY;
	private int targetTileX;
	private int targetTileY;

	public Map(int width, int height, int tileSizeX, int tileSizeY) {
		this.nbTilesX = width;
		this.nbTilesY = height;
		this.tileSizeX = tileSizeX;
		this.tileSizeY = tileSizeY;
	}

	public int getNbTilesY() {
		return nbTilesY;
	}

	public int getNbTilesX() {
		return nbTilesX;
	}

	public int getTileSizeX() {
		return tileSizeX;
	}

	public int getTileSizeY() {
		return tileSizeY;
	}

	public int getPixelWidth() {
		return getNbTilesX() * getTileSizeX();
	}

	public int getPixelHeight() {
		return getNbTilesY() * getTileSizeY();
	}

	public Tileset newTileSet(int gid) {
		Tileset result = new Tileset(gid);
		tilesets.add(result);
		return result;
	}

	public Layer newLayer(String name) {
		Layer result = new Layer(name, nbTilesX, nbTilesY);
		layers.add(result);
		return result;
	}

	public Optional<ImageTile> findTile(int id) {
		return tilesets.stream().map(ts -> ts.findTile(id))
				.flatMap(o -> o.map(Stream::of).orElseGet(Stream::empty))
				.findAny();
	}

	public Layer getLayer(String name) {
		return layers.stream().filter(l -> name.equals(l.getName())).findAny()
				.orElse(null);
	}

	public List<Layer> getLayers() {
		return layers;
	}

	public List<ObjectGroup> getObjectGroups() {
		return objectGroups;
	}

	public void setBackground(String value) {
		this.background = value;
	}

	public String getBackground() {
		return background;
	}

	public boolean isValidTileCoordinate(int tileX, int tileY) {
		return tileX >= 0 && tileY >= 0 && tileX < getNbTilesX()
				&& tileY < getNbTilesY();
	}

	public ObjectGroup newObjectGroup(String name) {
		ObjectGroup result = new ObjectGroup(name);
		objectGroups.add(result);
		return result;
	}

	public int getInitialPositionX() {
		return initialX;
	}

	public int getInitialPositionY() {
		return initialY;
	}

	public void setInitialX(int initialX) {
		this.initialX = initialX;
	}

	public void setInitialY(int initialY) {
		this.initialY = initialY;
	}

	public int getTargetTileX() {
		return this.targetTileX;
	}

	public void setTargetTileX(int targetTileX) {
		this.targetTileX = targetTileX;
	}

	public int getTargetTileY() {
		return this.targetTileY;
	}

	public void setTargetTileY(int targetTileY) {
		this.targetTileY = targetTileY;
	}

	public Set<MapObject> getObjects() {
		return getObjectGroups().stream().flatMap(og -> og.getObjects().stream()).collect(Collectors.toSet());
	}
}
