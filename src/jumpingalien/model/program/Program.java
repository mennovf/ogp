package jumpingalien.model.program;

import java.util.Map;

import jumpingalien.model.gameobject.GameObject;

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
	private Map<String, Object> globalVariables;
	
	/**
	 * The game object this program is controlling.
	 */
	private GameObject gameObject;
	
	

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
	public Program(Statement mainStatement, Map<String, Object> globalVariables) throws NullPointerException {
		
		if (globalVariables == null) {
			throw new NullPointerException("The variables map can't be null.");
		}
		
		this.mainStatement = mainStatement;
		this.globalVariables = globalVariables;
	}
	
	
	/**
	 * Returns the game object this program is controlling.
	 */
	public GameObject getGameObject() {
		return this.gameObject;
	}
	
	
	/**
	 * Sets the game object this program is controlling.
	 * 
	 * @param gameObject
	 * 			The game object to set.
	 * 
	 * @post The game object this program is controlling will be the given game object.
	 * 			| new.getGameObject() == (new gameObject)
	 * 
	 * @post This program will be the program controlling the game object.
	 * 			| (new gameObject).getProgram() == new
	 */
	public void setGameObject(GameObject gameObject) {
		this.gameObject = gameObject;
	}
}
