package jumpingalien.tests.util;

import jumpingalien.util.Sprite;

public class TestUtils {

	/**
	 * Convenience method to convert the given list of double arguments to an
	 * array.
	 * 
	 * <p>
	 * Avoids repeatedly writing
	 * <code>new double[] { value1, value2, ..., valueN }</code>
	 */
	public static double[] doubleArray(double... values) {
		return values;
	}

	/**
	 * Convenience method to convert the given list of int arguments to an
	 * array.
	 * 
	 * <p>
	 * Avoids repeatedly writing
	 * <code>new int[] { value1, value2, ..., valueN }</code>
	 */
	public static int[] intArray(int... values) {
		return values;
	}

	/**
	 * Convenience method to convert a nested array to String.
	 * 
	 * The result can be used as a literal to initialize a nested array in Java
	 * code.
	 */
	public static String nestedArrayToString(int[][] array) {
		StringBuilder result = new StringBuilder("{ ");
		for (int i = 0; i < array.length; i++) {
			if (i > 0) {
				result.append(", ");
			}
			result.append("{");
			for (int j = 0; j < array[i].length; j++) {
				if (j > 0) {
					result.append(", ");
				}
				result.append(array[i][j]);
			}
			result.append("}");
		}
		result.append(" }");
		return result.toString();
	}

	/**
	 * Convenience method to create a sprite array with 30 sprites. All sprites
	 * in the array are distinct objects with the given size.
	 */
	public static Sprite[] spriteArrayForSize(int width, int height) {
		return spriteArrayForSize(width, height, 30);
	}

	/**
	 * Convenience method to create a sprite array with a given number of
	 * sprites. All sprites in the array are distinct objects with the given
	 * size.
	 */
	public static Sprite[] spriteArrayForSize(int width, int height, int n) {
		Sprite[] result = new Sprite[n];
		for (int i = 0; i < result.length; i++) {
			result[i] = new Sprite(String.format("Sprite %d", i), width, height);
		}
		return result;
	}
}
