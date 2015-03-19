package jumpingalien.part2.internal;

import java.util.Optional;

import jumpingalien.common.game.AlienInfoProvider;

public interface AlienInfoProvider2 extends AlienInfoProvider {

	public Optional<Integer> getAlienHealth();
	
	public Optional<Boolean> isImmune();
}
