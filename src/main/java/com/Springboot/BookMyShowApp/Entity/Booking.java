package com.Springboot.BookMyShowApp.Entity;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Component
@Entity
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookingId ;
	@NotBlank(message = "Movie name cannnot be blank")
	@NotNull(message = "Movie name cannot be null")
	private String bookingMovieName ;
	private LocalTime bookingShowTime ;
	private String seatNo ;
	private Time showTime ;
	private int noOfTickets ;
	private double totalAmount ;
	@OneToOne
	private Movie movie ;
	@ManyToMany
	private List<Seat> ticketSeat ;
	@ManyToOne
	private Payment bookingPayment ;
	
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public String getBookingMovieName() {
		return bookingMovieName;
	}
	public void setBookingMovieName(String bookingMovieName) {
		this.bookingMovieName = bookingMovieName;
	}
	public LocalTime getBookingShowTime() {
		return bookingShowTime;
	}
	public void setBookingShowTime(LocalTime localTime) {
		this.bookingShowTime = localTime;
	}
	public String getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}
	public Time getShowTime() {
		return showTime;
	}
	public void setShowTime(Time showTime) {
		this.showTime = showTime;
	}
	public int getNoOfTickets() {
		return noOfTickets;
	}
	public void setNoOfTickets(int noOfTickets) {
		this.noOfTickets = noOfTickets;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public List<Seat> getTicketSeat() {
		return ticketSeat;
	}
	public void setTicketSeat(List<Seat> ticketSeat) {
		this.ticketSeat = ticketSeat;
	}
	public Payment getBookingPayment() {
		return bookingPayment;
	}
	public void setBookingPayment(Payment bookingPayment) {
		this.bookingPayment = bookingPayment;
	}
	
	
	
	
	
}
