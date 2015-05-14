package jumpingalien.model.gameobject;

import jumpingalien.model.Constants;
import jumpingalien.model.Vector;
import jumpingalien.model.program.LanguageProgram;
import jumpingalien.util.Sprite;

/**
 * A class representing Mazub's evil twin brother Buzam.
 * 
 * @author Rugen Heidbuchel, Menno Vanfrachem
 */
public class Buzam extends Mazub {

	
	/**
	 * Creates a new Buzam with the given parameters.
	 * 
	 * @param position
	 * 			The begin position in meters.
	 * 
	 * @param sprites
	 * 			The spriteset.
	 * 
	 * @param vxInit
	 * 			The initial running speed in m/s.
	 * 
	 * @param vxMax
	 * 			The maximum running speed in m/s.
	 * 
	 * @param direction
	 * 			The starting direction. 1.0 means facing
	 * 			right, -1.0 means facing left.
	 * 
	 * @throws NullPointerException
	 * 			Throws a NullPointerException when sprites is null.
	 * 			| sprites == null
	 * 
	 * @throws IllegalArgumentException
	 * 			Throws an IllegalArgumentException when the given
	 * 			position and/or direction are not valid.
	 * 			| !isValidPosition(position) || !isValidDirection(direction)
	 */
	public Buzam(Vector<Double> position, Sprite[] sprites, double vxInit,
			double vxMax, double direction) throws NullPointerException,
			IllegalArgumentException {
		
		this(position, sprites, vxInit, vxMax, direction, null);
	}
	
	
	/**
	 * Creates a new Buzam with the given parameters.
	 * 
	 * @param position
	 * 			The begin position in meters.
	 * 
	 * @param sprites
	 * 			The spriteset.
	 * 
	 * @param vxInit
	 * 			The initial running speed in m/s.
	 * 
	 * @param vxMax
	 * 			The maximum running speed in m/s.
	 * 
	 * @param direction
	 * 			The starting direction. 1.0 means facing
	 * 			right, -1.0 means facing left.
	 * 
	 * @throws NullPointerException
	 * 			Throws a NullPointerException when sprites is null.
	 * 			| sprites == null
	 * 
	 * @throws IllegalArgumentException
	 * 			Throws an IllegalArgumentException when the given
	 * 			position and/or direction are not valid.
	 * 			| !isValidPosition(position) || !isValidDirection(direction)
	 */
	public Buzam(Vector<Double> position, Sprite[] sprites, double vxInit,
			double vxMax, double direction, LanguageProgram program) throws NullPointerException,
			IllegalArgumentException {
		
		super(position, sprites, vxInit, vxMax, direction, program);
		
		this.setHealth(Constants.buzamBeginHealth);
	}

}
