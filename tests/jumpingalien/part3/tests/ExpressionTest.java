package jumpingalien.part3.tests;

import static org.junit.Assert.*;

import java.util.HashMap;

import jumpingalien.model.Utilities;
import jumpingalien.model.Vector;
import jumpingalien.model.gameobject.GameObject;
import jumpingalien.model.program.LanguageProgram;
import jumpingalien.model.program.ProgramFactory;
import jumpingalien.model.program.expression.*;
import jumpingalien.model.program.statement.*;
import jumpingalien.part2.tests.TestUtilities;
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

	private LanguageProgram createProgram(Statement mainStatement) {
		return new LanguageProgram(mainStatement, globals);
	}
	
	private <R> R runExpression(Expression<R> expr) {
		LanguageProgram p = new LanguageProgram(new Break(), globals);
		return expr.evaluate(this.globals, new CallStack(p));
	}
	
	@Test
	public void BinaryAddition_ok() {
		Expression<Double> addition = pf.createAddition(new Value<Double>(3.0), new Value<Double>(1.0), sl);
		assertEquals(4.0, this.runExpression(addition), 1e-7);
	}
	
	
	@Test
	public void Variable_ok() {
		this.globals.put("key", "variable");
		assertEquals("variable", this.runExpression(new Variable<String>("key")));
	}
	
	
	@Test
	public void Value_ok() {
		assertEquals("string", this.runExpression(new Value<String>("string")));
	}
	
	
	@Test
	public void Random_isRandom() {
		assertNotEquals(this.runExpression(pf.createRandom(new Value<Double>(1.0), sl)),this.runExpression(pf.createRandom(new Value<Double>(1.0), sl)));
	}
	
	
	@Test
	public void Random_maxValue() {
		int amount = 30;
		Double max = 10.0;
		for (int i = 0 ; i<amount; ++i) {
			assertTrue(this.runExpression(pf.createRandom(new Value<Double>(max), sl)) <= max);
		}
	}
	
	
	@Test
	public void Subtraction_ok() {
		assertEquals(1.0, this.runExpression(pf.createSubtraction(new Value<Double>(2.0), new Value<Double>(1.0), sl)), 1e-7);
	}
	
	@Test
	public void Multiplication_ok() {
		assertEquals(4.0, this.runExpression(pf.createMultiplication(new Value<Double>(3.0), new Value<Double>(4.0/3.0), sl)), 1e-7);
	}
	
	
	@Test
	public void Division_ok() {
		assertEquals(9.0/4.0, this.runExpression(pf.createDivision(new Value<Double>(3.0), new Value<Double>(4.0/3.0), sl)), 1e-7);
	}
	
	
	@Test
	public void SquareRoot_ok() {
		assertEquals(3.0, this.runExpression(pf.createSqrt(new Value<Double>(9.0), sl)), 1e-7);
	}
	
	
	@Test
	public void And_ok() {
		assertTrue(this.runExpression(pf.createAnd(new Value<Boolean>(true), new Value<Boolean>(true), sl)));
		assertFalse(this.runExpression(pf.createAnd(new Value<Boolean>(false), new Value<Boolean>(true), sl)));
	}
	
	
	@Test
	public void Or_ok() {
		assertTrue(this.runExpression(pf.createOr(new Value<Boolean>(true), new Value<Boolean>(true), sl)));
		assertTrue(this.runExpression(pf.createOr(new Value<Boolean>(false), new Value<Boolean>(true), sl)));
		assertFalse(this.runExpression(pf.createOr(new Value<Boolean>(false), new Value<Boolean>(false), sl)));
	}
	
	
	
	@Test
	public void Negation_ok() {
		assertTrue(this.runExpression(pf.createNot(new Value<Boolean>(false), sl)));
		assertFalse(this.runExpression(pf.createNot(new Value<Boolean>(true), sl)));
	}
	
	
	@Test
	public void equalsNull() {
		assertTrue(this.runExpression(pf.createEquals(new Value<GameObject>(null), new Value<GameObject>(null), sl)));
	}
	
	
	@Test
	public void NotEqualsNull() {
		assertTrue(this.runExpression(pf.createNotEquals(new Value<GameObject>(null), new Value<GameObject>(TestUtilities.shark(new Vector<>(0.0, 0.0))), sl)));
	}
}
