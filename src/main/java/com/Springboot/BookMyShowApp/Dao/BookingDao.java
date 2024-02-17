package com.Springboot.BookMyShowApp.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Springboot.BookMyShowApp.Entity.Booking;
import com.Springboot.BookMyShowApp.Repository.BookingRepo;

@Repository
public class BookingDao {

	@Autowired
	private BookingRepo bookingRepo ;
	
	public Booking saveBooking(Booking booking)
	{
		return bookingRepo.save(booking);
	}
	
	public Booking findBooking(int bookingId)
	{
		Optional<Booking> opBooking = bookingRepo.findById(bookingId) ;
		if( opBooking.isPresent())
		{
			return opBooking.get();
		}
		return null ;
	}
	public Booking deleteBooking(int bookingId)
	{
		Booking exiBooking = findBooking(bookingId);
	    bookingRepo.delete(exiBooking);
	    return exiBooking ;
	}
	public List<Booking> findAllBooking()
	{
		return bookingRepo.findAll();
	}
	
	public Booking updateBooking(Booking booking , int bookingId)
	{
		Booking exBooking = findBooking(bookingId);
		if(exBooking != null){
			booking.setBookingId(bookingId);
			return bookingRepo.save(booking);
		}
		return null;
		
	}
}
