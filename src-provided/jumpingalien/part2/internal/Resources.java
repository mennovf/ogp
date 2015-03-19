package jumpingalien.part2.internal;

import java.util.stream.IntStream;

import jumpingalien.common.sprites.ImageSprite;
import jumpingalien.common.sprites.JumpingAlienSprites;

public class Resources extends JumpingAlienSprites {

	public static final String PLANT_LEFT_FILENAME = "levels/items/plantPurple.png";
	public static final ImageSprite PLANT_SPRITE_LEFT = ImageSprite
			.createSprite(PLANT_LEFT_FILENAME);
	public static final ImageSprite PLANT_SPRITE_RIGHT = ImageSprite
			.createHFlippedSprite(PLANT_LEFT_FILENAME);

	public static final String SHARK_LEFT_FILENAME = "levels/enemies/fishSwim1.png";
	public static final String SHARK_LEFT2_FILENAME = "levels/enemies/fishSwim2.png";
	public static final String SHARK_DEAD_FILENAME = "levels/enemies/fishDead.png";

	public static final ImageSprite SHARK_SPRITE_LEFT = ImageSprite
			.createSprite(SHARK_LEFT_FILENAME);
	public static final ImageSprite SHARK_SPRITE_RIGHT = ImageSprite
			.createHFlippedSprite(SHARK_LEFT_FILENAME);

	public static final String SLIME_LEFT_FILENAME = "levels/enemies/slimeWalk1.png";
	public static final String SLIME_LEFT2_FILENAME = "levels/enemies/slimeWalk2.png";
	public static final String SLIME_DEAD_FILENAME = "levels/enemies/slimeDead.png";

	public static final ImageSprite SLIME_SPRITE_LEFT = ImageSprite
			.createSprite(SLIME_LEFT_FILENAME);
	public static final ImageSprite SLIME_SPRITE_RIGHT = ImageSprite
			.createHFlippedSprite(SLIME_LEFT_FILENAME);

	public static final ImageSprite[] NUMBER_SPRITES = IntStream
			.rangeClosed(0, 9)
			.mapToObj(n -> String.format("levels/hud/hud_%d.png", n))
			.map(ImageSprite::createSprite)
			.toArray(size -> new ImageSprite[size]);
	public static final ImageSprite HEALTH_FULL = ImageSprite
			.createSprite("levels/hud/hud_heartFull.png");
	public static final ImageSprite HEALTH_HALF = ImageSprite
			.createSprite("levels/hud/hud_heartHalf.png");
	public static final ImageSprite HEALTH_EMPTY = ImageSprite
			.createSprite("levels/hud/hud_heartEmpty.png");

}
