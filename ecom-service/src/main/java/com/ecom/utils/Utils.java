package com.ecom.utils;

public class Utils {

	public static String generateRamdonNumber() {
		long randomNumber = (long) (Math.random()*Math.pow(10,10));
		return "" + randomNumber;
	}
	
}
