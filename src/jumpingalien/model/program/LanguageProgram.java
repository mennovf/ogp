package jumpingalien.model.program;

import java.util.Map;

import jumpingalien.model.gameobject.GameObject;
import jumpingalien.model.program.exception.JumpingAlienLanguageRuntimeException;
import jumpingalien.model.program.statement.CallStack;
import jumpingalien.model.program.statement.Statement;

/**
 * A class representing a program.
 * 
 * @author Rugen Heidbuchel, Menno Vanfrachem
 */
public class LanguageProgram implements Program{
	
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
	 * Contains the time not yet consumed by statements.
	 */
	private double excessTime; 
	
	
	/**
	 * Rememberers whether a JumpingAlienLanguageRuntimeException has occurred during
	 * previous excecutions.
	 */
	private boolean errorOcurred;
	

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
	public LanguageProgram(Statement mainStatement, Map<String, Object> globalVariables) throws NullPointerException {
		
		if (globalVariables == null) {
			throw new NullPointerException("The variables map can't be null.");
		}
		
		this.mainStatement = mainStatement;
		this.globalVariables = globalVariables;
		this.excessTime = 0;
		this.errorOcurred = false;
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
	 * 			| new.getGameObject() == gameObject
	 */
	public void setGameObject(GameObject gameObject) {
		this.gameObject = gameObject;
		this.globalVariables.put("self", gameObject);
	}
	
	/**
	 * Advance the main statement.
	 * 
	 * @param dt
	 * 			The amount of gameworld time that has passed since the last call.
	 * 
	 * @effect Advances the time of mainStatement by (dt + excessTime). Here excessTime is the time left from the last execution.
	 * 			| this.excessTime = this.mainStatement.advanceTime(dt + this.excessTime, this.globalVariables)
	 */
	@Override
	public void advanceTime(double dt) {
		if (!this.errorOcurred) {
			try {
				this.excessTime = this.mainStatement.advanceTime(dt + this.excessTime, this.globalVariables, new CallStack(this));
			}
			catch (JumpingAlienLanguageRuntimeException e) {
				this.errorOcurred = true;
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * Returns whether this program is well-formed.
	 * Well-formed means every break statement is embedded in either a While or a ForEach and that
	 * a ForEach may not contain any Actions.
	 * 
	 * @return whether this program is well-formed.
	 */
	public boolean isWellFormed() {
		return this.mainStatement.isWellFormed(new CallStack(this));
	}
}
