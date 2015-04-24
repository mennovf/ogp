package jumpingalien.model.Reactions;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import jumpingalien.model.Collidable;
import jumpingalien.model.GameObject;

public class GameObjectCollisionDamager extends CollisionDamager {

	Set<Class<? extends GameObject>> collidesWith;
	public GameObjectCollisionDamager(GameObject owner, int damage,
			double timeInterval, Collection<Class<? extends GameObject>> gameobjs) {
		super(owner, damage, timeInterval);
		collidesWith = new HashSet<>();
		this.collidesWith.addAll(gameobjs);
	}

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
