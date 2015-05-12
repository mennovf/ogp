package jumpingalien.part3.tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import jumpingalien.model.program.Program;
import jumpingalien.model.program.statement.*;
import jumpingalien.model.program.expression.*;
import jumpingalien.part3.programs.IProgramFactory.Kind;
import jumpingalien.part3.programs.IProgramFactory.SortDirection;

public class StatementTest {
	
	private Map<String, Object> globals;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		this.globals = new HashMap<String, Object>();
	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void isWellFormed_BreakInWhile() {
		Program p = new Program(new WhileLoop(new Value<Boolean>(true), new Break()), globals);
		assertTrue(p.isWellFormed());
	}


	@Test
	public void isWellFormed_BreakInForEach() {
		Program p = new Program(new ForEachLoop(Kind.ANY, "", new Value<Boolean>(true), new Value<Double>(0.0), SortDirection.ASCENDING, new Break()), globals);
		assertTrue(p.isWellFormed());
	}
	

	@Test
	public void isWellFormed_BreakInNotALoop() {
		Program p = new Program(new Sequence(new Statement[]{new Break()}), globals);
		assertFalse(p.isWellFormed());
	}
	

	@Test
	public void isWellFormed_ActionInForEach() {
		Program p = new Program(new ForEachLoop(Kind.ANY, "", new Value<Boolean>(true), new Value<Double>(0.0), SortDirection.ASCENDING, new Wait(new Value<Double>(0.1))), globals);
		assertFalse(p.isWellFormed());
	}
	

	@Test
	public void isWellFormed_ActionInNotAForEach() {
		Program p = new Program(new Sequence(new Statement[]{new Wait(new Value<Double>(0.2))}), globals);
		assertTrue(p.isWellFormed());
	}
	
	@Test
	public void If_ok() {
		globals.put("result", new Value<Boolean>(null));

		If mainStatement = new If(new Value<Boolean>(true) , new Assignment("result", new Value<Boolean>(true)), new Assignment("result", new Value<Boolean>(false)));
		Program p = new Program(mainStatement, globals);
		p.advanceTime(Statement.defaultTime);
		assertTrue((Boolean)globals.get("result"));
		
		mainStatement = new If(new Value<Boolean>(false) , new Assignment("result", new Value<Boolean>(true)), new Assignment("result", new Value<Boolean>(false)));
		p = new Program(mainStatement, globals);
		p.advanceTime(Statement.defaultTime);
		assertFalse((Boolean)globals.get("result"));
		
	}
}
