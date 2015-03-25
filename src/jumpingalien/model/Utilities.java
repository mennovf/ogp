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
	
	
	/**
	 * Converts the given length in meters to pixels.
	 * 
	 * @param	m
	 * 			The value to convert from meters to pixels.
	 * 
	 * @return	m converted to pixels.
	 * 
	 * @post	m has to be positive
	 * 			| m >= 0
	 */
	public static int metersToPixels(double m) {
		return (int)(Math.abs(m) / 0.01);
	}
	
	public static double pixelsToMeters(int p) {
		return Math.abs(p) * 0.01;
	}
	
	/**
	 * @param v The Vector2D to convert.
	 * @return An array of 2 elements where the first element is v.x and the second element is v.y.
	 */
	public static int[] intVectorToArray(Vector2D<Integer> v){
		int[] vs = {v.x, v.y};
		return vs;
	}

	/**
	 * @param v The Vector2D to convert.
	 * @return An array of 2 elements where the first element is v.x and the second element is v.y.
	 */
	public static double[] doubleVectorToArray(Vector2D<Double> v){
		double[] vs = {v.x, v.y};
		return vs;
	}
}
