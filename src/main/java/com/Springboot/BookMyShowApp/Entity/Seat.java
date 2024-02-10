package com.Springboot.BookMyShowApp.Entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Component
@Entity
public class Seat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int seatNo ;
	private SeatType seatType ;
	private String seatPrice ;
	public int getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}
	
	public SeatType getSeatType() {
		return seatType;
	}
	public void setSeatType(SeatType seatType) {
		this.seatType = seatType;
	}
	public String getSeatPrice() {
		return seatPrice;
	}
	public void setSeatPrice(String seatPrice) {
		this.seatPrice = seatPrice;
	}
	
	
	
}
