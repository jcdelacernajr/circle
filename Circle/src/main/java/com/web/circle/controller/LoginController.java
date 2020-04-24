package com.web.circle.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {

	@GetMapping
	public String index() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//log.info("User Principal: "+ auth.getName() + " Authorities: "+ auth.getAuthorities());
		if(!auth.getPrincipal().equals("anonymousUser")) {
			return "redirect:/";
		}
		
		return "login";
	}
	
}
