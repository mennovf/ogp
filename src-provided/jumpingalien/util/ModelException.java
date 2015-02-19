package jumpingalien.util;

@SuppressWarnings("serial")
public class ModelException extends RuntimeException {

	public ModelException(Throwable cause) {
		super(cause);
	}
	
	public ModelException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ModelException(String message) {
		super(message);
	}
}
