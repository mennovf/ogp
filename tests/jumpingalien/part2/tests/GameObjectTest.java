package jumpingalien.part2.tests;

import static org.junit.Assert.*;

import jumpingalien.common.sprites.JumpingAlienSprites;
import jumpingalien.model.Constants;
import jumpingalien.model.GameObject;
import jumpingalien.model.Mazub;
import jumpingalien.model.Plant;
import jumpingalien.model.Shark;
import jumpingalien.model.Tile;
import jumpingalien.model.TileType;
import jumpingalien.model.Utilities;
import jumpingalien.model.Vector;
import jumpingalien.model.World;
import jumpingalien.model.Reactions.PlantMazubCollisionDamager;
import jumpingalien.part2.internal.Resources;
import jumpingalien.util.Sprite;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class GameObjectTest {
	
	
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
		world = Utilities.world();
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
	public void constructor_passableForPlant() {
		Sprite[] plantSprites = new Sprite[] {Resources.PLANT_SPRITE_LEFT, Resources.PLANT_SPRITE_RIGHT};
		Plant plant = new Plant(new Vector<>(0.0, 0.0), plantSprites);
		assertTrue(plant.isPassable());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void contructor_invalidPosition(){
		new Mazub(new Vector<>(-1.0, 0.0), JumpingAlienSprites.ALIEN_SPRITESET, testVxInit, testVxMax, 1);
	}
	
	
	
	@Test
	public void canHaveAsWorld_true() {
		assertTrue(mazub.canHaveAsWorld(world));
	}
	
	@Test
	public void canHaveAsWorld_false() {
		assertFalse(mazub.canHaveAsWorld(null));
	}
	
	
	
	@Test
	public void hasProperWorld_true() {
		assertTrue(mazub.hasProperWorld());
	}
	
	@Test
	public void hasProperWorld_false() {
		Shark shark = Utilities.shark(testStartPosition);
		assertFalse(shark.hasProperWorld());
	}
	
	
	
	@Test
	public void getWorld() {
		assertEquals(world, mazub.getWorld());
	}
	
	
	
	@Test
	public void setWorld() {
		World testWorld = new World(70, 5, 5, 140, 140, 3, 3);
		mazub.setWorld(testWorld);
		assertEquals(testWorld, mazub.getWorld());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setWorld_invalid() {
		mazub.setWorld(null);
	}
	
	
	
	@Test
	public void inWorld_true() {
		assertTrue(mazub.inWorld());
	}
	
	@Test
	public void inWorld_false() {
		Shark shark = Utilities.shark(testStartPosition);
		assertFalse(shark.inWorld());
	}
	
	
	
	@Test
	public void removeFromWorld() {
		mazub.removeFromWorld();
		assertNull(mazub.getWorld());
		assertFalse(world.containsGameObject(mazub));
		assertFalse(mazub.inWorld());
	}
	
	
	
	@Test
	public void isPassable() {
		assertFalse(mazub.isPassable());
		
		Sprite[] plantSprites = new Sprite[] {Resources.PLANT_SPRITE_LEFT, Resources.PLANT_SPRITE_RIGHT};
		Plant plant = new Plant(new Vector<>(0.0, 0.0), plantSprites);
		assertTrue(plant.isPassable());
	}
	
	
	
	@Test
	public void getMaximumHealth() {
		assertEquals(Constants.mazubMaxHealth, mazub.getMaximumHealth());
	}
	
	
	
	@Test
	public void getHealth() {
		assertEquals(Constants.mazubBeginHealth, mazub.getHealth());
	}
	
	
	
	@Test
	public void getCollisionDamagers() {
		assertEquals(3, mazub.getCollisionDamagers().size());
	}
	
	
	
	@Test
	public void addCollisionDamager() {
		PlantMazubCollisionDamager damager = new PlantMazubCollisionDamager(mazub, -30, 0.6);
		mazub.addCollisionDamager(damager);
		assertTrue(mazub.getCollisionDamagers().contains(damager));
	}
	
	
	
	@Test
	public void isAlive_alive() {
		assertTrue(mazub.isAlive());
	}
	
	@Test
	public void isAlive_aliveWithZeroHealth() {
		mazub.setHealth(0);
		assertTrue(mazub.isAlive());
	}
	
	@Test
	public void isAlive_notAlive() {
		mazub.setHealth(0);
		for (int i = 0; i <= (int) Math.ceil(Constants.deathTime / Constants.maxTimeInterval); i++) {
			world.advanceTime(Constants.maxTimeInterval);
		}
		assertFalse(mazub.isAlive());
	}
	
	
	
	@Test
	public void isHealthZero_true() {
		mazub.setHealth(0);
		assertTrue(mazub.isHealthZero());
	}
	
	@Test
	public void isHealthZero_false() {
		assertFalse(mazub.isHealthZero());
	}
	
	
	
	@Test
	public void increaseHealth() {
		int addHealth = 10;
		mazub.increaseHealth(addHealth);
		assertEquals(Constants.mazubBeginHealth + addHealth, mazub.getHealth());
		
		mazub.increaseHealth(-addHealth);
		assertEquals(Constants.mazubBeginHealth, mazub.getHealth());
	}
	
	
	
	@Test
	public void takeDamage() {
		int addHealth = 10;
		mazub.takeDamage(addHealth);
		assertEquals(Constants.mazubBeginHealth + addHealth, mazub.getHealth());
		
		mazub.takeDamage(-addHealth);
		assertEquals(Constants.mazubBeginHealth, mazub.getHealth());
	}
	
	
	
	@Test
	public void isValidHealth_true() {
		assertTrue(mazub.isValidHealth(mazub.getHealth()));
	}
	
	@Test
	public void isValidHealth_negative() {
		assertFalse(mazub.isValidHealth(-10));
	}
	
	@Test
	public void isValidHealth_tooBig() {
		assertFalse(mazub.isValidHealth(mazub.getMaximumHealth() + 1));
	}
	
	
	
	@Test
	public void setHealth() {
		mazub.setHealth(1);
		assertEquals(1, mazub.getHealth());
		
		mazub.setHealth(Constants.mazubMaxHealth + 10);
		assertEquals(Constants.mazubMaxHealth, mazub.getHealth());
		
		mazub.setHealth(0);
		assertEquals(0, mazub.getHealth());
		
		mazub.setHealth(Constants.mazubBeginHealth);
		assertEquals(0, mazub.getHealth());
	}
	
	
	
	@Test
	public void getPositionInMeters() {
		assertEquals(testStartPosition, mazub.getPositionInMeters());
	}
	
	
	
	@Test
	public void getPositionInPixels() {
		assertEquals(Utilities.metersVectorToPixels(testStartPosition), mazub.getPositionInPixels());
	}
	
	
	
	@Test
	public void getBoundingBoxPositionInPixels() {
		assertEquals(mazub.getPositionInPixels(), mazub.getBoundingBoxPositionInPixels());
	}
	
	
	
	@Test
	public void setPositionInMeters() {
		Vector<Double> testPos = Utilities.pixelsVectorToMeters(new Vector<>(71, 70));
		mazub.setPositionInMeters(testPos);
		assertEquals(testPos, mazub.getPositionInMeters());
	}
	
	@Test(expected = NullPointerException.class)
	public void setPositionInMeters_null() {
		mazub.setPositionInMeters(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setPositionInMeters_invalidPosition() {
		Vector<Double> testPos = new Vector<>(-10.0, 20.0);
		mazub.setPositionInMeters(testPos);
	}
	
	
	
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
	
	
	
	@Test
	public void getTopRightPixel() {
		assertEquals(Vector.add(mazub.getPositionInPixels(), mazub.getSizeInPixels()), mazub.getTopRightPixel());
	}
	
	
	
	@Test
	public void getCenterInPixels(){
		assertEquals((int)mazub.getCenterInPixels().x, mazub.getPositionInPixels().x + (int)(0.5 * mazub.getSizeInPixels().x));
		assertEquals((int)mazub.getCenterInPixels().y, mazub.getPositionInPixels().y + (int)(0.5 * mazub.getSizeInPixels().y));
	}
	
	
	
	@Test
	public void onGround_leftWallOnGround() {
		assertTrue(mazub.onGround());
	}
	
	@Test
	public void onGround_leftWallNotOnGround() {
		mazub.setPositionInMeters(testStartPosition.addY(Constants.metersPerPixel));
		assertFalse(mazub.onGround());
		
		mazub.setPositionInMeters(mazub.getPositionInMeters().addY(Constants.metersPerPixel));
		assertFalse(mazub.onGround());
	}
	
	@Test
	public void onGround_noWalls() {
		mazub.setPositionInMeters(testStartPosition.addX(Constants.metersPerPixel));
		assertTrue(mazub.onGround());
	}
	
	@Test
	public void onGround_rightWallOnGround() {
		world.setTileType(new Vector<>(3, 1), TileType.GROUND);
		mazub.setPositionInMeters(Utilities.pixelsVectorToMeters(new Vector<>(210 - mazub.getSizeInPixels().x, 69)));
		assertTrue(mazub.onGround());
		
		mazub.setPositionInMeters(Utilities.pixelsVectorToMeters(new Vector<>(210 - mazub.getSizeInPixels().x + 1, 69)));
		assertTrue(mazub.onGround());
	}
	
	@Test
	public void onGround_rightWallNotOnGround() {
		world.setTileType(new Vector<>(3, 1), TileType.GROUND);
		mazub.setPositionInMeters(Utilities.pixelsVectorToMeters(new Vector<>(210 - mazub.getSizeInPixels().x, 70)));
		assertFalse(mazub.onGround());
	}
	
	@Test
	public void onGround_notOnGround() {
		mazub.setPositionInMeters(Vector.add(mazub.getPositionInMeters(),
				new Vector<>(Constants.metersPerPixel, Constants.metersPerPixel)));
		assertFalse(mazub.onGround());
	}
	
	@Test
	public void onGround_topWallNotOnGround() {
		world.setTileType(new Vector<>(2, 3), TileType.GROUND);
		mazub.setPositionInMeters(Utilities.pixelsVectorToMeters(new Vector<>(140, 210 - mazub.getSizeInPixels().y)));
		assertFalse(mazub.onGround());
		
		mazub.setPositionInMeters(Utilities.pixelsVectorToMeters(new Vector<>(140, 210 - mazub.getSizeInPixels().y + 1)));
		assertFalse(mazub.onGround());
	}
	
	
	
	@Test
	public void collidesWithGameObjectClass() {
		assertTrue(mazub.collidesWithGameObjectClass(Shark.class));
	}
	
	
	
	@Test
	public void collidesWithTileType_collides() {
		assertTrue(mazub.collidesWithTileType(TileType.GROUND));
	}
	
	@Test
	public void collidesWithTileType_doesNotCollide() {
		assertFalse(mazub.collidesWithTileType(TileType.AIR));
	}
	
	
	
	@Test
	public void getSpeed() {
		assertEquals(new Vector<>(0.0, 0.0), mazub.getSpeed());
	}
	
	
	
	@Test
	public void getAcceleration() {
		assertEquals(new Vector<>(0.0, 0.0), mazub.getAcceleration());
	}
	
	
	
	@Test
	public void setAcceleration() {
		Vector<Double> testAcc = new Vector<>(10.0, -9.81);
		mazub.setAcceleration(testAcc);
		assertEquals(testAcc, mazub.getAcceleration());
	}
	
	
	
	@Test
	public void getFacing() {
		assertEquals(1.0, mazub.getFacing(), testAccuracy);
	}

	
	
	@Test
	public void isValidDirection() {
		assertTrue(GameObject.isValidDirection(1));
		assertTrue(GameObject.isValidDirection(-1));
		assertFalse(GameObject.isValidDirection(0));
		assertFalse(GameObject.isValidDirection(2));
		assertFalse(GameObject.isValidDirection(-2));
		assertFalse(GameObject.isValidDirection(0.5));
		assertFalse(GameObject.isValidDirection(-0.5));
	}
	
	
	
	@Test
	public void getCurrentSprite() {
		assertEquals(sprites[0], mazub.getCurrentSprite());
	}
	
	
	
	@Test
	public void getSizeInPixels() {
		Sprite sprite = mazub.getCurrentSprite();
		assertEquals(new Vector<>(sprite.getWidth(), sprite.getHeight()), mazub.getSizeInPixels());
	}
	
	
	
	@Test
	public void getSizeInMeters() {
		Sprite sprite = mazub.getCurrentSprite();
		assertEquals(Utilities.pixelsVectorToMeters(new Vector<>(sprite.getWidth(), sprite.getHeight())),
				mazub.getSizeInMeters());
	}
	
	
	
	@Test
	public void getBoundingBoxSizeInPixels() {
		assertEquals(mazub.getSizeInPixels(), mazub.getBoundingBoxSizeInPixels());
	}
	
	
	
	@Test
	public void inContactWithTileOfType_true() {
		assertTrue(mazub.inContactWithTileOfType(TileType.GROUND));
		assertFalse(mazub.inContactWithTileOfType(TileType.AIR));
	}
	
	@Test
	public void inContactWithTileOfType_false() {
		assertFalse(mazub.inContactWithTileOfType(TileType.WATER));
		assertFalse(mazub.inContactWithTileOfType(TileType.MAGMA));
	}
	
	
	
	@Test
	public void doesOverLapWith_GameObject_true() {
		Shark shark = Utilities.shark(testStartPosition);
		assertTrue(mazub.doesOverlapWith(shark));
	}
	
	@Test
	public void doesOverLapWith_GameObject_false() {
		Shark shark = Utilities.shark(Utilities.pixelsVectorToMeters(mazub.getTopRightPixel()));
		assertFalse(mazub.doesOverlapWith(shark));
	}
	
	@Test
	public void doesOverLapWith_Tile_true() {
		Tile tile = new Tile(world.getTileContainingPixel(Utilities.metersVectorToPixels(testStartPosition)),
				70, TileType.GROUND);
		assertTrue(mazub.doesOverlapWith(tile));
	}
	
	@Test
	public void doesOverLapWith_Tile_false() {
		Tile tile = new Tile(mazub.getTopRightPixel(), 70, TileType.GROUND);
		assertFalse(mazub.doesOverlapWith(tile));
	}

}
