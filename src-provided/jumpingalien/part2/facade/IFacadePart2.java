package jumpingalien.part2.facade;

import java.util.Collection;

import jumpingalien.model.Plant;
import jumpingalien.model.Shark;
import jumpingalien.model.Mazub;
import jumpingalien.model.Slime;
import jumpingalien.model.School;
import jumpingalien.model.World;
import jumpingalien.part1.facade.IFacade;
import jumpingalien.util.ModelException;
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
public interface IFacadePart2 extends jumpingalien.part1.facade.IFacade {

	/**
	 * Returns the current number of hitpoints of the given alien.
	 */
	int getNbHitPoints(Mazub alien);

	/**
	 * Create a new game world with the given parameters.
	 * 
	 * @param tileSize
	 *            Length (in pixels) of a side of each square tile in the world
	 * @param nbTilesX
	 *            Number of tiles in the horizontal direction
	 * @param nbTilesY
	 *            Number of tiles in the vertical direction
	 * @param visibleWindowWidth
	 *            Width of the visible window, in pixels
	 * @param visibleWindowHeight
	 *            Height of the visible window, in pixels
	 * @param targetTileX
	 *            Tile x-coordinate of the target tile of the created world
	 * @param targetTileY
	 *            Tile y-coordinate of the target tile of the created world
	 */
	World createWorld(int tileSize, int nbTilesX, int nbTilesY,
			int visibleWindowWidth, int visibleWindowHeight, int targetTileX,
			int targetTileY);

	/**
	 * Returns the size of the given game world, in number of pixels.
	 * 
	 * @param world
	 *            The world for which to return the size.
	 * @return The size of the game world, in pixels, as an array of two
	 *         elements: width (X) and height (Y), in that order.
	 */
	int[] getWorldSizeInPixels(World world);

	/**
	 * Returns the length of a square tile side in the given world.
	 * 
	 * @param world
	 *            The game world for which to retrieve the tile length
	 * 
	 * @return The length of a square tile side, expressed as a number of
	 *         pixels.
	 */
	int getTileLength(World world);

	/**
	 * Starts the game that is played in the given world.
	 * After this method has been invoked, no further game objects will be added
	 * via {@link IFacadePart2#addPlant(World, Plant)},
	 * {@link IFacadePart2#addShark(World, Shark)},
	 * {@link IFacadePart2#addSlime(World, Slime)}, or
	 * {@link IFacadePart2#setMazub(World, Mazub)}), and no geological features
	 * will be changed via
	 * {@link IFacadePart2#setGeologicalFeature(World, int, int, int)}.
	 * 
	 * @param The
	 *            world for which to start the game.
	 */
	void startGame(World world);

	/**
	 * Returns whether the game, played in the given game world, is over.
	 * The game is over when Mazub has died, or has reached the target tile.
	 * 
	 * @param world
	 *            The world for which to check whether the game is over
	 * @return true if the game is over, false otherwise.
	 */
	boolean isGameOver(World world);

	/**
	 * Returns whether the game played in the given world has finished and the
	 * player has won. The player wins when Mazub has reached the target tile.
	 * 
	 * @param world
	 *            The world for which to check whether the player won
	 * @return true if the game is over and the player has won; false otherwise.
	 */
	boolean didPlayerWin(World world);

	/**
	 * Advance the time for the world and all its objects by the given amount.
	 * 
	 * This method replaces {@link IFacadePart2#advanceTime(Mazub, double)}.
	 * 
	 * @param world
	 *            The world whose time needs to advance
	 * @param dt
	 *            The time interval (in seconds) by which to advance the given
	 *            world's time.
	 */
	void advanceTime(World world, double dt);

	/**
	 * @deprecated This method no longer needs to be implemented in your facade.
	 *             It has been replaced by
	 *             {@link IFacadePart2#advanceTime(World, double)}.
	 */
	@Override
	@Deprecated
	default public void advanceTime(Mazub alien, double dt) {
		throw new IllegalStateException(
				"This method should no longer be implemented or called");
	};

	/**
	 * Return the coordinates of the rectangular visible window that moves
	 * together with Mazub.
	 * 
	 * @return The pixel coordinates of the visible window, in the order
	 *         <b>left, bottom, right, top</b>.
	 */
	int[] getVisibleWindow(World world);

