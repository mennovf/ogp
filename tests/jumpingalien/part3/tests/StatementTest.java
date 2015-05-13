package jumpingalien.part3.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import jumpingalien.model.Utilities;
import jumpingalien.model.Vector;
import jumpingalien.model.gameobject.Plant;
import jumpingalien.model.gameobject.Shark;
import jumpingalien.model.program.Program;
import jumpingalien.model.program.statement.*;
import jumpingalien.model.program.expression.*;
import jumpingalien.model.world.World;
import jumpingalien.part3.internal.Resources;
import jumpingalien.part3.programs.IProgramFactory.Kind;
import jumpingalien.part3.programs.IProgramFactory.SortDirection;
import jumpingalien.util.Sprite;

public class StatementTest {
	
	private Map<String, Object> globals;
	private World world;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		this.globals = new HashMap<String, Object>();
		this.world = new World(70, 20, 12, 1024, 751, 19, 11);
	}

	@After
	public void tearDown() throws Exception {
	}


	private Program createProgram(Statement mainStatement) {
		return new Program(mainStatement, globals);
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
		List<Statement> statements = new ArrayList<>();
		statements.add(new Break());
		Program p = new Program(new Sequence(statements), globals);
		assertFalse(p.isWellFormed());
	}
	

	@Test
	public void isWellFormed_ActionInForEach() {
		Program p = new Program(new ForEachLoop(Kind.ANY, "", new Value<Boolean>(true), new Value<Double>(0.0), SortDirection.ASCENDING, new Wait(new Value<Double>(0.1))), globals);
		assertFalse(p.isWellFormed());
	}
	

	@Test
	public void isWellFormed_ActionInNotAForEach() {
		List<Statement> statements = new ArrayList<>();
		statements.add(new Wait(new Value<Double>(0.2)));
		Program p = new Program(new Sequence(statements), globals);
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
	
	@Test
	public void Assignment_ok() {
		globals.put("result", false);
		Program p = createProgram(new Assignment("result", new Value<Boolean>(true)));
		p.advanceTime(Statement.defaultTime);
		
		assertTrue((Boolean)globals.get("result"));
	}
	
	@Test
	public void Sequence_ok() {
		globals.put("done", false);
		List<Statement> statements = new ArrayList<>();
		statements.add(new Wait(new Value<Double>(0.1)));
		statements.add(new Assignment("done", new Value<Boolean>(true)));
		Sequence seq = new Sequence(statements);
		Program p = createProgram(seq);

		p.advanceTime(0.1);
		assertFalse(seq.isFinished());
		assertFalse((Boolean)globals.get("done"));

		p.advanceTime(Statement.defaultTime);
		assertTrue(seq.isFinished());
		assertTrue((Boolean)globals.get("done"));
	}
	

	@Test
	public void Break_WhileOk() {
		globals.put("result", true);
		List<Statement> statements = new ArrayList<>();
		statements.add(new Break());
		statements.add(new Assignment("result", new Value<Boolean>(false)));
		WhileLoop w = new WhileLoop(new Value<Boolean>(true), new Sequence(statements));
		Program p = createProgram(w);

		p.advanceTime(2*Statement.defaultTime);
		assertTrue(w.isFinished());
		
		p.advanceTime(Statement.defaultTime * 1.1);
		assertTrue((Boolean)globals.get("result"));
	}
	
	
	@Test
	public void Break_ForEachOk() {
		globals.put("result", true);
		List<Statement> statements = new ArrayList<>();
		statements.add(new Break());
		statements.add(new Assignment("result", new Value<Boolean>(false)));
		ForEachLoop fel = new ForEachLoop(Kind.ANY, "o", new Value<Boolean>(true), new Value<Double>(0.0), SortDirection.ASCENDING, new Sequence(statements));
		Program p = createProgram(fel);

		Plant plant = new Plant(new Vector<>(0.0, 0.0), new Sprite[]{Resources.PLANT_SPRITE_LEFT, Resources.PLANT_SPRITE_RIGHT}, p);
		plant.setWorld(this.world);
		

		p.advanceTime(2*Statement.defaultTime);
		assertTrue(fel.isFinished());
		
		p.advanceTime(Statement.defaultTime * 1.1);
		assertTrue((Boolean)globals.get("result"));
	}
	
	@Test
	public void Wait_ok() {
		Wait w = new Wait(new Value<Double>(0.1));
		Program p = createProgram(w);
		
		p.advanceTime(0.09);
		assertFalse(w.isFinished());
		p.advanceTime(0.02);
		assertTrue(w.isFinished());
	}
	
	
	@Test
	public void WhileLoop_cond() {
		globals.put("cond", true);
		globals.put("result", true);
		List<Statement> statements = new ArrayList<>();
		statements.add(new Wait(new Value<Double>(0.1)));
		statements.add(new Assignment("cond", new Value<Boolean>(false)));
		statements.add(new Assignment("result", new Value<Boolean>(false)));
		WhileLoop w = new WhileLoop(new Variable<Boolean>("cond"), new Sequence(statements));
		Program p = createProgram(w);
		
		p.advanceTime(0.1);
		p.advanceTime(Statement.defaultTime);
		assertFalse(w.isFinished());
		assertTrue((Boolean)globals.get("result"));
		assertTrue((Boolean)globals.get("cond"));
		
		p.advanceTime(0.1);
		assertTrue(w.isFinished());
		assertFalse((Boolean)globals.get("cond"));
		assertFalse((Boolean)globals.get("result"));
	}
	
	
	@Test
	public void WhileLoop_keepGoing() {
		globals.put("i", 0.0);
		List<Statement> statements = new ArrayList<>();
		statements.add(new Assignment("i", new BinaryOperation<Double, Double, Double>(new Variable<Double>("i"), new Value<Double>(1.0), (Double a, Double b)->a+b)));
		WhileLoop w = new WhileLoop(new Value<Boolean>(true), new Sequence(statements));
		Program p = createProgram(w);
		
		p.advanceTime(Statement.defaultTime * 2);
		assertEquals(1.0, (Double)globals.get("i"), 1e-7);
		assertFalse(w.isFinished());

		p.advanceTime(Statement.defaultTime * 2);
		assertEquals(2.0, (Double)globals.get("i"), 1e-7);
		assertFalse(w.isFinished());
	}
}
