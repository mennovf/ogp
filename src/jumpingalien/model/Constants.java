package jumpingalien.model;

public class Constants {
	
	/**
	 * The maximum time allowed between two frames.
	 */
	public final static double maxTimeInterval = 0.2;
	
	/**
	 * The amount of time it takes for a dead game object to be removed from the world.
	 */
	public final static double deathTime = 0.6;
	
	/**
	 * The vertical acceleration due to gravity.
	 */
	public final static double gravityAcceleration = -10.0;
	
	/**
	 * The size of the screen in pixels.
	 */
	public final static Vector<Double> screenSize = new Vector<>(1024.0, 768.0);
	
	/**
	 * The amount of meters in one pixel.
	 */
	public final static double metersPerPixel = 0.01;
	
	
	
	// MAZUB
	
	/**
	 * The time interval after enemy damage in which Mazub will
	 * not take additional enemy damage.
	 */
	public static double mazubEnemyDamageInterval = 0.6;
	
	/**
	 * The time interval after terrain damage in which Mazub will
	 * not take additional enemy damage.
	 */
	public static double mazubTerrainDamageInterval = 0.2;
	
	/**
	 * The amount of health Mazub gains when eating a plant.
	 */
	public final static int mazubPlantHealthGain = 50;
	
	/**
	 * The amount of health Mazub loses when colliding with an enemy.
	 */
	public final static int mazubEnemyDamage = -50;
	
	/**
	 * The amount of health Mazub loses when colliding with WATER terrain.
	 */
	public final static int mazubWaterDamage = -2;
	
	/**
	 * The amount of health Mazub loses when colliding with MAGMA terrain.
	 */
	public final static int mazubMagmaDamage = -50;
	
	
	
	// PLANT
	
	/**
	 * The length of one movement interval of a plant.
	 */
	public final static double plantMoveTime = 0.5;
	
	/**
	 * The horizontal speed of a plant.
	 */
	public final static double plantSpeed = 0.5;
	
	
	
	// SLIME
	
	/**
	 * The horizontal acceleration of a slime.
	 */
	public final static double slimeHorizontalAcceleration = 0.7;
	
	/**
	 * The maximum horizontal speed of a slime.
	 */
	public final static double slimeMaxHorizontalSpeed = 2.5;
	
	/**
	 * The minimum duration of a slime's movement period.
	 */
	public final static double slimeMinMoveTime = 2.0;
	
	/**
	 * The maximum duration of a slime's movement period.
	 */
	public final static double slimeMaxMoveTime = 6.0;
	
	/**
	 * The amount of damage a slime receives on contact with an enemy (Mazub or Shark).
	 */
	public final static int slimeEnemyContactDamage = 50;
	
	
	
	// SHARK
	
	/**
	 * The maximum amount of health a shark can have.
	 */
	public final static int sharkMaxHealth = 100;
	
	/**
	 * The amount of health a shark has when it gets initialised.
	 */
	public final static int sharkBeginHealth = 100;
	
	/**
	 * The horizontal acceleration of a shark.
	 */
	public final static double sharkHorizontalAcceleration = 1.5;
	
	/**
	 * The maximum horizontal speed of a shark.
	 */
	public final static double sharkMaxHorizontalSpeed = 4.0;
	
	/**
	 * The initial vertical jump speed of a shark.
	 */
	public final static double sharkInitialJumpSpeed = 2.0;
	
	/**
	 * The vertical acceleration of a shark for swimming up or down.
	 */
	public final static double sharkVerticalAcceleration = 0.2;
	
	/**
	 * The minimum duration of a shark's movement interval.
	 */
	public final static double sharkMinMoveTime = 1.0;
	
	/**
	 * The maximum duration of a shark's movement interval.
	 */
	public final static double sharkMaxMoveTime = 4.0;
	
	/**
	 * The amount of health a shark loses when colliding with an enemy.
	 */
	public final static int sharkEnemyDamage = -50;
}
