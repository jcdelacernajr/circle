package com.web.circle.model.entity;

import java.util.Date;

import javax.persistence.CascadeType;
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
 * 
 * @author jr
 * */
@Entity
@Table(name = "organizations")
public class Organizations extends CircleAuditing {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "organization_id")
	private long organizationId;
	
	@Column(name = "company_heading")
	private String companyHeading;
	
	@Column(name = "establishment_name")
	private String establishmentName;
	
	@Column(name = "founded")
	private Date founded;
	
	@Column(name = "executive")
	private String executive;
	
	@Column(name = "annual_budget")
	private Double annualBudget;
	
	@Column(name = "predecessor")
	private String predecessor;
	
	@Column(name = "telephone_no")
	private String telephoneNo;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "website")
	private String website;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "postal_code")
	private String postalCode;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "jurisdiction")
	private String jurisdiction;
	
	@Column(name = "dissolved")
	private String dissolved;
	
	/**
	 * The organization license.
	 * */
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "license_fk")
	private License license;
	
	/**
	 * The current used logo of organization
	 * */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "logo_id")
	private UploadFile uploadFile;

	public License getLicense() {
		return license;
	}

	public void setLicense(License license) {
		this.license = license;
	}

	public UploadFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(UploadFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	public long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(long organizationId) {
		this.organizationId = organizationId;
	}

	public String getCompanyHeading() {
		return companyHeading;
	}

	public void setCompanyHeading(String companyHeading) {
		this.companyHeading = companyHeading;
	}

	public String getEstablishmentName() {
		return establishmentName;
	}

	public void setEstablishmentName(String establishmentName) {
		this.establishmentName = establishmentName;
	}

	public Date getFounded() {
		return founded;
	}

	public void setFounded(Date founded) {
		this.founded = founded;
	}

	public String getExecutive() {
		return executive;
	}

	public void setExecutive(String executive) {
		this.executive = executive;
	}

	public Double getAnnualBudget() {
		return annualBudget;
	}

	public void setAnnualBudget(Double annualBudget) {
		this.annualBudget = annualBudget;
	}

	public String getPredecessor() {
		return predecessor;
	}

	public void setPredecessor(String predecessor) {
		this.predecessor = predecessor;
	}

	public String getTelephoneNo() {
		return telephoneNo;
	}

	public void setTelephoneNo(String telephoneNo) {
		this.telephoneNo = telephoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getJurisdiction() {
		return jurisdiction;
	}

	public void setJurisdiction(String jurisdiction) {
		this.jurisdiction = jurisdiction;
	}

	public String getDissolved() {
		return dissolved;
	}

	public void setDissolved(String dissolved) {
		this.dissolved = dissolved;
	}
	
}
