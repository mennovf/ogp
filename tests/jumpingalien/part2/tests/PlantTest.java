package jumpingalien.part2.tests;

import static org.junit.Assert.*;
import jumpingalien.model.Constants;
import jumpingalien.model.Mazub;
import jumpingalien.model.Plant;
import jumpingalien.model.Vector;
import jumpingalien.model.World;
import jumpingalien.part2.internal.Resources;
import jumpingalien.util.Sprite;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PlantTest {

	private Plant plant;
	private Sprite[] sprites;
	private Vector<Double> startPos;
	private World world;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		startPos = new Vector<>(10.0, 1.0);
		sprites = new Sprite[] {Resources.PLANT_SPRITE_LEFT, Resources.PLANT_SPRITE_RIGHT};
		plant = new Plant(startPos, sprites);
		world = TestUtilities.world();
		world.addGameObject(plant);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void moveRight(){
		plant.advanceTime(Constants.maxTimeInterval);
		assertTrue(plant.getPositionInMeters().x > startPos.x);
	}

	@Test
	public void moveLeft(){
		for (int i = 0; i * Constants.maxTimeInterval < Constants.plantMoveTime; ++i){
			plant.advanceTime(Constants.maxTimeInterval);
		}
		double xPos = plant.getPositionInMeters().x;
		plant.advanceTime(Constants.maxTimeInterval);
		assertTrue(plant.getPositionInMeters().x < xPos);
	}
	
	@Test
	public void directionToggle(){
		double eps = 0.00001;
		plant.advanceTime(0.2);
		plant.advanceTime(0.2);
		plant.advanceTime(0.1 - eps);
		assertEquals(1.0, plant.getFacing(), 1e-7);
		
		plant.advanceTime(2 * eps);
		assertEquals(-1.0, plant.getFacing(), 1e-7);
	}
	
	@Test
	public void getEaten_ok() {
		Mazub mazub = TestUtilities.mazub(startPos);
		world.setMazub(mazub);
		
		assertTrue(plant.isAlive());
		
		world.advanceTime(Constants.maxTimeInterval);
		
		assertTrue(plant.isAlive());
		assertTrue(plant.isHealthZero());
		
		
		world.advanceTime(Constants.maxTimeInterval);
		world.advanceTime(Constants.maxTimeInterval);
		world.advanceTime(1e-5);
		
		assertFalse(plant.isAlive());
	}
	
	@Test
	public void dontGetEaten_mazubFullHealth() {
		Mazub mazub = TestUtilities.mazub(startPos);
		world.setMazub(mazub);
		mazub.setHealth(Constants.mazubMaxHealth);
		
		world.advanceTime(Constants.maxTimeInterval);
		
		assertFalse(plant.isHealthZero());
	}
	
	@Test
	public void getEaten_mazubAlmostFull() {
		Mazub mazub = TestUtilities.mazub(startPos);
		world.setMazub(mazub);
		mazub.setHealth(Constants.mazubMaxHealth - Constants.mazubPlantHealthGain + 1);
		
		world.advanceTime(Constants.maxTimeInterval);
		
		assertTrue(plant.isHealthZero());
	}
}
