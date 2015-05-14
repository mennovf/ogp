package jumpingalien.model.program.statement;

import java.util.Stack;

import be.kuleuven.cs.som.annotate.*;
import jumpingalien.model.program.LanguageProgram;

/**
 * A class representing a call stack.
 * 
 * @author Rugen Heidbuchel, Menno Vanfrachem
 */
@SuppressWarnings("serial")
public class CallStack extends Stack<Statement> {
	
	/**
	 * The program calling this call stack's root statement.
	 */
	private final LanguageProgram program;
	
	
	/**
	 * Creates a new call stack with the given program.
	 * 
	 * @param program
	 * 			The program in which the root statement is being called.
	 */
	public CallStack(LanguageProgram program) {
		this.program = program;
	}
	
	
	
	/**
	 * Returns the root of this call stack. This is the program in which this
	 * call stack's root statement is being called.
	 */
	@Basic
	public LanguageProgram getProgram() {
		return this.program;
	}
	

	/**
	 * Returns the parent stack. This means the stack without the upper element.
	 * 
	 * @return This stack without it's upper element.
	 * 			| TODO: Write this
	 */
	public CallStack getParentCallStack() {
		//TODO: Throw nice exception when the stack is empty
		CallStack parentStack = (CallStack)this.clone();
		parentStack.pop();
		return parentStack;
	}
	
	
	/**
	 * Returns a new call stack created by appending the given statement.
	 * 
	 * @param child
	 * 			The statement to append to the stack.
	 * 
	 * @return A new call stack created by appending the given statement.
	 * 			| newStack = this.clone()
	 * 			| newStack.push(child)
	 * 			| return childStack
	 */
	public CallStack append(Statement child) {
		CallStack childStack = (CallStack)this.clone();
		childStack.push(child);
		return childStack;
	}
}
