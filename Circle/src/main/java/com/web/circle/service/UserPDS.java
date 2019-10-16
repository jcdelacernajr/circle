package com.web.circle.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.web.circle.model.entity.CirclePrincipal;
import com.web.circle.model.entity.Permissions;
import com.web.circle.model.entity.Roles;
import com.web.circle.model.entity.UserRoles;
import com.web.circle.model.entity.Users;
import com.web.circle.repository.PermissionRepo;
import com.web.circle.repository.RoleRepo;
import com.web.circle.repository.UserRepo;
import com.web.circle.repository.UserRoleRepo;

/**
 * User principal details service
 * 
 * @author jr
 * */
@Service
public class UserPDS implements UserDetailsService {

	private UserRepo userR;
	
	public UserPDS(UserRepo userR) {
		this.userR = userR;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Get the user credentials.
		Users user = this.userR.findByUsername(username);
		System.out.println("User: "+ user);
		if(user == null) {
			throw new UsernameNotFoundException("No user found for "+ username + ".");
		}
        UserP userPrincipal = new UserP(user);
        return userPrincipal;
	}

}
