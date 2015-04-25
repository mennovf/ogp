package jumpingalien.part2.tests;

import static org.junit.Assert.*;
import jumpingalien.model.Constants;
import jumpingalien.model.Shark;
import jumpingalien.model.Utilities;
import jumpingalien.model.Vector;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SharkTest {

	Shark shark;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		shark = Utilities.shark(new Vector<>(0.0, 0.0));
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void constructor_ok() {
		assertTrue(shark.getPositionInPixels().x == 0.0 && shark.getPositionInPixels().y == 0.0);
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
}
