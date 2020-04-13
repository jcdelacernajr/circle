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
@Table(name = "designations")
public class Designations extends CircleAuditing  {

	//private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "designation_id")
	private long designationId;
	
	@Column(name = "designation_label")
	private String designationLabel;
	
	@Column(name = "is_active")
	private int isActive;
	
	@Column(name = "created_by_user_fk")
	private int createdByUserFk;
	
//	@Column(name = "date_created")
//	private Date dateCreated;
	
	@Column(name = "modified_by_user_fk")
	private int modifiedByUserFk;
	
//	@Column(name = "date_modified")
//	private Date dateModified;
	
	@OneToMany(mappedBy = "designations")
	@JsonIgnore
	private List<UserRoles> userRoles;
	
	public List<UserRoles> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRoles> userRoles) {
		this.userRoles = userRoles;
	}

	public long getDesignationId() {
		return designationId;
	}

	public void setDesignationId(long designationId) {
		this.designationId = designationId;
	}

	public String getDesignationLabel() {
		return designationLabel;
	}

	public void setDesignationLabel(String designationLabel) {
		this.designationLabel = designationLabel;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public int getCreatedByUserFk() {
		return createdByUserFk;
	}

	public void setCreatedByUserFk(int createdByUserFk) {
		this.createdByUserFk = createdByUserFk;
	}

//	public Date getDateCreated() {
//		return dateCreated;
//	}
//
//	public void setDateCreated(Date dateCreated) {
//		this.dateCreated = dateCreated;
//	}

	public int getModifiedByUserFk() {
		return modifiedByUserFk;
	}

	public void setModifiedByUserFk(int modifiedByUserFk) {
		this.modifiedByUserFk = modifiedByUserFk;
	}

//	public Date getDateModified() {
//		return dateModified;
//	}
//
//	public void setDateModified(Date dateModified) {
//		this.dateModified = dateModified;
//	}
	
	
}
