package com.web.circle.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.circle.model.ClientTableDataModel;
import com.web.circle.model.OrganizationDataModel;
import com.web.circle.model.entity.Organizations;
import com.web.circle.repository.BranchRepository;
import com.web.circle.repository.DepartmentRepository;
import com.web.circle.repository.OrganizationRepository;
import com.web.circle.repository.PersonRepository;
import com.web.circle.repository.UserRepository;
import com.web.circle.service.OrganizationsService;

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
	
	@Autowired
	OrganizationsService organizationsService;

	
	@GetMapping("index")
    public String index(Model model) {
		// List of organization
    	List<Organizations> organizations = organizationRepository.getOrganizationList();
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
	
	/**
	 * OnClick table row
	 * Get the selected organization data
	 * 
	 * @param 
	 * */
	@GetMapping("/selected-organization")
	public @ResponseBody List<ClientTableDataModel> organizationData(@RequestParam(value="organizationId", required=true) long organizationId) {
		return organizationsService.organization(organizationId);
	}
}







