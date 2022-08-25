package com.microservice.pensionerdetails.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import com.microservice.pensionerdetails.client.AuthorizationClient;
import com.microservice.pensionerdetails.entity.PensionerDetail;
import com.microservice.pensionerdetails.exception.ResourceNotFoundException;
import com.microservice.pensionerdetails.repository.PensionDetailsRepository;
import com.microservice.pensionerdetails.service.PensionDetailsServiceImpl;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PensionDetailsController {

	private PensionDetailsRepository pensionrepository;

	private AuthorizationClient authclient;

	private PensionDetailsServiceImpl pensionservice;

	@Autowired
	public PensionDetailsController(PensionDetailsRepository pensionrepository, AuthorizationClient authclient,
			PensionDetailsServiceImpl pensionservice) {

		this.pensionservice = pensionservice;
		this.authclient = authclient;
		this.pensionrepository = pensionrepository;
	}

	@GetMapping("/pensionerDetail/{aadhaarNumber}")
	public PensionerDetail findByAadhaarNumber(@RequestHeader("Authorization") String token,
			@PathVariable Long aadhaarNumber) throws Exception {

		PensionerDetail pensionerDetail = null;
		try {
			System.out.println(authclient.authorization(token));
			if(authclient.authorization(token)) {
				pensionerDetail = pensionservice.getdetailsByAadhaarNumber(aadhaarNumber);
			} else {
				throw new Exception("Invalid token");
			}
		} catch (Exception e) {
			throw new Exception("Invalid token");
		}
		return pensionerDetail;

	}

}
