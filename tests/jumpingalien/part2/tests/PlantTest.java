package jumpingalien.part2.tests;

import static org.junit.Assert.*;
import jumpingalien.model.Constants;
import jumpingalien.model.Plant;
import jumpingalien.model.Utilities;
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
		world = Utilities.world();
		world.addGameObject(plant);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void moveRight(){
		plant.advanceTime(Constants.maxTimeInterval);
		assertTrue(plant.getPositionInMeters().x > startPos.x);
		assertTrue(plant.getCurrentSprite() == sprites[1]);
	}

	@Test
	public void moveLeft(){
		for (int i = 0; i * Constants.maxTimeInterval < Constants.plantMoveTime; ++i){
			plant.advanceTime(Constants.maxTimeInterval);
		}
		double xPos = plant.getPositionInMeters().x;
		plant.advanceTime(Constants.maxTimeInterval);
		assertTrue(plant.getPositionInMeters().x < xPos);
		assertTrue(plant.getCurrentSprite() == sprites[0]);
	}
	
	@Test
	public void directionToggle(){
		double eps = 0.00001;
		plant.advanceTime(0.5 - eps);
		assertTrue(plant.getFacing() == 1.0);
		
		plant.advanceTime(2 * eps);
		assertTrue(plant.getFacing() == -1.0);
	}

}
