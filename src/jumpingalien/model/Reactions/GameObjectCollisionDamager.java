package jumpingalien.model.Reactions;

import jumpingalien.model.Collidable;
import jumpingalien.model.GameObject;

public class GameObjectCollisionDamager extends CollisionDamager {

	Class<? extends GameObject> collideWith;
	public GameObjectCollisionDamager(GameObject owner, int damage,
			double timeInterval, Class<? extends GameObject> gameobj) {
		super(owner, damage, timeInterval);
		this.collideWith = gameobj;
	}

	@Override
	public boolean doesReactTo(Collidable obj) {
		return (obj.getClass() == collideWith) && !(((GameObject)obj).isHealthZero());
	}
}
