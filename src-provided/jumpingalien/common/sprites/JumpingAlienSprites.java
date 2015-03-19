package jumpingalien.common.sprites;

import static jumpingalien.common.sprites.ImageSprite.*;
import jumpingalien.util.Sprite;

public class JumpingAlienSprites {

	public static final Sprite[] ALIEN_SPRITESET = new Sprite[30];

	static {
		/* 0: stand, not ducking, front */
		ALIEN_SPRITESET[0] = createSprite("levels/player/p1_front.png").scaleToWidth(70);
		/* 1: stand, ducking, front */
		ALIEN_SPRITESET[1] = createSprite("levels/player/p1_duck_front.png")
				.scaleToHeight(70);
		/* 2: stand, not ducking, right */
		ALIEN_SPRITESET[2] = createSprite("levels/player/p1_stand.png").scaleToWidth(70);
		/* 3: stand, not ducking, left */
		ALIEN_SPRITESET[3] = createHFlippedSprite("levels/player/p1_stand.png").scaleToWidth(70);
		/* 4: jump, not ducking, right */
		ALIEN_SPRITESET[4] = createSprite("levels/player/p1_jump.png").scaleToWidth(70);
		/* 5: jump, not ducking, left */
		ALIEN_SPRITESET[5] = createHFlippedSprite("levels/player/p1_jump.png").scaleToWidth(70);
		/* 6: ducking, right */
		ALIEN_SPRITESET[6] = createSprite("levels/player/p1_duck.png")
				.scaleToHeight(70);
		/* 7: ducking, left */
		ALIEN_SPRITESET[7] = createHFlippedSprite("levels/player/p1_duck.png")
				.scaleToHeight(70);
		/* 8..18: moving, not ducking, right */
		for (int i = 0; i < 11; i++) {
			/* walk right */
			ALIEN_SPRITESET[8 + i] = createSprite(String.format(
					"levels/player/p1_walk/PNG/p1_walk%02d.png", i + 1)).scaleToWidth(70);
		}
		/* 19..29: moving, not ducking, right */
		for (int i = 0; i < 11; i++) {
			/* walk left */
			ALIEN_SPRITESET[19 + i] = createHFlippedSprite(String.format(
					"levels/player/p1_walk/PNG/p1_walk%02d.png", i + 1)).scaleToWidth(70);
		}

	}
}
