package com.web.circle.controller;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.web.circle.controller.DTO.form.AccountSetupForm;
import com.web.circle.exception.FileStorageException;
import com.web.circle.model.OrganizationDataModel;
import com.web.circle.model.FileMetaDataModel;
import com.web.circle.model.entity.Organizations;
import com.web.circle.model.entity.Person;
import com.web.circle.model.entity.Users;
import com.web.circle.repository.OrganizationRepository;
import com.web.circle.repository.PersonRepository;
import com.web.circle.repository.UploadFileRepository;
import com.web.circle.repository.UserRepository;
import com.web.circle.service.FileStorageService;

import lombok.extern.slf4j.Slf4j;

/**
 * Upload file controller // TODO....
 * 
 * @see https://www.javadevjournal.com/spring/spring-file-upload/
 * @author Juanito C. Dela Dela Cerna Jr. April 2020
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
	
	/**
     * Controller to display the file upload form on the front end.
     * @param model
     * @return
     */
    @GetMapping("/account-setup")
    public String accountSetup(final Model model) {
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
	    		OrganizationDataModel organizationDataModel = new OrganizationDataModel();
	    		organizationDataModel.setValue(orga.getOrganizationId());
	    		organizationDataModel.setText(orga.getEstablishmentName());
	    		organizationsList.add(organizationDataModel);
	    	}
	    	model.addAttribute("organizations", organizationsList);
	    	
        	return "account_setup";
    	} catch (NullPointerException err) {
    		log.info("account-setup: No photo found! ", err.getMessage());
    	}
    	return "account_setup";
    }
    
    @PostMapping("/upload-profile-picture") // TODO
	public String accountSetup(@ModelAttribute AccountSetupForm form, RedirectAttributes attributes, Model model) {
		Users user = getCurrentLoggedUser();
		try {
			// Store the file data.
			FileMetaDataModel data = fileStorageService.store(form.getFile(), user);
			
			long organizationFk = form.getOrganizationFk(); 
			String firstName = form.getFirstName();
			String middleName = form.getMiddleName();
			String lastName = form.getLastName();
			String extension = form.getExtension();
			String citizenship = form.getCitizenship();
			Date dateOfBerth = form.getDateOfBerth();
			String address = form.getAddress();
			
			
			// For debugging purpose.
			data.setUrl(fileDownloadUrl(data.getFileName(), "/media/download/"));
			model.addAttribute("uploadedFile", data);
			// ------ end
			
			// User profile picture.
			model.addAttribute("photoUrl", fileDownloadUrl(data.getFileName(), "/media/download/"));
		} catch (FileStorageException err) {
			model.addAttribute("error", "Unable to store the file");
			return "account_setup";
		}
		return "account_setup";
	}
	
	/*
	 * @PostMapping("/upload-profile-picture") public String
	 * accountSetup(@RequestParam("file") MultipartFile file, RedirectAttributes
	 * attributes, Model model) { Users user = getCurrentLoggedUser(); try { //
	 * Store the file data. FileMetaDataModel data = fileStorageService.store(file,
	 * user);
	 * 
	 * // For debugging purpose. data.setUrl(fileDownloadUrl(data.getFileName(),
	 * "/media/download/")); model.addAttribute("uploadedFile", data); // ------ end
	 * 
	 * // User profile picture. model.addAttribute("photoUrl",
	 * fileDownloadUrl(data.getFileName(), "/media/download/")); } catch
	 * (FileStorageException err) { model.addAttribute("error",
	 * "Unable to store the file"); return "account_setup"; } return
	 * "account_setup"; }
	 */
	
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
