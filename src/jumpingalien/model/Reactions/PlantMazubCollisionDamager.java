package jumpingalien.model.Reactions;

import jumpingalien.model.Collidable;
import jumpingalien.model.GameObject;
import jumpingalien.model.Mazub;

public class PlantMazubCollisionDamager extends GameObjectCollisionDamager {

	public PlantMazubCollisionDamager(GameObject owner, int damage,
			double timeInterval) {
		super(owner, damage, timeInterval, Mazub.class);
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
