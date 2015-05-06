package jumpingalien.model.gameobject;

import java.util.Set;

import jumpingalien.model.Vector;
import jumpingalien.model.world.TileType;
import jumpingalien.util.Sprite;

public class Buzam extends Mazub {

	public Buzam(Vector<Double> position, Sprite[] sprites, double vxInit,
			double vxMax, double direction) throws NullPointerException,
			IllegalArgumentException {
		super(position, sprites, vxInit, vxMax, direction);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Set<Class<? extends GameObject>> getCollidableObjectClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Set<TileType> getCollidableTileTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void handleStep(double dt) {
		// TODO Auto-generated method stub

	}

}
