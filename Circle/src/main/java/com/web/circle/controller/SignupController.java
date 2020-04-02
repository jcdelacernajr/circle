package com.web.circle.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.web.circle.controller.DTO.UserSignupDTO;
import com.web.circle.model.entity.Users;
import com.web.circle.service.UserService;

/**
 * @author Juanito C. Dela Dela Cerna Jr. March 2020
 * 
 * 
 * @see https://memorynotfound.com/spring-security-user-registration-example-thymeleaf/
 */
@Controller
@RequestMapping("/signup")
public class SignupController {
	
	@Autowired
	private UserService us;
	
	@ModelAttribute("user")
	public UserSignupDTO userSignupDTO() {
		return new UserSignupDTO();
	}
	
	@GetMapping
    public String showSignupForm(Model model) {
        return "signup";
    }
	
	@PostMapping
    public String signupUserAccount(@ModelAttribute("user") @Valid UserSignupDTO userDTO, BindingResult result){
        Users user = us.findByEmail(userDTO.getEmail());
        if (user != null){
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if (result.hasErrors()){
            return "signup";
        }
        us.save(userDTO);
        return "redirect:/signup?success";
    }
}
