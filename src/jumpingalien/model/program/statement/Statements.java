package jumpingalien.model.program.statement;


/**
 * A class handling the execution of a list of statements.
 */
public class Statements implements Statement {
	
	private Statement[] statements;
	private int currentStatementIndex;
	
	
	/**
	 * Constructs a new Statements.
	 * 
	 * @param statements
	 * 			The statements to control.
	 */
	public Statements(Statement[] statements) {
		this.statements = statements;
		reset();
	}

	/**
	 * Returns The statement being executed.
	 * 
	 * @return The statement being executed.
	 */
	private Statement currentStatement() {
		return this.statements[this.currentStatementIndex];
	}

	
	@Override
	public double advanceTime(double dt) {
		double timeLeft = dt;
		while (timeLeft >= Statement.defaultTime && ! this.isFinished()) {
			timeLeft = this.currentStatement().advanceTime(timeLeft);
	        if (this.currentStatement().isFinished()) {
	                this.currentStatementIndex += 1;
	        }
		}
		return timeLeft;
	}

	@Override
	public boolean isFinished() {
		return (currentStatementIndex == statements.length);
	}

	@Override
	public void reset() {
		for (Statement statement: this.statements) {
			statement.reset();
		}
		this.currentStatementIndex = 0;
	}
}