package com.web.circle.service;

import com.web.circle.controller.AccountSetupController;
import com.web.circle.controller.DTO.form.AccountSetupForm;
import com.web.circle.model.entity.Users;

/**
 * Account setup form.
 * This is use by AccountSetupController class @see {@link AccountSetupController }}
 * 
 * @author Juanito C. Dela Dela Cerna Jr. April 2020
 * */
public interface AccountSetupService {
	Users updateUser(AccountSetupForm form, Long fileId);
}
