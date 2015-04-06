package jumpingalien.model;

import java.util.Set;

import jumpingalien.util.Sprite;

public class Shark extends GameObject {

	public Shark(Vector<Double> position, Sprite[] sprites) {
		
		// GameObject
		super(100, 100, position, sprites);
	}
	
	
	@Override
	protected Set<Class<? extends GameObject>> getCollidables() {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public void advanceTime(double dt) {
		// TODO Auto-generated method stub
		
	}
}
