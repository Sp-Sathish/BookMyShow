package com.Springboot.BookMyShowApp.ExceptionHandler;

public class TheatreNotFound extends RuntimeException{
   
	String message;
	
	public String getMessage() {
		return message;
	}
	public TheatreNotFound(String message) {
		this.message = message;
	}

}
