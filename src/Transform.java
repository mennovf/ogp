/**
 * A POD class representing a transform in a 2D world.
 * The transform bundles a position (x and y coordinate)
 * and a direction (facing either left or right).
 */
public class Transform {
	public enum Direction{
		LEFT, RIGHT
	}
	public double x = 0, y = 0;
	public Direction facing;
	
	public Transform(double x, double y, Direction dir){
		this.x = x;
		this.y = y;
		this.facing = dir;
	}
}