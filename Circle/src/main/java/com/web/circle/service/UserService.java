package com.web.circle.service;


import com.web.circle.controller.DTO.UserSignupDTO;
import com.web.circle.model.entity.Users;

/**
 * @author Juanito C. Dela Dela Cerna Jr. March 2020
 */
public interface UserService {
	Users findByEmail(String email);
	Users save(UserSignupDTO userSignupDTO);
}
