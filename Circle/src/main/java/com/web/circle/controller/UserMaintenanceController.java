package com.web.circle.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.web.circle.model.entity.Users;
import com.web.circle.repository.UserRepository;


/**
 * This is for User signup controller.
 * 
 * @author Juanito C. Dela Dela Cerna Jr. March 2020
 * 
 */
@Controller
@RequestMapping("maintenance/user/")
public class UserMaintenanceController {
	
	private final UserRepository userRepository;
	
	@Autowired
	public UserMaintenanceController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	
	@GetMapping("index")
    public String index(@Valid Users user, BindingResult result, Model model){
		if (result.hasErrors()) {
            return "";
        }
        model.addAttribute("users", userRepository.findAll());
        
        return "maintenance/user/index";
    }
	
}
