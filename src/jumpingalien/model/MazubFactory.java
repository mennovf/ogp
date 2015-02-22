package jumpingalien.model;

import jumpingalien.util.Sprite;

public class MazubFactory {

	private double vxInit = 1, vxMax = 3;
	private Vector2D<Double> position = new Vector2D<>(0.0, 0.0);
	private Sprite[] sprites;
	private double direction = 1;
	
	public MazubFactory vxInit(double vxInit) {
		this.vxInit = vxInit;
		return this;
	}
	
	public MazubFactory vxMax(double vxMax) {
		this.vxMax = vxMax;
		return this;
	}
	
	public MazubFactory position(Vector2D<Double> position) {
		this.position = position;
		return this;
	}
	
	public MazubFactory sprites(Sprite[] sprites) {
		this.sprites = sprites;
		return this;
	}
	
	public Mazub build() {
		return new Mazub(position.x, position.y, sprites, vxInit, vxMax, direction);
	}
}