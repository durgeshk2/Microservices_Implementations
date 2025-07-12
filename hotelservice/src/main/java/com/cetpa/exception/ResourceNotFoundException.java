package com.cetpa.exception;
import com.cetpa.hotelServiceimpl.*;
public class ResourceNotFoundException extends RuntimeException
{
	ResourceNotFoundException()
	{
		super("Resource Not found");
	}

	public ResourceNotFoundException(String s)
	{
		super(s);
	}
}
