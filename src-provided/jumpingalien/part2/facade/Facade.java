package jumpingalien.part2.facade;

import java.util.ArrayList;
import java.util.Collection;

import jumpingalien.model.Utilities;
import jumpingalien.model.Vector;
import jumpingalien.model.gameobject.Gore;
import jumpingalien.model.gameobject.Mazub;
import jumpingalien.model.gameobject.Plant;
import jumpingalien.model.gameobject.School;
import jumpingalien.model.gameobject.Shark;
import jumpingalien.model.gameobject.Slime;
import jumpingalien.model.world.TileType;
import jumpingalien.model.world.World;
import jumpingalien.util.ModelException;
import jumpingalien.util.Sprite;

public class Facade implements IFacadePart2 {

	@Override
	public Mazub createMazub(int x, int y, Sprite[] sprites) {
		return new Mazub(new Vector<>(Utilities.pixelsToMeters(x), Utilities.pixelsToMeters(y)),
				sprites, 1.0, 3.0, 1.0);
	}

	@Override
	public int[] getLocation(Mazub alien) {
		return Utilities.intVectorToArray(alien.getPositionInPixels());
	}

	@Override
	public double[] getVelocity(Mazub alien) {
		return Utilities.doubleVectorToArray(alien.getSpeed());
	}

	@Override
	public double[] getAcceleration(Mazub alien) {
		return Utilities.doubleVectorToArray(alien.getAcceleration());
	}

	@Override
	public int[] getSize(Mazub alien) {
		int[] sizes = {alien.getWidth(), alien.getHeight()};
		return sizes;
	}

	@Override
	public Sprite getCurrentSprite(Mazub alien) {
		return alien.getCurrentSprite();
	}

	@Override
	public void startJump(Mazub alien) {
		alien.startJump();
	}

	@Override
	public void endJump(Mazub alien) {
		alien.endJump();
	}

	@Override
	public void startMoveLeft(Mazub alien) {
		alien.startMove(-1);
	}

	@Override
	public void endMoveLeft(Mazub alien) {
		alien.endMove();
	}

	@Override
	public void startMoveRight(Mazub alien) {
		alien.startMove(1);
	}

	@Override
	public void endMoveRight(Mazub alien) {
		alien.endMove();
	}

	@Override
	public void startDuck(Mazub alien) {
		alien.startDuck();
	}

	@Override
	public void endDuck(Mazub alien) {
		alien.endDuck();
	}

	@Override
	public void advanceTime(Mazub alien, double dt) {
		alien.advanceTime(dt);
	}

	@Override
	public int getNbHitPoints(Mazub alien) {
		return alien.getHealth();
	}

	@Override
	public World createWorld(int tileSize, int nbTilesX, int nbTilesY,
			int visibleWindowWidth, int visibleWindowHeight, int targetTileX,
			int targetTileY) {
		return new World(tileSize, nbTilesX, nbTilesY, visibleWindowWidth, visibleWindowHeight, targetTileX, targetTileY);
	}

	@Override
	public int[] getWorldSizeInPixels(World world) {
		return Utilities.intVectorToArray(world.getSizeInPixels());
	}

	@Override
	public int getTileLength(World world) {
		return world.getTileSize();
	}

	@Override
	public void startGame(World world) {
	}

	@Override
	public boolean isGameOver(World world) {
		return world.isGameOver();
	}

	@Override
	public boolean didPlayerWin(World world) {
		return world.didPlayerWin();
	}

	@Override
	public void advanceTime(World world, double dt) {
		world.advanceTime(dt);
	}

	@Override
	public int[] getVisibleWindow(World world) {
		return world.getVisibleWindow();
	}

	@Override
	public int[] getBottomLeftPixelOfTile(World world, int tileX, int tileY) {
		return Utilities.intVectorToArray(world.getBottomLeftPixelOfTile(new Vector<>(tileX, tileY)));
	}

