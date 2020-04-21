package com.web.circle.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.web.circle.model.entity.Users;
import com.web.circle.repository.OrganizationRepository;
import com.web.circle.repository.PersonRepository;
import com.web.circle.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;


/**
 * Base controller
 * @since April 2020
 * */
@Slf4j
public class BaseContoller {
	
	// Initialize user repository
	public UserRepository userRepository;
	public PersonRepository personRepository;
	public OrganizationRepository organizationRepository;
	public BaseContoller(UserRepository userRepository,
			PersonRepository personRepository, OrganizationRepository organizationRepository) {
		this.userRepository = userRepository;
		this.personRepository = personRepository;
		this.organizationRepository = organizationRepository;
	}

	/**
	 * Get current logged user data 
	 * */
	public Users getCurrentLoggedUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Users user = userRepository.findByUsername(auth.getName());
		log.info("getCurrentLoggedUser() ID: "+ user.getUserId());
		return user;
	}
	
	
	/**
	 * For file download
	 * 
	 * @param fileName
	 * @param baseURL
	 * */
	public String fileDownloadUrl(final String fileName, final String baseURL){
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(baseURL).path(fileName).toUriString();
    }
}
