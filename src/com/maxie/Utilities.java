package com.maxie;

/**
 * Help class to provide utilities used in calculations
 * @author Maxie
 *
 */

public class Utilities {
	/**
	 * Returns the first digit in any specified number
	 * @param x query to be formatted
	 * @return first digit of query
	 */
	public int firstDigit(int x) {
		if (x == 0)
			return 0;
		x = Math.abs(x);
		return (int) Math.floor(x / Math.pow(10, Math.floor(Math.log10(x))));
	}
}
