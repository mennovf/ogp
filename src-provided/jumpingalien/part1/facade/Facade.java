package jumpingalien.part1.facade;

import jumpingalien.model.Mazub;
import jumpingalien.model.Vector2D;
import jumpingalien.util.Sprite;

public class Facade implements IFacade {

	@Override
	public Mazub createMazub(int pixelLeftX, int pixelBottomY, Sprite[] sprites) {
		double x = pixelLeftX * 0.01;
		double y = pixelBottomY * 0.01;
		return new Mazub(x, y, sprites, 1.0, 3.0, 1.0);
	}

	@Override
	public int[] getLocation(Mazub alien) {
		Vector2D<Integer> position = alien.getPosition();
		int[] worldPosition = {position.x, position.y};
		return worldPosition;
	}

	@Override
	public double[] getVelocity(Mazub alien) {
		Vector2D<Double> speed = alien.getSpeed();
		double[] speedArray = {speed.x, speed.y};
		return speedArray;
	}

	@Override
	public double[] getAcceleration(Mazub alien) {
		Vector2D<Double> acc = alien.getAcceleration();
		double[] accArray = {acc.x, acc.y};
		return accArray;
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
	
}
