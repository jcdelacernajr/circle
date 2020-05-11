package com.web.circle.model;

import com.web.circle.controller.AccountSetupController;

import lombok.Getter;
import lombok.Setter;

/**
 * Branch data model
 * This is use by AccountSetupController class @see {@link AccountSetupController }}
 * 
 * For select option.
 * 
 * @author Juanito C. Dela Dela Cerna Jr. April 2020
 */
@Setter
@Getter
public class BranchDataModel {

	private Long value;
	private Boolean selected;
	private String text;
	
	public BranchDataModel() {}
	
	public BranchDataModel(Long value, Boolean selected, String text) {
		this.value = value;
		this.selected = selected;
		this.text = text;
	}
	
}
