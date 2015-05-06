package jumpingalien.part3.facade;

import jumpingalien.model.Buzam;
import jumpingalien.model.Mazub;
import jumpingalien.model.Plant;
import jumpingalien.model.Program;
import jumpingalien.model.School;
import jumpingalien.model.Shark;
import jumpingalien.model.Slime;
import jumpingalien.model.World;
import jumpingalien.part1.facade.IFacade;
import jumpingalien.part2.facade.IFacadePart2;
import jumpingalien.part3.programs.ParseOutcome;
import jumpingalien.part3.programs.ProgramParser;
import jumpingalien.util.Sprite;

/**
 * Implement this interface to connect your code to the graphical user interface
 * (GUI).
 * 
 * <ul>
 * <li>For separating the code that you wrote from the code that was provided to
 * you, put <b>ALL your code in the <code>src</code> folder</b> and <b>ALL your
 * tests in the <code>tests</code> folder</b>. The code that is provided to you
 * stays in the <code>src-provided</code> folder. Only if you modify the
 * provided code, it's advised to move the modified code to the <code>src</code>
 * folder, so that your changes cannot be accidentally overwritten by an updated
 * version of the provided code.<br>
 * Note: classes that belong to some package can be spread over multiple source
 * folders. For example, two classes, one in <code>src</code> and one in
 * <code>src-provided</code>, can be declared to belong to the same package,
 * even though they are not located in the same source folder.</li>
 * 
 * <li>You should at least <b>create the following classes</b> in the package
 * <code>jumpingalien.model</code>:
 * <ul>
 * <li>a class <code>Mazub</code> for representing Mazub the alien</li>
 * <li>a class <code>World</code> for representing the game world</li>
 * <li>a class <code>Plant</code> for representing a plant</li>
 * <li>a class <code>Shark</code> for representing a shark enemy</li>
 * <li>a class <code>Slime</code> for representing a slime enemy</li>
 * <li>a class <code>School</code> for representing a slime school</li>
 * <li>a class <code>Buzam</code> for representing Buzam, the evil twin of Mazub
 * </li>
 * <li>a class <code>Program</code> for representing a program</li>
 * </ul>
 * You may, of course, add additional classes as you see fit.
 * 
 * <li>You should <b>create a class <code>Facade</code></b> that implements this
 * interface. This class should be placed <b>in the package
 * <code>jumpingalien.part2.facade</code></b>.</li>
 * 
 * <li>
 * The <b>header of that Facade class</b> should look as follows:<br>
 * <code>class Facade implements IFacadePart2 { ... }</code><br>
 * The Facade class should also implement all methods of IFacade from part 1,
 * except {@link IFacade#advanceTime(Mazub, double)}. The IFacadePart2 interface
 * already extends IFacade, so this does not need to be indicated in the header
 * explicitly.
 * 
 * Consult the <a href=
 * "http://docs.oracle.com/javase/tutorial/java/IandI/createinterface.html">
 * Java tutorial</a> for more information on interfaces, if necessary.</li>
 *
 * <li>Your <code>Facade</code> class should offer a <b>default constructor</b>.
 * </li>
 * 
 * <li><b>Each method</b> defined in the interfaces <code>IFacadePart2</code>
 * and <code>IFacade</code> must be implemented by the class <code>Facade</code>
 * , except {@link IFacade#advanceTime(Mazub, double)}. For example, the
 * implementation of <code>getLocation</code> should call one or more methods on
 * the given <code>alien</code> to retrieve its current location.</li>
 * 
 * <li>Methods in this interface are <b>only allowed to throw exceptions of type
 * <code>jumpingalien.util.ModelException</code></b> (this class is provided).
 * This exception can be thrown only if calling a method of your
 * <code>Mazub</code> class with the given parameters would violate a
 * precondition or if the method of your <code>Mazub</code> class throws an
 * exception (if so, wrap the exception in a <code>ModelException</code>).</li>
 * 
 * <li><b>ModelException should not be used anywhere outside of your Facade
 * implementation.</b></li>
 * 
 * <li>Your Facade implementation should <b>only contain trivial code</b> (for
 * example, calling a method, combining multiple return values into an array,
 * creating @Value instances, catching exceptions and wrapping it in a
 * ModelException). All non-trivial code should be placed in the other classes
 * that you create.</li>
 * 
 * <li>The rules described above and the documentation described below for each
 * method apply only to the class implementing this interface. <b>Your class for
 * representing aliens should follow the rules described in the assignment.</b></li>
 * 
 * <li><b>Do not modify the signatures</b> of the methods defined in this
 * interface.</li>
 * 
 * </ul>
 *
 */
public interface IFacadePart3 extends IFacadePart2 {

	/**
	 * Create a new instance of Buzam, at the specified location, and with the
	 * specified sprites.
	 * 
	 * @param pixelLeftX
	 *            The x location of the bottom left pixel of Buzam
	 * @param pixelBottomY
	 *            The y location of the bottom left pixel of Buzam
	 * @param sprites
	 *            The sprites to be used by Buzam (following the same structure
	 *            as the table for Mazub in the assignment).
	 */
	public Buzam createBuzam(int pixelLeftX, int pixelBottomY, Sprite[] sprites);

