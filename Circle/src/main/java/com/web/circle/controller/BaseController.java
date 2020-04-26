package com.web.circle.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.web.circle.model.entity.Users;
import com.web.circle.repository.BranchRepository;
import com.web.circle.repository.DepartmentRepository;
import com.web.circle.repository.OrganizationRepository;
import com.web.circle.repository.PersonRepository;
import com.web.circle.repository.UserRepository;
import com.web.circle.utils.Utils;

import lombok.extern.slf4j.Slf4j;


/**
 * Base controller
 * @since April 2020
 * */
@Slf4j
public class BaseController {
	
	// Initialize user repository
	public UserRepository userRepository;
	public PersonRepository personRepository;
	public OrganizationRepository organizationRepository;
	public DepartmentRepository departmentRepository;
	public BranchRepository branchRepository;
	public BaseController(UserRepository userRepository,
			PersonRepository personRepository, OrganizationRepository organizationRepository,
			DepartmentRepository departmentRepository, BranchRepository branchRepository) {
		this.userRepository = userRepository;
		this.personRepository = personRepository;
		this.organizationRepository = organizationRepository;
		this.departmentRepository = departmentRepository;
		this.branchRepository = branchRepository;
	}

	/**
	 * Get current logged user data 
	 * 
	 * @param strCaller The name method.
	 * */
	public Users getCurrentLoggedUser(String strCaller) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Users user = userRepository.findByUsername(auth.getName());
		log.info(strCaller + " [ID: "+ user.getUserId() + "], [Date: "+ Utils.timestampToDate(System.currentTimeMillis()) + "]");        
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
