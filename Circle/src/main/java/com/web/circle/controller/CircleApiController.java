package com.web.circle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.circle.model.UsersModel;
import com.web.circle.model.tablepaging.Page;
import com.web.circle.model.tablepaging.PagingRequest;
import com.web.circle.service.UserTableServiceImp;

/**
 * 
 * @author jr
 * */
@RestController
@RequestMapping("api/user-list")
public class CircleApiController {
	
	private final UserTableServiceImp userTableService;
	
	@Autowired
	public CircleApiController(UserTableServiceImp userTableService) {
		this.userTableService = userTableService;
	}
	
	@PostMapping
    public Page<UsersModel> list(@RequestBody PagingRequest pagingRequest) {
		return userTableService.getUsers(pagingRequest);
	}

}
