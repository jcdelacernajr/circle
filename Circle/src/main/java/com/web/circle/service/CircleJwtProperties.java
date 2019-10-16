package com.web.circle.service;

/**
 * 
 * @author jr
 * */
public class CircleJwtProperties {

	public static final String SECRETE = "Circle";
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final int EXPIRATION_TIME = 864_000_000; // 10 days
}
