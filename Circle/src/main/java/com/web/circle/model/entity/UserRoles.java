package com.web.circle.model.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author jr
 * */
@Entity
@Table(name = "user_roles")
public class UserRoles extends CircleAuditing {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_role_id")
	private long userRoleId;
	
	@ManyToOne
	@JoinColumn(name = "user_fk")
	@JsonIgnore
	private Users users;
	
	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}
	
	@ManyToOne
	@JoinColumn(name = "role_fk")
	@JsonIgnore
	private Roles roles;
	
	public Roles getRoles() {
		return roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}

	@ManyToOne
	@JoinColumn(name = "permission_fk")
	@JsonIgnore
	private Permissions permissions;
	
	public Permissions getPermissions() {
		return permissions;
	}

	public void setPermissions(Permissions permissions) {
		this.permissions = permissions;
	}
	
	@ManyToOne
	@JoinColumn(name = "designation_fk")
	@JsonIgnore
	private Designations designations;
	
	public Designations getDesignations() {
		return designations;
	}

	public void setDesignations(Designations designations) {
		this.designations = designations;
	}

	@Column(name = "is_active")
	private int isActive;
	
	@Column(name = "created_by_user_fk")
	private long createdByUserFk;
	
	@Column(name = "modified_by_user_fk")
	private int modifiedByUserFk;
	
	// Constructor
	public UserRoles() {}

	public long getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(long userRoleId) {
		this.userRoleId = userRoleId;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public long getCreatedByUserFk() {
		return createdByUserFk;
	}

	public void setCreatedByUserFk(long createdByUserFk) {
		this.createdByUserFk = createdByUserFk;
	}

	public int getModifiedByUserFk() {
		return modifiedByUserFk;
	}

	public void setModifiedByUserFk(int modifiedByUserFk) {
		this.modifiedByUserFk = modifiedByUserFk;
	}

	@Override
	public String toString() {
		return "User roles "
				+ "[user_role_id=" + userRoleId +
				", user_fk=" + getUsers().getUserId() + 
				", role_fk =" + getRoles().getRoleId() +
				", role_label = "+ getRoles().getRoleLabel() + 
				", permission_fk = "+ getPermissions().getPermissionId() + 
				", permission_label =" + getPermissions().getPermissionLabel() +
				"]";                          
	}
	
}





