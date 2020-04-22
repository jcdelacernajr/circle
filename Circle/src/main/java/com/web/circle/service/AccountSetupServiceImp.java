package com.web.circle.service;

import java.text.ParseException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.circle.controller.AccountSetupController;
import com.web.circle.controller.DTO.form.AccountSetupForm;
import com.web.circle.model.entity.Department;
import com.web.circle.model.entity.Organizations;
import com.web.circle.model.entity.Person;
import com.web.circle.model.entity.UploadFile;
import com.web.circle.model.entity.Users;
import com.web.circle.repository.PersonRepository;
import com.web.circle.repository.UserRepository;
import com.web.circle.utils.Utils;

/**
 * Account setup form.
 * This is use by AccountSetupController class @see {@link AccountSetupController }}
 * 
 * @author Juanito C. Dela Dela Cerna Jr. March 2020
 * */
@Service
@Transactional
public class AccountSetupServiceImp implements AccountSetupService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PersonRepository personRepository;
	
	@Override
	public Users updateUser(AccountSetupForm form, Long fileId) {
		// User table
		Users user = userRepository.findById(form.getUserId()).get();
		
		// Organization table
		Organizations organization = new Organizations();
		organization.setOrganizationId(Long.parseLong(form.getOrganization()));
		// Set user organization.
		user.setOrganizations(organization);
		
		// Department table
		Department department = new Department();
		department.setDepartmentId(Long.parseLong(form.getDepartment()));
		// SEt user department.
		user.setDepartment(department);
		
		// Set person data.
		Person person = personRepository.findById(user.getPerson().getPersonId()).get();
		person.setFirstName(form.getFirstName());
		person.setMiddleName(form.getMiddleName());
		person.setLastName(form.getLastName());
		person.setNameExtension(form.getExtension());
		person.setCitizenship(form.getCitizenship());
		try {
			person.setDateOfBerth(Utils.stringToDate(form.getDateOfBerth()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		person.setAddress(form.getAddress());
		
		// Update the user photo of the person
		if(fileId != null) {
			UploadFile uploadFile = new UploadFile();
			uploadFile.setUploadFileId(fileId);
			person.setUploadFile(uploadFile);
		}
		user.setPerson(person);
		
		return userRepository.save(user);
	}

}
