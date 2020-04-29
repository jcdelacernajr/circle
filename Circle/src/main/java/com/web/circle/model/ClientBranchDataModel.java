package com.web.circle.model;

import com.fasterxml.jackson.databind.node.ObjectNode;

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
public class ClientBranchDataModel {

	private ObjectNode branch;
	private Long branchId;
	private String branchName;
	private String address;
	private String email;
	private String postalCode;
	private String telephoneNo;
	private String website;
	
}
