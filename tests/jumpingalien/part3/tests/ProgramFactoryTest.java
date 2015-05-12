package jumpingalien.part3.tests;

import static org.junit.Assert.*;
import jumpingalien.model.gameobject.GameObject;
import jumpingalien.model.program.ProgramFactory;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ProgramFactoryTest {
	
	ProgramFactory factory;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		factory = new ProgramFactory();
	}

	@After
	public void tearDown() throws Exception {
	}

}
