package com.web.circle.service;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.web.circle.model.entity.Users;
import com.web.circle.repository.UserRepository;

/**
 * @author jr
 * */
public class CircleJwtAuthorFilter extends BasicAuthenticationFilter {
	
	private UserRepository userR;
	
	public CircleJwtAuthorFilter(AuthenticationManager authenticationManager, UserRepository userR) {
		super(authenticationManager);
		this.userR = userR;
	
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// Read request authorization header
		String header = request.getHeader(CircleJwtProperties.HEADER_STRING);
		// Check the header if request contain a bearer
		if(header == null || !header.startsWith(CircleJwtProperties.TOKEN_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}
		
		// If header is not null, do check if the user is in the database.
		Authentication authentication = getUsernamePassword(request);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);
	}

	private Authentication getUsernamePassword(HttpServletRequest httpServletRequest) {
		String token = httpServletRequest.getHeader(CircleJwtProperties.HEADER_STRING)
				.replace(CircleJwtProperties.TOKEN_PREFIX, "");
		// parse the token and validate it
		if(token != null) {
			String userName = JWT.require(HMAC512(CircleJwtProperties.SECRETE.getBytes()))
					.build().verify(token).getSubject();
			
			// Search in the DB if we find the user by token subject (username)
            // If so, then grab user details and create spring auth token using username, pass, authorities/roles
			if(userName != null) {
				Users user = userR.findByUsername(userName);
				UserPrincipal userP = new UserPrincipal(user);
				UsernamePasswordAuthenticationToken authen = new UsernamePasswordAuthenticationToken(userName, null, userP.getAuthorities());
				return authen;		
			}
			return null;
		}
		return null;
	}
	
}






