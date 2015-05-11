package jumpingalien.model.program.expression;

import java.util.Map;
import java.util.function.Function;

/**
 * A class representing an unary operation.
 * 
 * @author Rugen Heidbuchel & Menno Vanfrachem
 *
 * @param <R>
 * 			The return type of this operation.
 * 
 * @param <V>
 * 			The type of the parameter of the operation.
 */
public class UnaryOperation<R, V> implements Expression<R> {
	
	private Expression<V> operand;
	private Function<V, R> operation;
	
	/**
	 * Creates a new UnaryOperation.
	 * 
	 * @param operand
	 * 			The value to which the operation is applied.
	 * 
	 * @param op
	 * 			The operation to apply to operand.
	 */
	public UnaryOperation(Expression<V> operand, Function<V, R> op) {
		this.operand = operand;
		this.operation = op;
	}

	/**
	 * Applies the operation to the operand.
	 * 
	 * @return the result of operation applied to operand.
	 */
	@Override
	public R evaluate(Map<String, Object> globals) {
		return this.operation.apply(this.operand.evaluate(globals));
	}
}
