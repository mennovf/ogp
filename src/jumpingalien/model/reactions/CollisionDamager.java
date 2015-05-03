package jumpingalien.model.reactions;

import jumpingalien.model.Collidable;
import jumpingalien.model.GameObject;

/**
 * A class handling the administration of registering damage to it's owner.
 *
 */
public abstract class CollisionDamager {
	protected final GameObject owner;
	protected double timeSince;
	protected final double timeInterval;
	private final int damage;

	/**
	 * Constructs a new CollisionDamager.
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
	 */
	protected CollisionDamager(GameObject owner, int damage, double timeInterval) {
		this.owner = owner;
		this.damage = damage;
		this.timeInterval = timeInterval;
		this.timeSince = timeInterval;
	}
	
	/**
	 * Returns whether this CollsionDamager applies damage due to a collison with obj.
	 * 
	 * @param obj
	 * 			The object to check.
	 * 
	 * @return Whether this CollsionDamager applies damage due to a collison with obj.
	 */
	public abstract boolean doesReactTo(Collidable obj);


	/**
	 * Apply damage to the owner if the conditions are met.
	 * 
	 * @param obj
	 * 			The object to react to.
	 * 
	 * @post If the owner can take damage, it takes damage.
	 * 			| if canTakeDamage()
	 * 			| then (new this.owner).getHealth() == (old this.owner).getHealht() + this.damage
	 * 
	 * @post timeSince will be reset to zero if damage is applied
	 * 			| if canTakeDamage()
	 * 			| then this.timeSince = 0
	 */
	public void reactTo(Collidable obj){
		assert this.doesReactTo(obj);
		if (this.canTakeDamage()){
			this.owner.getWorld().getCommandQueue().add(()->{owner.takeDamage(this.damage);});
			this.timeSince = 0;
		}
	}
	
	/**
	 * Returns whether the owner can take damage.
	 * 	
	 * @return Whether the owner can take damage.
	 * 			| (this.timeSince > this.timeInterval) && !(owner.isHealthZero())
	 */
	protected boolean canTakeDamage() {
		return (this.timeSince > this.timeInterval) && !(owner.isHealthZero());
	}

	/**
	 * Advances the internal timers.
	 * 
	 * @param dt
	 * 			The amount of time with which to progress the internal timers.
	 * 
	 * @post timeSince will be incremented by dt
	 * 			| new.timeSince == old.timeSince += dt
	 */
	public void advanceTime(double dt) {
		this.timeSince += dt;
	}
}