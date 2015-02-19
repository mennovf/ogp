package jumpingalien.part1.internal;

import jumpingalien.common.game.AbstractActionHandler;
import jumpingalien.part1.facade.IFacade;
import jumpingalien.model.Mazub;

public class Part1ActionHandler extends AbstractActionHandler<Mazub, IFacade> {

	Part1ActionHandler(JumpingAlienGamePart1 game) {
		super(game);
	}

	@Override
	protected IFacade getFacade() {
		return getGame().getFacade();
	}

	@Override
	protected JumpingAlienGamePart1 getGame() {
		return (JumpingAlienGamePart1) super.getGame();
	}

	@Override
	public void startJump() {
		addAlienCommand("startJump", getFacade()::startJump);
	}

	@Override
	public void endJump() {
		addAlienCommand("endJump", getFacade()::endJump);
	}

	@Override
	public void startMoveLeft() {
		addAlienCommand("startMoveLeft", getFacade()::startMoveLeft);
	}

	@Override
	public void startMoveRight() {
		addAlienCommand("startMoveRight", getFacade()::startMoveRight);
	}

	@Override
	public void endMoveLeft() {
		addAlienCommand("endMoveLeft", getFacade()::endMoveLeft);
	}

	@Override
	public void endMoveRight() {
		addAlienCommand("endMoveRight", getFacade()::endMoveRight);
	}

	@Override
	public void startDuck() {
		addAlienCommand("startDuck", getFacade()::startDuck);
	}

	@Override
	public void endDuck() {
		addAlienCommand("endDuck", getFacade()::endDuck);
	}

	@Override
	protected Mazub getAlien() {
		return getGame().getAlien();
	}
}
