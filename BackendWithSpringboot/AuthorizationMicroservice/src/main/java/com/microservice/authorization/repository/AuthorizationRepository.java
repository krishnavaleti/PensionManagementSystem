package com.microservice.authorization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.authorization.entity.UserData;

@Repository
public interface AuthorizationRepository extends JpaRepository<UserData,Integer>{

	UserData findByUserName(String username);
}
