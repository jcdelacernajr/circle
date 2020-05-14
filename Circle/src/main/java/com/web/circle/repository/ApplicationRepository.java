package com.web.circle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.circle.model.entity.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

}
