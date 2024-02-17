package com.Springboot.BookMyShowApp.Entity;

import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Component
public class Movie {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int movieId ;
	@NotBlank(message = "movie name cannot be blank")
	@NotNull(message = "movie name cannot be null")
	private String movieName ;
	private LocalTime showTime ;
	@NotBlank(message = "movie language cannot be blank")
	@NotNull(message = "movie language cannot be null")
	private String showLanguage ;
	private int totalSeats ;
	@OneToMany
	List<Seat> seatList;
	
	
	public int getTotalSeats() {
		return totalSeats;
	}
	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}
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
	public LocalTime getShowTime() {
		return showTime;
	}
	public void setShowTime(LocalTime showTime) {
		this.showTime = showTime;
	}
	public String getShowLanguage() {
		return showLanguage;
	}
	public void setShowLanguage(String showLanguage) {
		this.showLanguage = showLanguage;
	}
	public List<Seat> getSeatList() {
		return seatList;
	}
	public void setSeatList(List<Seat> seatList) {
		this.seatList = seatList;
	}

	
	
	
	
}
