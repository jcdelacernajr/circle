package com.web.circle.service;

import java.util.List;

import com.web.circle.model.BranchDataModel;
import com.web.circle.model.entity.Users;

/**
 * @author Juanito C. Dela Dela Cerna Jr. March 2020
 */
public interface BranchService {

	/**
	 * List of branch 
	 * 
	 * @param organizationId
	 * */
	List<BranchDataModel> branchList(long organizationId, Users user);
	
}
