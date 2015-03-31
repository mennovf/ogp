package jumpingalien.model;

import jumpingalien.util.Sprite;

public class Plant extends GameObject {
	
	private double directionTime;
	private final double maxDirectionTime;
	private final double speed;
	
	public Plant(Vector<Double> position, Sprite[] sprites){
		// GameObject
		super(1, 1, position, sprites);

		this.maxDirectionTime = 0.5;
		this.directionTime = 0.0;
		this.speed = 0.5;
	}
	
	@Override
	public void advanceTime(double dt){
		double timeLeft = maxDirectionTime - directionTime;
		directionTime = (directionTime + dt) % maxDirectionTime;
		Vector<Double> newPosition = new Vector<>(this.getPositionInMeters());
		// If the alternation of direction happens in this time interval
		// process the part of the interval before the alternation first.
		if (dt > timeLeft){
			newPosition = newPosition.setX(newPosition.x + this.getFacing() * this.speed * timeLeft);
			dt = dt - timeLeft;
			this.setFacing(this.getFacing() * -1);
		}
		// Then update the position with what's left
        newPosition = newPosition.setX(newPosition.x + this.getFacing() * this.speed * dt);
        this.setPosition(newPosition);
        this.setCurrentSprite(this.getSprites()[this.getFacing() < 0 ? 0 : 1]);
	}
}
