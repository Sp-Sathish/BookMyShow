package com.Springboot.BookMyShowApp.Service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Springboot.BookMyShowApp.Dao.BookingDao;
import com.Springboot.BookMyShowApp.Dao.MovieDao;
import com.Springboot.BookMyShowApp.Dao.PaymentDao;
import com.Springboot.BookMyShowApp.Dao.SeatDao;
import com.Springboot.BookMyShowApp.Entity.Booking;
import com.Springboot.BookMyShowApp.Entity.Movie;
import com.Springboot.BookMyShowApp.Entity.Payment;
import com.Springboot.BookMyShowApp.Entity.Seat;
import com.Springboot.BookMyShowApp.Entity.SeatType;
import com.Springboot.BookMyShowApp.ExceptionHandler.BookingNotFound;
import com.Springboot.BookMyShowApp.ExceptionHandler.SeatNotFound;
import com.Springboot.BookMyShowApp.Repository.SeatRepo;
import com.Springboot.BookMyShowApp.Repository.UserRepo;
import com.Springboot.BookMyShowApp.Util.ResponseStructure;

@Service
public class BookingService {

	@Autowired
	private BookingDao bookingDao ;
	@Autowired
	BookingDao tDao;
	@Autowired
	SeatDao sDao;
	@Autowired
	MovieService mService;
	@Autowired
	SeatRepo sRepo;
	@Autowired
	PaymentDao pDao;
	@Autowired
	MovieDao mDao;
	@Autowired
	UserRepo uDao;
	
	
	
	
	public ResponseEntity<ResponseStructure<Booking>> saveBooking(Booking booking)
	{
		ResponseStructure<Booking> structure =  new ResponseStructure<Booking>();
		structure.setMessage("Booking save success");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(bookingDao.saveBooking(booking));
		return new ResponseEntity<ResponseStructure<Booking>>(structure,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Booking>> findBooking(int bookingId)
	{
		ResponseStructure<Booking> structure  = new ResponseStructure<Booking>();
		Booking exiBooking= bookingDao.findBooking(bookingId);
		if(exiBooking != null)
		{
			
			structure.setMessage("Booking found success");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(exiBooking);
			return new ResponseEntity<ResponseStructure<Booking>>(structure,HttpStatus.FOUND);
		}
		throw new BookingNotFound("Booking not Found With Given Id");

	}
	public ResponseEntity<ResponseStructure<Booking>> updateBooking(Booking booking ,int bookingId)
	{
		ResponseStructure<Booking> structure = new ResponseStructure<Booking>() ;
		Booking exiBooking = bookingDao.findBooking(bookingId);
		if(exiBooking != null)
		{
			structure.setMessage("update Booking success");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(bookingDao.updateBooking(booking,bookingId));
			return new ResponseEntity<ResponseStructure<Booking>>(structure,HttpStatus.OK);
		}
		throw new BookingNotFound("Booking not Found With Given Id");
	}
	
	public ResponseEntity<ResponseStructure<Booking>> deleteBooking(int bookingId)
	{
		ResponseStructure<Booking> structure  = new ResponseStructure<Booking>();
		Booking exiBooking = bookingDao.findBooking(bookingId);
		if(exiBooking != null)
		{
			structure.setMessage("Booking delete success");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(exiBooking);
			return new ResponseEntity<ResponseStructure<Booking>>(structure,HttpStatus.OK);
		}
		throw new BookingNotFound("Booking not Found With Given Id");
	}
	public ResponseEntity<ResponseStructure<List<Booking>>> findAllBooking()
	{
			ResponseStructure<List<Booking>> structure  = new ResponseStructure<List<Booking>>();
			structure.setMessage("All Booking found success");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(bookingDao.findAllBooking());
			return new ResponseEntity<ResponseStructure<List<Booking>>>(structure ,HttpStatus.FOUND);	
	}
	
	public List<Seat> bookSeat(List<Seat> availableSeats, List<Integer> seatIds, int movieId){
		List<Seat> seats=new ArrayList<Seat>();
		for (Seat seatAvail : availableSeats) {
			for (Integer integer : seatIds) {
				if(seatAvail.getSeatId()== integer){
					seats.add(seatAvail);
					Movie movie=mDao.findMovie(movieId);
					movie.setTotalSeats(movie.getTotalSeats()-1);
					mDao.updateMovie(movie, movieId);
					seatAvail.setSeatAvailability(false);
					sDao.updateSeat(seatAvail, seatAvail.getSeatId());
				}
			}
		}
		return seats;
	}
    public ResponseEntity<ResponseStructure<Booking>> ticketBooking(String userEmail,String userPassword,int movieId,SeatType seatType,List<Integer> seatIds){
		
		Booking booking=new Booking();
		List<Seat> availableSeat=mService.findSeatsAvailability(movieId, seatType);
		if(availableSeat != null) {
		List<Seat> bookedSeats=bookSeat(availableSeat, seatIds,movieId);
		if(!bookedSeats.isEmpty()) {
		Payment payment= processPayement(bookedSeats);
		
		Movie movie=mDao.findMovie(movieId);
		booking.setMovie(movie);
		
		booking.setBookingMovieName(movie.getMovieName());
		booking.setBookingShowTime(movie.getShowTime());
		booking.setBookingPayment(payment);
		booking.setTicketSeat(bookedSeats);
		booking.setTotalAmount(payment.getTotalPrice());
		Booking newBooking=tDao.saveBooking(booking);
		
		ResponseStructure<Booking> structure=new ResponseStructure<Booking>();
		structure.setMessage("ticket booked successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(newBooking);
		return new ResponseEntity<ResponseStructure<Booking>>(structure,HttpStatus.CREATED);
		}
		throw new  SeatNotFound("user seats are not available please enter available seat Ids");

		
		}
		throw new  SeatNotFound("user seats are not available please enter available seat Ids");
	}
    
    private Payment processPayement(List<Seat> bookedSeats) {
		Payment payment=new Payment();
		long amount=0;
		for (Seat seat : bookedSeats) {
			if(seat.getSeatType()==SeatType.Type_A) {
				amount+=150;
			}
			else if(seat.getSeatType()==SeatType.Type_B) {
				amount+=110;
			}
			else {
				amount+=60;
			}
		}
		
		payment.setTotalPrice(amount);
		Payment newPayment=pDao.savePayment(payment);
		return newPayment;
	}
    
    
    
    public ResponseEntity<ResponseStructure<Payment>> cancelBooking(int bookingId){
		Booking booking=tDao.findBooking(bookingId);
		if(booking != null) {
			List<Seat> lists = booking.getTicketSeat();
			for (Seat seat : lists) {
				seat.setSeatAvailability(true);
				sDao.updateSeat(seat, seat.getSeatId());
				Movie movie=mDao.findMovie(booking.getMovie().getMovieId());
				movie.setTotalSeats(movie.getTotalSeats()+1);
				mDao.updateMovie(movie, booking.getMovie().getMovieId());
			}
			booking.setTicketSeat(null);
			Payment payment=booking.getBookingPayment();
			tDao.deleteBooking(bookingId);
			ResponseStructure<Payment> structure=new ResponseStructure<Payment>();
			structure.setMessage("cancel booking success.amount refunded");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(payment);
			return new ResponseEntity<ResponseStructure<Payment>> (structure,HttpStatus.OK);
		}
		else {
			throw new BookingNotFound("ticket not found for given id");
			
		}
	}
}
