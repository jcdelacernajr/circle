package com.web.circle.service;

import java.util.List;

import com.web.circle.controller.DTO.form.OrganizationSetupForm;
import com.web.circle.model.ClientTableDataModel;
import com.web.circle.model.entity.Organizations;

/**
 * @author Juanito C. Dela Dela Cerna Jr. March 2020
 */
public interface OrganizationsService {

	/**
	 * Organization
	 * 
	 * @param organizationId
	 * */
	List<ClientTableDataModel> organization(long organizationId);
	
	/**
	 * Add or record the new organization data.
	 * @param form
	 * */
	Organizations record(OrganizationSetupForm form);
	
}
