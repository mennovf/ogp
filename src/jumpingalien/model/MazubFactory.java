package jumpingalien.model;

public class MazubFactory {

	private double vxInit, vxMax;
	
	public MazubFactory setVxInit(double vxInit) {
		this.vxInit = vxInit;
		return this;
	}
	
	public MazubFactory setVxMax(double vxMax) {
		this.vxMax = vxMax;
		return this;
	}
	
	public Mazub build() {
		return new Mazub(null, this.vxInit, this.vxMax);
	}
}

/* Example usage:
 * Mazub mazub = new MazubFactor().setVxInit(1.0).setVxMax(3.0).build();
 */