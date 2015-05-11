package jumpingalien.model.program.expression;

import java.util.Map;

/**
 * An interface representing an expression.
 * 
 * @author Rugen Heidbuchel, Menno Vanfrachem
 * 
 * @param <T>
 * 			The return type of the expression.
 */
public interface Expression<T> {
	
	
	/**
	 * Recursively evaluates this expression and returns it result.
	 * @param globals TODO
	 * 
	 * @return The result evaluating this expression.
	 */
	T evaluate(Map<String, Object> globals);
}
