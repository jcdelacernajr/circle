package com.web.circle.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	
	/**
	 * Convert Date to string format
	 * Set the date format
	 * 
	 * @param date
	 * */
	public String dateToString(Date date) {
		DateFormat dFormat = new SimpleDateFormat("yyyy-mm-dd"); 
		return dFormat.format(date);
	}
	
	/**
	 * Convert String to date format
	 * @throws ParseException 
	 * 
	 * */
	public Date stringToDate(String strDate) throws ParseException {
		Date date = new SimpleDateFormat("yyyy-mm-dd").parse(strDate);
		return date;
	}
}
