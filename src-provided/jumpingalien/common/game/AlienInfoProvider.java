package jumpingalien.common.game;

import java.util.Optional;

import jumpingalien.util.Sprite;

public interface AlienInfoProvider<T> {

	public T getAlien();
	
	public Optional<int[]> getAlienXY();

	public Optional<int[]> getAlienSize();

	public Optional<Sprite> getPlayerSprite();

	public Optional<double[]> getAlienVelocity();

	public Optional<double[]> getAlienAcceleration();

}
