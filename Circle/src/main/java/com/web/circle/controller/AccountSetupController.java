package com.web.circle.controller;

import java.io.FileNotFoundException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.web.circle.exception.FileStorageException;
import com.web.circle.model.FileMetaDataModel;
import com.web.circle.model.entity.Users;
import com.web.circle.repository.PersonRepository;
import com.web.circle.repository.UploadFileRepository;
import com.web.circle.repository.UserRepository;
import com.web.circle.service.FileStorageService;

import lombok.extern.slf4j.Slf4j;

/**
 * Upload file controller // TODO....
 * 
 * @see https://www.javadevjournal.com/spring/spring-file-upload/
 * @author Juanito C. Dela Dela Cerna Jr. March 2020
 */
@Slf4j
@Controller
public class AccountSetupController extends BaseContoller {

	public AccountSetupController(UserRepository userRepository) {
		super(userRepository);
	}

	@Autowired
	FileStorageService fileStorageService;
	
	@Autowired
	private UploadFileRepository uploadFileRepository;
	@Autowired
	private PersonRepository personRepository;
	
	
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
        	return "account_setup";
    	} catch (NullPointerException err) {
    		log.info("account-setup: No photo found! ", err.getMessage());
    	}
    	return "account_setup";
    }
	
	@PostMapping("/upload-profile-picture")
	public String accountSetup(@RequestParam("file") MultipartFile file, RedirectAttributes attributes, Model model) {
		Users user = getCurrentLoggedUser();
		try {
			// Store the file data.
			FileMetaDataModel data = fileStorageService.store(file, user);
			
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
