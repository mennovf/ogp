package jumpingalien.model.gameobject;

import jumpingalien.model.Constants;
import jumpingalien.model.Vector;
import jumpingalien.util.Sprite;

public class Buzam extends Mazub {

	public Buzam(Vector<Double> position, Sprite[] sprites, double vxInit,
			double vxMax, double direction) throws NullPointerException,
			IllegalArgumentException {
		super(position, sprites, vxInit, vxMax, direction);
		
		this.setHealth(Constants.buzamBeginHealth);
	}

}
