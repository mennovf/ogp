package jumpingalien.part3.tests;

import static org.junit.Assert.*;
import static org.junit.Assume.*;
import jumpingalien.model.Program;
import jumpingalien.part3.facade.Facade;
import jumpingalien.part3.facade.IFacadePart3;
import jumpingalien.part3.programs.ParseOutcome;

import org.junit.Test;

public class PartialFacadeTest {

	@Test
	public void testParseSimplestProgram() {
		IFacadePart3 facade = new Facade();
		ParseOutcome<?> outcome = facade.parse("skip;");
		assertTrue(outcome.isSuccess());
	}

	@Test
	public void testParseFails() {
		IFacadePart3 facade = new Facade();
		ParseOutcome<?> outcome = facade.parse("skip && 3;");
		assertFalse(outcome.isSuccess());
	}

	@Test
	public void testBreakNotWellformed() {
		IFacadePart3 facade = new Facade();
		ParseOutcome<?> outcome = facade.parse("double d := 1.0; break;");
		assumeTrue(outcome.isSuccess());
		assertFalse(facade.isWellFormed((Program) outcome.getResult()));
	}
	
	@Test
	public void testWellformed() {
		IFacadePart3 facade = new Facade();
		ParseOutcome<?> outcome = facade.parse("double d := 1.0; while d < 3 do if random 2 <= 1 then break; fi done");
		assumeTrue(outcome.isSuccess());
		assertTrue(facade.isWellFormed((Program) outcome.getResult()));
	}
}