	/**
	 * Create a new instance of Buzam, at the specified location, and with the
	 * specified sprites.
	 * 
	 * @param pixelLeftX
	 *            The x location of the bottom left pixel of Buzam
	 * @param pixelBottomY
	 *            The y location of the bottom left pixel of Buzam
	 * @param sprites
	 *            The sprites to be used by Buzam (following the same structure
	 *            as the table for Mazub in the assignment).
	 * @param program
	 *            The program to execute, or null of Buzam should not execute a
	 *            program.
	 */
	public Buzam createBuzamWithProgram(int pixelLeftX, int pixelBottomY,
			Sprite[] sprites, Program program);

	/**
	 * /**
	 * Creates a new plant, located at the provided pixel location (x, y).
	 * The returned plant should not belong to a world.
	 * 
	 * @param x
	 *            The x-coordinate of the plant's initial position
	 * @param y
	 *            The y-coordinate of the plant's initial position
	 * @param sprites
	 *            An array of sprites for the new plant
	 * @param program
	 *            The program that this plant should execute, or null if the
	 *            plant should follow its default behavior.
	 * 
	 * @return A new plant, located at the provided location. The returned plant
	 *         should not belong to a world.
	 */
	public Plant createPlantWithProgram(int x, int y, Sprite[] sprites,
			Program program);

	/**
	 * Creates a new shark, located at the provided pixel location (x, y).
	 * The returned shark should not belong to a world.
	 * 
	 * @param x
	 *            The x-coordinate of the shark's initial position
	 * @param y
	 *            The y-coordinate of the shark's initial position
	 * @param sprites
	 *            An array of sprites for the new shark
	 * @param program
	 *            The program that this shark should execute, or null if the
	 *            shark should follow its default behavior.
	 * 
	 * @return A new shark, located at the provided location. The returned shark
	 *         should not belong to a world.
	 */
	public Shark createSharkWithProgram(int x, int y, Sprite[] sprites,
			Program program);

	/**
	 * Creates a new slime, located at the provided pixel location (x, y).
	 * The returned slime should not belong to a world.
	 * 
	 * @param x
	 *            The x-coordinate of the slime's initial position
	 * @param y
	 *            The y-coordinate of the slime's initial position
	 * @param sprites
	 *            An array of sprites for the new slime
	 * @param school
	 *            The initial school to which the new slime belongs
	 * @param program
	 *            The program that this slime should execute, or null if the
	 *            slime should follow its default behavior.
	 * 
	 * @return A new slime, located at the provided location and part of the
	 *         given school. The returned slime should not belong to a world.
	 */
	public Slime createSlimeWithProgram(int x, int y, Sprite[] sprites,
			School school, Program program);

	/**
	 * Parse the given text into the program.
	 * Follow the instructions given in {@link ProgramParser}.
	 * 
	 * @param text
	 *            The text to parse
	 * 
	 * @return A {@link ParseOutcome}, namely
	 *         ParseOutcome.Success if parsing was successful, or
	 *         ParseOutcome.Failure if parsing was not successful.
	 */
	ParseOutcome<?> parse(String text);

	/**
	 * Returns whether the given program is well-formed according to the rules
	 * in the assignment.
	 * 
	 * @param program
	 *            The program to check.
	 * @return true if the program is well-formed; false otherwise.
	 */
	boolean isWellFormed(Program program);

	/**
	 * Add the given Buzam to the given world.
	 * 
	 * @param world
	 *            The world to which Buzam should be added.
	 * @param plant
	 *            The Buzam object that needs to be added to the world.
	 */
	public void addBuzam(World world, Buzam buzam);

	/**
	 * Returns the current location of the given Buzam object.
	 * 
	 * @param alien
	 *            The alien of which to find the location
	 * @return An array, consisting of 2 integers {x, y}, that represents the
	 *         coordinates of the given alien's bottom left pixel in the world.
	 */
	public int[] getLocation(Buzam alien);

	/**
	 * Return the current velocity (in m/s) of the given alien.
	 * 
	 * @param alien
	 *            The alien of which to get the velocity.
	 * 
	 * @return an array, consisting of 2 doubles {vx, vy}, that represents the
	 *         horizontal and vertical components of the given alien's current
	 *         velocity, in units of m/s.
	 */
	public double[] getVelocity(Buzam alien);

	/**
	 * Return the current acceleration (in m/s^2) of the given alien.
	 * 
	 * @param alien
	 *            The alien of which to get the acceleration.
	 * 
	 * @return an array, consisting of 2 doubles {ax, ay}, that represents the
	 *         horizontal and vertical components of the given alien's current
	 *         acceleration, in units of m/s^2.
	 */
	public double[] getAcceleration(Buzam alien);

	/**
	 * Return the current size of the given alien.
	 * 
	 * @param alien
	 *            The alien of which to get the size.
	 * 
	 * @return An array, consisting of 2 integers {w, h}, that represents the
	 *         current width and height of the given alien, in number of pixels.
	 */
	public int[] getSize(Buzam alien);

	/**
	 * Return the current sprite image for the given alien.
	 * 
	 * @param alien
	 *            The alien for which to get the current sprite image.
	 * 
	 * @return The current sprite image for the given alien. Buzam, the evil
	 *         twin of Mazub, follows the same rules as Mazub with respect to
	 *         its sprites.
	 */
	public Sprite getCurrentSprite(Buzam alien);

	/**
	 * Returns the current number of hitpoints of the given alien.
	 */
	public int getNbHitPoints(Buzam alien);
}
