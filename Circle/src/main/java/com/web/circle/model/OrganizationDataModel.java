package com.web.circle.model;

import com.web.circle.controller.AccountSetupController;

import lombok.*;

/**
 * Organization data model
 * This is use by AccountSetupController class @see {@link AccountSetupController }}
 * 
 * @author jr April 2020
 * */
@Setter
@Getter
public class OrganizationDataModel {

	private Long value;
	private Boolean selected;
	private String text;
	
	public OrganizationDataModel() {
	}
	
	public OrganizationDataModel(Long value, String text) {
		this.value = value;
		this.text = text;
	}
	
}
