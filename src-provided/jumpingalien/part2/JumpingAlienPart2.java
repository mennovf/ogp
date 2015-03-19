package jumpingalien.part2;

import jumpingalien.part2.facade.Facade;
import jumpingalien.part2.internal.JumpingAlienGUIPart2;
import jumpingalien.part2.internal.JumpingAlienGamePart2;
import jumpingalien.part2.internal.Part2Options;

public class JumpingAlienPart2 {

	public static void main(String[] args) {
		Part2Options options = Part2Options.parse(args);

		JumpingAlienGamePart2 game = new JumpingAlienGamePart2(options, new Facade());

		new JumpingAlienGUIPart2(game).start();
	}

}
