package jumpingalien.model.program.expression;

import java.util.Map;

import jumpingalien.model.program.statement.CallStack;

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
	 * @param globals
	 * 			The global variables to be used in the expression.
	 * 
	 * @param callStack
	 * 			The call stack for this expression.
	 * 
	 * @return The result evaluating this expression.
	 */
	T evaluate(Map<String, Object> globals, CallStack callStack);
}
