package jumpingalien.model.program.statement;

public interface Action extends Statement {

	@Override 
	default boolean isWellFormed(CallStack callStack) {
		for (Statement statement : callStack) {
			if (!statement.isActionAllowed()) {
				return false;
			}
		}
		return true;
	}
}