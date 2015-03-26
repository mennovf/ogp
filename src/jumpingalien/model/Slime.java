package jumpingalien.model;

import jumpingalien.util.Sprite;

public class Slime extends GameObject {
	
	private School school;
	

	public Slime(Vector2D<Double> position, Sprite[] sprites, School school) {
		
		// GameObject
		super(100, 100, position, sprites);
		
		//TODO: Implement School functionality
	}
	
	
	/**
	 * Returns the school of this slime.
	 */
	public School getSchool() {
		return this.school;
	}
	
	
	public void canHaveAsSchool(School school) {
		return !(school == null) /*&& !school.isTerminated()*/;
		//TODO: Check exact definition of isTerminated
	}
	
	
	public void setSchool() {
		
	}
	

	@Override
	public void advanceTime(double dt) {
		// TODO Auto-generated method stub
		
	}

}
