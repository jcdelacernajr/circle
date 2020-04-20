package com.web.circle.model;

import lombok.*;

/**
 * File meta data
 * 
 * @author jr April 2020
 * */
@Setter
@Getter
public class OrganizationDataModel {

	private Long value;
	private String text;
	
	public OrganizationDataModel() {
	}
	
	public OrganizationDataModel(Long value, String text) {
		this.value = value;
		this.text = text;
	}
	
}
