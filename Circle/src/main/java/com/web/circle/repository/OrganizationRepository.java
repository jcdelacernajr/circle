package com.web.circle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.web.circle.model.entity.Organizations;

@Repository
public interface OrganizationRepository extends JpaRepository<Organizations, Long>  {
	
	/**
	 * Get all the organizations data. 
	 * */
	@Query("FROM Organizations WHERE organization_id != 1")
	List<Organizations> getOrganizationList();

}
