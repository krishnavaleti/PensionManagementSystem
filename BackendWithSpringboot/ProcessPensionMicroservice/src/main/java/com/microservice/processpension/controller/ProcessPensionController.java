package com.microservice.processpension.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.processpension.client.AuthClient;
import com.microservice.processpension.client.PensionDetailClient;
import com.microservice.processpension.entity.PensionDetails;
import com.microservice.processpension.entity.PensionTransactionDetail;
import com.microservice.processpension.entity.ProcessPensionInput;
import com.microservice.processpension.exception.ResourceNotFoundException;
import com.microservice.processpension.service.ProcessPensionService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProcessPensionController {
	
	private PensionDetailClient pensiondetailsClient;
	private ProcessPensionService processPensionService;

	@Autowired
	public ProcessPensionController(PensionDetailClient pensiondetailsClient, ProcessPensionService processPensionService) {
		this.pensiondetailsClient = pensiondetailsClient;
		this.processPensionService = processPensionService;
	}

	@RequestMapping(value="/processPension",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PensionDetails> findByAadhaarNumber(@RequestHeader(name = "Authorization") String token,
			@RequestBody ProcessPensionInput processPensionInput) {
		PensionDetails result = null;
		try {
			result = processPensionService.getPensionerDetail(token, processPensionInput);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Invalid Pensioner Input");
		}
		return ResponseEntity.ok(result);
	}

	@PostMapping("/ProcessPensionTransaction")
	public PensionTransactionDetail saveTransactionDetail(@RequestHeader(name = "Authorization") String token,
			@RequestBody PensionDetails pensiondetails) throws Exception {
		PensionTransactionDetail pensionTransactionDetail = null;
		try {
			System.out.println(pensiondetails);
			//System.out.println(token);
			pensionTransactionDetail = processPensionService.savePensionTransactionDetail(token, pensiondetails);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Pension Detail not coreect");
		}
		return pensionTransactionDetail;
	}






}
