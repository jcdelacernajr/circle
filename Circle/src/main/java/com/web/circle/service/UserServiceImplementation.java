package com.web.circle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.web.circle.controller.DTO.UserSignupDTO;
import com.web.circle.model.entity.Designations;
import com.web.circle.model.entity.Organizations;
import com.web.circle.model.entity.Permissions;
import com.web.circle.model.entity.Person;
import com.web.circle.model.entity.Roles;
import com.web.circle.model.entity.UserRoles;
import com.web.circle.model.entity.Users;
import com.web.circle.repository.UserRepository;
import com.web.circle.repository.UserRoleRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * Used by user signup controller
 * 
 * @author Juanito C. Dela Dela Cerna Jr. March 2020
 */
@Slf4j
@Service
public class UserServiceImplementation implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserRoleRepository UserRoleRepository;
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
		user.setEmail(userSignupDTO.getEmail());
		user.setUsername(userSignupDTO.getEmail());
		user.setPassword(encoder.encode(userSignupDTO.getPassword()));
		
		// Set to 1 for the default value. 1 is No Organizations selected.
		Organizations organizations = new Organizations();
		organizations.setOrganizationId(1); // 1 is the default value for the register user.
		
		user.setIsActive(true);
		user.setOrganizations(organizations); 
		
		// Set person id for initial data
		Person person = new Person();
		person.setPersonId(0);
		user.setPerson(person);
		
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		
		// Set user roles table.
		UserRoles userRoles = new UserRoles();
		userRoles.setIsActive(true);
		
		// Set to 3 the default data for user type designation
		Designations designations = new Designations();
		designations.setDesignationId(3);
		
		userRoles.setDesignations(designations);
		// Set to 1 for the default value.
		Permissions permission = new Permissions();
		permission.setPermissionId(1);
		
		userRoles.setPermissions(permission);
		// Set role data.
		Roles role = new Roles();
		// Set to 3 the default data for user type role.
		role.setRoleId(3);
		userRoles.setRoles(role);
		
		// Newly added user id.
		user.setUserId(0);
		userRoles.setUsers(user);
		
		// Record the user roles data into the table.
		UserRoleRepository.save(userRoles);
		// Return
        return userRepository.save(user);
	}

}
