package com.web.circle.model;

import com.web.circle.controller.AccountSetupController;

import lombok.*;

/**
 * Department data model
 * This is use by AccountSetupController class @see {@link AccountSetupController }}
 * 
 * @author Juanito C. Dela Dela Cerna Jr. April 2020
 */
@Setter
@Getter
public class DepartmentDataModel {
	
	private Long value;
	private Boolean selected;
	private String text;
	
	public DepartmentDataModel() {
		
	}
	
	public DepartmentDataModel(Long value, Boolean selected, String text) {
		this.value = value;
		this.selected = selected;
		this.text = text;
	}
	
}
