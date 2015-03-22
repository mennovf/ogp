package jumpingalien.model;

import jumpingalien.util.Sprite;

public class Plant extends GameObject {
	
	private Sprite[] sprites;
	private double facing;
	private double directionTime;
	private final double maxDirectionTime;
	private final double speed;
	private Vector2D<Double> position;
	public Plant(Sprite[] sprites){
		// GameObject
		super(1, 1);

		this.sprites = sprites;
		this.facing = -1;
		this.maxDirectionTime = 0.5;
		this.directionTime = 0.0;
		this.speed = 0.5;
		this.position = new Vector2D<>(0.0, 0.0);
	}
	
	@Override
	public void advanceTime(double dt){
		double timeLeft = maxDirectionTime - directionTime;
		// If the alternation of direction happens in this time interval
		// process the part of the interval before the alternation first.
		if (dt > timeLeft){
			this.position.x += this.facing * this.speed * timeLeft;
			dt = dt - timeLeft;
			this.facing *= -1;
		}
		// Then update the position with what's left
        this.position.x += this.facing * this.speed * dt;
	}
}
