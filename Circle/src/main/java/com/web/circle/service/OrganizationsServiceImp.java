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
import com.web.circle.model.ClientTableDataModel;
import com.web.circle.model.entity.Branch;
import com.web.circle.model.entity.Organizations;
import com.web.circle.repository.BranchRepository;
import com.web.circle.repository.OrganizationRepository;

@Service
public class OrganizationsServiceImp implements OrganizationsService {
	
	@Autowired
	private OrganizationRepository organizationRepository;
	@Autowired
	private BranchRepository branchRepository;

	@Override
	public List<ClientTableDataModel> organization(long organizationId) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			List<Organizations> organization = organizationRepository.getOrganization(organizationId);
			JSONArray array = new JSONArray();
			for(Organizations o: organization) {
				JSONObject jOb = new JSONObject();
				jOb.put("organizationId", o.getOrganizationId());
				jOb.put("establishmentName", o.getEstablishmentName());
				jOb.put("address", o.getAddress());
				jOb.put("email", o.getEmail());
				jOb.put("postalCode", o.getPostalCode());
				jOb.put("telephone", o.getTelephoneNo());
				jOb.put("website", o.getWebsite());
				
				array.put(jOb);
			}
			
			JsonParser jParser = new JsonParser();
			JsonElement jElement = jParser.parse(array.toString());
			List<ClientTableDataModel> orga =  objectMapper.readValue(jElement.toString(),  new TypeReference<List<ClientTableDataModel>>() { });
			return orga;
		} catch (Exception err) {
			err.printStackTrace();
		}
		return null;
	}

}
