package jumpingalien.model.program.expression;

import java.util.function.BiFunction;



public class BinaryOperation<R, V1, V2> implements Expression<R> {
	
	private Expression<V1> left;
	private Expression<V2> right;
	private BiFunction<V1, V2, R> operation;
	
	public BinaryOperation(Expression<V1> left, Expression<V2> right, BiFunction<V1, V2, R> op) {
		this.left = left;
		this.right = right;
		this.operation = op;
	}

	@Override
	public R evaluate() {
		return operation.apply(left.evaluate(), right.evaluate());
	}

}
