package com.microservice.pensionerdetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.microservice.pensionerdetails.repository.PensionDetailsRepository;
import com.microservice.pensionerdetails.service.PensionDetailsServiceImpl;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableAutoConfiguration
@EnableFeignClients
@EnableEurekaClient
public class PensionerDetailsMicroserviceApplication {
	
	@Autowired
	public PensionDetailsRepository pensionrepository;
	
	@Autowired
	public PensionDetailsServiceImpl pensionservice;

	public static void main(String[] args) {
		SpringApplication.run(PensionerDetailsMicroserviceApplication.class, args);
	}
	
}
