package jumpingalien.part2.tests;

import static org.junit.Assert.*;

import jumpingalien.model.Vector;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class VectorTest {
	
	
	private int intX = -23, intY = 45;
	private double doubleX = -23.5, doubleY = 45.3;
	private Vector<Integer> intVector = new Vector<>(intX, intY);
	private Vector<Double> doubleVector = new Vector<>(doubleX, doubleY);
	
	private double testEps = 1e-7;
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	
	
	@Test
	public void constructor() {
		assertEquals(intX, (int)intVector.x);
		assertEquals(intY, (int)intVector.y);
		assertEquals(doubleX, doubleVector.x, testEps);
		assertEquals(doubleY, doubleVector.y, testEps);
	}
	
	
	
	@Test
	public void setX() {
		int newIntX = 80;
		intVector = intVector.setX(newIntX);
		assertEquals(newIntX, (int)intVector.x);
		
		double newDoubleX = 80.234;
		doubleVector = doubleVector.setX(newDoubleX);
		assertEquals(newDoubleX, doubleVector.x, testEps);
	}
	
	
	
	@Test
	public void addX() {
		int addIntX = 80;
		intVector = intVector.addX(addIntX);
		assertEquals(intX + addIntX, (int)intVector.x);
		
		double addDoubleX = 80.234;
		doubleVector = doubleVector.addX(addDoubleX);
		assertEquals(doubleX + addDoubleX, doubleVector.x, testEps);
	}
	
	
	
	@Test
	public void setY() {
		int newIntY = 80;
		intVector = intVector.setY(newIntY);
		assertEquals(newIntY, (int)intVector.y);
		
		double newDoubleY = 80.234;
		doubleVector = doubleVector.setY(newDoubleY);
		assertEquals(newDoubleY, doubleVector.y, testEps);
	}
	
	
	
	@Test
	public void addY() {
		int addIntY = 80;
		intVector = intVector.addY(addIntY);
		assertEquals(intY + addIntY, (int)intVector.y);
		
		double addDoubleY = 80.234;
		doubleVector = doubleVector.addY(addDoubleY);
		assertEquals(doubleY + addDoubleY, doubleVector.y, testEps);
	}
	
	
	
	@Test
	public void add() {
		int addIntX = 40;
		int addIntY = -67;
		Vector<Integer> addIntVector = new Vector<>(addIntX, addIntY);
		intVector = Vector.add(intVector, addIntVector);
		assertEquals(intX + addIntX, (int)intVector.x);
		assertEquals(intY + addIntY, (int)intVector.y);
		
		double addDoubeX = 40.451;
		double addDoubleY = -67.9803;
		Vector<Double> addDoubleVector = new Vector<>(addDoubeX, addDoubleY);
		doubleVector = Vector.add(doubleVector, addDoubleVector);
		assertEquals(doubleX + addDoubeX, doubleVector.x, testEps);
		assertEquals(doubleY + addDoubleY, doubleVector.y, testEps);
	}
	
	
	
	@Test
	public void scale() {
		int scaleInt = 3;
		intVector = Vector.scale(intVector, scaleInt);
		assertEquals(intX * scaleInt, (int)intVector.x);
		assertEquals(intY * scaleInt, (int)intVector.y);
		
		double scaleDouble = 3;
		doubleVector = Vector.scale(doubleVector, scaleDouble);
		assertEquals(doubleX * scaleDouble, doubleVector.x, testEps);
		assertEquals(doubleY * scaleDouble, doubleVector.y, testEps);
	}
	
	
	
	@Test
	public void equals() {
		assertEquals(intVector, intVector);
		assertEquals(doubleVector, doubleVector);
		assertNotEquals(intVector, doubleVector);
	}
	
	
	
	@Test
	public void testToString() {
		assertEquals("[" + intX + ", " + intY + "]", intVector.toString());
		assertEquals("[" + doubleX + ", " + doubleY + "]", doubleVector.toString());
	}

}
