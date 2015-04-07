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
	
	
	/**
	 * Creates a new empty school.
	 */
	public School() {}
	
	
	/**
	 * @param slime
	 * 			The slime to check.
	 * 
	 * @return Whether this school can have the given slime as a slime.
	 */
	public static boolean canHaveAsSlime(Slime slime) {
		return !(slime == null) && !slime.isTerminated();
	}
	
	
	/**
	 * Adds the given slime to the collection of slimes. This method may only be called when
	 * the slime joins a school for the first time. Use acceptSlime otherwise.
	 * 
	 * @param slime
	 * 			The slime to add.
	 * 
	 * @post This school will contain the slime.
	 * 			| new.containsSlime((new slime))
	 * 
	 * @post The slime will have this school as it's school.
	 * 			| (new slime).getSchool() == new
	 * 
	 * @throws IllegalArgumentException
	 * 			Throws an IllegalArgumentException when this school can't have the given slime as a slime.
	 * 			| !this.canHaveAsSlime(slime)
	 */
	public void addSlime(Slime slime) throws IllegalArgumentException {
		if (!School.canHaveAsSlime(slime)) {
			throw new IllegalArgumentException("The given slime is not valid.");
		}
		this.slimes.add(slime);
		slime.setSchool(this);
	}
	
	
	/**
	 * Remove the given slime from this school.
	 * 
	 * @param slime
	 * 			The slime to remove.
	 * 
	 * @post This school will no longer contain the given slime.
	 * 			| !new.containsSlime((new slime))
	 * 
	 * @throws IllegalArgumentException
	 * 			Throws an IllegalArgumentException when this school does not contain the given slime.
	 * 			| !this.containsSlime(slime)
	 */
	private void removeSlime(Slime slime) {
		if (!this.containsSlime(slime)) {
			throw new IllegalArgumentException("School does not contain the slime to remove.");
		}
		this.slimes.remove(slime);
	}
	
	
	/**
	 * @param slime
	 * 			The slime to check.
	 * 
	 * @return Whether this school contains the given slime.
	 */
	public boolean containsSlime(Slime slime) {
		return slime == null ? false : this.slimes.contains(slime);
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
		assert fromSchool.containsSlime(slime) && School.canHaveAsSlime(slime);
		fromSchool.removeSlime(slime);
		for (Slime fromSlime: fromSchool.getSlimes()) {
			fromSlime.increaseHealth(1);
		}
		for (Slime toSlime: toSchool.getSlimes()) {
			toSlime.increaseHealth(-1);
		}
		slime.increaseHealth(toSchool.getSlimes().size() - fromSchool.getSlimes().size());
		toSchool.addSlime(slime);
	}
	
	/**
	 * Returns a set of all the slimes in this school.
	 * @return A set of all the slimes in this school.
	 */
	public Set<Slime> getSlimes(){
		return this.slimes;
	}
}
