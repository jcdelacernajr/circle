package com.web.circle.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Use by UserTableServiceImplementation
 * 
 * @author JC Dela Cerna Jr. April 2020
 * */
@Setter
@Getter
@NoArgsConstructor
public class UsersModel {

	private long userId;
	private String organization;
	private String email;
	private String username;
	private String password;
	private boolean isActive;
	private boolean isAccountNonExpired;
	private boolean isAccountNonLocked;
	private boolean isCredentialsNonExpired;
	
}
