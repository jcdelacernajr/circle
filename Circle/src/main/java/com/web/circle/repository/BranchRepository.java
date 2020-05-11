package com.web.circle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.web.circle.model.entity.Branch;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {

	/**
	 * Get the list of branch base on the selected id.
	 * 
	 * @param organizationId The selected organization id
	 * */
	@Query("FROM Branch WHERE organization_fk=:organizationId")
	List<Branch> findBranchsByOrganization(@Param("organizationId") long organizationId);
	
	/**
	 * Get the list of branch.
	 * */
	@Query("FROM Branch WHERE branch_id != 1 ORDER BY branch_id DESC")
	List<Branch> getBranchList();
}
