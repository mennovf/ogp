package jumpingalien.model.Reactions;

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
	 * 			The amount of time there minimaly has to be between taking instances of damage.
	 */
	protected CollisionDamager(GameObject owner, int damage, double timeInterval) {
		this.owner = owner;
		this.damage = damage;
		this.timeInterval = timeInterval;
		this.timeSince = timeInterval;
	}
	
	public abstract boolean doesReactTo(Collidable obj);

	public void reactTo(Collidable obj){
		assert this.doesReactTo(obj);
		if (this.canTakeDamage()){
			owner.takeDamage(this.damage);
			this.timeSince = 0;
		}
	}
	
	protected boolean canTakeDamage() {
		return (this.timeSince > this.timeInterval) && !(owner.isHealthZero());
	}

	public void advanceTime(double dt) {
		this.timeSince += dt;
	}
}