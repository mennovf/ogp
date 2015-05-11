package jumpingalien.model.program.statement;

import java.util.Map;

import jumpingalien.model.program.exception.JumpingAlienLanguageRuntimeException;
import jumpingalien.model.program.expression.Expression;

/**
 * A class representing an Assignment Statement.
 */
public class Assignment extends SimpleStatement {

	private final String identifier;
	private final Expression<Object> valueExpr;
	
	/**
	 * Constructs a new Assignment Statement.
	 * 
	 * @param identifier
	 * 			The identifier of the variable to assign to.
	 * 
	 * @param valueExpr
	 * 			The expression which's result is assigned to identifier.
	 */
	public Assignment(String identifier, Expression<Object> valueExpr) {
		super();
		this.identifier = identifier;
		this.valueExpr = valueExpr;
	}

	/**
	 * Stores the result of this.valueExpr in globals under this.identifier.
	 * 
	 * @param globals
	 * 			The map of global variables to mutate.
	 */
	@Override
	protected void run(Map<String, Object> globals) {
		if (! globals.containsKey(this.identifier)) {
			throw new JumpingAlienLanguageRuntimeException("Undefined identifier '" + this.identifier + "' in assignment.");
		}
		globals.put(this.identifier, valueExpr.evaluate(globals));
	}
}
