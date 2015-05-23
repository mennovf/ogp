package jumpingalien.part2.tests;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import jumpingalien.model.Vector;
import jumpingalien.model.gameobject.School;
import jumpingalien.model.gameobject.Slime;
import jumpingalien.tests.util.TestUtilities;

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
		slime = TestUtilities.slime(new Vector<>(0.0, 0.0), school);
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
	public void removeSlime() {
		school.removeSlime(slime);
		assertFalse(school.containsSlime(slime));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void removeSlime_notContainedSlime() {
		school.removeSlime(slime);
		school.removeSlime(slime);
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
	public void switchSchoolsOfSlimeTo_ok(){
		int amount1 = 5;
		for (int i = 0; i < amount1; ++i){
			TestUtilities.slime(new Vector<>(0.0, 0.0), school);
		}
		School school2 = new School();
		int amount2 = 7;
		for (int i = 0; i < amount2; ++i){
			TestUtilities.slime(new Vector<>(0.0, 0.0), school2);
		}
		school.addSlime(slime);
		School.switchSchoolsOfSlimeTo(slime, school2);
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
	public void getSlimes() {
		Set<Slime> slimes = new HashSet<Slime>();
		slimes.add(slime);
		assertEquals(slimes, school.getSlimes());
	}
	
	
	
	@Test
	public void size(){
		assertEquals(school.size(), 1);
		School otherSchool = new School();
		School.switchSchoolsOfSlimeTo(slime, otherSchool);
		assertEquals(school.size(), 0);
	}
	
	
	
	@Test
	public void takeDamageCausedBy(){
		school.takeDamageCausedBy(slime);
		assertEquals(slime.getHealth(), 100);
		
		Slime slime2 = TestUtilities.slime(new Vector<>(0.0, 0.0), school);
		Slime slime3 = TestUtilities.slime(new Vector<>(0.0, 0.0), school);
		
		school.takeDamageCausedBy(slime);
		assertEquals(slime.getHealth(), 100);
		assertEquals(slime2.getHealth(), 99);
		assertEquals(slime3.getHealth(), 99);
	}
}