package com.tillg.util;

public class FloatHelper {
	
	public static boolean isValidFloatStr (String str) {
		/** 
		 * Checks that the passed string represents a valid float - whatever valid means in detail...
		 */
		try {
			Float f = Float.valueOf(str);
			return true;
		} 
		catch (Exception e) {
			return false;
		}
	}

}
