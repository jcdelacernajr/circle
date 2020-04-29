package com.web.circle.service;

import java.util.List;

import com.web.circle.model.ClientTableDataModel;

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
	
}
