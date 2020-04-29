package com.web.circle.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.circle.model.ClientTableDataModel;
import com.web.circle.model.OrganizationDataModel;
import com.web.circle.model.entity.Organizations;
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
    public String index(Model model) {
		// List of organization
    	List<Organizations> organizations = organizationRepository.findAll();
    	ArrayList<Object> organizationsList = new ArrayList<Object>();
    	for(Organizations orga : organizations) {
    		// Organization
    		ClientTableDataModel clientTableDataModel = new ClientTableDataModel();
    		
			// Get the list of organizations
    		clientTableDataModel.setOrganizationId(orga.getOrganizationId());
    		clientTableDataModel.setEstablishmentName(orga.getEstablishmentName());
    		
    		// Add the list.
    		organizationsList.add(clientTableDataModel);
		}
    	model.addAttribute("organizations", organizationsList);
		
        return "clients/index";
    }
	
}
