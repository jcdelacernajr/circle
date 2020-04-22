package com.web.circle.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Utility class
 * 
 * */
public final class Utils {

	/**
	 * Convert String to date format
	 * @throws ParseException 
	 * 
	 * */
	public static Date stringToDate(String strDate) throws ParseException {
		Date date = new SimpleDateFormat("yyyy-mm-dd").parse(strDate);
		return date;
	}
	
}
