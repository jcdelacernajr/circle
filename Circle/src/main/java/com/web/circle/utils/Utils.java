package com.web.circle.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Utility class
 * 
 * @see https://www.javatpoint.com/java-timestamp-to-date
 * @author Juanito C. Dela Cerna Jr. April 2020
 * */
public final class Utils {

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
