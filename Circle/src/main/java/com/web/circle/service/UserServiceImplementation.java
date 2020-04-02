package com.web.circle.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.web.circle.controller.DTO.UserSignupDTO;
import com.web.circle.model.entity.Users;
import com.web.circle.repository.UserRepo;

/**
 * @author Juanito C. Dela Dela Cerna Jr. March 2020
 */
@Service
public class UserServiceImplementation implements UserService {
	
	@Autowired
	private UserRepo userRepository;
	
	@Autowired
	private PasswordEncoder encoder;


	@Override
	public Users findByEmail(String email) { // the email of user.
		return userRepository.findByEmail(email);
	}

	@Override
	public Users save(UserSignupDTO userSignupDTO) {
		// User table
		Users user = new Users();
		user.setUpdatedAt(null);
		user.setEmail(userSignupDTO.getEmail());
		user.setUsername(userSignupDTO.getEmail());
		user.setPassword(encoder.encode(userSignupDTO.getPassword()));
        return userRepository.save(user);
	}
	
}
