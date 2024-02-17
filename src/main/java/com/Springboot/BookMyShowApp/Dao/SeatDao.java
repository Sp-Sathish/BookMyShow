package com.Springboot.BookMyShowApp.Dao;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Springboot.BookMyShowApp.Entity.Booking;
import com.Springboot.BookMyShowApp.Entity.Seat;
import com.Springboot.BookMyShowApp.Repository.SeatRepo;
@Repository
public class SeatDao {

	@Autowired
	private SeatRepo seatRepo ;
	
	public Seat saveSeat (Seat seat)
	{
		return seatRepo.save(seat); 
	}
	
	public Seat findSeat(int seatId)
	{
		Optional<Seat> opSeat = seatRepo.findById(seatId) ;
		if( opSeat.isPresent())
		{
			return opSeat.get();
		}
		return null ;
	}
	
	public Seat deleteSeat(int seatId)
	{
		Seat  exiSeat= findSeat(seatId);
	    seatRepo.delete(exiSeat);
	    return exiSeat ;
	}
	public List<Seat> findAllSeats()
	{
		return seatRepo.findAll();
	}
	public Seat updateSeat(Seat seat , int seatId)
	{
		Seat exSeat = findSeat(seatId);
		if(exSeat != null){
			seat.setSeatId(seatId);
			return seatRepo.save(seat);
		}
		return null;
		
	}
}
