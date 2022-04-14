package com.revature.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.controllers.BeerController;
import com.revature.dtos.UserDTO;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Beer;
import com.revature.models.User;
import com.revature.models.UserRole;
import com.revature.repositories.BeerRepository;
import com.revature.repositories.UserRepository;

@Service
public class UserService {

	private UserRepository ur;
	private BeerRepository br;
	
	private static Logger log = LoggerFactory.getLogger(BeerController.class);

	@Autowired
	public UserService(UserRepository ur, BeerRepository br) {
		super();
		this.ur = ur;
		this.br = br;
	}

	
	public List<UserDTO> getAllUsers() {
		List<User> users = ur.findAll();
		
		List<UserDTO> usersDTO = users.stream()
				.map((user) -> new UserDTO(user))
				.collect(Collectors.toList());
		return usersDTO;
	}
	
//	public int getBeerOwnerID() {
//		List<Beer> beers = br.findAll();
//		
//		
//		return 5;
//	}
	



	public UserDTO getUserById(int id) throws UserNotFoundException {
		User user = ur.findById(id).orElseThrow(UserNotFoundException::new);
		return new UserDTO(user);
	}


	public User createUser(User user) {

		return ur.save(user);
	}





//	public List<Beer> getUserBeers(int id) {
//		List<Beer> beers = br.findAll();
//		User user = ur.getById(id);
////		UserDTO userO = ur.getBeerOwner();
//		List<Beer> beersByUser = beers.stream()
//				.filter(beer -> beer.getBeerOwner().equals(user))
//				.map((beer) -> new Beer(beer))
//				.collect(Collectors.toList());
//
////  This function works 
////		List<Beer> beers = br.findAll();
////
////		List<Beer> beersByUser = beers.stream()
////				.filter(beer -> beer.getBeerName().toString().equals("DosXX"))
////				.map((beer) -> new Beer(beer))
////				.collect(Collectors.toList());
//
//		return beersByUser;
//	}

//	public List<Beer> getUserBeers(int id) throws UserNotFoundException   {
//	User user = ur.findById(id).orElseThrow(UserNotFoundException::new);
//	List<Beer> beers = br.findbyUsers(user);
//
//
//	return beers;
//}



	



}
