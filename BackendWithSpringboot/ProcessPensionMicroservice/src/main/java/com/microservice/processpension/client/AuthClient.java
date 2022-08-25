package com.microservice.processpension.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "AUTH-MICROSERVICE", url="http://localhost:8081")
public interface AuthClient {

	@GetMapping("/authorize")
	public Boolean authorization(@RequestHeader("Authorization") String token);

}
