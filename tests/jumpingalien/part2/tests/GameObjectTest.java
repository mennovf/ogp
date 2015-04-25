package jumpingalien.part2.tests;

import static org.junit.Assert.*;
import jumpingalien.model.GameObject;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class GameObjectTest {

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
	public void isValidDirection() {
		assertTrue(GameObject.isValidDirection(1));
		assertTrue(GameObject.isValidDirection(-1));
		assertFalse(GameObject.isValidDirection(0));
		assertFalse(GameObject.isValidDirection(2));
		assertFalse(GameObject.isValidDirection(-2));
		assertFalse(GameObject.isValidDirection(0.5));
		assertFalse(GameObject.isValidDirection(-0.5));
	}

}
