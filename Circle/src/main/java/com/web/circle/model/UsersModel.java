package com.web.circle.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UsersModel {

	private long userId;
	private int organizationFk;
	private String email;
	private String username;
	private String password;
	private boolean isActive;
	private boolean isAccountNonExpired;
	private boolean isAccountNonLocked;
	private boolean isCredentialsNonExpired;
	
}
