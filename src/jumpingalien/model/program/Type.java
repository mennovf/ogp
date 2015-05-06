package jumpingalien.model.program;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class representing a wrapper for a type.
 * 
 * @author Rugen Heidbuchel, Menno Vanfrachem
 *
 * @param <T>
 * 			Represents the type in the wrapper.
 */
public class Type<T> {

	/**
	 * The value of this type.
	 */
	private T value = null;
	
	/**
	 * Creates an empty type.
	 */
	public Type() {}
	
	/**
	 * Creates a type with the given value.
	 * 
	 * @param value
	 * 			The value for the type.
	 */
	public Type(T value) {
		this.value = value;
	}
	
	/**
	 * Returns the value of this type.
	 */
	@Basic
	public T getValue() {
		//TODO: Should I copy the value?
		return this.value;
	}
}
