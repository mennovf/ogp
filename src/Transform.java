import be.kuleuven.cs.som.annotate.*;


/**
 * A POD class representing a transform in a 2D world.
 * The transform bundles a position (x and y coordinate)
 * and a direction (facing either left or right).
 */
public class Transform {
	public enum Direction{
		LEFT, RIGHT
	}
	private double x = 0, y = 0;
	private Direction facing;
	
	public Transform(double x, double y, Direction dir){
		this.setX(x);
		this.setY(y);
		this.setFacing(dir);
	}

	@Basic
	public double getX() {
		return x;
	}

	@Basic
	public void setX(double x) {
		this.x = x;
	}

	@Basic
	public double getY() {
		return y;
	}

	@Basic
	public void setY(double y) {
		this.y = y;
	}

	@Basic
	public Direction getFacing() {
		return facing;
	}

	@Basic
	public void setFacing(Direction facing) {
		this.facing = facing;
	}
}