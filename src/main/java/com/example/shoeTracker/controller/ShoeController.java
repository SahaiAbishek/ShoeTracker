package com.example.shoeTracker.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.shoeTracker.entity.ShoeEntity;
import com.example.shoeTracker.entity.UserEntity;
import com.example.shoeTracker.model.Shoe;
import com.example.shoeTracker.repo.UserRepo;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class ShoeController {

	Logger logger = LoggerFactory.getLogger(ShoeController.class);

	@Autowired
	private UserRepo userRepo;
//	@Autowired
//	private ShoeRepo shoeRepo;

	@GetMapping(value = "/users/{userID}/shoes/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Shoe> getAllShoes(@PathVariable String userID) {
		return new ResponseEntity<>(new Shoe(), HttpStatus.OK);
	}

	@GetMapping(value = "/users/{userID}/shoes/search", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Handled Exception", response = Exception.class),
			@ApiResponse(code = 200, message = "OK", response = Shoe.class) })
	public ResponseEntity<Shoe> findShoe(@PathVariable String userID, @RequestParam(required = false) String shoeName,
			@RequestParam(required = false) String shoeID, @RequestParam(required = false) String brand,
			@RequestParam(required = false) String model) throws Exception {
		if (shoeName == null && shoeID == null && brand == null && model == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(new Shoe(), HttpStatus.OK);
	}

	@PostMapping(value = "/users/{userID}/shoes/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Shoe> addShoe(@PathVariable String userID, @RequestBody Shoe shoe) throws Exception {
		try {
			Optional<UserEntity> optUserEntity = userRepo.findById(new Long(userID));
			UserEntity userEntity = optUserEntity.get();
			ShoeEntity shoeEntity = new ShoeEntity();
			BeanUtils.copyProperties(shoe, shoeEntity);
			userEntity.getShoes().add(shoeEntity);
			userRepo.save(userEntity);
			return new ResponseEntity<>(new Shoe(), HttpStatus.OK);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new Exception(ex.getMessage());
		}
	}

	@PutMapping(value = "/users/{userID}/shoes/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Shoe> update(@PathVariable String userID, @RequestParam(required = false) String shoeName,
			@RequestParam(required = false) String shoeID, @RequestParam(required = false) String brand,
			@RequestParam(required = false) String model, @RequestParam Shoe shoe) throws Exception {
		if (shoeName == null && shoeID == null && brand == null && model == null) {
			throw new Exception("All the search parameters are empty");
		}
		return new ResponseEntity<>(new Shoe(), HttpStatus.OK);
	}

	@DeleteMapping(value = "/users/{userID}/shoes/{shoeID}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Shoe> deleteShoe(@PathVariable String userID, @PathVariable String shoeID) throws Exception {
		return new ResponseEntity<>(new Shoe(), HttpStatus.OK);
	}

}
