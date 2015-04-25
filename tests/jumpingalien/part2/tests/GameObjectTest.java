package jumpingalien.part2.tests;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import jumpingalien.common.sprites.JumpingAlienSprites;
import jumpingalien.model.Constants;
import jumpingalien.model.GameObject;
import jumpingalien.model.Mazub;
import jumpingalien.model.Plant;
import jumpingalien.model.Shark;
import jumpingalien.model.Slime;
import jumpingalien.model.TileType;
import jumpingalien.model.Utilities;
import jumpingalien.model.Vector;
import jumpingalien.model.World;
import jumpingalien.model.Reactions.CollisionDamager;
import jumpingalien.model.Reactions.GameObjectCollisionDamager;
import jumpingalien.model.Reactions.PlantMazubCollisionDamager;
import jumpingalien.model.Reactions.TerrainCollisionDamager;
import jumpingalien.model.Reactions.TerrainDamageInfo;
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
	public void constructor_notPassableForPlant() {
		Sprite[] plantSprites = new Sprite[] {Resources.PLANT_SPRITE_LEFT, Resources.PLANT_SPRITE_RIGHT};
		Plant plant = new Plant(new Vector<>(0.0, 0.0), plantSprites);
		assertFalse(plant.isPassable());
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
		assertFalse(plant.isPassable());
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
		Set<CollisionDamager> damagers = new HashSet<CollisionDamager>();
		Collection<Class<? extends GameObject>> damageClasses = new HashSet<Class<? extends GameObject>>();
		damageClasses.add(Shark.class);
		damageClasses.add(Slime.class);
		damagers.add(new GameObjectCollisionDamager(mazub, Constants.mazubEnemyDamage, Constants.enemyDamageInterval, damageClasses));
		Collection<Class<? extends GameObject>> plantClass = new HashSet<Class<? extends GameObject>>();
		plantClass.add(Plant.class);
		damagers.add(new GameObjectCollisionDamager(mazub, Constants.mazubPlantHealthGain, 0, plantClass));
		Collection<TerrainDamageInfo> terrainInfos= new HashSet<>();
		terrainInfos.add(new TerrainDamageInfo(TileType.MAGMA, Constants.magmaDamage, 0));
		terrainInfos.add(new TerrainDamageInfo(TileType.WATER, Constants.waterDamage, Constants.terrainDamageInterval));
		damagers.add(new TerrainCollisionDamager(mazub, Constants.terrainDamageInterval, terrainInfos));
		
		assertEquals(damagers, mazub.getCollisionDamagers());
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
		assertFalse(mazub.isValidHealth(mazub.getMaximumHealth()));
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

}
