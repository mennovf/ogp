package jumpingalien.part3.internal;

import jumpingalien.common.sprites.ImageSprite;
import jumpingalien.util.Sprite;

public class Resources extends jumpingalien.part2.internal.Resources {

	public static final String PLANT_PROGRAM_FILENAME = "resources/programs/plant.txt";

	public static final String SHARK_PROGRAM_FILENAME = "resources/programs/shark.txt";

	public static final String SLIME_PROGRAM_FILENAME = "resources/programs/slime.txt";

	public static final String BUZAM_PROGRAM_FILENAME = "resources/programs/buzam.txt";
	
	public static final Sprite[] BUZAM_SPRITESET = new Sprite[ALIEN_SPRITESET.length];
	static {
		for (int i = 0; i < BUZAM_SPRITESET.length; i++) {
			BUZAM_SPRITESET[i] = ((ImageSprite) ALIEN_SPRITESET[i]).shiftHue(8);
		}
	}

}