	/**
	 * Returns the bottom left pixel coordinate of the tile at the given tile
	 * position.
	 * 
	 * @param world
	 *            The world from which to retrieve the tile.
	 * @param tileX
	 *            The x-position x_T of the tile
	 * @param tileY
	 *            The y-position y_T of the tile
	 * @return An array which contains the x-coordinate and y-coordinate of the
	 *         bottom left pixel of the given tile, in that order.
	 */
	int[] getBottomLeftPixelOfTile(World world, int tileX, int tileY);

	/**
	 * Returns the tile positions of all tiles within the given rectangular
	 * region.
	 * 
	 * @param world
	 *            The world from which the tile positions should be returned.
	 * @param pixelLeft
	 *            The x-coordinate of the left side of the rectangular region.
	 * @param pixelBottom
	 *            The y-coordinate of the bottom side of the rectangular region.
	 * @param pixelRight
	 *            The x-coordinate of the right side of the rectangular region.
	 * @param pixelTop
	 *            The y-coordinate of the top side of the rectangular region.
	 * 
	 * @return An array of tile positions, where each position (x_T, y_T) is
	 *         represented as an array of 2 elements, containing the horizontal
	 *         (x_T) and vertical (y_T) coordinate of a tile in that order.
	 *         The returned array is ordered from left to right,
	 *         bottom to top: all positions of the bottom row (ordered from
	 *         small to large x_T) precede the positions of the row above that.
	 * 
	 */
	int[][] getTilePositionsIn(World world, int pixelLeft, int pixelBottom,
			int pixelRight, int pixelTop);

	/**
	 * Returns the geological feature of the tile with its bottom left pixel at
	 * the given position.
	 * 
	 * @param world
	 *            The world containing the tile for which the
	 *            geological feature should be returned.
	 * 
	 * @param pixelX
	 *            The x-position of the pixel at the bottom left of the tile for
	 *            which the geological feature should be returned.
	 * @param pixelY
	 *            The y-position of the pixel at the bottom left of the tile for
	 *            which the geological feature should be returned.
	 * 
	 * @return The type of the tile with the given bottom left pixel position,
	 *         where
	 *         <ul>
	 *         <li>the value 0 is returned for an <b>air</b> tile;</li>
	 *         <li>the value 1 is returned for a <b>solid ground</b> tile;</li>
	 *         <li>the value 2 is returned for a <b>water</b> tile;</li>
	 *         <li>the value 3 is returned for a <b>magma</b> tile.</li>
	 *         </ul>
	 * 
	 * @note This method must return its result in constant time.
	 * 
	 * @throw ModelException if the given position does not correspond to the
	 *        bottom left pixel of a tile.
	 */
	int getGeologicalFeature(World world, int pixelX, int pixelY)
			throws ModelException;

	/**
	 * Modify the geological type of a specific tile in the given world to a
	 * given type.
	 * 
	 * @param world
	 *            The world in which the geological type of a tile needs to be
	 *            modified
	 * @param tileX
	 *            The x-position x_T of the tile for which the type needs to be
	 *            modified
	 * @param tileY
	 *            The y-position y_T of the tile for which the type needs to be
	 *            modified
	 * @param tileType
	 *            The new type for the given tile, where
	 *            <ul>
	 *            <li>the value 0 is provided for an <b>air</b> tile;</li>
	 *            <li>the value 1 is provided for a <b>solid ground</b> tile;</li>
	 *            <li>the value 2 is provided for a <b>water</b> tile;</li>
	 *            <li>the value 3 is provided for a <b>magma</b> tile.</li>
	 *            </ul>
	 */
	void setGeologicalFeature(World world, int tileX, int tileY, int tileType);

	/**
	 * Sets the given alien as the player's character in the given world.
	 * 
	 * @param world
	 *            The world for which to set the player's character.
	 * @param mazub
	 *            The alien to be set as the player's character.
	 */
	void setMazub(World world, Mazub alien);

	/**
	 * Returns whether the given alien is currently immune against enemies (see
	 * section 1.2.5 of the assignment).
	 * 
	 * @param alien
	 *            The alien for which to retrieve the immunity status.
	 * @return True if the given alien is immune against other enemies (i.e.,
	 *         there are no interactions between the alien and enemy objects).
	 */
	boolean isImmune(Mazub alien);

	/**
	 * Creates a new plant, located at the provided pixel location (x, y).
	 * The returned plant should not belong to a world.
	 * 
	 * @param x
	 *            The x-coordinate of the plant's initial position
	 * @param y
	 *            The y-coordinate of the plant's initial position
	 * @param sprites
	 *            An array of sprites for the new plant
	 * 
	 * @return A new plant, located at the provided location. The returned plant
	 *         should not belong to a world.
	 */
	Plant createPlant(int x, int y, Sprite[] sprites);

