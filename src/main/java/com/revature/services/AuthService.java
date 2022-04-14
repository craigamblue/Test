package com.revature.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dtos.UserDTO;
import com.revature.models.User;
import com.revature.repositories.UserRepository;

@Service
public class AuthService {

	private UserRepository ur;

	private static Logger log = LoggerFactory.getLogger(AuthService.class);

	@Autowired
	public AuthService(UserRepository ur) {
		super();
		this.ur = ur;
	}

	public UserDTO login(String username, String password) {
		// retrive user from db username sent by request, returns null if doesn't exist
		User principal = ur.findUserByUsername(username);

		// Check credentials sent in request vs those in db
		if (principal == null || !password.equals(principal.getPassword())) {
			log.warn("User: " + principal + " had invalid credentials");
			return null;
		}
		return new UserDTO(principal);
	}

	public String generateToken(UserDTO principal) {
		
		return principal.getId()+":"+principal.getUsername();
	}
}
