package jumpingalien.model;

import jumpingalien.util.Sprite;

public class Plant extends GameObject {
	
	private double directionTime;
	private final double maxDirectionTime;
	private final double speed;
	public Plant(Sprite[] sprites){
		// GameObject
		super(1, 1, sprites);

		this.maxDirectionTime = 0.5;
		this.directionTime = 0.0;
		this.speed = 0.5;
		this.setPosition(new Vector2D<>(0.0, 0.0));
	}
	
	@Override
	public void advanceTime(double dt){
		double timeLeft = maxDirectionTime - directionTime;
		Vector2D<Double> newPosition = this.getPositionInMeters();
		// If the alternation of direction happens in this time interval
		// process the part of the interval before the alternation first.
		if (dt > timeLeft){
			newPosition.x += this.getFacing() * this.speed * timeLeft;
			dt = dt - timeLeft;
			this.setFacing(this.getFacing() * -1);
		}
		// Then update the position with what's left
        newPosition.x += this.getFacing() * this.speed * dt;
        this.setPosition(newPosition);
	}
}
