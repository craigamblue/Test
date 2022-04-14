package com.revature.controllers;

import java.util.UUID;

import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dtos.UserDTO;
import com.revature.services.AuthService;
import com.revature.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private UserService us;
	private AuthService as;
	private static Logger log = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	public AuthController(UserService us, AuthService as) {
		super();
		this.us = us;
		this.as = as;
	}
 
	@PostMapping
	public ResponseEntity<UserDTO> login(@RequestParam("username") String username, @RequestParam("password") String password) {

		//Adding to MDC a random id for new request generated which is added to log
		MDC.put("RequestId",  UUID.randomUUID().toString());
		// principal = null if login fails
		UserDTO principal = as.login(username, password);

		if (principal == null) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		// Set token as id:username
		String token = as.generateToken(principal);
		// create Http Headers
		HttpHeaders hh = new HttpHeaders();
		// Set tokenName, value
		hh.set("Authorization", token);
		
		log.debug(username + " logged in succesfully "+token); 
		log.info(username + "logged in.");
		 return new ResponseEntity<>(principal, hh, HttpStatus.FORBIDDEN);
	}
	
	

}
