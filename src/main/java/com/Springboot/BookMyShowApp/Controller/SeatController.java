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

import com.Springboot.BookMyShowApp.Entity.Seat;
import com.Springboot.BookMyShowApp.Service.SeatService;
import com.Springboot.BookMyShowApp.Util.ResponseStructure;

@RestController
@RequestMapping("Seat")
public class SeatController {

	@Autowired
	private SeatService seatService ;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Seat>> saveSeat(@RequestBody Seat seat)
	{
		return seatService.saveSeat(seat);
	}
	@GetMapping
	public ResponseEntity<ResponseStructure<Seat>> findSeat(@RequestParam int seatId)
	{
		return seatService.findSeat(seatId);
	}
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Seat>> deleteSeat(@RequestParam int seatId)
	{
		return seatService.deleteSeat(seatId);
	}
	
	@GetMapping("AllSeat")
	public ResponseEntity<ResponseStructure<List<Seat>>> findAllSeats()
	{
		return seatService.findAllSeat();
	}
	
}
