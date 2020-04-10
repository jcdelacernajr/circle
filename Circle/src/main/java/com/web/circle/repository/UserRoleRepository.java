package com.web.circle.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.circle.model.entity.UserRoles;

/**
 * 
 * @author jr
 * */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRoles, Long> {

	List<UserRoles> findAllByUsers(long user_fk);

}
