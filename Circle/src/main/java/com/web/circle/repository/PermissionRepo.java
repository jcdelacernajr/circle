package com.web.circle.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.circle.model.entity.Permissions;

/**
 * 
 * @author jr
 * */
@Repository
public interface PermissionRepo extends JpaRepository<Permissions, Long> {
	
	Permissions findAllByPermissionId(Long permission_fk);
}
