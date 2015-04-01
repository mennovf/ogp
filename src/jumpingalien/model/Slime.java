package jumpingalien.model;

import jumpingalien.util.Sprite;

public class Slime extends GameObject {
	
	private School school;
	

	public Slime(Vector<Double> position, Sprite[] sprites, School school) {
		
		// GameObject
		super(100, 100, position, sprites);
		this.setSchool(school);
	}
	
	
	/**
	 * @return The school of this slime.
	 */
	public School getSchool() {
		return this.school;
	}
	
	
	/**
	 * @param school
	 * @return Whether this slime can have school as it's school.
	 * 			| !(school == null)
	 */
	public static boolean canHaveAsSchool(School school) {
		return !(school == null) /*&& !school.isTerminated()*/;
		//TODO: Check exact definition of isTerminated
	}
	
	
	/**
	 * Sets the school of this slime.
	 * 
	 * @param school
	 * 			The school to set.
	 * 
	 * @throws IllegalArgumentException
	 * 			Throws an IllegalArgumentException when this slime can't have the given school as school.
	 */
	public void setSchool(School school) throws IllegalArgumentException {
		if (canHaveAsSchool(school)) {
			this.school = school;
		} else {
			throw new IllegalArgumentException("Invalid school provided.");
		}
	}
	

	@Override
	public void advanceTime(double dt) {
		// TODO Auto-generated method stub
		this.setPosition(Vector.add(this.getPositionInMeters(), new Vector<Double>(0.005, 0.0)));
	}

}
