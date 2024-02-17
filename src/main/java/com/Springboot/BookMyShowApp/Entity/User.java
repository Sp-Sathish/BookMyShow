package com.Springboot.BookMyShowApp.Entity;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
@Component
@Entity
public class User{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId ;
	@NotBlank(message = "User name cannnot be blank")
	@NotNull(message = "User name cannot be null")
	private String userName ;
	@Email
	@NotBlank(message = "User Email cannnot be blank")
	@NotNull(message = "User Email cannot be null")
	private String userEmail ;
	@NotNull(message="password cannot be null")
	@NotBlank(message = "password cannot be blank")
	@Size(min=8, message = "password must contain minimum 8 characters.")
	@Pattern(regexp = "^(?=.*[a-z]) (?=.*[A-Z]) (?=.*\\d) (?=.*[@#$%^&+!]).{8,}$",
	message = "password must have at least 1 digit, 1 uppercase, 1 lowercase and 1 special character.")
	private String userPassword ;
	@OneToMany
	private List<Booking> bookingList;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public List<Booking> getBookingList() {
		return bookingList;
	}
	public void setBookingList(List<Booking> bookingList) {
		this.bookingList = bookingList;
	}
	
	
	
	
	
}
