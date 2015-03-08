package jumpingalien.part1.tests;

import static org.junit.Assert.*;
import jumpingalien.model.*;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;
import jumpingalien.common.sprites.JumpingAlienSprites;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MazubTest {
	
	private final double testAccuracy = 1e-7;
	
	private final Vector2D<Double> testStartPosition = new Vector2D<>(0.0, 0.0);
	private final double testVxInit = 1.0;
	private final double testVxMax = 3.0;
	private final double testStartDirection = 1.0;
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	private Mazub mazub;

	@Before
	public void setUp() throws Exception {
		
		Sprite[] sprites = JumpingAlienSprites.ALIEN_SPRITESET;
		mazub = new Mazub(testStartPosition.x, testStartPosition.y, sprites, testVxInit, testVxMax, testStartDirection);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	
	// isValidPosition Tests
	
	@Test
	public void testIsValidPosition_valid() {
		
		assertTrue(Mazub.isValidPosition(new Vector2D<Double>(0.0, 0.0)));
	}
	
	@Test
	public void testIsValidPosition_xTooSmall() {
		
		assertFalse(Mazub.isValidPosition(new Vector2D<Double>(-3.0, 0.0)));
	}
	
	@Test
	public void testIsValidPosition_yTooSmall() {
		
		assertFalse(Mazub.isValidPosition(new Vector2D<Double>(0.0, -3.0)));
	}
	
	@Test
	public void testIsValidPosition_xTooBig() {
		
		assertFalse(Mazub.isValidPosition(new Vector2D<Double>(Constants.screenSize.x + 1, 0.0)));
	}
	
	@Test
	public void testIsValidPosition_yTooBig() {
		
		assertFalse(Mazub.isValidPosition(new Vector2D<Double>(0.0, Constants.screenSize.y + 1)));
	}
	
	
	// isValidSpeed Tests
	
	@Test
	public void testIsValidSpeed_validNotDucking() {
		
		assertTrue(mazub.isValidSpeed(new Vector2D<Double>(testVxMax, 0.0)));
	}
	
	@Test
	public void testIsValidSpeed_validDucking() {
		
		mazub.startDuck();
		assertTrue(mazub.isValidSpeed(new Vector2D<Double>(Mazub.getMaxSpeedWhileDucking(), 0.0)));
	}
	
	@Test
	public void testIsValidSpeed_xTooSmall() {
		
		assertFalse(mazub.isValidSpeed(new Vector2D<Double>(-(testVxMax+1), 0.0)));
	}
	
	@Test
	public void testIsValidSpeed_xTooBig() {
		
		assertFalse(mazub.isValidSpeed(new Vector2D<Double>(testVxMax+1, 0.0)));
	}
	
	
	// getAcceleration Tests
	
	@Test
	public void testGetAcceleration_notMoving() {
		
		assertEquals(mazub.getAcceleration().x, 0.0, testAccuracy);
		assertEquals(mazub.getAcceleration().y, 0.0, testAccuracy);
	}
	
	@Test
	public void testGetAcceleration_startRunningRight() {
		
		mazub.startMove(1);
		
		assertEquals(mazub.getAcceleration().x, Mazub.getMaxAcceleration().x, testAccuracy);
		assertEquals(mazub.getAcceleration().y, 0.0, testAccuracy);
	}
	
	@Test
	public void testGetAcceleration_startRunningLeft() {
		
		mazub.startMove(-1);
		
		assertEquals(mazub.getAcceleration().x, -Mazub.getMaxAcceleration().x, testAccuracy);
		assertEquals(mazub.getAcceleration().y, 0.0, testAccuracy);
	}
	
	@Test
	public void testGetAcceleration_startJump() {
		
		mazub.startJump();
		mazub.advanceTime(Constants.maxTimeInterval);
		
		assertEquals(mazub.getAcceleration().x, 0.0, testAccuracy);
		assertEquals(mazub.getAcceleration().y, Mazub.getMaxAcceleration().y, testAccuracy);
	}
	
	
	// getSpeed Tests

	@Test
	public void testGetSpeed_notMoving() {
		
		assertEquals(mazub.getSpeed().x, 0.0, testAccuracy);
		assertEquals(mazub.getSpeed().y, 0.0, testAccuracy);
	}
	
	@Test
	public void testGetSpeed_running() {
		
		mazub.startMove(1);
		mazub.advanceTime(Constants.maxTimeInterval);
		
		assertNotEquals(mazub.getSpeed().x, 0.0, testAccuracy);
		assertEquals(mazub.getSpeed().y, 0.0, testAccuracy);
	}
	
	@Test
	public void testGetSpeed_jumping() {
		
		mazub.startJump();
		mazub.advanceTime(Constants.maxTimeInterval);
		
		assertEquals(mazub.getSpeed().x, 0.0, testAccuracy);
		assertNotEquals(mazub.getSpeed().y, 0.0, testAccuracy);
	}
	
	
	// getPosition Tests
	
	@Test
	public void testGetPosition_notMoved() {
		
		assertEquals(mazub.getPosition().x, 0.0, testAccuracy);
		assertEquals(mazub.getPosition().y, 0.0, testAccuracy);
	}
	
	@Test
	public void testGetPosition_running() {
		
		mazub.startMove(1);
		mazub.advanceTime(Constants.maxTimeInterval);
		
		assertNotEquals(mazub.getPosition().x, 0.0, testAccuracy);
		assertEquals(mazub.getPosition().y, 0.0, testAccuracy);
	}
	
	@Test
	public void testGetPosition_jumping() {
		
		mazub.startJump();
		mazub.advanceTime(Constants.maxTimeInterval);
		
		assertEquals(mazub.getPosition().x, 0.0, testAccuracy);
		assertNotEquals(mazub.getPosition().y, 0.0, testAccuracy);
	}
	
	
	// getHeight Tests
	
	@Test
	public void testGetHeight() {
		
		assertEquals(mazub.getHeight(), mazub.getCurrentSprite().getHeight());
	}
	
	
	// getWidth Tests
	
	@Test
	public void testGetWidth() {
		
		assertEquals(mazub.getWidth(), mazub.getCurrentSprite().getWidth());
	}
	
	
	// onGround Tests
	
	@Test
	public void testOnGround_notMoving() {
		
		assertTrue(mazub.onGround());
	}
	
	@Test
	public void testOnGround_running() {
		
		mazub.startMove(1);
		mazub.advanceTime(Constants.maxTimeInterval);
		
		assertTrue(mazub.onGround());
	}
	
	@Test
	public void testOnGround_jumping() {
		
		mazub.startJump();
		mazub.advanceTime(Constants.maxTimeInterval);
		
		assertFalse(mazub.onGround());
	}
	
	
	// startMove and endMove Tests
	
	@Test
	public void testMove() {
		
		mazub.startMove(1);
		mazub.advanceTime(Constants.maxTimeInterval);
		
		assertNotEquals(mazub.getSpeed().x, 0.0, testAccuracy);
		
		mazub.endMove();
		
		assertEquals(mazub.getSpeed().x, 0.0, testAccuracy);
	}
	
	
	// startJump and endJump Tests
	
	@Test
	public void testJump() {
		
		mazub.startJump();
		mazub.advanceTime(Constants.maxTimeInterval);
		
		assertNotEquals(mazub.getSpeed().y, 0.0, testAccuracy);
		
		mazub.endJump();
		
		assertEquals(mazub.getSpeed().y, 0.0, testAccuracy);
		
		mazub.advanceTime(Constants.maxTimeInterval);
		
		assertTrue(mazub.getSpeed().y < 0.0);
	}
	
	
	// startDuck and endDuck Tests
	
	@Test
	public void testDuck() {
		
		int height = mazub.getHeight();
		mazub.startDuck();
		mazub.advanceTime(Constants.maxTimeInterval);
		
		assertTrue(mazub.getHeight() < height);
		
		mazub.startMove(1);
		mazub.advanceTime(Constants.maxTimeInterval);
		
		assertEquals(mazub.getSpeed().x, 1.0, testAccuracy);
		
		mazub.endDuck();
		mazub.endMove();
		mazub.advanceTime(Constants.maxTimeInterval);
		
		assertEquals(mazub.getHeight(), height);
	}

}
