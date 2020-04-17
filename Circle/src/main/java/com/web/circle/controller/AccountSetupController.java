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
import com.web.circle.service.FileStorageService;

/**
 * Upload file controller // TODO....
 * 
 * @author Juanito C. Dela Dela Cerna Jr. March 2020
 */
@Controller
public class AccountSetupController {

	@Autowired
	FileStorageService fileStorageService;
	
	/**
     * Controller to display the file upload form on the frontend.
     * @param model
     * @return
     */
    @GetMapping("/account-setup")
    public String accountSetup(final Model model){
        return "account_setup";
    }
	
	@PostMapping("/account-setup")
	public String accountSetup(@RequestParam("file") MultipartFile file, RedirectAttributes attributes, Model model) {
		try {
			FileMetaDataModel data = fileStorageService.store(file);
			data.setUrl(fileDownloadUrl(data.getFileName(), "/media/download/"));
			model.addAttribute("uploadedFile", data);
		} catch (FileStorageException err) {
			model.addAttribute("error", "Unable to store the file");
			return "account_setup";
		}
		return "account_setup";
	}
	
	public String fileDownloadUrl(final String fileName, final String baseURL){
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(baseURL)
                .path(fileName).toUriString();
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
