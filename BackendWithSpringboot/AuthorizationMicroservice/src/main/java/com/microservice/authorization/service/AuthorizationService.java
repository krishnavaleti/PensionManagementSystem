package com.microservice.authorization.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.microservice.authorization.entity.UserData;
import com.microservice.authorization.exception.ResourceNotFound;
import com.microservice.authorization.repository.AuthorizationRepository;


@Service
public class AuthorizationService implements UserDetailsService{

	private AuthorizationRepository authorizationRepository;

	@Autowired
	public AuthorizationService(AuthorizationRepository authorizationRepository) {
		this.authorizationRepository = authorizationRepository;
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			UserData user = authorizationRepository.findByUserName(username);
			System.out.println(user);

			return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
					new ArrayList<>());

		} catch (Exception e) {
			throw new ResourceNotFound("User by the given username not found");
		}

	}
	



}
