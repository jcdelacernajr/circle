package com.web.circle.controller.DTO.form;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

/**
 * Account setup form.
 * 
 * @author Juanito C. Dela Dela Cerna Jr. March 2020
 * @see http://www.startwithjava.com/spring-boot-multiple-files-upload-with-other-input-fields/
 * */
public class AccountSetupForm {
	
	private MultipartFile file;
	private String organization;
	private String firstName;
	private String middleName;
	private String lastName;
	private String extension;
	private String citizenship;
	private Date dateOfBerth;
	private String address;
	
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
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
	public Date getDateOfBerth() {
		return dateOfBerth;
	}
	public void setDateOfBerth(Date dateOfBerth) {
		this.dateOfBerth = dateOfBerth;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
