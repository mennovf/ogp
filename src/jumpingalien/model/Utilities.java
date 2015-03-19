package jumpingalien.model;

public class Utilities {

	/**
	 * Clips the value to the given range.
	 * @param <T>
	 * 
	 * @param min
	 * 			The minimum of the range.
	 * 
	 * @param max
	 * 			The maximum of the range.
	 * 
	 * @param value
	 * 			The value to clip.
	 * 
	 * @return	The value clipped to the given range. If it's bigger than max, max will be returned. If it's smaller than min, min will be returned.
	 * 			| if (value < min)
	 * 			|	return min
	 * 			| if (value > max)
	 * 			|	return max
	 * 			| else
	 * 			|	return value
	 * 
	 * @pre		Max should be bigger than min.
	 * 			| max > min
	 */
	public static <T extends Comparable<T>> T clipInRange(T min, T max, T value) {
		
		assert max.compareTo(min) > 0;
		
		if (value.compareTo(min) < 0) {
			return min;
		} else if (value.compareTo(max) > 0) {
			return max;
		}
		
		return value;
	}
}
