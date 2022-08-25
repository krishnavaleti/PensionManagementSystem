package com.microservice.authorization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.microservice.authorization.entity.UserData;
import com.microservice.authorization.exception.ResourceNotFound;
import com.microservice.authorization.service.AuthorizationService;
import com.microservice.authorization.util.JWTUtil;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthorizationController {
	private JWTUtil jwtUtil;

	private AuthorizationService authorizationService;

	private AuthenticationManager authenticationManager;

	
	@Autowired
	public AuthorizationController(JWTUtil jwtUtil, AuthorizationService authorizationService,
			AuthenticationManager authenticationManager) {

		this.jwtUtil = jwtUtil;
		this.authorizationService = authorizationService;
		this.authenticationManager = authenticationManager;
	}

	//starting message 
	
	
	@GetMapping("/login")
	public ResponseEntity<String> welcome() {

		return ResponseEntity.ok("Welcome to Login Security application");
	}

	/*
	 * jwt token generation using user name and password & check in postman with
	 * link http://localhost:8080/authenticate
	 */
	@PostMapping("/authenticate")
	
	public ResponseEntity<String> generateToken(@RequestBody UserData userData) throws Exception {
	 
		System.out.println("UserName :" + userData.getUserName());
		System.out.println("Password" + userData.getPassword());
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(userData.getUserName(), userData.getPassword()));
       
		} catch (Exception e) {
			throw new ResourceNotFound("user not found");
		}

		return ResponseEntity.ok(jwtUtil.generateToken(userData.getUserName()));
	}
	
	
	//Validation of generated Token & Check in postman with http://localhost:8080/authorize
	@GetMapping("/authorize")
	public ResponseEntity<?> authorization(@RequestHeader("Authorization") String token1) {

		String token = token1.substring(7);
		System.out.println("Token Value: " +token1);
		System.out.println("Token Value: " +token);

		UserDetails user = authorizationService.loadUserByUsername(jwtUtil.extractUsername(token));

		if (jwtUtil.validateToken(token, user)) {
			return new ResponseEntity<>(true, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(false, HttpStatus.FORBIDDEN);
		}

	}

}

	

