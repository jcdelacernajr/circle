package com.web.circle.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.circle.model.UserLoginView;
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
/**
 * 
 * @author jr
 * */
public class CircleJwtAuthenFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authManager;

	public CircleJwtAuthenFilter(AuthenticationManager authManager) {
		this.authManager = authManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		// Get the user credentials.
		UserLoginView credentials = null;
		try {
			credentials = new ObjectMapper().readValue(request.getInputStream(), UserLoginView.class);
		} catch (IOException er) {
			er.printStackTrace();
		}
		// Create a login token.
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				credentials.getUsername(), credentials.getPassword(), new ArrayList<>());
		Authentication auth = authManager.authenticate(authenticationToken);
		return auth;
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// Get the user principal.
		UserPrincipal userP = (UserPrincipal) authResult.getPrincipal();
		// Create JWT token.
		String token = JWT.create()
				.withSubject(userP.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + CircleJwtProperties.EXPIRATION_TIME))
				.sign(HMAC512(CircleJwtProperties.SECRETE.getBytes()));
				
		response.addHeader(CircleJwtProperties.HEADER_STRING, CircleJwtProperties.TOKEN_PREFIX + token);		
		
	}
	
}
