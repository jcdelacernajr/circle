package com.web.circle.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.circle.model.entity.UserRoles;
import com.web.circle.model.entity.Users;
import com.web.circle.repository.UserRepository;
import com.web.circle.repository.UserRoleRepository;


/**
 * 
 * @author jr
 * */
@RestController
@RequestMapping("api/public")
@CrossOrigin
public class CirclePublicRestApiController {

	private UserRepository userR;
	private UserRoleRepository userRoleR;

	public CirclePublicRestApiController(UserRepository userR,UserRoleRepository userRoleR) {
		this.userR = userR;
		this.userRoleR = userRoleR;
	}
	
	 // Available to all authenticated users
    @GetMapping("test")
    public String test1(){
        return "API Test";
    }

    // Available to managers
    @GetMapping("management/reports")
    public String reports(){
        return "Some report data";
    }

    // Available to ROLE_ADMIN
    @GetMapping("admin/users")
    public List<Users> users(){
        return this.userR.findAll();
    }
    
    @GetMapping("admin/users/roles")
    public List<UserRoles> usersRoles(){
        return this.userRoleR.findAll();
    }
	
}
