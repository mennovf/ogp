package jumpingalien.part1.facade;

import jumpingalien.model.Mazub;
import jumpingalien.model.Vector2D;
import jumpingalien.util.Sprite;
import jumpingalien.model.Transform;

public class Facade implements IFacade {

	@Override
	public Mazub createMazub(int pixelLeftX, int pixelBottomY, Sprite[] sprites) {
		double x = pixelLeftX * 0.01;
		double y = pixelBottomY * 0.01;
		return new Mazub(x, y, sprites, 1.0, 3.0, Transform.Direction.RIGHT);
	}

	@Override
	public int[] getLocation(Mazub alien) {
		Vector2D<Double> position = alien.getCurrentPosition();
		int[] worldPosition = {metersToPixels(position.x), metersToPixels(position.y)};
		return worldPosition;
	}

	@Override
	public double[] getVelocity(Mazub alien) {
		Vector2D<Double> speed = alien.getCurrentSpeed();
		//Assuming they expect speed to be in m/s, not pixels/s.
		double[] speedArray = {speed.x, speed.y};
		return speedArray;
	}

	@Override
	public double[] getAcceleration(Mazub alien) {
		// TODO Auto-generated method stub
		return null;
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
		alien.startMove(Transform.Direction.LEFT);
	}

	@Override
	public void endMoveLeft(Mazub alien) {
		alien.endMove();
	}

	@Override
	public void startMoveRight(Mazub alien) {
		alien.startMove(Transform.Direction.RIGHT);
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
	
	/**
	 * @param m
	 * 		  The value to convert from meters to pixels.
	 * @return m converted to pixels.
	 * @post m has to be positive
	 * 			| m >= 0
	 */
	private static int metersToPixels(double m){
		return (int)(m / 0.01);
	}

	
}
