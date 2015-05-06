package jumpingalien.model.program.expression;

/**
 * A class representing an expression.
 * 
 * @author Rugen Heidbuchel, Menno Vanfrachem
 * 
 * @param <T extends Type<?>>
 * 			The return type of the expression.
 */
public interface Expression<T> {
	
	
	/**
	 * Recursively evaluates this expression and returns it result.
	 * 
	 * @return The result evaluating this expression
	 */
	T evaluate();
}
