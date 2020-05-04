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
	 * Convert string to Hex
	 * 
	 * @param value
	 * @return hex
	 * */
	public static String stringToHexadecimal(String value) {
		String hex = "";
		for(int i=0;i<value.length(); i++) {
			char ch = value.charAt(i);
			int in = (int)ch;
			String part = Integer.toHexString(in);
			hex += part;
		}
		return hex + ":" + UUID.randomUUID();
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
