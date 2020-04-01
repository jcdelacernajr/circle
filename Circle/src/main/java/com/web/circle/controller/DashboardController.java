package com.web.circle.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class DashboardController {
	
    @GetMapping("dashboard")
    public String home(){
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





