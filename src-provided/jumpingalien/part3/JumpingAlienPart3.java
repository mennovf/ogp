package jumpingalien.part3;

import jumpingalien.part3.facade.Facade;
import jumpingalien.part3.internal.JumpingAlienGUIPart3;
import jumpingalien.part3.internal.JumpingAlienGamePart3;
import jumpingalien.part3.internal.Part3Options;

public class JumpingAlienPart3 {

	public static void main(String[] args) {
		Part3Options options = Part3Options.parse(args);

		JumpingAlienGamePart3 game = new JumpingAlienGamePart3(options, new Facade());

		new JumpingAlienGUIPart3(game).start();
	}

}
