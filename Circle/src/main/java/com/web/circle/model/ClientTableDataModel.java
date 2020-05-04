package com.web.circle.model;


import lombok.Getter;
import lombok.Setter;

/**
 * Clients data model
 * 
 * Associated to ClientsController
 * @author JC Dela Cerna Jr. April 2020
 * */
@Setter
@Getter
public class ClientTableDataModel {

	private Long organizationId;
	private String logoUrl;
	private String establishmentName;
	private String address;
	private String email;
	private String postalCode;
	private String telephoneNo;
	private String website;
	
	public ClientTableDataModel() {}
}
