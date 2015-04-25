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
	
	private final Vector<Double> testStartPosition = Utilities.pixelsVectorToMeters(new Vector<>(70, 69));
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
	private World world;

	@Before
	public void setUp() throws Exception {
		world = Utilities.world();
		world.setTileType(new Vector<>(0, 0), TileType.GROUND);
		world.setTileType(new Vector<>(1, 0), TileType.GROUND);
		world.setTileType(new Vector<>(2, 0), TileType.GROUND);
		world.setTileType(new Vector<>(3, 0), TileType.GROUND);
		world.setTileType(new Vector<>(0, 1), TileType.GROUND);
		world.setTileType(new Vector<>(1, 1), TileType.AIR);
		world.setTileType(new Vector<>(2, 1), TileType.AIR);
		world.setTileType(new Vector<>(3, 1), TileType.AIR);
		world.setTileType(new Vector<>(0, 2), TileType.GROUND);
		world.setTileType(new Vector<>(1, 2), TileType.AIR);
		world.setTileType(new Vector<>(2, 2), TileType.AIR);
		world.setTileType(new Vector<>(3, 2), TileType.AIR);
		world.setTileType(new Vector<>(0, 3), TileType.GROUND);
		world.setTileType(new Vector<>(1, 3), TileType.AIR);
		world.setTileType(new Vector<>(2, 3), TileType.AIR);
		world.setTileType(new Vector<>(3, 3), TileType.AIR);
		mazub = new Mazub(new Vector<>(testStartPosition.x, testStartPosition.y), sprites, testVxInit, testVxMax, testStartDirection);
		world.setMazub(mazub);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	
	// Constructor tests
	@Test(expected=IllegalArgumentException.class)
	public void contructor_invalidPosition(){
		new Mazub(new Vector<>(-1.0, 0.0), JumpingAlienSprites.ALIEN_SPRITESET, testVxInit, testVxMax, 1);
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
		
		assertTrue(mazub.isValidPosition(new Vector<Double>(0.0, 0.0)));
	}
	
	@Test
	public void isValidPosition_xTooSmall() {
		
		assertFalse(mazub.isValidPosition(new Vector<Double>(-3.0, 0.0)));
	}
	
	@Test
	public void isValidPosition_yTooSmall() {
		
		assertFalse(mazub.isValidPosition(new Vector<Double>(0.0, -3.0)));
	}
	
	@Test
	public void isValidPosition_xTooBig() {
		
		assertFalse(mazub.isValidPosition(new Vector<Double>(world.getSizeInMeters().x + 1.0, 0.0)));
	}
	
	@Test
	public void isValidPosition_yTooBig() {
		
		assertFalse(mazub.isValidPosition(new Vector<Double>(0.0, world.getSizeInMeters().y + 1.0)));
	}
	
	
	// isValidSpeed Tests
	
	@Test
	public void isValidSpeed_validNotDucking() {
		
		assertTrue(mazub.isValidSpeed(new Vector<Double>(testVxMax, 0.0)));
	}
	
	@Test
	public void isValidSpeed_validDucking() {
		
		mazub.startDuck();
		assertTrue(mazub.isValidSpeed(new Vector<Double>(Constants.mazubMaxSpeedDucking, 0.0)));
	}
	
	@Test
	public void isValidSpeed_xTooSmall() {
		
		assertFalse(mazub.isValidSpeed(new Vector<Double>(-(testVxMax+1), 0.0)));
	}
	
	@Test
	public void isValidSpeed_xTooBig() {
		
		assertFalse(mazub.isValidSpeed(new Vector<Double>(testVxMax+1, 0.0)));
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
		
		assertEquals(mazub.getAcceleration().x, Constants.mazubMaxSpeedDucking, testAccuracy);
		assertEquals(mazub.getAcceleration().y, 0.0, testAccuracy);
	}
	
	@Test
	public void getAcceleration_startRunningLeft() {
		
		mazub.startMove(-1);
		
		assertEquals(mazub.getAcceleration().x, -Constants.mazubMaxSpeedDucking, testAccuracy);
		assertEquals(mazub.getAcceleration().y, 0.0, testAccuracy);
	}
	
	@Test
	public void getAcceleration_startJump() {
		
		mazub.startJump();
		mazub.advanceTime(Constants.maxTimeInterval);
		
		assertEquals(mazub.getAcceleration().x, 0.0, testAccuracy);
		assertEquals(mazub.getAcceleration().y, Constants.gravityAcceleration, testAccuracy);
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
		assertEquals(testStartPosition, mazub.getPositionInMeters());
	}
	
	@Test
	public void getPosition_running() {
		
		mazub.startMove(1);
		mazub.advanceTime(Constants.maxTimeInterval);
		
		assertNotEquals((int)mazub.getPositionInPixels().x, testStartPosition.x);
		assertEquals((int)mazub.getPositionInPixels().y, Utilities.metersToPixels(testStartPosition.y));
	}
	
	@Test
	public void getPosition_jumping() {
		
		mazub.startJump();
		mazub.advanceTime(Constants.maxTimeInterval);
		
		assertTrue(Util.fuzzyEquals(mazub.getPositionInMeters().x, testStartPosition.x));
		assertNotEquals(mazub.getPositionInMeters().y, testStartPosition.x);
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
	
	@Test
	public void duck_underTerrain(){
		Vector<Integer> tilePos = new Vector<>(1, 2);
		world.setTileType(tilePos, TileType.GROUND);

		int height = mazub.getHeight();
		mazub.startDuck();
		mazub.advanceTime(Constants.maxTimeInterval / 10.0);
		int duckHeight = mazub.getHeight();
		assertTrue(duckHeight < height);
		
		mazub.endDuck();
		mazub.advanceTime(Constants.maxTimeInterval / 10.0);
		
		assertEquals(mazub.getHeight(), duckHeight);
		
		world.setTileType(tilePos, TileType.AIR);
		mazub.advanceTime(Constants.maxTimeInterval / 10.0);
		
		assertEquals(mazub.getHeight(), height);
	}
	@Test
	public void duck_underObject(){
		Vector<Double> objPos = Utilities.pixelsVectorToMeters(new Vector<>(70, 145));
		Shark shark = Utilities.shark(objPos);
		world.addGameObject(shark);
		world.setMazub(mazub);

		int height = mazub.getHeight();
		mazub.startDuck();
		mazub.advanceTime(Constants.maxTimeInterval / 10.0);
		int duckHeight = mazub.getHeight();
		assertTrue(duckHeight < height);
		
		mazub.endDuck();
		mazub.advanceTime(Constants.maxTimeInterval / 10.0);
		
		assertEquals(mazub.getHeight(), duckHeight);
		
		world.removeGameObject(shark);
		mazub.advanceTime(Constants.maxTimeInterval / 10.0);
		
		assertEquals(mazub.getHeight(), height);
	}

	@Test
	public void moveThenJump(){
		mazub.startMove(1);
		mazub.advanceTime(Constants.maxTimeInterval);
		mazub.startJump();
		mazub.advanceTime(Constants.maxTimeInterval);
		
		assertTrue(mazub.getSpeed().x > 0);
		assertTrue(mazub.getSpeed().y > 0);
		mazub.endJump();
		assertTrue(Util.fuzzyEquals(mazub.getSpeed().y, 0.0));
		assertTrue(mazub.getSpeed().x > 0);
		mazub.endMove();
		assertTrue(Util.fuzzyEquals(mazub.getSpeed().x, 0.0));
		assertTrue(Util.fuzzyEquals(mazub.getSpeed().y, 0.0));
	}
	
	@Test
	public void jumpThenMove(){
		mazub.startJump();
		mazub.advanceTime(Constants.maxTimeInterval);
		mazub.startMove(1);
		mazub.advanceTime(Constants.maxTimeInterval);
		
		assertTrue(mazub.getSpeed().x > 0);
		assertTrue(mazub.getSpeed().y > 0);
		mazub.endJump();
		assertTrue(Util.fuzzyEquals(mazub.getSpeed().y, 0.0));
		assertTrue(mazub.getSpeed().x > 0);
		mazub.endMove();
		assertTrue(Util.fuzzyEquals(mazub.getSpeed().x, 0.0));
		assertTrue(Util.fuzzyEquals(mazub.getSpeed().y, 0.0));
	}
	
	@Test
	public void jumpAndDuck(){
		int height = mazub.getHeight();
		mazub.startJump();
		mazub.startDuck();
		mazub.advanceTime(Constants.maxTimeInterval);
		
		assertTrue(mazub.getSpeed().y > 0);
		int duckingHeight = mazub.getHeight();
		assertTrue(duckingHeight < height);
		
		mazub.endDuck();
		mazub.advanceTime(Constants.maxTimeInterval);
		
		assertTrue(mazub.getHeight() > duckingHeight);
		assertTrue(mazub.getSpeed().y > 0);
	}
	
	
	@Test
	public void duckAndMove(){
		int height = mazub.getHeight();
		mazub.startDuck();
		mazub.startMove(1);
		mazub.advanceTime(Constants.maxTimeInterval);
		
		int duckingHeight = mazub.getHeight();
		assertTrue(duckingHeight < height);
		assertTrue(mazub.getSpeed().x > 0);
		
		mazub.endDuck();
		mazub.advanceTime(Constants.maxTimeInterval);
		
		assertTrue(mazub.getHeight() > duckingHeight);
		assertTrue(mazub.getSpeed().x > 0);
	}

	@Test
	public void jumpDuckAndMove(){
		int height = mazub.getHeight();
		mazub.startJump();
		mazub.startMove(1);
		mazub.startDuck();
		mazub.advanceTime(Constants.maxTimeInterval);
		
		int duckingHeight = mazub.getHeight();
		assertTrue(duckingHeight < height);
		assertTrue(mazub.getSpeed().x > 0);
		assertTrue(mazub.getSpeed().y > 0);
		
		mazub.endDuck();
		mazub.advanceTime(Constants.maxTimeInterval);
		assertTrue(mazub.getHeight() > duckingHeight);
		assertTrue(mazub.getSpeed().x > 0);
		assertTrue(mazub.getSpeed().y > 0);
		
		mazub.endMove();
		mazub.advanceTime(Constants.maxTimeInterval);
		assertTrue(mazub.getHeight() > duckingHeight);
		assertEquals((double)mazub.getSpeed().x, 0.0, testAccuracy);
		assertTrue(mazub.getSpeed().y > 0);
		
		mazub.endJump();
		assertTrue(mazub.getHeight() > duckingHeight);
		assertEquals(mazub.getSpeed().x, 0.0, testAccuracy);
		assertEquals(mazub.getSpeed().y, 0.0, testAccuracy);
	}
	
	@Test
	public void getCenterInPixels(){
		assertEquals((int)mazub.getCenterInPixels().x, mazub.getPositionInPixels().x + (int)(0.5 * mazub.getSize().x));
		assertEquals((int)mazub.getCenterInPixels().y, mazub.getPositionInPixels().y + (int)(0.5 * mazub.getSize().y));
	}
	
	@Test
	public void deadAfterTime(){
		assertTrue(mazub.isAlive());
		mazub.increaseHealth(-mazub.getHealth());
		assertTrue(mazub.isAlive());
		
		//Wait 0.6s
		for (int i = 0; i * Constants.maxTimeInterval <= 0.6; ++i){
			assertTrue(mazub.isAlive());
			mazub.advanceTime(Constants.maxTimeInterval);
		}
		assertFalse(mazub.isAlive());
	}
	
	@Test 
	public void waterDamage(){
		mazub.setPositionInMeters(Utilities.pixelsVectorToMeters(new Vector<>(70, 140)));
		world.setTileType(new Vector<>(1, 1), TileType.WATER);
		
		double eps = 1e-5;
		world.advanceTime(0.2 - eps);
		
		assertEquals(mazub.getHealth(), 100);
		world.advanceTime(2*eps);
		
		assertEquals(mazub.getHealth(), 100 +  Constants.waterDamage);
		
		world.advanceTime(0.2);
		assertEquals(mazub.getHealth(), 100 + 2 * Constants.waterDamage);
	}
	
	
	@Test
	public void magmaDamage(){
		mazub.setPositionInMeters(new Vector<>(0.0, 70.0 * Constants.metersPerPixel));
		world.setTileType(new Vector<>(0, 0), TileType.GROUND);
		world.setTileType(new Vector<>(0, 1), TileType.MAGMA);
		
		double eps = 1e-5;
		world.advanceTime(eps);
		
		assertEquals(mazub.getHealth(), 100 + Constants.magmaDamage);
		
		world.advanceTime(0.2);
		world.advanceTime(eps);

		assertEquals(mazub.getHealth(), 100 + 2 * Constants.magmaDamage);
	}
}
