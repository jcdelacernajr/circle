package com.web.circle.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author jr
 * */
@Entity
@Table(name = "users")
public class Users extends CircleAuditing {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private long userId;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "is_active")
	private Boolean isActive;
	
	@Column(name = "is_account_non_expired")
	private boolean isAccountNonExpired;
	
	@Column(name = "is_account_non_locked")
	private boolean isAccountNonLocked;
	
	@Column(name = "is_credentials_non_expired")
	private boolean isCredentialsNonExpired;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="organization_fk")
	private Organizations organizations;
	
	public Organizations getOrganizations() {
		return organizations;
	}

	public void setOrganizations(Organizations organizations) {
		this.organizations = organizations;
	}
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="branch_fk")
	private Branch branch;
	
	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="department_fk")
	private Department department;
	
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "person_fk")
	private Person person;

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@OneToMany(mappedBy = "users")
	@JsonIgnore // We need to ignore this . This will prevent a infinite loop.
	private List<UserRoles> userRoles;
	
	public List<UserRoles> getUserRoles() {
		return userRoles;
	}
	
	public void setUserRoles(List<UserRoles> userRoles) {
		this.userRoles = userRoles;
	}
	
	// Ger all the user roles.
	public List<String> getRoleList(){
		ArrayList<String> ret = new ArrayList<String>();
		if(this.userRoles.size() > 0) {
			this.getUserRoles().forEach(r -> {
				ret.add(r.getRoles().getRoleLabel());
			});
		}
	    return new ArrayList<>(ret);
 	}
	
	// Get all the user permissions.
	public List<String> getPermissionsList(){
		ArrayList<String> ret = new ArrayList<String>();
		if(this.userRoles.size() > 0) {
			this.getUserRoles().forEach(r -> {
				ret.add(r.getPermissions().getPermissionLabel());
			});
		}
	    return new ArrayList<>(ret);
 	}
	
	public Users() {}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean isAccountNonExpired() {
		return isAccountNonExpired;
	}

	public void setAccountNonExpired(boolean isAccountNonExpired) {
		this.isAccountNonExpired = isAccountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}

	public void setAccountNonLocked(boolean isAccountNonLocked) {
		this.isAccountNonLocked = isAccountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean isCredentialsNonExpired) {
		this.isCredentialsNonExpired = isCredentialsNonExpired;
	}

}
