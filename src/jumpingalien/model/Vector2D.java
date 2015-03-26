package jumpingalien.model;

import be.kuleuven.cs.som.annotate.Value;


/**
 * A 2-dimensional vector.
 * 
 * @author Rugen Heidbuchel, Menno Vanfrachem
 *
 * @param <T>
 * 			The type of the vector's arguments.
 */
@Value
public class Vector2D <T> {

	public T x, y;
	
	public Vector2D(T x, T y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2D(Vector2D<T> vector) {
		this(vector.x, vector.y);
	}


	public static <S> Vector2D<S> add(Vector2D<S> left, Vector2D<S> right) {
		return new Vector2D<S>(add(left.x, right.x), add(left.y, right.y));
	}

	@SuppressWarnings("unchecked")
	private static <S> S add(S left, S right) {
		if (left.getClass() == Integer.class) {
			return (S) (Integer) ((Integer) left + (Integer) right);
		}
		if (left.getClass() == Double.class) {
			return (S) (Double) ((Double) left + (Double) right);
		}
		return null;
	}
	
	public static <S> Vector2D<S> scale(Vector2D<S> vector, S scalar) {
		return new Vector2D<S>(multiply(vector.x, scalar), multiply(vector.y, scalar));
	}
	
	@SuppressWarnings("unchecked")
	private static <S> S multiply(S left, S right) {
		if (left.getClass() == Integer.class) {
			return (S) (Integer) ((Integer) left * (Integer) right);
		}
		if (left.getClass() == Double.class) {
			return (S) (Double) ((Double) left * (Double) right);
		}
		return null;
	}
}