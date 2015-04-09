package jumpingalien.model;

/**
 * @author Rugen en Menno
 *
 * An enum representing the kind of overlap for an object.
 * This overlap works for both the x and y directions.
 */
public enum OverlapDirection {
	
	/**
	 * No overlap.
	 */
	NONE,
	
	/**
	 * An overlap on the lowest part.
	 */
	LOW,
	
	/**
	 * An overlap between the edges.
	 */
	MIDDLE,
	
	/**
	 * An overlap on the highest part.
	 */
	HIGH,
	
	/**
	 * A full overlap.
	 */
	FULL;
}
