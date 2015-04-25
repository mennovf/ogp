package jumpingalien.model.Reactions;

import java.util.Collection;
import java.util.HashSet;

import jumpingalien.model.Collidable;
import jumpingalien.model.GameObject;
import jumpingalien.model.Mazub;

/**
 * A class specifically meant for the plant being eaten by Mazub. It needs
 * it's own class because a plant won't be consumed if the Mazub's health is
 * already at it's maximum.
 * 
 */
public class PlantMazubCollisionDamager extends GameObjectCollisionDamager {
	static final private Collection<Class<? extends GameObject>> mazubClass = new HashSet<>();
	static {
		mazubClass.add(Mazub.class);
	}

	/**
	 * Constructor for PlantMazubCollisionDamager.
	 * 
	 * @param owner
	 * 			The owner of this damager. If the owner calls reactTo for a collision with an
	 * 			object, the owner will take damage if all the conditions are met.
	 * 
	 * @param damage
	 * 			The amount of damage taken when a collision occurs under the right conditions.
	 * 			If the health should be lowered due to the collision, damage should be < 0.
	 * 
	 * @param timeInterval
	 * 			The amount of time there minimally has to be between taking instances of damage.
	 * 
	 * @effect Calls the constructor of CollisionDamager.
	 * 			| super(owner, damage, timeInterval, Set(Mazub.class))
	 */
	public PlantMazubCollisionDamager(GameObject owner, int damage,
			double timeInterval) {
		super(owner, damage, timeInterval, mazubClass);
	}
	
	/**
	 * Returns whether or not this PlantMazubCollisionDamager will react to collidable.
	 * 
	 * @param collidable
	 * 			The object to check
	 * @return Whether or not this PlantMazubCollisionDamager will react to collidable.
	 * 			| CollisionDamager.reactTo(collidable) && ((Mazub)collidable).getHealth() != mazub.getMaximumHealth()
	 */
	@Override
	public boolean doesReactTo(Collidable collidable) {
		if (super.doesReactTo(collidable)){
			Mazub mazub = (Mazub)collidable;
			return (mazub.getHealth() != mazub.getMaximumHealth());
		}
		return false;
	}
}
