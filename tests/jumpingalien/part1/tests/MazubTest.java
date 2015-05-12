package jumpingalien.part1.tests;

import static org.junit.Assert.*;
import jumpingalien.model.*;
import jumpingalien.model.gameobject.Mazub;
import jumpingalien.model.gameobject.Shark;
import jumpingalien.model.world.TileType;
import jumpingalien.model.world.World;
import jumpingalien.part2.tests.TestUtilities;
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
    
    private Mazub mazub;
	private World world;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		world = TestUtilities.world();
		world.setTileType(new Vector<>(0, 0), TileType.GROUND);
		world.setTileType(new Vector<>(1, 0), TileType.GROUND);
		world.setTileType(new Vector<>(2, 0), TileType.GROUND);
		world.setTileType(new Vector<>(3, 0), TileType.GROUND);
		world.setTileType(new Vector<>(0, 1), TileType.GROUND);
		world.setTileType(new Vector<>(0, 2), TileType.GROUND);
		world.setTileType(new Vector<>(0, 3), TileType.GROUND);
		mazub = new Mazub(new Vector<>(testStartPosition.x, testStartPosition.y), sprites, testVxInit, testVxMax, testStartDirection);
		world.setMazub(mazub);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	
	
	@Test
	public void getCurrentSprite_start(){
		assertEquals(mazub.getCurrentSprite(), sprites[0]);
	}

	@Test
	public void getCurrentSprite_walkRight(){
		mazub.startRun(1);
		mazub.advanceTime(0.01);
		assertEquals(mazub.getCurrentSprite(), sprites[8]);
	}

	@Test
	public void getCurrentSprite_walkLeft(){
		mazub.startRun(-1);
		mazub.advanceTime(0.01);
		int m = (sprites.length - 8) / 2 - 1;
		assertEquals(mazub.getCurrentSprite(), sprites[9 + m]);
	}

	@Test
	public void getCurrentSprite_animation(){
		mazub.startRun(1);
		mazub.advanceTime(0.076);
		assertEquals(mazub.getCurrentSprite(), sprites[8 + 1]);
	}
	
	
	
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
	
	
	
	@Test
	public void setSpeed_ok() {
		Vector<Double> testSpeed = new Vector<>(testVxMax, 10.0);
		mazub.setSpeed(testSpeed);
		assertEquals(testSpeed, mazub.getSpeed());
	}
	
	@Test
	public void setSpeed_xTooSmall() {
		Vector<Double> testSpeed = new Vector<>(-(testVxMax + 1.0), 10.0);
		mazub.setSpeed(testSpeed);
		assertEquals(testSpeed.setX(-testVxMax), mazub.getSpeed());
	}
	
	@Test
	public void setSpeed_xTooBig() {
		Vector<Double> testSpeed = new Vector<>(testVxMax + 1.0, 10.0);
		mazub.setSpeed(testSpeed);
		assertEquals(testSpeed.setX(testVxMax), mazub.getSpeed());
	}
	
	
	
	@Test
	public void getHeight() {
		assertEquals(mazub.getHeight(), mazub.getCurrentSprite().getHeight());
	}
	
	
	
	@Test
	public void getWidth() {
		assertEquals(mazub.getWidth(), mazub.getCurrentSprite().getWidth());
	}
	
	
	
	@Test
	public void getAcceleration_notMoving() {
		
		assertEquals(mazub.getAcceleration().x, 0.0, testAccuracy);
		assertEquals(mazub.getAcceleration().y, 0.0, testAccuracy);
	}
	
	@Test
	public void getAcceleration_startRunningRight() {
		
		mazub.startRun(1);
		
		assertEquals(mazub.getAcceleration().x, Constants.mazubHorizontalAcceleration, testAccuracy);
		assertEquals(mazub.getAcceleration().y, 0.0, testAccuracy);
	}
	
	@Test
	public void getAcceleration_startRunningLeft() {
		
		mazub.startRun(-1);
		
		assertEquals(mazub.getAcceleration().x, -Constants.mazubHorizontalAcceleration, testAccuracy);
		assertEquals(mazub.getAcceleration().y, 0.0, testAccuracy);
	}
	
	@Test
	public void getAcceleration_startJump() {
		
		mazub.startJump();
		mazub.advanceTime(Constants.maxTimeInterval);
		
		assertEquals(mazub.getAcceleration().x, 0.0, testAccuracy);
		assertEquals(mazub.getAcceleration().y, Constants.gravityAcceleration, testAccuracy);
	}
	
	

	@Test
	public void getSpeed_notMoving() {
		
		assertEquals(mazub.getSpeed().x, 0.0, testAccuracy);
		assertEquals(mazub.getSpeed().y, 0.0, testAccuracy);
	}
	
	@Test
	public void getSpeed_running() {
		
		mazub.startRun(1);
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
	
	
	
	@Test
	public void getPosition_notMoved() {
		assertEquals(testStartPosition, mazub.getPositionInMeters());
	}
	
	@Test
	public void getPosition_running() {
		
		mazub.startRun(1);
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
	
	
	
	@Test
	public void onGround_notMoving() {
		
		assertTrue(mazub.onGround());
	}
	
	@Test
	public void onGround_running() {
		
		mazub.startRun(1);
		mazub.advanceTime(Constants.maxTimeInterval);
		
		assertTrue(mazub.onGround());
	}
	
	@Test
	public void onGround_jumping() {
		
		mazub.startJump();
		mazub.advanceTime(Constants.maxTimeInterval);
		
		assertFalse(mazub.onGround());
	}
	
	
	
	@Test
	public void move() {
		
		mazub.startRun(1);
		mazub.advanceTime(Constants.maxTimeInterval);
		
		assertNotEquals(mazub.getSpeed().x, 0.0, testAccuracy);
		
		mazub.stopRun();
		
		assertEquals(mazub.getSpeed().x, 0.0, testAccuracy);
	}
	
	
	
	@Test
	public void jump() {
		
		mazub.startJump();
		mazub.advanceTime(Constants.maxTimeInterval);
		
		assertNotEquals(mazub.getSpeed().y, 0.0, testAccuracy);
		
		mazub.stopJump();
		
		assertEquals(mazub.getSpeed().y, 0.0, testAccuracy);
		
		mazub.advanceTime(Constants.maxTimeInterval);
		
		assertTrue(mazub.getSpeed().y < 0.0);
	}
	
	
	
	@Test
	public void duck() {
		
		int height = mazub.getHeight();
		mazub.startDuck();
		mazub.advanceTime(Constants.maxTimeInterval);
		
		assertTrue(mazub.getHeight() < height);
		
		mazub.startRun(1);
		mazub.advanceTime(Constants.maxTimeInterval);
		
		assertEquals(mazub.getSpeed().x, 1.0, testAccuracy);
		
		mazub.stopDuck();
		mazub.stopRun();
		mazub.advanceTime(Constants.maxTimeInterval);
		
		assertEquals(mazub.getHeight(), height);
	}
	
	@Test
	public void duck_underTerrain(){
		
		Vector<Integer> tilePos = new Vector<>(1, 2);

		int height = mazub.getHeight();
		mazub.startDuck();
		mazub.advanceTime(Constants.maxTimeInterval / 10.0);
		int duckHeight = mazub.getHeight();
		assertTrue(duckHeight < height);
		
		world.setTileType(tilePos, TileType.GROUND);
		mazub.advanceTime(Constants.maxTimeInterval / 10.0);
		
		mazub.stopDuck();
		mazub.advanceTime(Constants.maxTimeInterval / 10.0);
		
		assertEquals(mazub.getHeight(), duckHeight);
		
		world.setTileType(tilePos, TileType.AIR);
		mazub.advanceTime(Constants.maxTimeInterval / 10.0);
		
		assertEquals(mazub.getHeight(), height);
	}
	
	@Test
	public void duck_underObject(){
		
		// This test probably fails due to an error in JUnit
		// It does pass when you run it in debug mode.
		
		Vector<Double> objPos = Utilities.pixelsVectorToMeters(new Vector<>(70, 145));
		Shark shark = TestUtilities.shark(objPos);
		world.addGameObject(shark);
		world.setMazub(mazub);

		int height = mazub.getHeight();
		mazub.startDuck();
		mazub.advanceTime(Constants.maxTimeInterval / 10.0);
		int duckHeight = mazub.getHeight();
		assertTrue(duckHeight < height);
		
		mazub.stopDuck();
		mazub.advanceTime(Constants.maxTimeInterval / 10.0);
		
		assertEquals(mazub.getHeight(), duckHeight);
		
		world.removeGameObject(shark);
		mazub.advanceTime(Constants.maxTimeInterval / 10.0);
		
		assertEquals(mazub.getHeight(), height);
	}

	
	
	@Test
	public void moveThenJump(){
		mazub.startRun(1);
		mazub.advanceTime(Constants.maxTimeInterval);
		mazub.startJump();
		mazub.advanceTime(Constants.maxTimeInterval);
		
		assertTrue(mazub.getSpeed().x > 0);
		assertTrue(mazub.getSpeed().y > 0);
		mazub.stopJump();
		assertTrue(Util.fuzzyEquals(mazub.getSpeed().y, 0.0));
		assertTrue(mazub.getSpeed().x > 0);
		mazub.stopRun();
		assertTrue(Util.fuzzyEquals(mazub.getSpeed().x, 0.0));
		assertTrue(Util.fuzzyEquals(mazub.getSpeed().y, 0.0));
	}
	
	@Test
	public void jumpThenMove(){
		mazub.startJump();
		mazub.advanceTime(Constants.maxTimeInterval);
		mazub.startRun(1);
		mazub.advanceTime(Constants.maxTimeInterval);
		
		assertTrue(mazub.getSpeed().x > 0);
		assertTrue(mazub.getSpeed().y > 0);
		mazub.stopJump();
		assertTrue(Util.fuzzyEquals(mazub.getSpeed().y, 0.0));
		assertTrue(mazub.getSpeed().x > 0);
		mazub.stopRun();
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
		
		mazub.stopDuck();
		mazub.advanceTime(Constants.maxTimeInterval);
		
		assertTrue(mazub.getHeight() > duckingHeight);
		assertTrue(mazub.getSpeed().y > 0);
	}
	
	
	@Test
	public void duckAndMove(){
		int height = mazub.getHeight();
		mazub.startDuck();
		mazub.startRun(1);
		mazub.advanceTime(Constants.maxTimeInterval);
		
		int duckingHeight = mazub.getHeight();
		assertTrue(duckingHeight < height);
		assertTrue(mazub.getSpeed().x > 0);
		
		mazub.stopDuck();
		mazub.advanceTime(Constants.maxTimeInterval);
		
		assertTrue(mazub.getHeight() > duckingHeight);
		assertTrue(mazub.getSpeed().x > 0);
	}

	@Test
	public void jumpDuckAndMove(){
		int height = mazub.getHeight();
		mazub.startJump();
		mazub.startRun(1);
		mazub.startDuck();
		mazub.advanceTime(Constants.maxTimeInterval);
		
		int duckingHeight = mazub.getHeight();
		assertTrue(duckingHeight < height);
		assertTrue(mazub.getSpeed().x > 0);
		assertTrue(mazub.getSpeed().y > 0);
		
		mazub.stopDuck();
		mazub.advanceTime(Constants.maxTimeInterval);
		assertTrue(mazub.getHeight() > duckingHeight);
		assertTrue(mazub.getSpeed().x > 0);
		assertTrue(mazub.getSpeed().y > 0);
		
		mazub.stopRun();
		mazub.advanceTime(Constants.maxTimeInterval);
		assertTrue(mazub.getHeight() > duckingHeight);
		assertEquals((double)mazub.getSpeed().x, 0.0, testAccuracy);
		assertTrue(mazub.getSpeed().y > 0);
		
		mazub.stopJump();
		assertTrue(mazub.getHeight() > duckingHeight);
		assertEquals(mazub.getSpeed().x, 0.0, testAccuracy);
		assertEquals(mazub.getSpeed().y, 0.0, testAccuracy);
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
