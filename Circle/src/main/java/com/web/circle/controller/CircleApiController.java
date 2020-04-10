package com.web.circle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.circle.model.UsersModel;
import com.web.circle.model.tablepaging.Page;
import com.web.circle.model.tablepaging.PagingRequest;
import com.web.circle.service.UserTableServiceImpl;

/**
 * 
 * @author jr
 * */
@RestController
@RequestMapping("api/user-list")
public class CircleApiController {
	
	private final UserTableServiceImpl userTableService;
	
	@Autowired
	public CircleApiController(UserTableServiceImpl userTableService) {
		this.userTableService = userTableService;
	}
	
	@PostMapping
    public Page<UsersModel> list(@RequestBody PagingRequest pagingRequest) {
		return userTableService.getUsers(pagingRequest);
	}

}
