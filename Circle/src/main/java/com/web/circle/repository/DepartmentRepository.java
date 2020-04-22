package com.web.circle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.circle.model.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