	@Override
	public int[][] getTilePositionsIn(World world, int pixelLeft,
			int pixelBottom, int pixelRight, int pixelTop) {
		
		ArrayList<Vector<Integer>> positionVectors =
				world.getTilePositionsInRectangle(new Vector<>(pixelLeft, pixelBottom),
						new Vector<>(pixelRight, pixelTop));
		int[][] positions = new int[positionVectors.size()][2];
		
		for (int i = 0; i < positionVectors.size(); i++) {
			Vector<Integer> vect = positionVectors.get(i);
			positions[i][0] = vect.x;
			positions[i][1] = vect.y;
		}
		
		return positions;
	}

	@Override
	public int getGeologicalFeature(World world, int pixelX, int pixelY)
			throws ModelException {
		return world.getTileTypeOfTile(new Vector<>(pixelX, pixelY)).getNumber();
	}

	@Override
	public void setGeologicalFeature(World world, int tileX, int tileY,
			int tileType) {
		world.setTileType(new Vector<>(tileX, tileY), TileType.tileTypeForNumber(tileType));
	}

	@Override
	public void setMazub(World world, Mazub alien) {
		world.setMazub(alien);
	}

	@Override
	public boolean isImmune(Mazub alien) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Plant createPlant(int x, int y, Sprite[] sprites) {
		return new Plant(new Vector<>(Utilities.pixelsToMeters(x), Utilities.pixelsToMeters(y)),
				sprites);
	}

	@Override
	public void addPlant(World world, Plant plant) {
		world.addGameObject(plant);
	}

	@Override
	public Collection<Plant> getPlants(World world) {
		return world.getGameObjectsWithClass(Plant.class);
	}

	@Override
	public int[] getLocation(Plant plant) {
		return Utilities.intVectorToArray(plant.getPositionInPixels());
	}

	@Override
	public Sprite getCurrentSprite(Plant plant) {
		return plant.getCurrentSprite();
	}

	@Override
	public Shark createShark(int x, int y, Sprite[] sprites) {
		return new Shark(new Vector<>(Utilities.pixelsToMeters(x), Utilities.pixelsToMeters(y)),
				sprites);
	}

	@Override
	public void addShark(World world, Shark shark) {
		world.addGameObject(shark);
	}

	@Override
	public Collection<Shark> getSharks(World world) {
		return world.getGameObjectsWithClass(Shark.class);
	}

	@Override
	public int[] getLocation(Shark shark) {
		return Utilities.intVectorToArray(shark.getPositionInPixels());
	}

	@Override
	public Sprite getCurrentSprite(Shark shark) {
		return shark.getCurrentSprite();
	}

	@Override
	public School createSchool() {
		return new School();
	}

	@Override
	public Slime createSlime(int x, int y, Sprite[] sprites, School school) {
		return new Slime(new Vector<>(Utilities.pixelsToMeters(x), Utilities.pixelsToMeters(y)),
				sprites, school);
	}

	@Override
	public void addSlime(World world, Slime slime) {
		world.addGameObject(slime);
	}

	@Override
	public Collection<Slime> getSlimes(World world) {
		return world.getGameObjectsWithClass(Slime.class);
	}

	@Override
	public int[] getLocation(Slime slime) {
		return Utilities.intVectorToArray(slime.getPositionInPixels());
	}

	@Override
	public Sprite getCurrentSprite(Slime slime) {
		return slime.getCurrentSprite();
	}

	@Override
	public School getSchool(Slime slime) {
		return slime.getSchool();
	}
	
	
	//TODO: We added this!!!
	@Override
	public Collection<Gore> getBlood(World world) {
		return world.getGameObjectsWithClass(Gore.class);
	}
	
	@Override
	public int[] getLocation(Gore blood) {
		return Utilities.intVectorToArray(blood.getPositionInPixels());
	}
	
	@Override
	public Sprite getCurrentSprite(Gore blood) {
		return blood.getCurrentSprite();
	}
	
}
