package jumpingalien.part2.facade;

import java.util.Collection;

import jumpingalien.model.Mazub;
import jumpingalien.model.Plant;
import jumpingalien.model.School;
import jumpingalien.model.Shark;
import jumpingalien.model.Slime;
import jumpingalien.model.Utilities;
import jumpingalien.model.Vector2D;
import jumpingalien.model.World;
import jumpingalien.util.ModelException;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;

public class Facade implements IFacadePart2 {

	@Override
	public Mazub createMazub(int pixelLeftX, int pixelBottomY, Sprite[] sprites) {
		double x = pixelLeftX * 0.01;
		double y = pixelBottomY * 0.01;
		return new Mazub(x, y, sprites, 1.0, 3.0, 1.0);
	}

	@Override
	public int[] getLocation(Mazub alien) {
		return Utilities.intVectorToArray(alien.getPosition());
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
		world.startGame();
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
		return Utilities.intVectorToArray(world.getVisibleWindowSize());
	}

	@Override
	public int[] getBottomLeftPixelOfTile(World world, int tileX, int tileY) {
		return Utilities.intVectorToArray(world.getBottomLeftPixelOfTile(new Vector2D<>(tileX, tileY)));
	}

	@Override
	public int[][] getTilePositionsIn(World world, int pixelLeft,
			int pixelBottom, int pixelRight, int pixelTop) {
		return world.getTilePositionsInRectangle(new Vector2D<>(pixelLeft, pixelBottom), new Vector2D<>(pixelRight, pixelTop));
	}

	@Override
	public int getGeologicalFeature(World world, int pixelX, int pixelY)
			throws ModelException {
		return world.getGeologicalFeature(new Vector2D<>(pixelX, pixelY));
	}

	@Override
	public void setGeologicalFeature(World world, int tileX, int tileY,
			int tileType) {
		world.setGeologicalFeature(new Vector2D<>(tileX, tileY), tileType);
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
		return new Plant(new Vector2D<>((double)x, (double)y) , sprites);
	}

	@Override
	public void addPlant(World world, Plant plant) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<Plant> getPlants(World world) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getLocation(Plant plant) {
		return Utilities.intVectorToArray(plant.getPosition());
	}

	@Override
	public Sprite getCurrentSprite(Plant plant) {
		return plant.getCurrentSprite();
	}

	@Override
	public Shark createShark(int x, int y, Sprite[] sprites) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addShark(World world, Shark shark) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<Shark> getSharks(World world) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getLocation(Shark shark) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Sprite getCurrentSprite(Shark shark) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public School createSchool() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Slime createSlime(int x, int y, Sprite[] sprites, School school) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addSlime(World world, Slime slime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<Slime> getSlimes(World world) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getLocation(Slime slime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Sprite getCurrentSprite(Slime slime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public School getSchool(Slime slime) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
