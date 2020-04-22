package com.web.circle.controller;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web.circle.controller.DTO.form.AccountSetupForm;
import com.web.circle.exception.FileStorageException;
import com.web.circle.model.FileMetaDataModel;
import com.web.circle.model.OrganizationDataModel;
import com.web.circle.model.entity.Organizations;
import com.web.circle.model.entity.Person;
import com.web.circle.model.entity.Users;
import com.web.circle.repository.OrganizationRepository;
import com.web.circle.repository.PersonRepository;
import com.web.circle.repository.UserRepository;
import com.web.circle.service.AccountSetupService;
import com.web.circle.service.FileStorageService;

import lombok.extern.slf4j.Slf4j;

/**
 * Account setup controller
 * 
 * @author Juanito C. Dela Dela Cerna Jr. April 2020
 * 
 * -------------------------------------------------------------------------------
 * @see https://www.javadevjournal.com/spring/spring-file-upload/
 * @see https://developer.snapappointments.com/bootstrap-select/examples/#styling
 * @see https://formden.com/blog/date-picker
 * 
 */
@Slf4j
@Controller
public class AccountSetupController extends BaseContoller {

	// Initialize repository.
	public AccountSetupController(UserRepository userRepository,
			PersonRepository personRepository, OrganizationRepository organizationRepository) {
		super(userRepository, personRepository, organizationRepository);
	}

	@Autowired
	FileStorageService fileStorageService;
	@Autowired
	AccountSetupService accountSetupService;
	
	/**
     * Controller to display the file upload form on the front end.
     * @param model
     * @return
     */
    @GetMapping("/account-setup")
    public String accountSetup(final Model model) {
    	log.info("loadAccountSetup() :" + System.currentTimeMillis());
		try {
	    	// Get current logged user.
	    	Users user = getCurrentLoggedUser();
	    	// Get person photo 
	    	String fileName = user.getPerson().getUploadFile().getFileName();
	    	// User profile picture.
	    	model.addAttribute("photoUrl", fileDownloadUrl(fileName, "/media/download/"));
	    	
	    	// List of organization
	    	List<Organizations> organizations = organizationRepository.findAll();
	    	ArrayList<Object> organizationsList = new ArrayList<Object>();
	    	for(Organizations orga : organizations) {
	    		// Display the selected organization
	    		OrganizationDataModel organizationDataModel = new OrganizationDataModel();
	    		if(user.getOrganizations().getOrganizationId() == orga.getOrganizationId()) {
		    		organizationDataModel.setValue(orga.getOrganizationId());
		    		organizationDataModel.setSelected(true);
		    		organizationDataModel.setText(orga.getEstablishmentName());
	    		} 
	    		else { 
	    			// Display the list of organizations
	    			organizationDataModel.setValue(orga.getOrganizationId());
		    		organizationDataModel.setText(orga.getEstablishmentName());
	    		}
	    		// Add the list.
	    		organizationsList.add(organizationDataModel);
    		}
	    	// Get person data.
	    	Person person = personRepository.findById(user.getPerson().getPersonId()).get();
	    	model.addAttribute("organizations", organizationsList);
	    	model.addAttribute("firstName", person.getFirstName());
	    	model.addAttribute("middleName", person.getMiddleName());
	    	model.addAttribute("lastName", person.getLastName());
	    	model.addAttribute("extension", person.getNameExtension());
	    	model.addAttribute("citizenship", person.getCitizenship());
	    	model.addAttribute("dateOfBerth", person.getDateOfBerth());
	    	model.addAttribute("address", person.getAddress());
	    	
        	return "account_setup";
    	} catch (NullPointerException err) {
    		log.error("account-setup: No photo found! took " + System.currentTimeMillis() + " Error: "+ err.getMessage());
    	}
    	return "account_setup";
    }
    
    @PostMapping("/account-setup-form") // TODO
	public String accountSetup(@ModelAttribute AccountSetupForm form, RedirectAttributes attributes, Model model) {
		Users user = getCurrentLoggedUser();
		try {
			// Store the file data.
			Long fileId = null;
			FileMetaDataModel data = null;
			// If the user have no selected new photo
			// This function will get the previews photo.
			if(!form.getFile().isEmpty()) {
				data = fileStorageService.store(form.getFile(), user);
				fileId = data.getFileId();
				// User profile picture.
		    	model.addAttribute("photoUrl", fileDownloadUrl(data.getFileName(), "/media/download/"));
			} 
			else {
				// Get person last photo 
		    	String fileName = user.getPerson().getUploadFile().getFileName();
		    	// User profile picture.
		    	model.addAttribute("photoUrl", fileDownloadUrl(fileName, "/media/download/"));
			}
			
			// Set user id
			form.setUserId(user.getUserId());
			// Update the user data.
			accountSetupService.updateUser(form, fileId);

	    	// List of organization
	    	List<Organizations> organizations = organizationRepository.findAll();
	    	ArrayList<Object> organizationsList = new ArrayList<Object>();
	    	for(Organizations orga : organizations) {
	    		// Display the selected organization
	    		OrganizationDataModel organizationDataModel = new OrganizationDataModel();
	    		if(user.getOrganizations().getOrganizationId() == orga.getOrganizationId()) {
		    		organizationDataModel.setValue(orga.getOrganizationId());
		    		organizationDataModel.setSelected(true);
		    		organizationDataModel.setText(orga.getEstablishmentName());
	    		} 
	    		else { 
	    			// Display the list of organizations
	    			organizationDataModel.setValue(orga.getOrganizationId());
		    		organizationDataModel.setText(orga.getEstablishmentName());
	    		}
	    		// Add the list.
	    		organizationsList.add(organizationDataModel);
    		}
	    	// Get person data.
	    	Person person = personRepository.findById(user.getPerson().getPersonId()).get();
	    	model.addAttribute("organizations", organizationsList);
	    	model.addAttribute("firstName", person.getFirstName());
	    	model.addAttribute("middleName", person.getMiddleName());
	    	model.addAttribute("lastName", person.getLastName());
	    	model.addAttribute("extension", person.getNameExtension());
	    	model.addAttribute("citizenship", person.getCitizenship());
	    	model.addAttribute("dateOfBerth", dateToString(person.getDateOfBerth()));
	    	model.addAttribute("address", person.getAddress());
	    	model.addAttribute("message","You have successfully update your personal information.");
			
		} catch (FileStorageException err) {
			model.addAttribute("error", "Unable to store the file");
			return "account_setup";
		}
		return "account_setup";
	}
	
	 /**
     * Controller to allow customer to download the file by passing the file name as the
     * request URL.
     * @param fileName
     * @param response
     * @return
     * @throws FileNotFoundException
     */
    @GetMapping("/media/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFIle(@PathVariable String fileName, final HttpServletResponse response) throws FileNotFoundException {
        FileMetaDataModel fileData = fileStorageService.getFile(fileName);
        response.setContentType(fileData.getMime());
       return  ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + fileName + "\"").body(fileData.getResource());
    }
}
