package com.web.circle.model.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;

public class CirclePrincipal {

	@Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private boolean active;

    private String roles = "";

    private String permissions = "";

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}
    
	public List<String> getRoleList(){
		if(this.roles.length() > 0){
		    return Arrays.asList(this.roles.split(","));
		}
		return new ArrayList<>();
	}
	
	public List<String> getPermissionList(){
		if(this.permissions.length() > 0){
		    return Arrays.asList(this.permissions.split(","));
		}
		return new ArrayList<>();
	}
	
	
	
}
