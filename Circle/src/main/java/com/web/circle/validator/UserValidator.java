package com.web.circle.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.web.circle.model.entity.Users;
import com.web.circle.service.UserService;

/**
 * @author Juanito C. Dela Dela Cerna Jr. March 2020
 */
@Component
public class UserValidator implements Validator {
	
	@Autowired
	private UserService us;

	@Override
	public boolean supports(Class<?> clazz) {
		return Users.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		Users user = (Users) o;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
		if(user.getUsername().length() < 6 || user.getUsername().length() > 32) {
			 errors.rejectValue("username", "Size.userForm.username");
		}
		if (us.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }
        
	}

}
