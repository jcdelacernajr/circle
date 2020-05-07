package com.web.circle.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.web.circle.controller.DTO.form.BranchSetupForm;
import com.web.circle.model.BranchDataModel;
import com.web.circle.model.BranchTableDataModel;
import com.web.circle.model.ClientTableDataModel;
import com.web.circle.model.entity.Branch;
import com.web.circle.model.entity.Organizations;
import com.web.circle.model.entity.Users;
import com.web.circle.repository.BranchRepository;
import com.web.circle.utils.Utils;

/**
 * @author Juanito C. Dela Dela Cerna Jr. March 2020
 */
@Service
public class BranchServiceImp implements BranchService {
	
	@Autowired
	private BranchRepository branchRepository;

	@Override
	public List<BranchDataModel> branchList(long organizationId, Users user) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			// List of branch
	    	List<Branch> bList = branchRepository.findBranchsByOrganization(organizationId);
			
	    	JSONArray array = new JSONArray();
			for(Branch b: bList) {
				if(b.getBranchId() == user.getBranch().getBranchId()) {
					JSONObject jOb = new JSONObject();
					jOb.put("value", b.getBranchId());
					jOb.put("selected", true);
					jOb.put("text", b.getBranchName());
					array.put(jOb);
				}
				else {
					JSONObject jOb = new JSONObject();
					jOb.put("value", b.getBranchId());
					jOb.put("text", b.getBranchName());
					array.put(jOb);
				}
			}
	    	
	    	JsonParser jParser = new JsonParser();
			JsonElement jElement = jParser.parse(array.toString());
			List<BranchDataModel> branchList =  objectMapper.readValue(jElement.toString(),  new TypeReference<List<BranchDataModel>>() { });
	    	return branchList;
		} catch(Exception err) {
			
		}
		return null;
	}

	@Override
	public ArrayList<Object> branchList(long organizationId) {
    	List<Branch> branch = branchRepository.findBranchsByOrganization(organizationId);
    	ArrayList<Object> branchList = new ArrayList<Object>();
    	for(Branch b : branch) {
    		BranchTableDataModel branchTableDataModel = new BranchTableDataModel();
    		branchTableDataModel.setBranchId(b.getBranchId());
    		branchTableDataModel.setBranchName(b.getBranchName());
    		branchTableDataModel.setAddress(b.getAddress());
    		branchTableDataModel.setLogoUrl(Utils.fileDownloadUrl(b.getUploadFile().getFileName(), "/media/download/"));
    		// Add the list.
    		branchList.add(branchTableDataModel);
		}
		return branchList;
	}

	@Override
	public Branch record(BranchSetupForm form) {
		// TODO Auto-generated method stub
		return null;
	}

}
