package jumpingalien.model.program.exception;

import jumpingalien.part3.programs.SourceLocation;

public class JumpingAlienLanguageParseException extends RuntimeException {
	
	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = 5333758491580736327L;

	public JumpingAlienLanguageParseException(SourceLocation location) {
		super("Parse error at: " + location.toString() + ".");
	}
}