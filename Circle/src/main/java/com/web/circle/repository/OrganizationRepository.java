package com.web.circle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.web.circle.model.entity.Organizations;

@Repository
public interface OrganizationRepository extends JpaRepository<Organizations, Long>  {
	
	/**
	 * Get all the organizations data. 
	 * */
	@Query("FROM Organizations WHERE organization_id != 1 ORDER BY organization_id DESC")
	List<Organizations> getOrganizationList();
	
	/**
	 * Get the selected organization.
	 * 
	 * @param organizationId The selected organization id
	 * */
	@Query("FROM Organizations WHERE organization_id=:organizationId")
	List<Organizations> getOrganization(@Param("organizationId") long organizationId);

}
