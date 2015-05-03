package jumpingalien.part2.tests;

import static org.junit.Assert.*;
import jumpingalien.model.Utilities;
import jumpingalien.model.Vector;
import jumpingalien.model.world.Tile;
import jumpingalien.model.world.TileType;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TileTest {
	
	
	private Vector<Integer> position;
	private int size;
	private TileType type;
	private Tile tile;
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		position = new Vector<Integer>(1, 5);
		size = 70;
		type = TileType.MAGMA;
		tile = new Tile(position, size, type);
	}

	@After
	public void tearDown() throws Exception {
	}

	
	
	
	@Test(expected = IllegalArgumentException.class)
	public void constructor_wrongPosition_negativeX() {
		new Tile(new Vector<Integer>(-1, 5), 70, TileType.MAGMA);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void constructor_wrongPosition_negativeY() {
		new Tile(new Vector<Integer>(1, -5), 70, TileType.MAGMA);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void constructor_wrongSize_negativeXAndY() {
		new Tile(new Vector<Integer>(-1, -5), 70, TileType.MAGMA);
	}
	
	
	
	@Test
	public void getPositionInTiles() {
		assertEquals(position, tile.getPositionInTiles());
	}
	
	
	
	@Test
	public void getPositionInPixels() {
		assertEquals(Vector.scale(position, size), tile.getPositionInPixels());
	}
	
	
	
	@Test
	public void getPositionInMeters() {
		assertEquals(Utilities.pixelsVectorToMeters(tile.getPositionInPixels()),
				tile.getPositionInMeters());
	}
	
	
	
	@Test
	public void getBoundingBoxPositionInPixels() {
		assertEquals(Vector.scale(position, size), tile.getBoundingBoxPositionInPixels());
	}
	
	
	
	@Test
	public void getTopRightPixel() {
		assertEquals(Vector.add(tile.getPositionInPixels(), tile.getSizeVectorInPixels()),
				tile.getTopRightPixel());
	}
	
	
	
	@Test
	public void getSizeInPixels() {
		assertEquals(size, tile.getSizeInPixels());
	}
	
	
	
	@Test
	public void getSizeVectorInPixels() {
		assertEquals(new Vector<>(size, size), tile.getSizeVectorInPixels());
	}
	
	
	
	@Test
	public void getBoundingBoxSizeInPixels() {
		assertEquals(tile.getSizeVectorInPixels(), tile.getBoundingBoxSizeInPixels());
	}
	
	
	
	@Test
	public void getType() {
		assertEquals(type, tile.getType());
	}
	
	
	
	@Test
	public void isPassable() {
		assertEquals(type.isPassable(), tile.isPassable());
	}

}
