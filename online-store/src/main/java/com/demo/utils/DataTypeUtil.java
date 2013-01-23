package com.demo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DataTypeUtil {

	public static boolean isInteger(String value) {
		try {
		    Integer.parseInt(value);
		    return true;
		}
		catch(NumberFormatException nFE) {
		    return false;
		}
	}
	
	public static boolean isDouble(String value) {
		try {
		    Double.parseDouble(value);
		    return true;
		}
		catch(NumberFormatException nFE) {
		    return false;
		}
	}
	
	public static boolean isDatetime(String strDate, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
		try {
			formatter.parse(strDate);
			return true;
		} catch (ParseException e) {
			System.out.println("Ex");
			return false;
		}
       
	}
	
	public static void main(String[] args) {
		String strDate = "2012/12/12";
		String format = "yyyy-MM-dd";
		DataTypeUtil.isDatetime(strDate, format);
	}
	
}
