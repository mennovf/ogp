package jumpingalien.part2.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import jumpingalien.common.sprites.JumpingAlienSprites;
import jumpingalien.model.Constants;
import jumpingalien.model.GameObject;
import jumpingalien.model.Mazub;
import jumpingalien.model.Shark;
import jumpingalien.model.Tile;
import jumpingalien.model.TileType;
import jumpingalien.model.Utilities;
import jumpingalien.model.Vector;
import jumpingalien.model.World;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class WorldTest {

	World world;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		world = new World(70, 20, 12, 1024, 751, 19, 11);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void constructor_ok(){
		assertEquals(world.getTileSize(), 70);
		assertEquals(world.getNumberOfTiles(), new Vector<>(20, 12));
		assertEquals(world.getTargetTilePosition(), new Vector<>(19, 11));
	}
	
	@Test
	public void getSizeInPixels_ok(){
		assertEquals(world.getSizeInPixels(), new Vector<>(70*20, 70*12));
	}
	
	@Test
	public void getSizeInMeters_ok(){
		assertEquals(world.getSizeInMeters(), new Vector<Double>(70 * 20 * Constants.metersPerPixel,
				70 * 12 * Constants.metersPerPixel));
	}
	
	@Test
	public void pixelInWorld_inside(){
		assertTrue(world.pixelInWorld(new Vector<>(20, 20)));
		assertTrue(world.pixelInWorld(new Vector<>(0, 0)));
		assertTrue(world.pixelInWorld(new Vector<>(0, 20)));
		assertTrue(world.pixelInWorld(new Vector<>(20, 0)));
		assertTrue(world.pixelInWorld(new Vector<>(70 * 20 - 1, 20)));
		assertTrue(world.pixelInWorld(new Vector<>(20, 70 * 12 - 1)));
		assertTrue(world.pixelInWorld(new Vector<>(70 * 20 - 1, 70 * 12 - 1)));
	}
	
	@Test
	public void pixelInWorld_outside(){
		assertFalse(world.pixelInWorld(new Vector<>(-1, -1)));
		assertFalse(world.pixelInWorld(new Vector<>(-1, 20)));
		assertFalse(world.pixelInWorld(new Vector<>(20, -1)));
		assertFalse(world.pixelInWorld(new Vector<>(70 * 20, 20)));
		assertFalse(world.pixelInWorld(new Vector<>(20, 70 * 12)));
		assertFalse(world.pixelInWorld(new Vector<>(70 * 20, 70 * 12)));
	}
	
	@Test
	public void tilePositionInWorld_inside(){
		assertTrue(world.tilePositionInWorld(new Vector<>(0, 0)));
		assertTrue(world.tilePositionInWorld(new Vector<>(0, 5)));
		assertTrue(world.tilePositionInWorld(new Vector<>(5, 0)));
		assertTrue(world.tilePositionInWorld(new Vector<>(20 - 1, 5)));
		assertTrue(world.tilePositionInWorld(new Vector<>(5, 12 - 1)));
		assertTrue(world.tilePositionInWorld(new Vector<>(20 - 1, 12 - 1)));
	}

	@Test
	public void tilePositionInWorld_outside(){
		assertFalse(world.tilePositionInWorld(new Vector<>(-1, -1)));
		assertFalse(world.tilePositionInWorld(new Vector<>(-1, 5)));
		assertFalse(world.tilePositionInWorld(new Vector<>(5, -1)));
		assertFalse(world.tilePositionInWorld(new Vector<>(20, 5)));
		assertFalse(world.tilePositionInWorld(new Vector<>(5, 12)));
		assertFalse(world.tilePositionInWorld(new Vector<>(20, 12)));
	}
	
	
	@Test
	public void getBottomLeftPixelOfTile(){
		Vector<Integer> p = world.getBottomLeftPixelOfTile(new Vector<>(0, 0));
		assertEquals(p, new Vector<>(0, 0));

		p = world.getBottomLeftPixelOfTile(new Vector<>(5, 5));
		assertEquals(p, new Vector<>(5*70, 5*70));
	}
	
	@Test
	public void getTileContainingPixel(){
		Vector<Integer> tile = world.getTileContainingPixel(new Vector<>(0, 0));
		assertEquals(tile, new Vector<>(0, 0));

		tile = world.getTileContainingPixel(new Vector<>(70 * 20 - 1, 70 * 12 - 1));
		assertEquals(tile, new Vector<>(19, 11));

		tile = world.getTileContainingPixel(new Vector<>(70 * 18 + 35, 70 * 10 + 35));
		assertEquals(tile, new Vector<>(18, 10));

		tile = world.getTileContainingPixel(new Vector<>(70 * 18 - 35, 70 * 10 - 35));
		assertEquals(tile, new Vector<>(17, 9));
	}
	
	@Test
	public void getTilePositionsInRectangle(){
		//Small rectangle
		ArrayList<Vector<Integer>> tiles = world.getTilePositionsInRectangle(new Vector<>(0, 0), new Vector<>(69, 69));
		assertEquals(tiles.size(), 1);
		assertEquals(tiles.get(0), new Vector<>(0, 0));
		
		
		//Whole world rectangle
		tiles = world.getTilePositionsInRectangle(new Vector<>(0, 0), new Vector<>(70 * 20 - 1, 70 * 12 - 1));
		ArrayList<Vector<Integer>> correct =  new ArrayList<>();
		for (int i = 0; i < 20; ++i){
			for (int j = 0; j < 12; ++j){
				correct.add(new Vector<>(i, j));
			}
		}
		assertEquals(tiles.size(), correct.size());
		for (Vector<Integer> correctTile : correct){
			boolean found = false;
			for (Vector<Integer> tile: tiles){
				if (tile.equals(correctTile)){
					found = true;
				}
			}
			assertTrue(found);
		}
		
		//Middle rectangle
		tiles = world.getTilePositionsInRectangle(new Vector<>(50, 50), new Vector<>(60, 80));
		correct =  new ArrayList<>();
		correct.add(new Vector<>(0, 0));
		correct.add(new Vector<>(0, 1));
		assertEquals(tiles.size(), correct.size());
		for (Vector<Integer> correctTile : correct){
			boolean found = false;
			for (Vector<Integer> tile: tiles){
				if (tile.equals(correctTile)){
					found = true;
				}
			}
			assertTrue(found);
		}
	}
	
	@Test
	public void startGame(){
		world.setMazub(new Mazub(new Vector<>(0.0, 0.0), JumpingAlienSprites.ALIEN_SPRITESET, 1, 2, 1));
		world.startGame();
		assertFalse(world.isGameOver());
	}
	
	@Test
	public void didPlayerWin(){
		Vector<Double> position = Utilities.pixelsVectorToMeters(Vector.scale(world.getTargetTilePosition(), world.getTileSize()));
		Mazub mazub = new Mazub(position, JumpingAlienSprites.ALIEN_SPRITESET, 1, 2, 1);
		world.setMazub(mazub);
		assertTrue(world.didPlayerWin());
	}
	
	@Test
	public void isGameOver(){
		Mazub mazub = new Mazub(new Vector<>(0.0, 0.0), JumpingAlienSprites.ALIEN_SPRITESET, 1, 2, 1);
		world.setMazub(mazub);
		
		mazub.setHealth(0);
		assertTrue(world.isGameOver());
		
		mazub.setPositionInMeters(Utilities.pixelsVectorToMeters(world.getTargetTilePosition()));
		world.advanceTime(Constants.maxTimeInterval);
		assertTrue(world.isGameOver());
	}
	
	@Test
	public void isGameOver_MazubUnderMap() {
		Mazub mazub = new Mazub(new Vector<>(0.0, 0.0), JumpingAlienSprites.ALIEN_SPRITESET, 1, 2, 1);
		world.setMazub(mazub);
		
		assertFalse(world.isGameOver());
		
		world.advanceTime(Constants.maxTimeInterval);
		
		assertTrue(mazub.isHealthZero());
		assertTrue(world.isGameOver());
	}
	
	@Test
	public void setTileType(){
		world.setTileType(new Vector<>(0, 0), TileType.GROUND);
		assertEquals(world.getTileType(new Vector<>(0, 0)), TileType.GROUND);
	}
	
	@Test
	public void unassignedTileShouldBeAir(){
		assertEquals(world.getTileType(new Vector<>(5, 5)), TileType.AIR);
	}
	
	@Test
	public void addGameObject(){
		Shark shark = Utilities.shark(new Vector<>(0.0, 0.0));
		world.addGameObject(shark);
		assertTrue(world.containsGameObject(shark));
	}
	
	@Test
	public void removeGameObject(){
		Shark shark = Utilities.shark(new Vector<>(0.0, 0.0));
		world.addGameObject(shark);
		assertTrue(world.containsGameObject(shark));
		world.removeGameObject(shark);
		assertFalse(world.containsGameObject(shark));
	}
	
	@Test
	public void setMazub(){
		Mazub mazub = new Mazub(new Vector<>(0.0, 0.0), JumpingAlienSprites.ALIEN_SPRITESET, 1, 2, 1);
		world.setMazub(mazub);
		assertEquals(world.getMazub(), mazub);
	}
	
	@Test
	public void canHaveAsMazub_ok(){
		Mazub mazub = new Mazub(new Vector<>(0.0, 0.0), JumpingAlienSprites.ALIEN_SPRITESET, 1, 2, 1);
		assertTrue(world.canHaveAsMazub(mazub));
	}
	
	@Test
	public void canHaveAsMazub_cant(){
		assertFalse(world.canHaveAsMazub(null));
	}
	
	@Test
	public void hasProperMazub(){
		Mazub mazub = new Mazub(new Vector<>(0.0, 0.0), JumpingAlienSprites.ALIEN_SPRITESET, 1, 2, 1);
		world.setMazub(mazub);
		assertTrue(world.hasProperMazub());
	}
	
	@Test
	public void getGameObjectWithClass(){
		HashSet<Shark> sharks = new HashSet<>();
		for (int i = 0; i < 3; ++i){
			Shark shark = Utilities.shark(new Vector<>(0.0, 0.0));
			sharks.add(shark);
			world.addGameObject(shark);
		}
		Set<Shark> objs = world.getGameObjectsWithClass(Shark.class);
		
		assertEquals(objs.size(), sharks.size());
		for (Shark shark : sharks){
			assertTrue(objs.contains(shark));
		}
	}
	
//	@Test
//	public void objectsOverlap_overlap_obvious(){
//		Shark shark1 = Utilities.shark(new Vector<>(0.0, 0.0));
//		world.addGameObject(shark1);
//		Shark shark2 = Utilities.shark(new Vector<>(0.0, 0.0));
//		world.addGameObject(shark2);
//		assertTrue(world.objectsOverlap(shark1, shark2));
//	}
//
//	@Test
//	public void objectsOverlap_overlap_closex(){
//		Shark shark1 = Utilities.shark(new Vector<>(0.0, 0.0));
//		world.addGameObject(shark1);
//		Shark shark2 = Utilities.shark(new Vector<>(shark1.getSize().x * Constants.metersPerPixel - 1, 0.0));
//		world.addGameObject(shark2);
//		assertTrue(world.objectsOverlap(shark1, shark2));
//	}
//
//	@Test
//	public void objectsOverlap_noOverlap_closex(){
//		Shark shark1 = Utilities.shark(new Vector<>(0.0, 0.0));
//		world.addGameObject(shark1);
//		Shark shark2 = Utilities.shark(new Vector<>(shark1.getSize().x * Constants.metersPerPixel, 0.0));
//		world.addGameObject(shark2);
//		assertFalse(world.objectsOverlap(shark1, shark2));
//	}
//
//	@Test
//	public void objectsOverlap_overlap_closey(){
//		Shark shark1 = Utilities.shark(new Vector<>(0.0, 0.0));
//		world.addGameObject(shark1);
//		Shark shark2 = Utilities.shark(new Vector<>(0.0, shark1.getSize().y * Constants.metersPerPixel - 1));
//		world.addGameObject(shark2);
//		assertTrue(world.objectsOverlap(shark1, shark2));
//	}
//
//	@Test
//	public void objectsOverlap_noOverlap_closey(){
//		Shark shark1 = Utilities.shark(new Vector<>(0.0, 0.0));
//		world.addGameObject(shark1);
//		Shark shark2 = Utilities.shark(new Vector<>(0.0, shark1.getSize().y * Constants.metersPerPixel));
//		world.addGameObject(shark2);
//		assertFalse(world.objectsOverlap(shark1, shark2));
//	}
//
//	@Test
//	public void objectsOverlap_overlap_topRightCorner(){
//		Shark shark1 = Utilities.shark(new Vector<>(0.0, 0.0));
//		world.addGameObject(shark1);
//		Shark shark2 = Utilities.shark(new Vector<>(shark1.getSize().x * Constants.metersPerPixel - 1, shark1.getSize().y * Constants.metersPerPixel - 1));
//		world.addGameObject(shark2);
//		assertTrue(world.objectsOverlap(shark1, shark2));
//	}
//
//	@Test
//	public void objectsOverlap_noOverlap_topTightCorner(){
//		Shark shark1 = Utilities.shark(new Vector<>(0.0, 0.0));
//		world.addGameObject(shark1);
//		Shark shark2 = Utilities.shark(new Vector<>(shark1.getSize().x * Constants.metersPerPixel, shark1.getSize().y * Constants.metersPerPixel));
//		world.addGameObject(shark2);
//		assertFalse(world.objectsOverlap(shark1, shark2));
//	}
	
	@Test
	public void getObjectsCollidingWithObject_some(){
		Shark shark1 = Utilities.shark(new Vector<>(0.0, 0.0));
		world.addGameObject(shark1);
		Shark shark2 = Utilities.shark(new Vector<>(0.0, 0.0));
		world.addGameObject(shark2);
		Set<GameObject> coll = world.getObjectsCollidingWithObject(shark1);
		assertEquals(coll.size(), 1);
		assertTrue(coll.contains(shark2));
	}

	@Test
	public void getObjectsCollidingWithObject_none(){
		Shark shark1 = Utilities.shark(new Vector<>(0.0, 0.0));
		world.addGameObject(shark1);
		Shark shark2 = Utilities.shark(new Vector<>(shark1.getSize().x * Constants.metersPerPixel, 0.0));
		world.addGameObject(shark2);
		Set<GameObject> coll = world.getObjectsCollidingWithObject(shark1);
		assertEquals(coll.size(), 0);
	}
	
	@Test
	public void getTilesCollidingWithObject_center(){
		Shark shark = Utilities.shark(new Vector<>(0.0, 0.0));
		Set<Tile> tiles = world.getTilesCollidingWithObject(shark);
		assertEquals(tiles.size(), 1);
	}

	@Test
	public void getTilesCollidingWithObject_cornerOfTiles(){
		Shark shark = Utilities.shark(Utilities.pixelsVectorToMeters(new Vector<>(50, 50)));
		int toX = 2;
		int toY = 2;
		
		Set<Tile> tiles = world.getTilesCollidingWithObject(shark);
		assertEquals(tiles.size(), toX * toY);
		for (Tile tile : tiles){
			assertTrue(tile.getPositionInTiles().x < toX);
			assertTrue(tile.getPositionInTiles().x < toY);
		}
	}
	
	@Test
	public void getVisibleWindow_bottomLeftEdge(){
		Mazub mazub = new Mazub(new Vector<>(0.0, 0.0), JumpingAlienSprites.ALIEN_SPRITESET, 1, 2, 1);
		world.setMazub(mazub);
		int[] window = world.getVisibleWindow();
		assertEquals(window[0], 0);
		assertEquals(window[1], 0);
		assertEquals(window[2], 1024);
		assertEquals(window[3], 751);
	}

	@Test
	public void getVisibleWindow_topRightEdge(){
		Mazub mazub = new Mazub(new Vector<>(70 * 20 * Constants.metersPerPixel, 70 * 12 * Constants.metersPerPixel), JumpingAlienSprites.ALIEN_SPRITESET, 1, 2, 1);
		world.setMazub(mazub);
		int[] window = world.getVisibleWindow();
		assertEquals(window[0], 70 * 20 - 1024 - 1);
		assertEquals(window[1], 70 * 12 - 751 - 1);
		assertEquals(window[2], 70 * 20 - 1);
		assertEquals(window[3], 70 * 12 - 1);
	}

	@Test
	public void getVisibleWindow_middle(){
		Mazub mazub = new Mazub(new Vector<>(70 * 10 * Constants.metersPerPixel, 70 * 6 * Constants.metersPerPixel), JumpingAlienSprites.ALIEN_SPRITESET, 1, 2, 1);
		world.setMazub(mazub);
		int[] window = world.getVisibleWindow();
		assertTrue(mazub.getPositionInPixels().x - window[0] > 200);
		assertTrue(mazub.getPositionInPixels().y - window[1] > 200);
		assertTrue(window[2] - mazub.getPositionInPixels().x - mazub.getSize().x > 200);
		assertTrue(window[3] - mazub.getPositionInPixels().y - mazub.getSize().y > 200);
	}
}