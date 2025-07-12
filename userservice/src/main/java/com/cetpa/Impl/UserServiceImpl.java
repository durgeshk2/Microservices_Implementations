package com.cetpa.Impl;

import java.util.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cetpa.entities.Hotel;
import com.cetpa.entities.Rating;
import com.cetpa.entities.User;
import com.cetpa.exception.ResourceNotFoundException;
import com.cetpa.external.service.HotelService;
import com.cetpa.repositories.UserRepository;
import com.cetpa.services.UserService;

@Service
public class UserServiceImpl implements UserService
{
	@Autowired
   private UserRepository userRepository;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private HotelService hotelService;
	
	private Logger logger=LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public User saveUser(User user) 
	{
		//To generate real time user id in project
		String ramdomUserId=UUID.randomUUID().toString();
		user.setUserId(ramdomUserId);
		
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser()
	{
		// we can implement RestTemplate class to fetch all the details of all users and its rating and hotel details as we have done below
	    
		//return userRepository.findAll();
		List<User>user= userRepository.findAll();
		
		return user;
	}
    
	@Override
	public User getUser(String userId) {
		//get user from database with the help of user repository
		User user= userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("user with given id not found "+userId));
		//fetch Rating of above user from RatingService
		//http://localhost:8082/ratings/users/b89266bb-26f7-4e5c-b298-1c21bb364049
		Rating[] ratingsOfUser=restTemplate.getForObject("http://RATINGSERVICE/ratings/users/"+user.getUserId(), Rating[].class);
		
		logger.info("{} ",ratingsOfUser);
		
		List<Rating> ratings=Arrays.stream(ratingsOfUser).toList();
		
		List<Rating>ratingList=ratings.stream().map(rating->{
			//API call to hotel service to get hotel
			//http://localhost:8081/hotels/adec433f-53ae-40e4-85da-201e3e576901
		   // ResponseEntity<Hotel>forEntity=restTemplate.getForEntity("http://HOTELSERVICE/hotels/"+rating.getHotelId(),Hotel.class);
			//Hotel hotel=forEntity.getBody();
			Hotel hotel=hotelService.getHotel(rating.getHotelId());
			//logger.info("response Status code:{}",forEntity.getStatusCode());
			//set the hotels to ratings
			rating.setHotel(hotel);
			//return the rating
			return rating;
			
		}).collect(Collectors.toList());
		
		
		
		
		user.setRating(ratingList);
		return user;
	}

}
