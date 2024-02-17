package com.Springboot.BookMyShowApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Springboot.BookMyShowApp.Entity.Booking;
import com.Springboot.BookMyShowApp.Entity.Payment;
import com.Springboot.BookMyShowApp.Entity.SeatType;
import com.Springboot.BookMyShowApp.Service.BookingService;
import com.Springboot.BookMyShowApp.Util.ResponseStructure;

@RestController
@RequestMapping("Booking")
public class BookingController {


	@Autowired
	private BookingService bookingService ;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Booking>> saveBooking(@RequestBody Booking booking)
	{
		return bookingService.saveBooking(booking);
	}
	@GetMapping
	public ResponseEntity<ResponseStructure<Booking>> findBooking(@RequestParam int bookingId)
	{
		return bookingService.findBooking(bookingId);
	}
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Booking>> deleteBooking(@RequestParam int bookingId)
	{
		return bookingService.deleteBooking(bookingId);
	}
	
	@GetMapping("AllBooking")
	public ResponseEntity<ResponseStructure<List<Booking>>> findAllBooking()
	{
		return bookingService.findAllBooking();
	}
	@PutMapping
	ResponseEntity<ResponseStructure<Booking>> updateBooking(@RequestBody Booking booking,@RequestParam int bookingId){
		return bookingService.updateBooking(booking,bookingId);
	}
	@PostMapping("bookTicket")
	ResponseEntity<ResponseStructure<Booking>> ticketBooking(@RequestParam String userEmail,@RequestParam String userPassword,@RequestParam int movieId,@RequestParam SeatType seatType,@RequestBody List<Integer> seatIds){
		return bookingService.ticketBooking(userEmail, userPassword, movieId, seatType, seatIds);
	}
	@DeleteMapping("cancelBooking")
	public ResponseEntity<ResponseStructure<Payment>> cancelBooking(int bookingId){
		return bookingService.cancelBooking(bookingId);
	}
	
}
