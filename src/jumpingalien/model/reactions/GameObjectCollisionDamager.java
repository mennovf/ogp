package jumpingalien.model.reactions;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import jumpingalien.model.Collidable;
import jumpingalien.model.GameObject;

/**
 * A class handling the administration for a collision with a GameObject.
 */
public class GameObjectCollisionDamager extends CollisionDamager {

	Set<Class<? extends GameObject>> collidesWith;
	
	/**
	 * Constructs a new GameObjectCollisionDamager.
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
	 * @param gameobjs
	 * 			A collection of GameObjects for which this instance is responsible.
	 * 
	 * @effect Calls the constructor of CollisionDamager.
	 * 			| super(owner, damage, timeInterval)
	 * 
	 * @post The collidesWith set will contain all the gameobjs
	 * 			| new.collidesWith.containsAll(gameobjs)
	 */
	public GameObjectCollisionDamager(GameObject owner, int damage,
			double timeInterval, Collection<Class<? extends GameObject>> gameobjs) {
		super(owner, damage, timeInterval);
		collidesWith = new HashSet<>();
		this.collidesWith.addAll(gameobjs);
	}


	/**
	 * Returns whether this GameObjectCollisionDamager applies damage due to a collison with obj.
	 * 
	 * @param obj
	 * 			The object to check.
	 * 
	 * @return Whether this CollsionDamager applies damage due to a collison with obj.
	 * 		   It reacts to obj if it's an instance of one of the classes in this.collidesWith
	 * 			| obj instanceof (any class in this.collidesWith)
	 */
	@Override
	public boolean doesReactTo(Collidable obj) {
		for (Class<? extends GameObject> cls : collidesWith){
			if (obj.getClass() == cls && !(((GameObject)obj).isHealthZero())){
				return true;
			}
		}
		return false;
	}
}
