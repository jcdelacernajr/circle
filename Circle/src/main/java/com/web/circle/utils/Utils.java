package com.web.circle.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


/**
 * Utility class
 * 
 * @see https://www.javatpoint.com/java-timestamp-to-date
 * @author Juanito C. Dela Cerna Jr. April 2020
 * */
public final class Utils {
	
	/**
	 * Convert hex to string
	 * 
	 * @param
	 * @return string
	 * */
	public static String hexadecimalToString(String value) {
		StringBuilder  output = new StringBuilder("");
		for(int i = 0; i < value.length(); i += 2) {
			String str = value.substring(i, i + 2);
			output.append((char) Integer.parseInt(str, 16));
		}
		return output.toString();
	}
	
	/**
	 * Convert string to Hex
	 * 
	 * @param value
	 * @return hex
	 * */
	public static String stringToHexadecimal(String value) {
		char[] chars = value.toCharArray();
	    StringBuilder hex = new StringBuilder();
	    for (char ch : chars) {
	        hex.append(Integer.toHexString((int) ch));
	    }
	    return hex.toString();
	}

	/**
	 * Convert String to date format
	 * @throws ParseException 
	 * 
	 * */
	public static Date stringToDate(String strDate) throws ParseException {
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
		return date;
	}
	
	/**
	 * Convert Date to string format
	 * Set the date format
	 * 
	 * @param date
	 * */
	public static String dateToString(Date date) {
		DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		return dFormat.format(date);
	}
	
	/**
	 * Timestamp to date
	 * 
	 * @param timestamp
	 * */
	public static Date timestampToDate(long tstamp) {
		Timestamp timestamp = new Timestamp(tstamp);
		Date date = new Date(timestamp.getTime());
		return date;
	}
}
