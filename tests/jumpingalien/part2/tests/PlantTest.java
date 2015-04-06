package jumpingalien.part2.tests;

import static org.junit.Assert.*;
import jumpingalien.model.Constants;
import jumpingalien.model.Plant;
import jumpingalien.model.Vector;
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

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		startPos = new Vector<>(10.0, 1.0);
		sprites = new Sprite[] {Resources.SLIME_SPRITE_LEFT, Resources.SLIME_SPRITE_RIGHT};
		plant = new Plant(startPos, sprites);
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
		plant.advanceTime(0.5 + 0.6);
		assertTrue(plant.getPositionInMeters().x < startPos.x);
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
