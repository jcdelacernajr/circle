package com.web.circle.service;

/**
 * @author Juanito C. Dela Dela Cerna Jr. March 2020
 */
public interface SecurityService {

	String findLoggedInUsername();
	
	void autoLogin(String username, String password);
}
