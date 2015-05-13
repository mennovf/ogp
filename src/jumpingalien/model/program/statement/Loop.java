package jumpingalien.model.program.statement;

import be.kuleuven.cs.som.annotate.*;

public abstract class Loop implements Statement {
	@Override
	@Immutable
	@Basic
	public boolean isBreakable() {
		return true;
	}
}
