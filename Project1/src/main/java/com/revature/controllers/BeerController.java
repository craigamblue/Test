package com.revature.controllers;

import java.util.List;
import java.util.UUID;

import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Beer;
import com.revature.services.AuthService;
import com.revature.services.BeerService;
import com.revature.services.UserService;

@RestController
@RequestMapping("/beers")
public class BeerController {

	private BeerService bs;
	private UserService us;
	private AuthService as;

	// Better practice to use class logger over root logger as we can specify
	// different logger configurations based on class
	private static Logger log = LoggerFactory.getLogger(BeerController.class);

	@Autowired
	public BeerController(BeerService bs, UserService us, AuthService as) {
		super();
		this.bs = bs;
		this.us = us; 
		this.as = as;
	}

	@GetMapping
	public ResponseEntity<List<Beer>> getAllBeers() {
		log.info("HTTP Request for all beers");
		log.debug("Got all beers Successfull");
		return new ResponseEntity<>(bs.getAllBeers(), HttpStatus.OK);
	} 

	@GetMapping("/{id}")
	public ResponseEntity<Beer> getById(@PathVariable("id") int id) {

			log.debug("Got beers by Id Successfull");
			return new ResponseEntity<>(bs.getBeerbyId(id), HttpStatus.OK);

	}



	@PostMapping // Needs to be string to have entity print out
	public ResponseEntity<String> createBeer(@RequestBody Beer beer, @RequestHeader("Authorization") String token) {
		char idString = token.charAt(0);
		int id = Character.getNumericValue(idString);
		MDC.put("RequestId",  UUID.randomUUID().toString());
//		if (token == null) {
		if (us.getUserById(id).equals(us.getUserById(1)) || us.getUserById(id).equals(us.getUserById(2))
				|| us.getUserById(id).equals(us.getUserById(4))) {

			Beer newBeer = bs.createBeer(beer);
			log.debug("Beer created by Employee Successfull");
			log.info("New beer " + newBeer.getBeerName() + " added to inventory by " + us.getUserById(id));
			return new ResponseEntity<>("New beer " + newBeer.getBeerName() + " added to inventory by employee",
					HttpStatus.CREATED);

		}
		log.warn("User: " + us.getUserById(id) + " without proper access attempted to add a beer.");
		return new ResponseEntity<>("You are not allowed, you are now being tracked by Craig.",
				HttpStatus.NOT_ACCEPTABLE);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteBeer(@PathVariable("id") int beerId,
			@RequestHeader("Authorization") String token) {
		MDC.put("RequestId",  UUID.randomUUID().toString());
		char idString = token.charAt(0);
		int userId = Character.getNumericValue(idString);
//		if (token == null) {
		if (us.getUserById(userId).equals(us.getUserById(1)) || us.getUserById(userId).equals(us.getUserById(2))
				|| us.getUserById(userId).equals(us.getUserById(4))) {
			log.debug("Beer removed by employee successfull");
			log.info("Beer " + bs.getBeerbyId(beerId) + " has been removed from inventory " + us.getUserById(userId));
			bs.deleteBeerById(beerId);

			return new ResponseEntity<>("Beer  has been removed from inventory " + us.getUserById(userId),
					HttpStatus.OK);

		} else {
			log.warn("User: " + us.getUserById(userId) + " attempted to delete a beer");
			return new ResponseEntity<>("You are not allowed, you are now being tracked by Craig.",
					HttpStatus.NOT_ACCEPTABLE);
		}

	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> purchaseBeer(@PathVariable("id") int id, Beer beer){
		MDC.put("RequestId",  UUID.randomUUID().toString());
		bs.updatebeer(beer, id);
		log.debug("Beer bought succesfully");
		log.info("Beer bought");
		return new ResponseEntity<>("Beer bought ", HttpStatus.OK);
	}

}
