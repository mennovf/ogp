package jumpingalien.part1.internal;

import java.util.Optional;

import jumpingalien.common.game.IActionHandler;
import jumpingalien.common.game.AlienInfoProvider;
import jumpingalien.common.game.JumpingAlienGame;
import jumpingalien.common.game.WorldInfoProvider;
import jumpingalien.common.sprites.JumpingAlienSprites;
import jumpingalien.part1.facade.IFacade;
import jumpingalien.model.Mazub;
import jumpingalien.util.Sprite;

public class JumpingAlienGamePart1 extends JumpingAlienGame {

	private static final int WORLD_HEIGHT = 768;
	private static final int WORLD_WIDTH = 1024;

	private Mazub alien;

	public JumpingAlienGamePart1(Part1Options options, IFacade facade) {
		super(options, facade);
	}

	@Override
	public IFacade getFacade() {
		return (IFacade) super.getFacade();
	}

	@Override
	public Part1Options getOptions() {
		return (Part1Options) super.getOptions();
	}

	@Override
	public void load() {
	}

	@Override
	protected void createModel() {
		int initialPositionX = 0;
		int initialPositionY = 0;

		setAlien(getFacade().createMazub(initialPositionX, initialPositionY,
				JumpingAlienSprites.ALIEN_SPRITESET));
	}

	private void setAlien(Mazub alien) {
		if (this.alien != null) {
			throw new IllegalStateException("Mazub already created!");
		}
		this.alien = alien;
	}

	Mazub getAlien() {
		return alien;
	}

	@Override
	protected void advanceTime(double dt) {
		getFacade().advanceTime(getAlien(), dt);
	}

	@Override
	protected IActionHandler createActionHandler() {
		return new Part1ActionHandler(this);
	}

	@Override
	protected AlienInfoProvider createAlienInfoProvider() {
		return new AlienInfoProvider() {

			@Override
			public Optional<int[]> getAlienXY() {
				return catchErrorGet(() -> getFacade().getLocation(getAlien()));
			}

			@Override
			public Optional<double[]> getAlienVelocity() {
				return catchErrorGet(() -> getFacade().getVelocity(getAlien()));
			}

			@Override
			public Optional<double[]> getAlienAcceleration() {
				return catchErrorGet(() -> getFacade().getAcceleration(
						getAlien()));
			}

			@Override
			public Optional<int[]> getAlienSize() {
				return catchErrorGet(() -> getFacade().getSize(getAlien()));
			}

			@Override
			public Optional<Sprite> getPlayerSprite() {
				return catchErrorGet(() -> getFacade().getCurrentSprite(
						getAlien()));
			}
		};
	}

	@Override
	protected WorldInfoProvider createWorldInfoProvider() {
		return new WorldInfoProvider() {

			@Override
			public Optional<int[]> getWorldSize() {
				return Optional.of(new int[] { WORLD_WIDTH, WORLD_HEIGHT });
			}
		};
	}
}
