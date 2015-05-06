package jumpingalien.part2.internal.tmxfile;

import java.io.File;
import java.util.Optional;

import jumpingalien.part2.internal.tmxfile.data.ImageTile;
import jumpingalien.part2.internal.tmxfile.data.Layer;
import jumpingalien.part2.internal.tmxfile.data.Map;
import jumpingalien.part2.internal.tmxfile.data.MapObject;
import jumpingalien.part2.internal.tmxfile.data.ObjectGroup;
import jumpingalien.part2.internal.tmxfile.data.Tileset;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

class TMXFileSAXHandler extends DefaultHandler {

	private Map map;
	private Tileset currentTileset;
	private Layer currentLayer;
	private ImageTile currentTile;

	private final String parentFolder;
	private ObjectGroup currentObjectGroup;
	private MapObject currentObject;

	public TMXFileSAXHandler(String parentFolder) {
		this.parentFolder = parentFolder;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		switch (qName) {
		case "map": {
			int width = Integer.parseInt(attributes.getValue("width"));
			int height = Integer.parseInt(attributes.getValue("height"));
			int tileWidth = Integer.parseInt(attributes.getValue("tilewidth"));
			int tileHeight = Integer
					.parseInt(attributes.getValue("tileheight"));
			map = new Map(width, height, tileWidth, tileHeight);
			map.setBackground(attributes.getValue("backgroundcolor"));
			map.setInitialX(tileHeight - 1);
			map.setInitialY(tileHeight - 1);
			map.setTargetTileX(width - 2);
			map.setTargetTileY(1);
			break;
		}
		case "properties": {
			break;
		}
		case "property": {
			String name = attributes.getValue("name");
			switch (name) {
			case "initialX": {
				int initialX = Integer.parseInt(attributes.getValue("value"));
				map.setInitialX(initialX);
				break;
			}
			case "initialY": {
				int initialY = Integer.parseInt(attributes.getValue("value"));
				map.setInitialY(initialY);
				break;
			}
			case "targetTileX": {
				int targetTileX = Integer
						.parseInt(attributes.getValue("value"));
				map.setTargetTileX(targetTileX);
				break;
			}
			case "targetTileY": {
				int targetTileY = Integer
						.parseInt(attributes.getValue("value"));
				map.setTargetTileY(targetTileY);
				break;
			}
			default: {
				if (currentObject != null) {
					currentObject.setAttribute(name, attributes.getValue("value"));
				} else {
					map.setAttribute(name, attributes.getValue("value"));
				}
			}
			}
			break;
		}
		case "tileset": {
			int gid = Integer.parseInt(attributes.getValue("firstgid"));
			currentTileset = map.newTileSet(gid);
			break;
		}
		case "layer": {
			String name = attributes.getValue("name");
			currentLayer = map.newLayer(name);
			break;
		}
		case "data":
			// ignore
			break;
		case "tile": {
			if (currentTileset != null) {
				int id = Integer.parseInt(attributes.getValue("id"));
				currentTile = currentTileset.newTile(id);
			} else if (currentLayer != null) {
				int id = Integer.parseInt(attributes.getValue("gid"));
				Optional<ImageTile> tile = map.findTile(id);
				currentLayer.addTile(tile.orElse(null));
			}
			break;
		}
		case "image": {
			int width = Integer.parseInt(attributes.getValue("width"));
			int height = Integer.parseInt(attributes.getValue("height"));
			String filename = attributes.getValue("source");
			File file = new File(parentFolder, filename);
			currentTile.setImage(width, height, file);
			break;
		}
		case "objectgroup": {
			String name = attributes.getValue("name");
			currentObjectGroup = map.newObjectGroup(name);
			break;
		}
		case "object": {
			int id = Integer.parseInt(attributes.getValue("id"));
			long gid = Long.parseLong(attributes.getValue("gid"));
			int x = Integer.parseInt(attributes.getValue("x"));
			int y = map.getPixelHeight()
					- Integer.parseInt(attributes.getValue("y"));
			boolean hflip = (gid & (0x1 << 31)) != 0;
			boolean vflip = (gid & (0x1 << 30)) != 0;

			map.findTile((int) (gid & 0x1fffffff)).ifPresent(tile -> {
				currentObject = new MapObject(id, tile, x, y);
				currentObject.setHFlip(hflip);
				currentObject.setVFlip(vflip);
				currentObjectGroup.addObject(currentObject);
			});
			break;
		}
		default:
			System.out.println("UNHANDLED: " + qName);
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		switch (qName) {
		case "tileset":
			currentTileset = null;
			break;
		case "layer":
			currentLayer = null;
			break;
		case "tile":
			currentTile = null;
			break;
		case "objectgroup":
			currentObjectGroup = null;
			break;
		case "object":
			currentObject = null;
			break;
		}
	}

	public Map getResult() {
		return map;
	}
}
