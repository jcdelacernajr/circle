package com.web.circle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.circle.model.entity.Branch;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {

}
