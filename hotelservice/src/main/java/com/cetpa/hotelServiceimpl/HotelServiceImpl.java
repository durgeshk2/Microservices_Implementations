package com.cetpa.hotelServiceimpl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cetpa.entities.Hotel;
import com.cetpa.exception.ResourceNotFoundException;
import com.cetpa.repositories.HotelRepository;
import com.cetpa.service.HotelService;


@Service
public class HotelServiceImpl implements HotelService
{

	@Autowired
	private HotelRepository hotelRepository;
	@Override
	public Hotel create(Hotel hotel)
	{
		String hotelId=UUID.randomUUID().toString();
		hotel.setId(hotelId);
		return hotelRepository.save(hotel) ;
	}

	@Override
	public List<Hotel> getAll() {
		
		return hotelRepository.findAll();
	}

	@Override
	public Hotel get(String id)
	{
		
      return hotelRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Resource with given id not found"));
	}

}
