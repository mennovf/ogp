package jumpingalien.part3.tests;

import static org.junit.Assert.*;

import java.util.HashMap;

import jumpingalien.model.program.Program;
import jumpingalien.model.program.ProgramFactory;
import jumpingalien.model.program.expression.*;
import jumpingalien.model.program.statement.*;
import jumpingalien.part3.programs.SourceLocation;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ExpressionTest {
	
	private HashMap<String, Object> globals;
	private ProgramFactory pf;
	private final SourceLocation sl = new SourceLocation(0, 0);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		this.globals = new HashMap<>();
		this.pf = new ProgramFactory();
	}

	@After
	public void tearDown() throws Exception {
	}

	private Program createProgram(Statement mainStatement) {
		return new Program(mainStatement, globals);
	}
	
	
	@Test
	public void BinaryAddition_ok() {
		Expression<Double> addition = pf.createAddition(new Value<Double>(3.0), new Value<Double>(1.0), sl);
		HashMap<String, Object> globals = new HashMap<String, Object>();
		Program p = new Program(new Break(), globals);
		assertEquals(4.0, addition.evaluate(globals, new CallStack(p)), 1e-7);
	}
}
