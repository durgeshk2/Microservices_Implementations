package com.cetpa.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cetpa.entities.User;
import com.cetpa.services.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@RestController
@RequestMapping("/users")
public class UserController 
{
	@Autowired
	private UserService userService;
	//create
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user)
	{
		User user1=userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user1);
	}
	//single user
	//http://localhost:8080/user/userId
	//this is link to communicate with rating Service	
	@GetMapping("/{userId}")
	
	@CircuitBreaker(name="ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
	//@RateLimiter(name="ratingHotelBreaker",fallbackMethod="ratingHotelFallback")
	public ResponseEntity<User> getSingleUser(@PathVariable String userId)
	{
		
		User user=userService.getUser(userId);
		return ResponseEntity.ok(user);
	}
	
	//creating fallback method for circuit_breaker
	
	public ResponseEntity<User> ratingHotelFallback(String userId,Exception ex)
	{
		final Logger logger = LoggerFactory.getLogger(User.class);
     logger.info("Fallback is excecuited because service is down:{}",ex.getMessage());
		User user=User.builder()
				.email("dummy@gmail.com")
				.name("Dummy")
				.about("This user is create dummy because some services is down") 
				.userId("141234")
				.build();
		
		//return new ResponseEntity<>(user,HttpStatus.OK);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	
	//all user
	@GetMapping
    public ResponseEntity<List<User>> getAllUser()
    {
    	List<User> userList=userService.getAllUser();
    	return ResponseEntity.ok(userList);
    }
}
