package jumpingalien.model.gameobject;

import java.util.HashSet;
import java.util.Set;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class representing a school of slimes.
 * 
 * @invar All slimes this school contains will be valid slimes.
 * 			| for each slime in this.getSlimes():
 * 			|	this.canHaveAsSlime(slime)
 * 
 * @author Rugen Heidbuchel, Menno Vanfrachem
 */
public class School {

	/**
	 * A set will all slimes this school contains.
	 */
	private Set<Slime> slimes = new HashSet<>();
	
	
	/**
	 * Creates a new empty school.
	 * 
	 * @post The school will have no slimes.
	 * 			| new.getSlimes() == new HashSet<Slime>()
	 */
	public School() {}
	
	
	/**
	 * Returns whether this school can have the given slime as a slime.
	 * 
	 * @param slime
	 * 			The slime to check.
	 * 
	 * @return Whether this school can have the given slime as a slime.
	 * 			The slime cannot be null.
	 * 			| slime != null
	 */
	public static boolean canHaveAsSlime(Slime slime) {
		return slime != null;
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
	@Basic
	public void addSlime(Slime slime) throws IllegalArgumentException {
		if (!School.canHaveAsSlime(slime)) {
			throw new IllegalArgumentException("The given slime is not valid.");
		}
		this.slimes.add(slime);
		if (slime.getSchool() != this) {
			slime.setSchool(this);
		}
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
	@Basic
	public void removeSlime(Slime slime) {
		if (!this.containsSlime(slime)) {
			throw new IllegalArgumentException("School does not contain the slime to remove.");
		}
		this.slimes.remove(slime);
	}
	
	
	/**
	 * Returns whether this school contains the given slime.
	 * 
	 * @param slime
	 * 			The slime to check.
	 * 
	 * @return Whether this school contains the given slime.
	 * 			| slime == null ? false : this.getSlimes().contains(slime)
	 */
	public boolean containsSlime(Slime slime) {
		return slime == null ? false : this.getSlimes().contains(slime);
	}
	
	
	/**
	 * Switches the slime between the two given schools and updates the hitpoints accordingly.
	 * 
	 * @param toSchool
	 * 			The school to go to.
	 * 
	 * @param slime
	 * 			The slime to switch.
	 * 
	 * @pre The slime should be part of a school.
	 * 			| slime.hasProperSchool()
	 * 
	 * @pre The school the slime wants to switch to has to be able to contain the slime.
	 * 			| toSchool.canHaveAsSlime(slime)
	 * 
	 * @post The school the slime switched to will contain the slime.
	 * 			| (new toSchool).containsSlime(new slime)
	 * 
	 * @post The school the slime switched from will no longer contain the slime.
	 * 			| !(new (oldSlime.getSchool())).containsSlime(new slime)
	 * 
	 * @post The slime will now have the school it switched to as it's school.
	 * 			| (new slime).getSchool() == (new toSchool)
	 * 
	 * @effect All slimes in the old school will gain one hitpoint.
	 * 			| for each schoolSlime in oldSlime.getSchool().getSlimes():
	 * 			|	if schoolSlime != slime:
	 * 			|		schoolSlime.increaseHealth(1)
	 * 
	 * @effect All slimes in the new school will lose one hitpoint.
	 * 			| for each schoolSlime in toSchool.getSlimes():
	 * 			|	schoolSlime.increaseHealth(-1);
	 * 
	 * @effect The slime will gain 1 hitpoint from every slime in the new
	 * 			school and lost 1 hitpoint from every slime in the old school.
	 * 			| slime.increaseHealth(toSchool.size() - 1 - fromSchool.size())
	 */
	public static void switchSchoolsOfSlimeTo(Slime slime, School toSchool) {
		assert slime.hasProperSchool();
		School fromSchool = slime.getSchool();
		assert fromSchool.containsSlime(slime) && School.canHaveAsSlime(slime);
		for (Slime fromSlime: fromSchool.getSlimes()) {
			if (fromSlime != slime) {
				fromSlime.increaseHealth(1);
			}
		}
		for (Slime toSlime: toSchool.getSlimes()) {
			toSlime.increaseHealth(-1);
		}
		slime.increaseHealth(toSchool.size() - fromSchool.size() + 1);
		toSchool.addSlime(slime);
	}
	
	
	/**
	 * Returns a set of all the slimes in this school.
	 * 
	 * @return A set of all the slimes in this school.
	 */
	@Basic
	public Set<Slime> getSlimes(){
		return this.slimes;
	}
	
	
	/**
	 * Returns the number of slimes currently part of this school.
	 * 
	 * @return The number of slimes currently part of this school.
	 * 			| this.getSlimes.size()
	 */
	public int size(){
		return this.getSlimes().size();
	}
	
	/**
	 * Has every slime part of this school take 1 point of damage.
	 * The Slime slime is ignored.
	 * 
	 * @param slime
	 * 			The slime which has received damage.
	 * 
	 * @effect Every slime will lose one hitpoint.
	 * 			| for each schoolSlime in this.getSlimes():
	 * 			|	if schoolSlime != slime:
	 * 			|		schoolSlime.increaseHealth(-1)
	 */
	public void takeDamageCausedBy(Slime slime){
		for (Slime member : this.slimes){
			if (member != slime){
				member.increaseHealth(-1);
			}
		}
	}
}
