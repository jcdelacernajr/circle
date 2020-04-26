package com.web.circle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.circle.repository.BranchRepository;
import com.web.circle.repository.DepartmentRepository;
import com.web.circle.repository.OrganizationRepository;
import com.web.circle.repository.PersonRepository;
import com.web.circle.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * Clients controller
 * 
 * @author Juanito C. Dela Dela Cerna Jr. April 2020
 */
@Slf4j
@Controller
@RequestMapping("clients")
public class ClientsController extends BaseController {
	
	public ClientsController(UserRepository userRepository, PersonRepository personRepository,
			OrganizationRepository organizationRepository, DepartmentRepository departmentRepository,
			BranchRepository branchRepository) {
		super(userRepository, personRepository, organizationRepository, departmentRepository, branchRepository);
	}

	@GetMapping("index")
    public String index() {
        return "clients/index";
    }
	
}
