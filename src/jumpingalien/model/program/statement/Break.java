package jumpingalien.model.program.statement;

import java.util.Map;

public class Break extends SimpleStatement {
	
	@Override
	protected void run(Map<String, Object> globals, CallStack callStack) {
		callStack.pop().executeBreak(callStack);
	}

	@Override
	public boolean isWellFormed(CallStack callStack) {
		for (Statement statement : callStack) {
			if (statement.isBreakable()) {
				return true;
			}
		}
		return false;
	}
}