package com.example.shoeTracker.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.shoeTracker.entity.UserEntity;
import com.example.shoeTracker.model.User;
import com.example.shoeTracker.repo.UserRepo;

@RestController
public class UserController {

	Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserRepo userRepo;

	@GetMapping(value = "/users/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getAllUsers() {
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(value = "/users/{userName}/{password}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> validateUser(@PathVariable String userName, @PathVariable String password) {
		UserEntity userEntity = userRepo.find(userName, password);
		if (userEntity != null) {
			User user = new User();
			BeanUtils.copyProperties(userEntity, user);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/users/")
	public ResponseEntity<String> addUser(@RequestBody User user) {
		logger.info("Inside addUser ..");
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(user, userEntity);
		try {
			userRepo.save(userEntity);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("User created Successfully", HttpStatus.OK);
	}

}
