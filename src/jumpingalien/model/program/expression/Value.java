package jumpingalien.model.program.expression;

import java.util.Map;

/**
 * A expression representing a value.
 * 
 * @author Rugen Heidbuchel & Menno Vanfrachem
 *
 * @param <T>
 * 			The type of the value it holds.
 */
public class Value<T> implements Expression<T> {
	
	private T val;
	
	/**
	 * Construct a new Value.
	 * 
	 * @param val
	 * 			The value 'this' will contain.
	 */
	public Value(T val) {
		this.val = val;
	}

	/**
	 * Returns the stored value.
	 * 
	 * @return Returns the stored value.
	 */
	@Override
	public T evaluate(Map<String, Object> globals) {
		return val;
	}
}
