package jumpingalien.part2.internal.tmxfile;

import java.io.File;
import java.io.FileInputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import jumpingalien.part2.internal.tmxfile.data.Map;

public class TMXFileReader {

	public static void main(String[] args) {
		TMXFileReader reader = new TMXFileReader("levels/");
		Map map = reader.read("simple_and_small.tmx");
		System.out.println(map);
	}

	private final String levelsFolder;

	public TMXFileReader(String levelsFolder) {
		this.levelsFolder = levelsFolder;
	}

	public Map read(String filename) throws IllegalArgumentException {
		try {
			SAXParserFactory pf = SAXParserFactory.newInstance();
			SAXParser parser = pf.newSAXParser();
			TMXFileSAXHandler handler = new TMXFileSAXHandler(levelsFolder);
			parser.parse(new FileInputStream(new File(levelsFolder, filename)),
					handler);
			return handler.getResult();
		} catch (Exception e) {
			throw new IllegalArgumentException("Could not parse: "
					+ e.getMessage(), e);
		}
	}

}
