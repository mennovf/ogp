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
	
	/**
	 * Creates a new vector with the given x and y values.
	 * 
	 * @param x
	 * 			The x value.
	 * 
	 * @param y
	 * 			The y value.
	 */
	public Vector(T x, T y) {
		this.x = x;
		this.y = y;
	}
	
	
	/**
	 * Returns a new vector created by changing the x value
	 * of this vector to the given x value.
	 * 
	 * @param x
	 * 			The new x value.
	 * 
	 * @return A new vector of the same type with x value
	 * 			set to x.
	 * 			| new Vector<>(x, this.y)
	 */
	public Vector<T> setX(T x) {
		return new Vector<>(x, this.y);
	}
	
	
	/**
	 * Returns a new vector created by adding the given x
	 * value to the x value of this vector.
	 * 
	 * @param x
	 * 			The x to add.
	 * 
	 * @return A new vector of the same type with x added
	 * 			to the x value of this vector.
	 * 			| new Vector<>(this.x + x, this.y)
	 */
	public Vector<T> addX(T x) {
		return new Vector<>(add(this.x, x), this.y);
	}
	
	
	/**
	 * Returns a new vector created by changing the y value
	 * of this vector to the given y value.
	 * 
	 * @param y
	 * 			The new y value.
	 * 
	 * @return A new vector of the same type with y value
	 * 			set to y.
	 * 			| new Vector<>(this.x, y)
	 */
	public Vector<T> setY(T y) {
		return new Vector<>(this.x, y);
	}
	
	
	/**
	 * Returns a new vector created by adding the given y
	 * value to the y value of this vector.
	 * 
	 * @param y
	 * 			The y to add.
	 * 
	 * @return A new vector of the same type with y added
	 * 			to the y value of this vector.
	 * 			| new Vector<>(this.x, this.y + y)
	 */
	public Vector<T> addY(T y) {
		return new Vector<>(this.x, add(this.y, y));
	}
	


	/**
	 * Adds two vectors of the same type.
	 * 
	 * @param left
	 * 			The left vector in the sum.
	 * 
	 * @param right
	 * 			The right vector is the sum.
	 * 
	 * @return The sum of the two given vectors.
	 * 			| new Vector<>(left.x + right.x, left.y + right.y)
	 */
	public static <S> Vector<S> add(Vector<S> left, Vector<S> right) {
		return new Vector<S>(add(left.x, right.x), add(left.y, right.y));
	}
	
	
	/**
	 * Adds two values of the same type.
	 * 
	 * @param left
	 * 			The left value in the sum.
	 * 
	 * @param right
	 * 			The right value in the sum.
	 * 
	 * @return The sum of the two given values.
	 * 			| left + right
	 */
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
	
	
	/**
	 * Multiplies the given vector with a given scalar of the same type.
	 * 
	 * @param vector
	 * 			The vector.
	 * 
	 * @param scalar
	 * 			The scalar to multiply the vector with.
	 * 
	 * @return The product of the scalar and the vector.
	 * 			| new Vector<>(vector.x * scalar, vector.y * scalar)
	 */
	public static <S> Vector<S> scale(Vector<S> vector, S scalar) {
		return new Vector<S>(multiply(vector.x, scalar), multiply(vector.y, scalar));
	}
	
	
	/**
	 * Multiplies two values of the same type.
	 * 
	 * @param left
	 * 			The left value in the multiplication.
	 * 
	 * @param right
	 * 			The right value in the multiplication.
	 * 
	 * @return The product of the given values.
	 * 			| left * right
	 */
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
	
	
	@Override
	@SuppressWarnings("unchecked")
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (!(object instanceof Vector<?>)) {
			return false;
		}
		Vector<T> vector = (Vector<T>) object;
		return this.x.equals(vector.x) && this.y.equals(vector.y);
	}
	
	
	@Override
	public String toString() {
		return "[" + this.x + ", " + this.y + "]";
	}
}