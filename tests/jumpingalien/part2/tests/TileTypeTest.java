package jumpingalien.part2.tests;

import static org.junit.Assert.*;
import jumpingalien.model.TileType;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TileTypeTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	
	
	@Test
	public void tileTypeForNumber() {
		TileType type = TileType.tileTypeForNumber(0);
		assertEquals(TileType.AIR, type);
		
		type = TileType.tileTypeForNumber(1);
		assertEquals(TileType.GROUND, type);
		
		type = TileType.tileTypeForNumber(2);
		assertEquals(TileType.WATER, type);
		
		type = TileType.tileTypeForNumber(3);
		assertEquals(TileType.MAGMA, type);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void tileTypeForNumber_unknownNumber_tooHigh() {
		TileType.tileTypeForNumber(4);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void tileTypeForNumber_unknownNumber_negative() {
		TileType.tileTypeForNumber(-1);
	}
	
	
	
	@Test
	public void getNumber() {
		TileType type = TileType.AIR;
		assertEquals(0, type.getNumber());
		
		type = TileType.GROUND;
		assertEquals(1, type.getNumber());
		
		type = TileType.WATER;
		assertEquals(2, type.getNumber());
		
		type = TileType.MAGMA;
		assertEquals(3, type.getNumber());
	}
	
	
	
	@Test
	public void isPassable() {
		TileType type = TileType.AIR;
		assertTrue(type.isPassable());
		
		type = TileType.GROUND;
		assertFalse(type.isPassable());
		
		type = TileType.WATER;
		assertTrue(type.isPassable());
		
		type = TileType.MAGMA;
		assertTrue(type.isPassable());
	}
}
