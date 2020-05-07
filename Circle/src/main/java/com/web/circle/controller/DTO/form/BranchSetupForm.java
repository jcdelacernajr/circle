package com.web.circle.controller.DTO.form;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

/**
 * Account setup form.
 * This is use by ClientsController
 * 
 * @author Juanito C. Dela Dela Cerna Jr. April 2020
 * */
@Setter
@Getter
public class BranchSetupForm {

	private Long userId;
	private Long fileId;
	private MultipartFile file;
	private String branchName;
	private String address;

	// TODO. add more if needed.
}
