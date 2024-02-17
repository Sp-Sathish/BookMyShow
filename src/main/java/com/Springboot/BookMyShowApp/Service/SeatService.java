package com.Springboot.BookMyShowApp.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Springboot.BookMyShowApp.Dao.SeatDao;
import com.Springboot.BookMyShowApp.Entity.Seat;
import com.Springboot.BookMyShowApp.ExceptionHandler.PaymentNotFound;
import com.Springboot.BookMyShowApp.ExceptionHandler.SeatNotFound;
import com.Springboot.BookMyShowApp.Util.ResponseStructure;

@Service
public class SeatService {

	@Autowired
	private SeatDao seatDao ;
	
	
	public ResponseEntity<ResponseStructure<Seat>> saveSeat(Seat seat)
	{
		ResponseStructure<Seat> structure =  new ResponseStructure<Seat>();
		structure.setMessage("Seat save success");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(seatDao.saveSeat(seat));
		return new ResponseEntity<ResponseStructure<Seat>>(structure,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Seat>> findSeat(int seatId)
	{
		ResponseStructure<Seat> structure  = new ResponseStructure<Seat>();
		Seat exiSeat = seatDao.findSeat(seatId);
		if(exiSeat != null)
		{
			
			structure.setMessage("Seat found success");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(exiSeat);
			return new ResponseEntity<ResponseStructure<Seat>>(structure,HttpStatus.FOUND);
		}
		throw new SeatNotFound("Seat not found with given id");

	}
	public ResponseEntity<ResponseStructure<Seat>> deleteSeat(int seatId)
	{
		ResponseStructure<Seat> structure  = new ResponseStructure<Seat>();
		Seat exiSeat = seatDao.findSeat(seatId);
		if(exiSeat != null)
		{
			structure.setMessage("Seat delete success");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(exiSeat);
			return new ResponseEntity<ResponseStructure<Seat>>(structure,HttpStatus.OK);
		}
		throw new SeatNotFound("Seat not found with given id");
	}
	
	public ResponseEntity<ResponseStructure<List<Seat>>> findAllSeat()
	{
	
			ResponseStructure<List<Seat>> structure  = new ResponseStructure<List<Seat>>();
			structure.setMessage("All Seats found success");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(seatDao.findAllSeats());
			return new ResponseEntity<ResponseStructure<List<Seat>>>(structure ,HttpStatus.FOUND);
		
		
	}
}
