package jumpingalien.model.Reactions;

import java.util.Collection;
import java.util.HashSet;

import jumpingalien.model.Collidable;
import jumpingalien.model.GameObject;
import jumpingalien.model.Mazub;

public class PlantMazubCollisionDamager extends GameObjectCollisionDamager {
	static final private Collection<Class<? extends GameObject>> mazubClass = new HashSet<>();
	static {
		mazubClass.add(Mazub.class);
	}

	public PlantMazubCollisionDamager(GameObject owner, int damage,
			double timeInterval) {
		super(owner, damage, timeInterval, mazubClass);
	}
	
	@Override
	public boolean doesReactTo(Collidable collidable) {
		if (super.doesReactTo(collidable)){
			Mazub mazub = (Mazub)collidable;
			return (mazub.getHealth() != mazub.getMaximumHealth());
		}
		return false;
	}
}
