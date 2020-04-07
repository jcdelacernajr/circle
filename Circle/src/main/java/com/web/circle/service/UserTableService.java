package com.web.circle.service;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.springframework.util.StringUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.web.circle.model.UsersModel;
import com.web.circle.model.entity.Users;
import com.web.circle.model.UsersComparators;
import com.web.circle.model.tablepaging.Column;
import com.web.circle.model.tablepaging.Order;
import com.web.circle.model.tablepaging.Page;
import com.web.circle.model.tablepaging.PagingRequest;
import com.web.circle.repository.UserRepo;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * This class is for the User maintenance table 
 * Using Bootstrap DataTables
 * 
 * @author JC Dela Cerna Jr. April 2020
 */
@Slf4j
@Service
public class UserTableService {
	
	private static final Comparator<UsersModel> EMPTY_COMPARATOR = (e1, e2) -> 0;
	 
	// User repository
	private final UserRepo userRepository;
	 
	 
	@Autowired
	public UserTableService(UserRepo userRepository) {
    	this.userRepository = userRepository;
	}

		public Page<UsersModel> getUsers(PagingRequest pagingRequest) {
	        ObjectMapper objectMapper = new ObjectMapper();

	        try {
	        	// Get the json value from json file. For testing purposes.
	            //List<UsersModel> users = objectMapper.readValue(getClass().getClassLoader().getResourceAsStream("users.json"), 
	            //        new TypeReference<List<UsersModel>>() {
                //});
	        	
	        	
	        	JSONArray arr  = new JSONArray();
	            List<Users> uEntity = userRepository.findAll();
	            for (Users e : uEntity) {
	            	JSONObject jo = new JSONObject();
	            	jo.put("userId", e.getUserId());
	            	jo.put("organizationFk", e.getOrganizationFk());
	            	jo.put("email", e.getEmail());
	            	jo.put("username", e.getUsername());
	            	jo.put("password", "****");
	            	jo.put("active", e.getIsActive());
	            	jo.put("accountNonExpired", e.isAccountNonExpired());
	            	jo.put("accountNonLocked", e.isAccountNonLocked());
	            	jo.put("credentialsNonExpired", e.isCredentialsNonExpired());
	            	arr.put(jo);
				}
	            JsonParser jParser = new JsonParser();
	            JsonElement jElement = jParser.parse(arr.toString());
	            // System.out.println("arr: " +  jElement);
	            List<UsersModel> users =  objectMapper.readValue(jElement.toString(),  new TypeReference<List<UsersModel>>() { });
	            return getPage(users, pagingRequest);

	        } catch (Exception e) {
	        	log.error(e.getMessage(), e);
	        }

	        return new Page<>();
	    }

	    private Page<UsersModel> getPage(List<UsersModel> users, PagingRequest pagingRequest) {
	        List<UsersModel> filtered = users.stream().sorted(sortUsers(pagingRequest))
	                                           .filter(filterUsers(pagingRequest))
	                                           .skip(pagingRequest.getStart())
	                                           .limit(pagingRequest.getLength())
	                                           .collect(Collectors.toList());

	        long count = users.stream().filter(filterUsers(pagingRequest)).count();

	        Page<UsersModel> page = new Page<>(filtered);
	        page.setRecordsFiltered((int) count);
	        page.setRecordsTotal((int) count);
	        page.setDraw(pagingRequest.getDraw());

	        return page;
	    }

	    private Predicate<UsersModel> filterUsers(PagingRequest pagingRequest) {
	        if (pagingRequest.getSearch() == null || StringUtils.isEmpty(pagingRequest.getSearch().getValue())) {
	            return user -> true;
	        }

	        String value = pagingRequest.getSearch().getValue();
	        
	        log.info("Value: "+ value);

	        return user -> user.getEmail().toLowerCase().contains(value) || user.getUsername().toLowerCase().contains(value) || 
	        		String.valueOf(user.getUserId()).toLowerCase().contains(value);
	    }

	    private Comparator<UsersModel> sortUsers(PagingRequest pagingRequest) {
	        if (pagingRequest.getOrder() == null) {
	            return EMPTY_COMPARATOR;
	        }

	        try {
	            Order order = pagingRequest.getOrder().get(0);

	            int columnIndex = order.getColumn();
	            Column column = pagingRequest.getColumns().get(columnIndex);

	            Comparator<UsersModel> comparator = UsersComparators.getComparator(column.getData(), order.getDir());
	            if (comparator == null) {
	                return EMPTY_COMPARATOR;
	            }

	            return comparator;

	        } catch (Exception e) {
	            log.error(e.getMessage(), e);
	        }

	        return EMPTY_COMPARATOR;
	    }

}
