package com.web.circle.service;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.web.circle.model.BranchDataModel;
import com.web.circle.model.entity.Branch;
import com.web.circle.repository.BranchRepository;

/**
 * @author Juanito C. Dela Dela Cerna Jr. March 2020
 */
@Service
public class BranchServiceImp implements BranchService {
	
	@Autowired
	private BranchRepository branchRepository;

	@Override
	public List<BranchDataModel> branchList(long organizationId) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			// List of branch
	    	List<Branch> bList = branchRepository.findBranchsByOrganization(organizationId);
			
	    	JSONArray array = new JSONArray();
			for(Branch b: bList) {
				JSONObject jOb = new JSONObject();
				jOb.put("value", b.getBranchId());
				jOb.put("text", b.getBranchName());
				array.put(jOb);
			}
	    	
	    	JsonParser jParser = new JsonParser();
			JsonElement jElement = jParser.parse(array.toString());
			List<BranchDataModel> branchList =  objectMapper.readValue(jElement.toString(),  new TypeReference<List<BranchDataModel>>() { });
	    	return branchList;
		} catch(Exception err) {
			
		}
		return null;
	}

}
