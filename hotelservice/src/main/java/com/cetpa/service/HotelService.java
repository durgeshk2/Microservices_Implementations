package com.cetpa.service;
import java.util.*;
import com.cetpa.entities.Hotel;

public interface HotelService 
{
	//create
	Hotel create(Hotel hotel);
	//getAll
	List<Hotel> getAll();
	//getSingle
	Hotel get(String id);

	
}
