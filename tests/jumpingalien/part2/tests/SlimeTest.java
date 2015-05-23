package jumpingalien.part2.tests;

import static org.junit.Assert.*;
import jumpingalien.model.Constants;
import jumpingalien.model.Vector;
import jumpingalien.model.gameobject.School;
import jumpingalien.model.gameobject.Slime;
import jumpingalien.part2.internal.Resources;
import jumpingalien.tests.util.TestUtilities;
import jumpingalien.util.Sprite;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SlimeTest {

	
	School school;
	Slime slime;
	Sprite[] sprites;
	Vector<Double> position;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		school = new School();
		sprites = new Sprite[] {Resources.SLIME_SPRITE_LEFT, Resources.SLIME_SPRITE_RIGHT};
		position = new Vector<>(0.0, 0.0);
		slime = TestUtilities.slime(position, school);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	
	

	@Test
	public void constructor() {
		assertEquals(school, slime.getSchool());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void constructor_invalidSchool() {
		new Slime(position, sprites, null);
	}
	
	
	
	@Test
	public void getSchool() {
		assertEquals(school, slime.getSchool());
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
		assertEquals(school, slime.getSchool());
		assertTrue(school.containsSlime(slime));
		
		School secondSchool = new School();
		slime.setSchool(secondSchool);
		assertEquals(secondSchool, slime.getSchool());
		assertTrue(secondSchool.containsSlime(slime));
		assertFalse(school.containsSlime(slime));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setSchool_null(){
		slime.setSchool(null);
	}
	
	
	
	@Test
	public void takeDamage() {
		Slime secondSlime = new Slime(position, sprites, school);
		
		int damage = -20;
		slime.takeDamage(damage);
		assertEquals(Constants.slimeBeginHealth + damage, slime.getHealth());
		assertEquals(Constants.slimeBeginHealth - 1, secondSlime.getHealth());
	}
}
