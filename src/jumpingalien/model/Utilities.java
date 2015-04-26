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
	 * Clips the values of the given vector to the given ranges.
	 * 
	 * @param min
	 * 			A 2D vector representing the minimum values for x and y.
	 * 
	 * @param max
	 * 			A 2D vector representing the maximum values for x and y.
	 * 
	 * @param vector
	 * 			The vector to clip.
	 * 
	 * @return The vector clipped to the given ranges.
	 */
	public static <T extends Comparable<T>> Vector<T> clipVectorInRange(Vector<T> min, Vector<T> max, Vector<T> vector) {
		return new Vector<T>(clipInRange(min.x, max.x, vector.x), clipInRange(min.y, max.y, vector.y));
	}
	
	
	/**
	 * Converts the given length in meters to pixels.
	 * 
	 * @param	m
	 * 			The value to convert from meters to pixels.
	 * 
	 * @return	m converted to pixels.
	 */
	public static int metersToPixels(double m) {
		return (int)(Math.abs(m) / Constants.metersPerPixel);
	}
	
	/**
	 * Converts the given vector in meters to pixels.
	 * 
	 * @param m
	 * 			The vector to convert from meters to pixels.
	 * 
	 * @return m converted to pixels.
	 */
	public static Vector<Integer> metersVectorToPixels(Vector<Double> m) {
		return new Vector<>(metersToPixels(m.x), metersToPixels(m.y));
	}
	
	/**
	 * Converts the given length in pixels to meters.
	 * 
	 * @param p
	 * 			The value to convert from pixels to meters.
	 * 
	 * @return p converted to meters
	 */
	public static double pixelsToMeters(int p) {
		return Math.abs(p) * Constants.metersPerPixel;
	}
	
	/**
	 * Converts the given vector in pixels to meters.
	 * 
	 * @param p
	 * 			The vector to convert from pixels to meters.
	 * 
	 * @return p converted to meters.
	 */
	public static Vector<Double> pixelsVectorToMeters(Vector<Integer> p) {
		return new Vector<>(pixelsToMeters(p.x), pixelsToMeters(p.y));
	}
	
	/**
	 * @param v The Vector to convert.
	 * @return An array of 2 elements where the first element is v.x and the second element is v.y.
	 */
	public static int[] intVectorToArray(Vector<Integer> v){
		int[] vs = {v.x, v.y};
		return vs;
	}

	/**
	 * @param v The Vector to convert.
	 * @return An array of 2 elements where the first element is v.x and the second element is v.y.
	 */
	public static double[] doubleVectorToArray(Vector<Double> v){
		double[] vs = {v.x, v.y};
		return vs;
	}
}
