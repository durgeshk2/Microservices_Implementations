package com.cetpa.services;

import java.util.List;

import com.cetpa.entities.Rating;

public interface RatingService 
{
	//create
	Rating create(Rating rating)
;	//get All ratings
	List<Rating> getRatings();
	//get all by UserId
	List<Rating> getRatingByUserId(String userId);
	//get all by hotel
	List<Rating> getRatingByHotelId(String hotelId);

}
