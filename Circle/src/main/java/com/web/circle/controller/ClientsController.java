package com.web.circle.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web.circle.controller.DTO.form.OrganizationSetupForm;
import com.web.circle.exception.FileStorageException;
import com.web.circle.model.BranchTableDataModel;
import com.web.circle.model.ClientTableDataModel;
import com.web.circle.model.FileMetaDataModel;
import com.web.circle.model.OrganizationDataModel;
import com.web.circle.model.entity.Branch;
import com.web.circle.model.entity.Organizations;
import com.web.circle.model.entity.Users;
import com.web.circle.repository.BranchRepository;
import com.web.circle.repository.DepartmentRepository;
import com.web.circle.repository.OrganizationRepository;
import com.web.circle.repository.PersonRepository;
import com.web.circle.repository.UserRepository;
import com.web.circle.service.BranchService;
import com.web.circle.service.FileStorageService;
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
	@Autowired
	BranchService branchService;
	@Autowired
	FileStorageService fileStorageService;

	/**
	 * Display the list of organizations
	 * on client page.
	 * */
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
    		// Logo url.
    		clientTableDataModel.setLogoUrl(fileDownloadUrl(orga.getUploadFile().getFileName(), "/media/download/"));
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
	
	/**
	 * Display the organization data and the list of branches.
	 * 
	 * @param organizationId
	 * */
	@RequestMapping("page-sidebar")
	public String pageSideBar(Model model, @RequestParam(value="organizationId") long organizationId) {
		// Organization data.
		Organizations or = organizationRepository.findById(organizationId).get();
		model.addAttribute("organizationId", or.getOrganizationId());		
		model.addAttribute("establishmentName", or.getEstablishmentName());
		model.addAttribute("address", or.getAddress());
		model.addAttribute("email", or.getEmail());
		model.addAttribute("postalCode", or.getPostalCode());
		model.addAttribute("telephone", or.getTelephoneNo());
		model.addAttribute("website", or.getWebsite());
		model.addAttribute("company_heading", or.getCompanyHeading());
		model.addAttribute("founded",or.getFounded());
		model.addAttribute("executive",or.getExecutive());
		model.addAttribute("annualBudget",or.getAnnualBudget());
		model.addAttribute("predecessor",or.getPredecessor());
		model.addAttribute("type",or.getType());
		model.addAttribute("jurisdiction",or.getJurisdiction());
		model.addAttribute("dissolved",or.getDissolved());
		model.addAttribute("circle_key",or.getLicense().getCircleKey());
		// List of branch
		model.addAttribute("branchList", branchService.branchList(organizationId));
		return "clients/page_sidebar :: page-sidebar";
	}
	
	/**
	 * Add client page.
	 * 
	 * */
	@RequestMapping("add-client-page")
	public String addClientForm(Model model) {
		return "clients/add_client_page :: add-client-page";
	}
	
	/**
	 * Organization form
	 * 
	 * TODO...
	 * https://riptutorial.com/thymeleaf/example/21967/ajax-form-submition-with-jquery
	 * 
	 * */
	@PostMapping("/submit-organization-form")
	@ResponseBody
	public ResponseEntity<String> organizationSetupForm(@ModelAttribute OrganizationSetupForm form) {
		Users user = getCurrentLoggedUser("organizationSetupForm(");
		try {
			
			// Store the file data.
			Long fileId = null;
			FileMetaDataModel data = null;
			if(!form.getFile().isEmpty()) {
				data = fileStorageService.store(form.getFile(), user);
				fileId = data.getFileId();
				form.setFileId(fileId);
			} 
			
			// Set user id
			form.setUserId(user.getUserId());
			// Record the data.
			organizationsService.record(form);
			
		} catch (FileStorageException err) {
			JSONObject obj = new JSONObject();
			obj.put("status", 0);
			obj.put("message", "Storing file error");
			return new ResponseEntity<>(obj.toString(), HttpStatus.BAD_REQUEST);
		}
		
		JSONObject obj = new JSONObject();
		obj.put("status", 1);
		obj.put("message", "success");
		return new ResponseEntity<>(obj.toString(), HttpStatus.OK);
	}
}








