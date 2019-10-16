package com.web.circle.model.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author jr
 * */
@Entity
@Table(name = "permissions")
public class Permissions extends CircleAuditing  {

	//private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "permission_id")
	private long permissionId;
	
	@Column(name = "permission_label")
	private String permissionLabel;
	
	@Column(name = "is_active")
	private int isActive;
	
	@Column(name = "added_by_user_fk")
	private int addedByUserFk;
	
//	@Column(name = "date_created")
//	private Date dateCtreated;
	
	@OneToMany(mappedBy = "permissions")
	@JsonIgnore
	private List<UserRoles> userRoles;
	
	public List<UserRoles> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRoles> userRoles) {
		this.userRoles = userRoles;
	}
	
	public long getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(long permissionId) {
		this.permissionId = permissionId;
	}

	public String getPermissionLabel() {
		return permissionLabel;
	}

	public void setPermissionLabel(String permissionLabel) {
		this.permissionLabel = permissionLabel;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public int getAddedByUserFk() {
		return addedByUserFk;
	}

	public void setAddedByUserFk(int addedByUserFk) {
		this.addedByUserFk = addedByUserFk;
	}

//	public Date getDateCtreated() {
//		return dateCtreated;
//	}
//
//	public void setDateCtreated(Date dateCtreated) {
//		this.dateCtreated = dateCtreated;
//	}
	
}




