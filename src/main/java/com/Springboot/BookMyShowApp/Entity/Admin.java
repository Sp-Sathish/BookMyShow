package com.Springboot.BookMyShowApp.Entity;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Component
@Entity
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int admId ;
	@NotNull(message="Admin name cannot be null.")
	@NotBlank(message ="Admin name cannot be blank.")
	private String admName ;
	@NotBlank(message = "Admin email cannot be blank")
	@NotNull(message="Admin email cannot be null")
	@Email
	private String admEmail ;
	@NotNull(message="password cannot be null")
	@NotBlank(message = "password cannot be blank")
	@Size(min=8, message = "password must contain minimum 8 characters.")
	@Pattern(regexp = "^(?=.*[a-z]) (?=.*[A-Z]) (?=.*\\d) (?=.*[@#$%^&+!]).{8,}$",
	message = "password must have at least 1 digit, 1 uppercase, 1 lowercase and 1 special character.")
	
	private String admPassword ;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Theatre> theatreList ;
	public int getAdmId() {
		return admId;
	}
	public void setAdmId(int admId) {
		this.admId = admId;
	}
	public String getAdmName() {
		return admName;
	}
	public void setAdmName(String admName) {
		this.admName = admName;
	}
	public String getAdmEmail() {
		return admEmail;
	}
	public void setAdmEmail(String admEmail) {
		this.admEmail = admEmail;
	}
	public String getAdmPassword() {
		return admPassword;
	}
	public void setAdmPassword(String admPassword) {
		this.admPassword = admPassword;
	}
	public List<Theatre> getTheatreList() {
		return theatreList;
	}
	public void setTheatreList(List<Theatre> theatreList) {
		this.theatreList = theatreList;
	}
	
	
	
	
}
