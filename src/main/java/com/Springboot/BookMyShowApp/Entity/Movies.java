package com.Springboot.BookMyShowApp.Entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Component
public class Movies {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int movieId ;
	private String movieName ;
	private String showTime ;
	private String showLanguage ;
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getShowTime() {
		return showTime;
	}
	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}
	public String getShowLanguage() {
		return showLanguage;
	}
	public void setShowLanguage(String showLanguage) {
		this.showLanguage = showLanguage;
	}

	
	
	
	
}
