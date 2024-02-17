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

import com.Springboot.BookMyShowApp.Entity.Movie;
import com.Springboot.BookMyShowApp.Entity.Seat;
import com.Springboot.BookMyShowApp.Entity.SeatType;
import com.Springboot.BookMyShowApp.Service.MovieService;
import com.Springboot.BookMyShowApp.Util.ResponseStructure;

@RestController
@RequestMapping("movie")
public class MovieController {

	@Autowired
	private MovieService movieService ;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Movie>> saveBooking(@RequestBody Movie movie)
	{
		return movieService.saveMovie(movie);
	}
	@GetMapping
	public ResponseEntity<ResponseStructure<Movie>> findMovie(@RequestParam int movieId)
	{
		return movieService.findMovie(movieId);
	}
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Movie>> deleteMovie(@RequestParam int movieId)
	{
		return movieService.deleteMovie(movieId);
	}
	
	@GetMapping("AllBooking")
	public ResponseEntity<ResponseStructure<List<Movie>>> findAllMovie()
	{
		return movieService.findAllMovie();
	}
	
	@PutMapping("assignSeats")
	public  ResponseEntity<ResponseStructure<Movie>> assignSeatsToMovies(@RequestParam int movieId,@RequestBody List<Integer> seatIds) {
		return movieService.assignSeatsToMovies(movieId, seatIds);
	}
	@GetMapping("findSeatAvailability")
	ResponseEntity<ResponseStructure<List<Seat>>> findSeatAvailability(@RequestParam int movieId,@RequestParam SeatType seatType) {
		return movieService.findSeatAvailability(movieId, seatType);
	}
}
