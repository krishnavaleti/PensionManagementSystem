package com.microservice.authorization;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

import com.microservice.authorization.controller.AuthorizationController;
import com.microservice.authorization.entity.UserData;
import com.microservice.authorization.repository.AuthorizationRepository;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableAutoConfiguration
@EnableFeignClients

public class AuthorizationMicroserviceApplication {

	private AuthorizationRepository authorizationrepository;
	
	
	@Autowired
	public AuthorizationMicroserviceApplication(AuthorizationRepository authorizationrepository) {
		this.authorizationrepository = authorizationrepository;
	}
	
	//Passing Data to Entity->UserData and Saving values to H2 Database

	@PostConstruct
	public void initUser() {
		List<UserData> users = Stream.of(new UserData(1, "testOne", "test@1234"), new UserData(2, "admin", "admin")

		).collect(Collectors.toList());
		authorizationrepository.saveAll(users);
	}

	public static void main(String[] args) {
		SpringApplication.run(AuthorizationMicroserviceApplication.class, args);
	}

}
