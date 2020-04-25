package com.web.circle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Juanito C. Dela Dela Cerna Jr. March 2020
 * 
 */
@Controller
@RequestMapping("/human-resources/dashboard")
public class DashboardController {
	
    @GetMapping
    public String dashboard(){
        return "human_resources/dashboard";
    }
    
}





