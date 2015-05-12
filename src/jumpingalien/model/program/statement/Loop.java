package jumpingalien.model.program.statement;

import be.kuleuven.cs.som.annotate.*;

public abstract class Loop implements Statement {

	@Override
	public void executeBreak(CallStack callStack) {
		// TODO Auto-generated method stub
		Statement.super.executeBreak(callStack);
	}
	
	@Override
	@Immutable
	@Basic
	public boolean isBreakable() {
		return true;
	}
}
