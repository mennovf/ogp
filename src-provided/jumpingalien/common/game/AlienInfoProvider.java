package jumpingalien.common.game;

import java.util.Optional;

import jumpingalien.util.Sprite;

public interface AlienInfoProvider {

	public Optional<int[]> getAlienXY();

	public Optional<int[]> getAlienSize();

	public Optional<Sprite> getPlayerSprite();

	public Optional<double[]> getAlienVelocity();

	public Optional<double[]> getAlienAcceleration();

}
