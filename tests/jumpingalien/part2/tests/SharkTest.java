package jumpingalien.part2.tests;

import static org.junit.Assert.*;
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
	public void constructor_ok(){
		assertEquals(shark.getPosition(), new Vector<Double>(0.0, 0.0));
	}
}
