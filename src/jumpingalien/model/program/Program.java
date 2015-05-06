package jumpingalien.model.program;

import java.util.Map;

/**
 * A class representing a program.
 * 
 * @author Rugen Heidbuchel, Menno Vanfrachem
 */
public class Program {
	
	/**
	 * The main statement of the program.
	 */
	private final Statement mainStatement;
	
	/**
	 * A map with the variables of the program.
	 */
	private Map<String, Type<?>> globalVariables;

	/**
	 * Creates a new program with the given parameters.
	 * 
	 * @param mainStatement
	 * 			The main statement of the program.
	 * 
	 * @param globalVariables
	 * 			A map of variables of the program.
	 * 
	 * @throws NullPointerException
	 * 			Throws a NullPointerException when globalVariables is null.
	 * 			| globalVariables == null
	 */
	public Program(Statement mainStatement, Map<String, Type<?>> globalVariables) throws NullPointerException {
		
		if (globalVariables == null) {
			throw new NullPointerException("The variables map can't be null.");
		}
		
		this.mainStatement = mainStatement;
		this.globalVariables = globalVariables;
	}
}
