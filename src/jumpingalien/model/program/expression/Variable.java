package jumpingalien.model.program.expression;

import java.util.Map;

import jumpingalien.model.program.exception.JumpingAlienLanguageRuntimeException;

/**
 * A class representing the value of a variable.
 */
public class Variable implements Expression<Object> {
	
	private final String identifier;
	
	/**
	 * Constructs a new Variable.
	 * 
	 * @param id
	 * 			The identifier of this variable in the source code.
	 */
	public Variable(String id) {
		this.identifier = id;
	}

	@Override
	public Object evaluate(Map<String, Object> globals) {
		if (!globals.containsKey(this.identifier)) {
			throw new JumpingAlienLanguageRuntimeException("Undefined identifier '" + this.identifier + "'.");
		}
		return globals.get(this.identifier);
	}
}
