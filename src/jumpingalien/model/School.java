package jumpingalien.model;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author Rugen en Menno
 * 
 * A class representing a school of slimes.
 *
 */
public class School {

	private Set<Slime> slimes = new HashSet<>();
	
	
	public School() {}
	
	
	public boolean canHaveAsSlime(Slime slime) {
		return !(slime == null) && !slime.isTerminated();
	}
	
	
	/**
	 * Adds the given slime to the collection of slimes. This method may only be called when
	 * the slime joins a school for the first time. Use acceptSlime otherwise.
	 * 
	 * @param slime
	 * 			The slime to add.
	 */
	public void addSlime(Slime slime) throws IllegalArgumentException {
		if (!this.canHaveAsSlime(slime)) {
			throw new IllegalArgumentException("The given slime is not valid.");
		}
		this.slimes.add(slime);
		slime.setSchool(this);
	}
	
	
	public void removeSlime(Slime slime) {
		//TODO: Implement
	}
	
	
	public boolean containsSlime(Slime slime) {
		//TODO: Implement
		return false;
	}
	
	
	/**
	 * Switches the slime between the two given schools and updates the hitpoints accordingly.
	 * 
	 * @param fromSchool
	 * 			The school to leave from.
	 * 
	 * @param toSchool
	 * 			The school to go to.
	 * 
	 * @param slime
	 * 			The slime to switch.
	 * 
	 * @pre The school the slime wants to switch from has to contain the slime.
	 * 			| fromSchool.containsSlime(slime)
	 * 
	 * @pre The school the slime wants to switch to has to be able to contain the slime.
	 * 			| toSchool.canHaveAsSlime(slime)
	 * 
	 * @post The school the slime switched to will contain the slime.
	 * 			| (new toSchool).containsSlime(new slime)
	 * 
	 * @post The school the slime switched from will no longer contain the slime.
	 * 			| !(new fromSchool).containsSlime(new slime)
	 * 
	 * @post The slime will now have the school it switched to as it's school.
	 * 			| (new slime).getSchool() == (new toSchool)
	 * 
	 * no style specified -> nominally
	 */
	public static void switchSchools(School fromSchool, School toSchool, Slime slime) {
		assert fromSchool.containsSlime(slime) && toSchool.canHaveAsSlime(slime);
		fromSchool.removeSlime(slime);
		for (Slime fromSlime: fromSchool.slimes) {
			fromSlime.increaseHealth(1);
		}
		for (Slime toSlime: toSchool.slimes) {
			toSlime.increaseHealth(-1);
		}
		slime.increaseHealth(toSchool.slimes.size() - fromSchool.slimes.size());
		toSchool.addSlime(slime);
	}
}
