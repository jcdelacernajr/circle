package com.web.circle.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Juanito C. Dela Dela Cerna Jr. March 2020
 */
@Service
public class SecurityServiceImplementation implements SecurityService {
	
	@Autowired
	private AuthenticationManager am;
	
	@Autowired
	private UserPDS urPDS;
	// Logger
	private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImplementation.class);
	
	

	@Override
	public String findLoggedInUsername() {
		Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
		if(userDetails instanceof UserDetails) {
			return ((UserDetails) userDetails).getUsername();
		}
		return null;
	}

	@Override
	public void autoLogin(String username, String password) {
		UserDetails userDetails = urPDS.loadUserByUsername(username);
		UsernamePasswordAuthenticationToken usToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
		am.authenticate(usToken);
		if(usToken.isAuthenticated()) {
			SecurityContextHolder.getContext().setAuthentication(usToken);
			logger.debug(String.format("Auto login %s successfully!", username));
		}
	}
}
