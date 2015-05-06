package jumpingalien.part3.programs;

import java.util.List;

import jumpingalien.model.Program;

/**
 * A class that represents the outcome of parsing a string.
 * Parsing can have two outcomes:
 * <ul>
 * <li>Success, in which case the result is a Program object;</li>
 * <li>Failure, in which case the result is a list of error messages.</li>
 * </ul>
 * 
 * Instances of this class can only be created using the static
 *  {@link ParseOutcome#success(Program)} and {@link ParseOutcome#failure(List)} methods.
 *  
 * @param <T> The type of the result stored in the outcome;
 * can be either a Program (for success) or a List&lt;String&gt; of error messages (for failure).
 */
public abstract class ParseOutcome<T> {

	/**
	 * Create a new ParseOutcome that denotes a failure.
	 * The parse result is a list of error strings.
	 */
	public static Failure failure(List<String> errors) {
		return new Failure(errors);
	}

	/**
	 * Create a new ParseOutcome that denotes success.
	 * The parse result is a Program object that represents the parsed program.
	 */
	public static Success success(Program program) {
		return new Success(program);
	}

	public static class Failure extends ParseOutcome<List<String>> {

		private Failure(List<String> errors) {
			super(errors);
		}

		@Override
		public boolean isSuccess() {
			return false;
		}

	}

	public static class Success extends ParseOutcome<Program> {
		private Success(Program program) {
			super(program);
		}

		@Override
		public boolean isSuccess() {
			return true;
		}
	}

	private final T result;

	private ParseOutcome(T result) {
		this.result = result;
	}

	public abstract boolean isSuccess();

	protected final boolean isFailure() {
		return !isSuccess();
	}

	public T getResult() {
		return result;
	}
}
