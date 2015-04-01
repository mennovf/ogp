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
public class Vector <T> {

	public final T x, y;
	
	public Vector(T x, T y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector<T> setX(T x){
		return new Vector<>(x, this.y);
	}
	
	public Vector<T> setY(T y){
		return new Vector<>(this.x, y);
	}

	public Vector(Vector<T> vector) {
		this(vector.x, vector.y);
	}


	public static <S> Vector<S> add(Vector<S> left, Vector<S> right) {
		return new Vector<S>(add(left.x, right.x), add(left.y, right.y));
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
	
	public static <S> Vector<S> scale(Vector<S> vector, S scalar) {
		return new Vector<S>(multiply(vector.x, scalar), multiply(vector.y, scalar));
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