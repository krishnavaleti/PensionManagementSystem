package com.microservice.processpension.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.microservice.processpension.entity.PensionerDetails;

@FeignClient(name = "PENSIONDETAILS-MICROSERVICE", url="http://localhost:8082")
public interface PensionDetailClient {

	@GetMapping("pensionerDetail/{aadhaarNumber}")
	public PensionerDetails findByAadhaarNumber(@RequestHeader("Authorization") String token, @PathVariable String aadhaarNumber);
	
}
