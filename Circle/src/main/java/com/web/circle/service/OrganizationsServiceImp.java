package com.web.circle.service;

import java.util.List;

import javax.transaction.Transactional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.web.circle.controller.DTO.form.OrganizationSetupForm;
import com.web.circle.model.ClientTableDataModel;
import com.web.circle.model.entity.License;
import com.web.circle.model.entity.Organizations;
import com.web.circle.model.entity.UploadFile;
import com.web.circle.model.entity.Users;
import com.web.circle.repository.OrganizationRepository;
import com.web.circle.utils.Utils;

@Service
@Transactional
public class OrganizationsServiceImp implements OrganizationsService {
	
	@Autowired
	private OrganizationRepository organizationRepository;

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

	@Override
	public Organizations record(OrganizationSetupForm form) {
		
		// Organization table
		Organizations organization = new Organizations();
		organization.setOrganizationId(0);
		organization.setEstablishmentName(form.getEstablishmentName());
		organization.setType(form.getType());
		organization.setAddress(form.getAddress());
		
		// License table.
		License license = new License();
		license.setLicenseId(0);
		license.setCircleKey(Utils.stringToHexadecimal(form.getEstablishmentName()));
		
		// User table.
		Users user = new Users();
		user.setUserId(form.getUserId());
		license.setOrganizations(organization);
		license.setUsers(user);
		
		organization.setLicense(license);
		
		// File data.
		Long fileId = form.getFileId(); 
		if(fileId != null) {
			UploadFile uploadFile = new UploadFile();
			uploadFile.setUploadFileId(fileId);
			organization.setUploadFile(uploadFile);
		} else {
			// Set the logo id to 1. 
			// if the organization has no logo yet.
			UploadFile uploadFile = new UploadFile();
			uploadFile.setUploadFileId(1); // The default-no-image id 
			organization.setUploadFile(uploadFile);
		}
		
		return organizationRepository.save(organization);
	}

}

