	/**
	 * Add the given plant as a game object to the given world.
	 * 
	 * @param world
	 *            The world to which the plant should be added.
	 * @param plant
	 *            The plant that needs to be added to the world.
	 */
	void addPlant(World world, Plant plant);

	/**
	 * Returns all the plants currently located in the given world.
	 * 
	 * @param world
	 *            The world for which to retrieve all plants.
	 * @return All plants that are located somewhere in the given world. There
	 *         are no restrictions on the type or order of the returned
	 *         collection, but each plant may only be returned once.
	 */
	Collection<Plant> getPlants(World world);

	/**
	 * Returns the current location of the given plant.
	 * 
	 * @param plant
	 *            The plant of which to find the location
	 * @return An array, consisting of 2 integers {x, y}, that represents the
	 *         coordinates of the given plant's bottom left pixel in the world.
	 */
	int[] getLocation(Plant plant);

	/**
	 * Return the current sprite image for the given plant.
	 * 
	 * @param plant
	 *            The plant for which to get the current sprite image.
	 * 
	 * @return The current sprite image for the given plant, determined by its
	 *         orientation as defined in the assignment.
	 */
	public Sprite getCurrentSprite(Plant plant);

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
	 * 
	 * @return A new shark, located at the provided location. The returned shark
	 *         should not belong to a world.
	 */
	Shark createShark(int x, int y, Sprite[] sprites);

	/**
	 * Add the given shark as a game object to the given world.
	 * 
	 * @param world
	 *            The world to which the shark should be added.
	 * @param shark
	 *            The shark that needs to be added to the world.
	 */
	void addShark(World world, Shark shark);

	/**
	 * Returns all the sharks currently located in the given world.
	 * 
	 * @param world
	 *            The world for which to retrieve all sharks.
	 * @return All sharks that are located somewhere in the given world. There
	 *         are no restrictions on the type or order of the returned
	 *         collection, but each shark may only be returned once.
	 */
	Collection<Shark> getSharks(World world);

	/**
	 * Returns the current location of the given shark.
	 * 
	 * @param shark
	 *            The shark of which to find the location
	 * @return An array, consisting of 2 integers {x, y}, that represents the
	 *         coordinates of the given shark's bottom left pixel in the world.
	 */
	int[] getLocation(Shark shark);

	/**
	 * Return the current sprite image for the given shark.
	 * 
	 * @param shark
	 *            The shark for which to get the current sprite image.
	 * 
	 * @return The current sprite image for the given shark, determined by its
	 *         orientation as defined in the assignment.
	 */
	public Sprite getCurrentSprite(Shark shark);

	/**
	 * Creates a new slime school.
	 * 
	 * @return A new school for slimes, without any members.
	 */
	School createSchool();

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
	 * 
	 * @return A new slime, located at the provided location and part of the
	 *         given school. The returned slime should not belong to a world.
	 */
	Slime createSlime(int x, int y, Sprite[] sprites, School school);

	/**
	 * Add the given slime as a game object to the given world.
	 * 
	 * @param world
	 *            The world to which the slime should be added.
	 * @param slime
	 *            The slime that needs to be added to the world.
	 */
	void addSlime(World world, Slime slime);

	/**
	 * Returns all the slimes currently located in the given world.
	 * 
	 * @param world
	 *            The world for which to retrieve all slimes.
	 * @return All slimes that are located somewhere in the given world. There
	 *         are no restrictions on the type or order of the returned
	 *         collection, but each slime may only be returned once.
	 */
	Collection<Slime> getSlimes(World world);

	/**
	 * Returns the current location of the given slime.
	 * 
	 * @param slime
	 *            The slime of which to find the location
	 * @return An array, consisting of 2 integers {x, y}, that represents the
	 *         coordinates of the given slime's bottom left pixel in the world.
	 */
	int[] getLocation(Slime slime);

	/**
	 * Return the current sprite image for the given slime.
	 * 
	 * @param slime
	 *            The slime for which to get the current sprite image.
	 * 
	 * @return The current sprite image for the given slime, determined by its
	 *         orientation as defined in the assignment.
	 */
	public Sprite getCurrentSprite(Slime slime);

	/**
	 * Returns the current school to which the given slime belongs.
	 * 
	 * @param slime
	 *            The slime for which to retrieve the school.
	 * 
	 * @return The current school of the given slime.
	 */
	School getSchool(Slime slime);

}
