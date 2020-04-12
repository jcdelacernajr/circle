package com.web.circle.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

	@GetMapping
	public String index() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("Principal: " + auth.getPrincipal()+ " Name: "+ auth.getName() + " Auth: "+ auth.getAuthorities());
		if(!auth.getPrincipal().equals("anonymousUser")) {
			System.out.println("dashboard-----------------------------------------------------");
			
			//Permissions p = new Permissions();
			
			//System.out.println("is authorized: "+ auth.getAuthorities().contains(p));
			
			return "redirect:/";
		}
		
		return "login";
	}
	
}
