package jumpingalien.model;

import be.kuleuven.cs.som.annotate.*;

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
}
