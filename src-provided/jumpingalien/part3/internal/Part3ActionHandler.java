package jumpingalien.part3.internal;

import jumpingalien.common.game.AbstractActionHandler;
import jumpingalien.part3.facade.IFacadePart3;
import jumpingalien.model.Mazub;

public class Part3ActionHandler extends AbstractActionHandler<Mazub, IFacadePart3> {

	public Part3ActionHandler(JumpingAlienGamePart3 game) {
		super(game);
	}

	@Override
	protected JumpingAlienGamePart3 getGame() {
		return (JumpingAlienGamePart3) super.getGame();
	}

	@Override
	protected IFacadePart3 getFacade() {
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
