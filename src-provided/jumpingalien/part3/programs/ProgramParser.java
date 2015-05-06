package jumpingalien.part3.programs;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import jumpingalien.part3.programs.internal.ParserVisitor;
import jumpingalien.part3.programs.internal.generated.JumpingAlienProgLexer;
import jumpingalien.part3.programs.internal.generated.JumpingAlienProgParser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

/**
 * Parser for JumpingAlien programs.
 * 
 * To use this class:
 * 
 * First, create an implementation if IProgramFactory:
 * 
 * <pre>
 * <code>
 * IProgramFactory&lt;MyExpression, MyStatement, MyType, MyProgram&gt; factory = new ProgramFactory<>();
 * </code>
 * </pre>
 * 
 * Then, create an instance of this class:
 * 
 * <pre>
 * <code>
 * ProgramParser&lt;MyExpression, MyStatement, MyType, MyProgram&gt; parser = new ProgramParser<>(factory);
 * </code>
 * </pre>
 * 
 * Finally, parse a string or file: <code><pre>
 * Optional&lt;MyProgram&gt; parseResult = parser.parse(textToParse);
 * </pre></code>
 * 
 * If parsing is successful, <code>parseResult.isPresent()</code> returns true
 * and <code>parseResult.get()</code> returns the created program.
 * 
 * If parsing was not successful, <code>parseResult.isPresent()</code> returns
 * false and <code>parser.getErrors()</code> can be used to retrieve the list of
 * errors during parsing.
 *
 * @param <E>
 *            The class of an Expression
 * @param <S>
 *            The class of a Statement
 * @param <T>
 *            The class of a Type
 * @param <P>
 *            The class of a Program
 */
public class ProgramParser<E, S, T, P> {

	private final IProgramFactory<E, S, T, P> factory;

	private final List<String> errors = new ArrayList<>();

	public ProgramParser(IProgramFactory<E, S, T, P> factory) {
		this.factory = factory;
	}

	public IProgramFactory<E, S, T, P> getFactory() {
		return factory;
	}

	/**
	 * Returns the Program that results from parsing the given string,
	 * or Optional.empty() if parsing has failed.
	 * 
	 * When parsing has failed, the error messages can be retrieved with the
	 * getErrors() method.
	 */
	public Optional<P> parseString(String string) {
		return parse(new ANTLRInputStream(string));
	}

	/**
	 * Returns the Program that results from parsing the file with the given
	 * name,
	 * or Optional.empty() if parsing has failed.
	 * 
	 * When parsing has failed, the error messages can be retrieved with the
	 * getErrors() method.
	 */
	public Optional<P> parseFile(String filename) throws IOException {
		return parse(new ANTLRInputStream(new FileReader(filename)));
	}

	/**
	 * Returns the Program that results from parsing the given CharStream,
	 * or Optional.empty() if parsing has failed.
	 * 
	 * When parsing has failed, the error messages can be retrieved with the
	 * getErrors() method.
	 */
	protected Optional<P> parse(CharStream input) {
		reset();

		JumpingAlienProgLexer lexer = new JumpingAlienProgLexer(input);
		JumpingAlienProgParser parser = new JumpingAlienProgParser(
				new CommonTokenStream(lexer));
		parser.addErrorListener(new BaseErrorListener() {
			@Override
			public void syntaxError(Recognizer<?, ?> recognizer,
					Object offendingSymbol, int line, int charPositionInLine,
					String msg, RecognitionException e) {
				errors.add(msg + " (" + line + ", " + charPositionInLine + ")");
			}
		});
		ParserVisitor<E, S, T> visitor = new ParserVisitor<E, S, T>(factory);
		try {
			visitor.visit(parser.program());
			if (errors.isEmpty()) {
				return Optional.ofNullable(factory.createProgram(
						visitor.getMainStatement(), visitor.getGlobals()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			errors.add(e.toString());
		}
		return Optional.empty();
	}

	protected void reset() {
		this.errors.clear();
	}

	public List<String> getErrors() {
		return Collections.unmodifiableList(errors);
	}

	public static <E, S, T, P> ProgramParser<E, S, T, P> create(
			IProgramFactory<E, S, T, P> factory) {
		return new ProgramParser<E, S, T, P>(factory);
	}
}
