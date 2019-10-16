package com.web.circle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.circle.model.entity.Permissions;
import com.web.circle.model.entity.Roles;

/**
 * 
 * @author jr
 * */
@Repository
public interface RoleRepo extends JpaRepository<Roles, Long>{
	
	Roles findAllByRoleId(Long role_id);
}
