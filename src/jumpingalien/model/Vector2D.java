package jumpingalien.model;

/**
 * A 2-dimensional vector.
 * 
 * @author Rugen Heidbuchel, Menno Vanfrachem
 *
 * @param <T>
 * 			The type of the vector's arguments.
 */
public class Vector2D <T> {

	public T x, y;
	
	public Vector2D(T x, T y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2D(Vector2D<T> vector) {
		this(vector.x, vector.y);
	}
}
