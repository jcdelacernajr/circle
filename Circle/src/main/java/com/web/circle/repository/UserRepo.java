package com.web.circle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.circle.model.entity.Users;

@Repository
public interface UserRepo extends JpaRepository<Users, Long> {
	Users findByUsername(String username);
	Users findByEmail(String email);
}
