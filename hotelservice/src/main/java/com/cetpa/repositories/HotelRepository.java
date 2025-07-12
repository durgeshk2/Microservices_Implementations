package com.cetpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cetpa.entities.Hotel;

public interface HotelRepository  extends JpaRepository<Hotel,String>
{

}
