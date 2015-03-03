package jumpingalien.part1.tests;

import static org.junit.Assert.*;
import jumpingalien.model.*;
import jumpingalien.util.Sprite;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MazubTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	private Mazub mazub;

	@Before
	public void setUp() throws Exception {
		
		Sprite[] sprites = null;
		mazub = new Mazub(0.0, 0.0, sprites, 1.0, 3.0, 1.0);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetSpeed_notMoving() {
		
		assertTrue(mazub.getSpeed().x == 0.0 && mazub.getSpeed().y == 0.0);
	}
	
	@Test
	public void testGetPosition() {
		
	}
	
	@Test
	public void testGetHeight() {
		
	}
	
	@Test
	public void testGetWidth() {
		
	}
	
	@Test
	public void testOnGround() {
		
	}
	
	@Test
	public void testMove() {
		
	}
	
	@Test
	public void testJump() {
		
	}
	
	@Test
	public void testDuck() {
		
	}

}
