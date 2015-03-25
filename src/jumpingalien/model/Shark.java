package jumpingalien.model;

import jumpingalien.util.Sprite;

public class Shark extends GameObject {

	public Shark(int x, int y, Sprite[] sprites) {
		
		// GameObject
		super(100, 100, sprites);
		
		this.setPosition(new Vector2D<>(Utilities.pixelsToMeters(x), Utilities.pixelsToMeters(y)));
	}

	@Override
	public void advanceTime(double dt) {
		// TODO Auto-generated method stub
		
	}
}
