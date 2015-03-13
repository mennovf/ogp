package jumpingalien.part1.tests;

import static org.junit.Assert.*;
import jumpingalien.model.*;
import jumpingalien.util.Sprite;
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
    private final Sprite[] sprites = JumpingAlienSprites.ALIEN_SPRITESET;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	private Mazub mazub;

	@Before
	public void setUp() throws Exception {
		mazub = new Mazub(testStartPosition.x, testStartPosition.y, sprites, testVxInit, testVxMax, testStartDirection);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	
	// Constructor tests
	@Test(expected=IllegalArgumentException.class)
	public void contructor_invalidPosition(){
		new Mazub(-1.0, 0.0, JumpingAlienSprites.ALIEN_SPRITESET, testVxInit, testVxMax, 1);
	}
	
	@Test(expected=NullPointerException.class)
	public void contructor_invalidSprites(){
		new Mazub(0.0, 0.0, null, testVxInit, testVxMax, 1);
	}
	
	
	// getCurrentSprite tests
	@Test
	public void getCurrentSprite_start(){
		assertEquals(mazub.getCurrentSprite(), sprites[0]);
	}

	@Test
	public void getCurrentSprite_walkRight(){
		mazub.startMove(1);
		mazub.advanceTime(0.01);
		assertEquals(mazub.getCurrentSprite(), sprites[8]);
	}

	@Test
	public void getCurrentSprite_walkLeft(){
		mazub.startMove(-1);
		mazub.advanceTime(0.01);
		int m = (sprites.length - 8) / 2 - 1;
		assertEquals(mazub.getCurrentSprite(), sprites[9 + m]);
	}

	@Test
	public void getCurrentSprite_animation(){
		mazub.startMove(1);
		mazub.advanceTime(0.076);
		assertEquals(mazub.getCurrentSprite(), sprites[8 + 1]);
	}

	// isValidPosition Tests
	
	@Test
	public void isValidPosition_valid() {
		
		assertTrue(Mazub.isValidPosition(new Vector2D<Double>(0.0, 0.0)));
	}
	
	@Test
	public void isValidPosition_xTooSmall() {
		
		assertFalse(Mazub.isValidPosition(new Vector2D<Double>(-3.0, 0.0)));
	}
	
	@Test
	public void isValidPosition_yTooSmall() {
		
		assertFalse(Mazub.isValidPosition(new Vector2D<Double>(0.0, -3.0)));
	}
	
	@Test
	public void isValidPosition_xTooBig() {
		
		assertFalse(Mazub.isValidPosition(new Vector2D<Double>(Constants.screenSize.x + 1, 0.0)));
	}
	
	@Test
	public void isValidPosition_yTooBig() {
		
		assertFalse(Mazub.isValidPosition(new Vector2D<Double>(0.0, Constants.screenSize.y + 1)));
	}
	
	
	// isValidSpeed Tests
	
	@Test
	public void isValidSpeed_validNotDucking() {
		
		assertTrue(mazub.isValidSpeed(new Vector2D<Double>(testVxMax, 0.0)));
	}
	
	@Test
	public void isValidSpeed_validDucking() {
		
		mazub.startDuck();
		assertTrue(mazub.isValidSpeed(new Vector2D<Double>(Mazub.getMaxSpeedWhileDucking(), 0.0)));
	}
	
	@Test
	public void isValidSpeed_xTooSmall() {
		
		assertFalse(mazub.isValidSpeed(new Vector2D<Double>(-(testVxMax+1), 0.0)));
	}
	
	@Test
	public void isValidSpeed_xTooBig() {
		
		assertFalse(mazub.isValidSpeed(new Vector2D<Double>(testVxMax+1, 0.0)));
	}
	
	
	// getAcceleration Tests
	
	@Test
	public void getAcceleration_notMoving() {
		
		assertEquals(mazub.getAcceleration().x, 0.0, testAccuracy);
		assertEquals(mazub.getAcceleration().y, 0.0, testAccuracy);
	}
	
	@Test
	public void getAcceleration_startRunningRight() {
		
		mazub.startMove(1);
		
		assertEquals(mazub.getAcceleration().x, Mazub.getMaxAcceleration().x, testAccuracy);
		assertEquals(mazub.getAcceleration().y, 0.0, testAccuracy);
	}
	
	@Test
	public void getAcceleration_startRunningLeft() {
		
		mazub.startMove(-1);
		
		assertEquals(mazub.getAcceleration().x, -Mazub.getMaxAcceleration().x, testAccuracy);
		assertEquals(mazub.getAcceleration().y, 0.0, testAccuracy);
	}
	
	@Test
	public void getAcceleration_startJump() {
		
		mazub.startJump();
		mazub.advanceTime(Constants.maxTimeInterval);
		
		assertEquals(mazub.getAcceleration().x, 0.0, testAccuracy);
		assertEquals(mazub.getAcceleration().y, Mazub.getMaxAcceleration().y, testAccuracy);
	}
	
	
	// getSpeed Tests

	@Test
	public void getSpeed_notMoving() {
		
		assertEquals(mazub.getSpeed().x, 0.0, testAccuracy);
		assertEquals(mazub.getSpeed().y, 0.0, testAccuracy);
	}
	
	@Test
	public void getSpeed_running() {
		
		mazub.startMove(1);
		mazub.advanceTime(Constants.maxTimeInterval);
		
		assertNotEquals(mazub.getSpeed().x, 0.0, testAccuracy);
		assertEquals(mazub.getSpeed().y, 0.0, testAccuracy);
	}
	
	@Test
	public void getSpeed_jumping() {
		
		mazub.startJump();
		mazub.advanceTime(Constants.maxTimeInterval);
		
		assertEquals(mazub.getSpeed().x, 0.0, testAccuracy);
		assertNotEquals(mazub.getSpeed().y, 0.0, testAccuracy);
	}
	
	
	// getPosition Tests
	
	@Test
	public void getPosition_notMoved() {
		
		assertEquals((int)mazub.getPosition().x, 0);
		assertEquals((int)mazub.getPosition().y, 0);
	}
	
	@Test
	public void getPosition_running() {
		
		mazub.startMove(1);
		mazub.advanceTime(Constants.maxTimeInterval);
		
		assertNotEquals((int)mazub.getPosition().x, 0);
		assertEquals((int)mazub.getPosition().y, 0);
	}
	
	@Test
	public void getPosition_jumping() {
		
		mazub.startJump();
		mazub.advanceTime(Constants.maxTimeInterval);
		
		assertEquals((int)mazub.getPosition().x, 0);
		assertNotEquals((int)mazub.getPosition().y, 0);
	}
	
	
	// getHeight Tests
	
	@Test
	public void getHeight() {
		
		assertEquals(mazub.getHeight(), mazub.getCurrentSprite().getHeight());
	}
	
	
	// getWidth Tests
	
	@Test
	public void getWidth() {
		
		assertEquals(mazub.getWidth(), mazub.getCurrentSprite().getWidth());
	}
	
	
	// onGround Tests
	
	@Test
	public void onGround_notMoving() {
		
		assertTrue(mazub.onGround());
	}
	
	@Test
	public void onGround_running() {
		
		mazub.startMove(1);
		mazub.advanceTime(Constants.maxTimeInterval);
		
		assertTrue(mazub.onGround());
	}
	
	@Test
	public void onGround_jumping() {
		
		mazub.startJump();
		mazub.advanceTime(Constants.maxTimeInterval);
		
		assertFalse(mazub.onGround());
	}
	
	
	// startMove and endMove Tests
	
	@Test
	public void move() {
		
		mazub.startMove(1);
		mazub.advanceTime(Constants.maxTimeInterval);
		
		assertNotEquals(mazub.getSpeed().x, 0.0, testAccuracy);
		
		mazub.endMove();
		
		assertEquals(mazub.getSpeed().x, 0.0, testAccuracy);
	}
	
	
	// startJump and endJump Tests
	
	@Test
	public void jump() {
		
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
	public void duck() {
		
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
