package jumpingalien.model.program.expression;

import java.util.Map;
import java.util.function.BiFunction;

import jumpingalien.model.program.statement.CallStack;



/**
 * A class representing a binary operation.
 * 
 * @author Rugen Heidbuchel & Menno Vanfrachem
 *
 * @param <R> The return type of this operation.
 * 
 * @param <V1> The type of the first parameter of this operation.
 * 
 * @param <V2> The Type of the second parameter of this operation.
 */
public class BinaryOperation<R, V1, V2> implements Expression<R> {
	
	private Expression<V1> left;
	private Expression<V2> right;
	private BiFunction<V1, V2, R> operation;
	
	/**
	 * Constructs a new operation.
	 * 
	 * @param left
	 * 			The left parameter of op.
	 * 
	 * @param right
	 * 			The right parameter of op.
	 * 
	 * @param op
	 * 			The operation to apply to left and right.
	 */
	public BinaryOperation(Expression<V1> left, Expression<V2> right, BiFunction<V1, V2, R> op) {
		this.left = left;
		this.right = right;
		this.operation = op;
	}

	/**
	 * Evaluates this expression.
	 * 
	 * @return Returns the result of op applied to left and right.
	 * 			| op.apply(left.evaluate(), right.evaluate())
	 */
	@Override
	public R evaluate(Map<String, Object> globals, CallStack callStack) {
		return operation.apply(left.evaluate(globals, callStack), right.evaluate(globals, callStack));
	}
}
