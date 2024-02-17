package com.Springboot.BookMyShowApp.Entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Component
@Entity
public class TheatreAdmin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int theatreAdmId ;
	@NotBlank(message = "TheatreAdmin name cannnot be blank")
	@NotNull(message = "TheatreAdmin name cannot be null")
	private String theatreAdmName ;
	@NotBlank(message = "theatreAdmin Email cannnot be blank")
	@NotNull(message = "theatreAdmin Email cannot be null")
	@Email
	private String theatreAdmEmail ;
	@NotNull(message="password cannot be null")
	@NotBlank(message = "password cannot be blank")
	@Size(min=8, message = "password must contain minimum 8 characters.")
	@Pattern(regexp = "^(?=.*[a-z]) (?=.*[A-Z]) (?=.*\\d) (?=.*[@#$%^&+!]).{8,}$",
	message = "password must have at least 1 digit, 1 uppercase, 1 lowercase and 1 special character.")
	private String theatreAdmPassword ;
	@OneToOne(cascade = CascadeType.ALL)
	private Theatre adminTheatre;
	public int getTheatreAdmId() {
		return theatreAdmId;
	}
	public void setTheatreAdmId(int theatreAdmId) {
		this.theatreAdmId = theatreAdmId;
	}
	public String getTheatreAdmName() {
		return theatreAdmName;
	}
	public void setTheatreAdmName(String theatreAdmName) {
		this.theatreAdmName = theatreAdmName;
	}
	public String getTheatreAdmEmail() {
		return theatreAdmEmail;
	}
	public void setTheatreAdmEmail(String theatreAdmEmail) {
		this.theatreAdmEmail = theatreAdmEmail;
	}
	public String getTheatreAdmPassword() {
		return theatreAdmPassword;
	}
	public void setTheatreAdmPassword(String theatreAdmPassword) {
		this.theatreAdmPassword = theatreAdmPassword;
	}
	public Theatre getAdminTheatre() {
		return adminTheatre;
	}
	public void setAdminTheatre(Theatre adminTheatre) {
		this.adminTheatre = adminTheatre;
	}
	
	
}
