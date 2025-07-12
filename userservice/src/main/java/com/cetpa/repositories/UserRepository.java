package com.cetpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cetpa.entities.User;

public interface UserRepository extends JpaRepository<User,String>
{
	//JpaRepository class has all the requred basic methods to perform but, 
   // if you want to perform any custom methods or query write here
}
