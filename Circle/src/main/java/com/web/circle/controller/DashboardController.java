package com.web.circle.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Juanito C. Dela Dela Cerna Jr. March 2020
 * 
 */
@Controller
@RequestMapping("/")
public class DashboardController {
	
    @GetMapping("dashboard")
    public String dashboard(){
        return "dashboard";
    }

    @GetMapping("login")
    public String login() {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	//System.out.println("Principal: " + auth.getPrincipal());
    	if(!auth.getPrincipal().equals("anonymousUser")) {
    		return "redirect:/dashboard";
    	}
    	
    	return "login";
	}
    
    
}





