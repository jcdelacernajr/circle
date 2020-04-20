package com.web.circle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.circle.model.entity.Organizations;

@Repository
public interface OrganizationRepository extends JpaRepository<Organizations, Long>  {

}
