package jumpingalien.model.Reactions;

import jumpingalien.model.Collidable;
import jumpingalien.model.GameObject;

public class GameObjectCollisionDamager<T extends GameObject> extends CollisionDamager {

	Class<T> collideWith;
	public GameObjectCollisionDamager(GameObject owner, int damage,
			double timeInterval, Class<T> gameobj) {
		super(owner, damage, timeInterval);
		this.collideWith = gameobj;
	}

	@Override
	public boolean doesReactTo(Collidable obj) {
		return (obj.getClass() == collideWith);
	}
}
