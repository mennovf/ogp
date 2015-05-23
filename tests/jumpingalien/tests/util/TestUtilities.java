package jumpingalien.tests.util;

import jumpingalien.model.Vector;
import jumpingalien.model.gameobject.Mazub;
import jumpingalien.model.gameobject.School;
import jumpingalien.model.gameobject.Shark;
import jumpingalien.model.gameobject.Slime;
import jumpingalien.model.world.World;
import jumpingalien.part2.internal.Resources;
import jumpingalien.util.Sprite;

/**
 * A class with utilities for use in the test classes.
 * 
 * @author Rugen Heidbuchel, Menno Vanfrachem
 */
public class TestUtilities {

	/**
	 * A convenience method (for testing) to construct a slime using its sprites.
	 * 
	 * @param pos The position of the slime
	 * @param school The school of the slime
	 * @return A slime with pos as it's position and school as it's school.
	 */
	public static Slime slime(Vector<Double> pos, School school){
		Sprite[] sprites = new Sprite[] {Resources.SLIME_SPRITE_LEFT, Resources.SLIME_SPRITE_RIGHT};
		return new Slime(pos, sprites, school);
	}

	/**
	 * A convenience method (for testing) to construct a shark using its sprites.
	 * 	
	 * @param pos
	 * 			The position of the new shark.
	 * 
	 * @return A shark object.
	 * 			| shark.getPositionInMeters() == pos
	 *			| shark.getCurrentSprite() == Resources.SHARK_SPRITE_LEFT 
	 */
	public static Shark shark(Vector<Double> pos){
		Sprite[] sprites = new Sprite[] {Resources.SHARK_SPRITE_LEFT, Resources.SHARK_SPRITE_RIGHT};
		return new Shark(pos, sprites);
	}
	
	/**
	 * A convenience method (for testing) to construct a shark using its sprites.
	 * 
	 * @return Returns a default world.
	 * 			| new World(70, 20, 12, 1024, 751, 18, 9)
	 */
	public static World world(){
		return new World(70, 20, 12, 1024, 751, 18, 9);
	}
	
	/**
	 * A convenience method (for testing) to construct a Mazub using its sprites.
	 * 
	 * @param pos
	 * 			Mazubs position.
	 * 
	 * @return A Mazub.
	 * 			| new Mazub(pos, Resources.ALIEN_SPRITESET, 1, 3, 1)
	 */
	public static Mazub mazub(Vector<Double> pos) {
		return new Mazub(pos, Resources.ALIEN_SPRITESET, 1, 3, 1);
	}
}
