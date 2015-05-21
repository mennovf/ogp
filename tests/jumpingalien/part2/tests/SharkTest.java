package jumpingalien.part2.tests;

import static org.junit.Assert.*;
import jumpingalien.model.Constants;
import jumpingalien.model.Utilities;
import jumpingalien.model.Vector;
import jumpingalien.model.gameobject.Mazub;
import jumpingalien.model.gameobject.School;
import jumpingalien.model.gameobject.Shark;
import jumpingalien.model.gameobject.Slime;
import jumpingalien.model.world.TileType;
import jumpingalien.model.world.World;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SharkTest {

	private Shark shark;
	private World world;
	private Vector<Double> startPos;
	private Mazub mazub;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		world = TestUtilities.world();
		
		world.setTileType(new Vector<Integer>(0, 0), TileType.GROUND);
		world.setTileType(new Vector<Integer>(0, 1), TileType.GROUND);
		world.setTileType(new Vector<Integer>(1, 0), TileType.GROUND);
		world.setTileType(new Vector<Integer>(2, 0), TileType.GROUND);
		world.setTileType(new Vector<Integer>(2, 1), TileType.GROUND);
		world.setTileType(new Vector<Integer>(1, 1), TileType.WATER);
		startPos = Utilities.pixelsVectorToMeters(new Vector<>(world.getTileSize(), world.getTileSize()));
		shark = TestUtilities.shark(startPos);
		mazub = TestUtilities.mazub(Vector.add(startPos, shark.getSizeInMeters()));
		world.setMazub(mazub);

		world.addGameObject(shark);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	
	
	@Test
	public void constructor_ok() {
		assertEquals(startPos, shark.getPositionInMeters());
	}
	
	
	
	@Test
	public void setSpeed_ok() {
		shark.setSpeed(new Vector<>(0.0, 0.0));
		assertEquals(0.0, shark.getSpeed().x, 1e-7);
		assertEquals(0.0, shark.getSpeed().y, 1e-7);
	}

	@Test
	public void setSpeed_clipped() {
		shark.setSpeed(new Vector<>(Constants.sharkMaxHorizontalSpeed + 1, 0.0));
		assertEquals(Constants.sharkMaxHorizontalSpeed, shark.getSpeed().x, 1e-7);

		shark.setSpeed(new Vector<>(-Constants.sharkMaxHorizontalSpeed - 1, 0.0));
		assertEquals(-Constants.sharkMaxHorizontalSpeed, shark.getSpeed().x, 1e-7);
	}
	
	
	
	@Test
	public void airDamage() {
		world.setTileType(new Vector<Integer>(1, 1), TileType.AIR);
		
		world.advanceTime(0.1);
		
		assertEquals(Constants.sharkBeginHealth, shark.getHealth());
		
		world.advanceTime(Constants.maxTimeInterval);
		assertEquals(Constants.sharkBeginHealth + Constants.sharkAirDamage, shark.getHealth());

		world.advanceTime(Constants.maxTimeInterval);
		assertEquals(Constants.sharkBeginHealth + 2 * Constants.sharkAirDamage, shark.getHealth());
	}
	
	@Test
	public void magmaDamage() {
		world.setTileType(new Vector<Integer>(1, 1), TileType.MAGMA);
		
		world.advanceTime(1e-5);
		
		assertEquals(Constants.sharkBeginHealth + Constants.magmaDamage, shark.getHealth());
		
		world.advanceTime(Constants.maxTimeInterval);
		assertEquals(Constants.sharkBeginHealth + 2 * Constants.magmaDamage, shark.getHealth());
	}

	@Test
	public void mazubDamage() {
		mazub.setPositionInMeters(new Vector<>(70 * 0.01, 70 * 0.01 + shark.getSizeInMeters().y - 0.01));
		world.advanceTime(Constants.maxTimeInterval);
		
		assertEquals(Constants.sharkBeginHealth + Constants.sharkEnemyDamage, shark.getHealth());
		
		for (int i = 0; i * Constants.maxTimeInterval <= Constants.enemyDamageInterval; ++i) {
			world.advanceTime(Constants.maxTimeInterval);
		}
		
		assertEquals(Constants.sharkBeginHealth + 2 * Constants.sharkEnemyDamage, shark.getHealth());
	}

	// Run this test about ten times, the randomness tends to make it fail.
	@Test
	public void slimeDamage() {
		School school = new School();
		Slime slime = TestUtilities.slime(new Vector<>(70 * 0.01, 70 * 0.01), school);
		world.addGameObject(slime);
		world.advanceTime(Constants.maxTimeInterval);
		
		assertEquals(Constants.sharkBeginHealth + Constants.sharkEnemyDamage, shark.getHealth());
		
		for (int i = 0; i * Constants.maxTimeInterval <= Constants.enemyDamageInterval; ++i) {
			world.advanceTime(Constants.maxTimeInterval);
		}
		
		assertEquals(Constants.sharkBeginHealth + 2 * Constants.sharkEnemyDamage, shark.getHealth());
	}
}
