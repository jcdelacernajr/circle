package com.web.circle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.circle.repository.BranchRepository;
import com.web.circle.repository.DepartmentRepository;
import com.web.circle.repository.OrganizationRepository;
import com.web.circle.repository.PersonRepository;
import com.web.circle.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * Branch controller
 * 
 * @author Juanito C. Dela Dela Cerna Jr. April 2020
 */
@Slf4j
@Controller
@RequestMapping("apps/branch")
public class BranchController extends BaseController {
	
	public BranchController(UserRepository userRepository, PersonRepository personRepository,
			OrganizationRepository organizationRepository, DepartmentRepository departmentRepository,
			BranchRepository branchRepository) {
		super(userRepository, personRepository, organizationRepository, departmentRepository, branchRepository);
	}

	/**
	 * Display the list of organizations
	 * on client page.
	 * */
	@GetMapping("index")
    public String index(Model model) {
		
		return "apps/branch/index";
	}
}
