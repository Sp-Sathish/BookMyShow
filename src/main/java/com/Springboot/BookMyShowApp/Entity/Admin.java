package com.Springboot.BookMyShowApp.Entity;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Component
@Entity
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int admId ;
	private String admName ;
	private String admEmail ;
	private String admPassword ;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Movies> moviesList ;
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
	public List<Movies> getMoviesList() {
		return moviesList;
	}
	public void setMoviesList(List<Movies> moviesList) {
		this.moviesList = moviesList;
	}
	
	
	
}
