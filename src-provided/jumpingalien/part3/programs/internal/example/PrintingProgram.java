package jumpingalien.part3.programs.internal.example;

import java.util.Map;

class PrintingProgram {

	private Map<String, PrintingObject> globals;
	private PrintingObject main;

	public PrintingProgram(PrintingObject mainStatement, Map<String, PrintingObject> globalVariables) {
		if (mainStatement == null) {
			throw new NullPointerException("main must not be null");
		}
		if (globalVariables == null) {
			throw new NullPointerException("globals must not be null");
		}
		this.main = mainStatement;
		this.globals = globalVariables;
	}
	
	@Override
	public String toString() {
		return "Globals:\n" + globals.toString() + "\n\n" + main.toString();
	}
}
