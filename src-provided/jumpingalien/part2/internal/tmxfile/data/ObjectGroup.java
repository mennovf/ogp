package jumpingalien.part2.internal.tmxfile.data;

import java.util.ArrayList;
import java.util.List;

public class ObjectGroup {

	private final List<MapObject> objects = new ArrayList<>();

	private final String name;

	public ObjectGroup(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void addObject(MapObject obj) {
		objects.add(obj);
	}

	public List<MapObject> getObjects() {
		return objects;
	}

}
