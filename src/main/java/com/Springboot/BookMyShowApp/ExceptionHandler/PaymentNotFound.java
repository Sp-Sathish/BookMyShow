package com.Springboot.BookMyShowApp.ExceptionHandler;

public class PaymentNotFound extends RuntimeException{

String message;
	
	public String getMessage() {
		return message;
	}

	public PaymentNotFound(String message) {
		this.message = message;
	}
}
