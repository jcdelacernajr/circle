package com.web.circle.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.web.circle.model.entity.Users;
import com.web.circle.repository.UserRepository;

/**
 * User principal details service
 * 
 * @author jr
 * */
@Service
public class UserPrincipalDetailsService implements UserDetailsService {

	private UserRepository userR;
	
	public UserPrincipalDetailsService(UserRepository userR) {
		this.userR = userR;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Get the user credentials.
		Users user = this.userR.findByUsername(username);
		//System.out.println("User: "+ user);
		if(user == null) {
			throw new UsernameNotFoundException("No user found for "+ username + ".");
		}
        UserPrincipal userPrincipal = new UserPrincipal(user);
        return userPrincipal;
	}

}
