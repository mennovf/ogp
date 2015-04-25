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


public class SchoolTest {

	
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
	public void canHaveAsSlime_valid(){
		assertTrue(School.canHaveAsSlime(slime));
	}
	
	@Test
	public void canHaveAsSlime_invalid(){
		assertFalse(School.canHaveAsSlime(null));
	}
	
	
	
	@Test
	public void addSlime_ok(){
		school.addSlime(slime);
		assertTrue(school.containsSlime(slime));
		assertEquals(school, slime.getSchool());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void addSlime_invalid(){
		school.addSlime(null);
	}
	
	@Test
	public void containSlime_contains(){
		school.addSlime(slime);
		assertTrue(school.containsSlime(slime));
	}
	
	@Test
	public void containsSlime_doesntContain(){
		assertFalse(school.containsSlime(null));
	}
	
	@Test
	public void switchSchools_ok(){
		int amount1 = 5;
		for (int i = 0; i < amount1; ++i){
			Utilities.slime(new Vector<>(0.0, 0.0), school);
		}
		School school2 = new School();
		int amount2 = 7;
		for (int i = 0; i < amount2; ++i){
			Utilities.slime(new Vector<>(0.0, 0.0), school2);
		}
		school.addSlime(slime);
		School.switchSchools(school, school2, slime);
		assertTrue(school2.containsSlime(slime));
		assertFalse(school.containsSlime(slime));
		assertEquals(slime.getSchool(), school2);
		assertEquals(slime.getHealth(), 100);
		for (Slime schoolSlime : school.getSlimes()){
			assertEquals(schoolSlime.getHealth(), 100);
		}
		for (Slime schoolSlime : school2.getSlimes()){
			if (schoolSlime != slime) {
				assertEquals(schoolSlime.getHealth(), 100 - 1);
			}
		}
	}
	
	@Test
	public void size(){
		assertEquals(school.size(), 1);
		School otherSchool = new School();
		School.switchSchools(school, otherSchool, slime);
		assertEquals(school.size(), 0);
	}
	
	@Test
	public void takeDamageCausedBy(){
		school.takeDamageCausedBy(slime);
		assertEquals(slime.getHealth(), 100);
		
		Slime slime2 = Utilities.slime(new Vector<>(0.0, 0.0), school);
		Slime slime3 = Utilities.slime(new Vector<>(0.0, 0.0), school);
		
		school.takeDamageCausedBy(slime);
		assertEquals(slime.getHealth(), 100);
		assertEquals(slime2.getHealth(), 99);
		assertEquals(slime3.getHealth(), 99);
	}
}