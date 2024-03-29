package com.web.circle.controller.DTO.form;

import org.springframework.web.multipart.MultipartFile;

import com.web.circle.controller.AccountSetupController;

/**
 * Account setup form.
 * This is use by AccountSetupController class @see {@link AccountSetupController }}
 * 
 * @author Juanito C. Dela Dela Cerna Jr. April 2020
 * @see http://www.startwithjava.com/spring-boot-multiple-files-upload-with-other-input-fields/
 * */
public class AccountSetupForm {
	
	private MultipartFile file;
	private Long userId;
	private String organization;
	private String branch;
	private String department;
	private String firstName;
	private String middleName;
	private String lastName;
	private String extension;
	private String citizenship;
	private String dateOfBerth;
	private String address;
	
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public String getCitizenship() {
		return citizenship;
	}
	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}
	public String getDateOfBerth() {
		return dateOfBerth;
	}
	public void setDateOfBerth(String dateOfBerth) {
		this.dateOfBerth = dateOfBerth;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
