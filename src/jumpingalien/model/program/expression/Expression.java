package jumpingalien.model.program.expression;

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
	 * 
	 * @return The result evaluating this expression.
	 */
	T evaluate();
}
