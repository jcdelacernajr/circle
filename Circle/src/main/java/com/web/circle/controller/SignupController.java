package com.web.circle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.circle.model.entity.Users;
import com.web.circle.service.SecurityService;
import com.web.circle.service.UserService;
import com.web.circle.validator.UserValidator;

/**
 * @author Juanito C. Dela Dela Cerna Jr. March 2020
 */
@Controller
@RequestMapping("/")
public class SignupController {
	
	@Autowired
	private UserService us;
	@Autowired
	private SecurityService ss;
	@Autowired
	private UserValidator uv;
	
	@GetMapping("signup")
    public String signup(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	//System.out.println("Principal: " + auth.getPrincipal());
    	if(!auth.getPrincipal().equals("anonymousUser")) {
    		return "redirect:/";
    	}
    	
    	return "signup";
    }

    @PostMapping("/signup")
    public String registration(@ModelAttribute("userForm") Users userForm, BindingResult bindingResult) {
        uv.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "signup";
        }
        us.save(userForm);
        ss.autoLogin(userForm.getUsername(), userForm.getPassword());
        return "redirect:/";
    }
}
