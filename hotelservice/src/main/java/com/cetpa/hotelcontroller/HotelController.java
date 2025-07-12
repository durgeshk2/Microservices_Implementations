package com.cetpa.hotelcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.cetpa.entities.Hotel;
import com.cetpa.service.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController 
{
	@Autowired
private HotelService hotelService;
	
	//single
	//http://localhost:8080/hotels
	@PostMapping
	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel)
	{
		Hotel h1=hotelService.create(hotel);
		return ResponseEntity.status(HttpStatus.CREATED).body(h1);
	}
	
	//get single	
	//http://localhost:8081/hotels/adec433f-53ae-40e4-85da-201e3e576901
	@GetMapping("/{hotelId}")
	public ResponseEntity<Hotel> getSingleData( @PathVariable String  hotelId)
	{
		Hotel h1=hotelService.get(hotelId);
		return ResponseEntity.status(HttpStatus.OK).body(h1);
	}
	//getAll
	@GetMapping
	public ResponseEntity<List<Hotel>>  getHotelList()
	{
		       List<Hotel> hotelList=hotelService.getAll();
			   return ResponseEntity.status(HttpStatus.OK).body(hotelList);
	}
}
