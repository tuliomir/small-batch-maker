/**
 * 
 */
package com.tuliomir.smallbatchmaker;

import java.util.Random;

/**
 * @author tuliomiranda
 *
 */
public class Util {
	
	private static Random randomGenerator;

	/**
	 * Returns a pseudo-random number between min and max, inclusive.
	 * The difference between min and max can be at most
	 * <code>Integer.MAX_VALUE - 1</code>.
	 *
	 * @param min Minimum value
	 * @param max Maximum value.  Must be greater than min.
	 * @return Integer between min and max, inclusive.
	 * @see java.util.Random#nextInt(int)
	 */
	public static int randomInt(int min, int max) {

	    randomGenerator = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = randomGenerator.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	public static void println(String string) {
		System.out.println(string);
	}
}
