package com.cetpa.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cetpa.entities.Rating;
import com.cetpa.services.RatingService;

@RestController
@RequestMapping("/ratings")
public class RatingControllers 
{
	//create rating
	@Autowired
	private RatingService ratingService;
	
	@PostMapping
	public ResponseEntity<Rating> createRating(@RequestBody Rating rating)
	{
		Rating rating1=ratingService.create(rating);	
		return ResponseEntity.status(HttpStatus.CREATED).body(rating1);
	}
	
	//get all 
	@GetMapping
	public ResponseEntity<List<Rating>> getAllRatings()
	{
		List<Rating> ratingList=ratingService.getRatings();
		return ResponseEntity.ok(ratingList);
	}
	
	//get all by User id
	@GetMapping("/users/{userId}")
	public ResponseEntity<List<Rating>> getRatingsByUserId( @PathVariable String userId)
	{
		List<Rating> ratingList=ratingService.getRatingByUserId(userId);
		return ResponseEntity.ok(ratingList);
	}
	//get all by Hotel id
	@GetMapping("/hotels/{hotelId}")
		public ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable String hotelId)
		{
			List<Rating> ratingList=ratingService.getRatingByHotelId(hotelId);
			return ResponseEntity.ok(ratingList);
		}
}
