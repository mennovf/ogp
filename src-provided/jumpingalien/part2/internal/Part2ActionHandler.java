package jumpingalien.part2.internal;

import jumpingalien.common.game.AbstractActionHandler;
import jumpingalien.part2.facade.IFacadePart2;
import jumpingalien.model.Mazub;

public class Part2ActionHandler extends AbstractActionHandler<Mazub, IFacadePart2> {

	public Part2ActionHandler(JumpingAlienGamePart2 game) {
		super(game);
	}

	@Override
	protected JumpingAlienGamePart2 getGame() {
		return (JumpingAlienGamePart2) super.getGame();
	}

	@Override
	protected IFacadePart2 getFacade() {
		return getGame().getFacade();
	}

	@Override
	protected Mazub getAlien() {
		return getGame().getAlien();
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
}
