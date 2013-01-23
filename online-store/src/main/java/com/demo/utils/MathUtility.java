package com.demo.utils;

public class MathUtility {

	public static double Round(double number, int decimalPlaces) {
		double modifier = Math.pow(10.0, decimalPlaces);
		return Math.round(number * modifier) / modifier;
	}
}
