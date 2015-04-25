package jumpingalien.part2.tests;

import static org.junit.Assert.*;
import jumpingalien.model.School;
import jumpingalien.model.Slime;
import jumpingalien.model.Utilities;
import jumpingalien.model.Vector;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SlimeTest {

	
	School school;
	Slime slime;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		school = new School();
		slime = Utilities.slime(new Vector<>(0.0, 0.0), school);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	
	

	@Test
	public void constructor_ok(){
		assertEquals(slime.getSchool(), school);
	}
	
	
	
	
	@Test
	public void canHaveAsSchool_ok(){
		assertTrue(Slime.canHaveAsSchool(school));
	}

	@Test
	public void canHaveAsSchool_null(){
		assertFalse(Slime.canHaveAsSchool(null));
	}
	
	

	@Test
	public void hasProperSchool(){
		assertTrue(slime.hasProperSchool());
	}
	
	
	
	@Test
	public void setSchool(){
		slime.setSchool(school);
		assertEquals(slime.getSchool(), school);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setSchool_null(){
		slime.setSchool(null);
	}
}
