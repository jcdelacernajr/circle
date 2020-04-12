package com.web.circle.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.web.circle.model.entity.CirclePrincipal;
import com.web.circle.model.entity.Users;


/**
 * User principal
 *  
 * @author jr
 * */
public class UserPrincipal implements UserDetails  {

	//private static final long serialVersionUID = 1L;
	private Users user;

	public UserPrincipal(Users user) {
		this.user = user;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// Get the User role.
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		this.user.getUserRoles().forEach(r ->{
			GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+ r.getRoles().getRoleLabel()); 
			authorities.add(authority);
		});
		
		// Get the user permissions.
		this.user.getUserRoles().forEach(p ->{
			GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+p.getPermissions().getPermissionLabel());
			authorities.add(authority);
		});
		
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		return this.user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.user.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.user.isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.user.isCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		return this.user.getIsActive() == true;
	}

}
