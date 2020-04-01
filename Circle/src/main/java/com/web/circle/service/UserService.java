package com.web.circle.service;

import com.web.circle.model.entity.Users;

/**
 * @author Juanito C. Dela Dela Cerna Jr. March 2020
 */
public interface UserService {
	void save(Users users);
	Users findByUsername(String username);
}
