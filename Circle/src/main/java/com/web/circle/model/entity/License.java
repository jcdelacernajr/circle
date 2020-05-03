package com.web.circle.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * License table
 * 
 * @author JC Dela Cerna Jr. May 2020
 * */
 @Entity
 @Table(name = "license")
public class License extends CircleAuditing {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "license_id")
	private long licenseId;
	
	/**
	 * The organization who own the license.
	 * */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_fk")
	private Organizations organizations;
	
	/**
	 * Circle License serial key.
	 * */
	@Column(name = "circle_key")
	private String circleKey;
	
	/**
	 * The license start date.
	 * */
	@Column(name = "validity")
	private Date validity;
	 
	/**
	 * The license end date.
	 * */
	@Column(name = "expiration")
	private Date expiration;
	
	/**
	 * Added by the current logged user. 
	 * */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_fk")
	private Users users;

	public long getLicenseId() {
		return licenseId;
	}

	public void setLicenseId(long licenseId) {
		this.licenseId = licenseId;
	}

	public Organizations getOrganizations() {
		return organizations;
	}

	public void setOrganizations(Organizations organizations) {
		this.organizations = organizations;
	}

	public String getCircleKey() {
		return circleKey;
	}

	public void setCircleKey(String circleKey) {
		this.circleKey = circleKey;
	}

	public Date getValidity() {
		return validity;
	}

	public void setValidity(Date validity) {
		this.validity = validity;
	}

	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}
	
}
