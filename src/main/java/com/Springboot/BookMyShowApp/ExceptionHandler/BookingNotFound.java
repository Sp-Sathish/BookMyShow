package com.Springboot.BookMyShowApp.ExceptionHandler;

public class BookingNotFound extends RuntimeException {
String message;
	
	public String getMessage() {
		return message;
	}
	public BookingNotFound(String message) {
		this.message = message;
	}

}
