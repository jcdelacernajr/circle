package com.web.circle.service;

import java.util.ArrayList;
import java.util.List;

import com.web.circle.controller.DTO.form.BranchSetupForm;
import com.web.circle.model.BranchDataModel;
import com.web.circle.model.BranchTableDataModel;
import com.web.circle.model.entity.Branch;
import com.web.circle.model.entity.Users;

/**
 * @author Juanito C. Dela Dela Cerna Jr. March 2020
 */
public interface BranchService {

	/**
	 * List of branch 
	 * 
	 * @param organizationId
	 * @param user
	 * @deprecated
	 * */
	List<BranchDataModel> branchList(long organizationId, Users user);
	
	/**
	 * List of branch 
	 * 
	 * @param organizationId
	 * */
	ArrayList<Object> branchList(long organizationId);
	
	/**
	 * Add new branch record
	 * 
	 * @param form
	 * */
	Branch record(BranchSetupForm form);
}
