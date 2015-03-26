package jumpingalien.model;

import jumpingalien.util.Sprite;

public class Shark extends GameObject {

	public Shark(Vector2D<Double> position, Sprite[] sprites) {
		
		// GameObject
		super(100, 100, position, sprites);
	}

	@Override
	public void advanceTime(double dt) {
		// TODO Auto-generated method stub
		
	}
}
