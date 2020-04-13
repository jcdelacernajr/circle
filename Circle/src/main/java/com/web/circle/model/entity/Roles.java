package com.web.circle.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author jr
 * */
@Entity
@Table(name = "roles")
public class Roles extends CircleAuditing  {
	
	//private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private long roleId;
	
	@Column(name = "role_label")
	private String roleLabel;
	
	@Column(name = "is_active")
	private int isActive;
	
//	@Column(name = "date_created")
//	private Date dateCreated;
//	
//	@Column(name = "date_modified")
//	private Date dateModified;
	
	@OneToMany(mappedBy = "roles")
	@JsonIgnore
	private List<UserRoles> userRoles;
	
	public List<UserRoles> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRoles> userRoles) {
		this.userRoles = userRoles;
	}

	public String getRoleLabel() {
		return roleLabel;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public void setRoleLabel(String roleLabel) {
		this.roleLabel = roleLabel;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

//	public Date getDateCreated() {
//		return dateCreated;
//	}
//
//	public void setDateCreated(Date dateCreated) {
//		this.dateCreated = dateCreated;
//	}
//
//	public Date getDateModified() {
//		return dateModified;
//	}
//
//	public void setDateModified(Date dateModified) {
//		this.dateModified = dateModified;
//	}

}




