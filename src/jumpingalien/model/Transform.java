package jumpingalien.model;
/**
 * A POD class representing a transform in a 2D world.
 * The transform bundles a position (x and y coordinate)
 * and a direction (facing either left or right).
 */
public class Transform {
	public enum Direction{
		LEFT, RIGHT
	}
	
	public Vector2D<Double> position = new Vector2D<>(0.0, 0.0);
	public Direction facing;
	
	public Transform(double x, double y, Direction dir){
		this.position = new Vector2D<>(x, y);
		this.facing = dir;
	}
}
