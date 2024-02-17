package com.Springboot.BookMyShowApp.ExceptionHandler;

public class AdminNotFound extends RuntimeException{

    String message;
	
	public String getMessage() {
		return message;
	}

	public AdminNotFound(String message) {
		this.message = message;
	}
}
