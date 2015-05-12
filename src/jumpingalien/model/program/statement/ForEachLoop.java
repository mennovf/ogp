package jumpingalien.model.program.statement;

import java.util.Map;

import jumpingalien.model.program.expression.Expression;
import jumpingalien.part3.programs.IProgramFactory.Kind;
import jumpingalien.part3.programs.IProgramFactory.SortDirection;

/**
 * A class representing a for each loop.
 * 
 * @author Rugen Heidbuchel, Menno Vanfrachem
 */
public class ForEachLoop extends Loop {

	
	private final Kind kind;
	private final String variable;
	private final Expression<Boolean> whereExpression;
	private final Expression<Double> sortExpression;
	private final SortDirection sortDirection;
	private final Statement body;
	
	
	public ForEachLoop(Kind kind, String variable, Expression<Boolean> whereExpression,
			Expression<Double> sortExpression, SortDirection sortDirection, Statement body) {
		this.kind = kind;
		this.variable = variable;
		this.whereExpression = whereExpression;
		this.sortExpression = sortExpression;
		this.sortDirection = sortDirection;
		this.body = body;
	}
	
	
	
	@Override
	public double advanceTime(double dt, Map<String, Object> globals, CallStack callStack) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	@Override
	public boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void forceFinish() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public boolean isWellFormed(CallStack callStack) {
		return this.body.isWellFormed(this.getOwnCallStack(callStack));
	}

	@Override
	public boolean isActionAllowed() {
		return false;
	}
}
