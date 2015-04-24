package jumpingalien.model.Reactions;

import jumpingalien.model.Collidable;
import jumpingalien.model.GameObject;

public abstract class CollisionDamager {
	protected final GameObject owner;
	private double timeSince;
	private final double timeInterval;
	private final int damage;

	protected CollisionDamager(GameObject owner, int damage, double timeInterval) {
		this.owner = owner;
		this.damage = damage;
		this.timeInterval = timeInterval;
		this.timeSince = timeInterval;
	}
	
	public abstract boolean doesReactTo(Collidable obj);

	public void reactTo(Collidable obj){
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