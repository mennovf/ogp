package jumpingalien.model.program.expression;

public class Literal<T> implements Expression<T> {
	
	private T val;
	
	public Literal(T val) {
		this.val = val;
	}

	@Override
	public T evaluate() {
		return val;
	}

	public static void main(String[] args) {
		Literal<Double> five = new Literal<Double>(5.0);
		BinaryOperation<Double, Double, Double> addition = new BinaryOperation<>(five, five,
				(left, right)->{return left + right;});
		BinaryOperation<Double, Double, Double> subtraction = new BinaryOperation<>(five, five,
				(left, right)->{return left - right;});
		System.out.println(addition.evaluate());
		System.out.println(subtraction.evaluate());
	}
}
