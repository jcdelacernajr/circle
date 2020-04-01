package com.web.circle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
	public void save(Users users) {
		users.setUsername(users.getUsername());
		users.setPassword(encoder.encode(users.getPassword()));
		userRepository.save(users);
		
	}

	@Override
	public Users findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	// TODO...
	// source https://hellokoding.com/registration-and-login-example-with-spring-security-spring-boot-spring-data-jpa-hsql-jsp/
	
}
