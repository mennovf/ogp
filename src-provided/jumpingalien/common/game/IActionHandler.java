package jumpingalien.common.game;

public interface IActionHandler {

	public void startJump();

	public void endJump();

	
	public void startMoveLeft();

	public void startMoveRight();

	public void endMoveLeft();

	public void endMoveRight();
	
	
	public void startDuck();
	
	public void endDuck();
}
