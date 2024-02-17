package com.Springboot.BookMyShowApp.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Springboot.BookMyShowApp.Dao.MovieDao;
import com.Springboot.BookMyShowApp.Dao.SeatDao;
import com.Springboot.BookMyShowApp.Entity.Movie;
import com.Springboot.BookMyShowApp.Entity.Seat;
import com.Springboot.BookMyShowApp.Entity.SeatType;
import com.Springboot.BookMyShowApp.ExceptionHandler.MovieNotFound;
import com.Springboot.BookMyShowApp.ExceptionHandler.SeatNotFound;
import com.Springboot.BookMyShowApp.Repository.SeatRepo;
import com.Springboot.BookMyShowApp.Util.ResponseStructure;

@Service
public class MovieService {

	@Autowired
	private MovieDao movieDao ;
	@Autowired
	SeatDao sDao;
	@Autowired
	SeatRepo sRepo;
	
	public ResponseEntity<ResponseStructure<Movie>> saveMovie(Movie movie)
	{
		ResponseStructure<Movie> structure =  new ResponseStructure<Movie>();
		structure.setMessage("Movie save success");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(movieDao.saveMovie(movie));
		return new ResponseEntity<ResponseStructure<Movie>>(structure,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Movie>> findMovie(int movieId)
	{
		ResponseStructure<Movie> structure  = new ResponseStructure<Movie>();
		Movie exiMovie = movieDao.findMovie(movieId);
		if(exiMovie != null)
		{
			
			structure.setMessage("Movie found success");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(exiMovie);
			return new ResponseEntity<ResponseStructure<Movie>>(structure,HttpStatus.FOUND);
		}
		throw new MovieNotFound("Movie not Found With Given Id");

	}
	
	public ResponseEntity<ResponseStructure<Movie>> updateMovie(Movie movie ,int movieId)
	{
		ResponseStructure<Movie> structure = new ResponseStructure<Movie>() ;
		Movie exiMovie = movieDao.findMovie(movieId);
		if(exiMovie != null)
		{
			structure.setMessage("update Movie success");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(movieDao.updateMovie(movie,movieId));
			return new ResponseEntity<ResponseStructure<Movie>>(structure,HttpStatus.OK);
		}
		throw new MovieNotFound("Movie not Found With Given Id");
	}
	public ResponseEntity<ResponseStructure<Movie>> deleteMovie(int movieId)
	{
		ResponseStructure<Movie> structure  = new ResponseStructure<Movie>();
		Movie exiMovie = movieDao.findMovie(movieId);
		if(exiMovie != null)
		{
			structure.setMessage("Movie delete success");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(exiMovie);
			return new ResponseEntity<ResponseStructure<Movie>>(structure,HttpStatus.OK);
		}
		throw new MovieNotFound("Movie not Found With Given Id");
	}
	
	public ResponseEntity<ResponseStructure<List<Movie>>> findAllMovie()
	{
	
			ResponseStructure<List<Movie>> structure  = new ResponseStructure<List<Movie>>();
			structure.setMessage("All Movie found success");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(movieDao.findAllMovies());
			return new ResponseEntity<ResponseStructure<List<Movie>>>(structure ,HttpStatus.FOUND);
		
		
	}
	public  ResponseEntity<ResponseStructure<List<Seat>>> findSeatAvailability(int movieId,SeatType seatType) {
		Movie movie=movieDao.findMovie(movieId);
		List<Seat> seatList=movie.getSeatList();
		List<Seat> seatsList=new ArrayList<Seat>();
		for (Seat seat : seatList) {
			if(seat.isSeatAvailability()==true && seat.getSeatType()==seatType) {
				seatsList.add(seat);
			}
		}
		ResponseStructure<List<Seat>> structure=new ResponseStructure<List<Seat>>();
		structure.setMessage("find seat availability found success");
		structure.setStatus(HttpStatus .FOUND.value());
		structure.setData(seatsList);
		return new ResponseEntity<ResponseStructure<List<Seat>>>(structure,HttpStatus.FOUND);
	}
	public List<Seat> findSeatsAvailability(int movieId,SeatType seatType) {
		Movie movie=movieDao.findMovie(movieId);
		List<Seat> seatList=movie.getSeatList();
		List<Seat> seatsList=new ArrayList<Seat>();
		for (Seat seat : seatList) {
			if(seat.isSeatAvailability()==true && seat.getSeatType()==seatType) {
				seatsList.add(seat);
			}
		}
		return seatsList;
	}
	public  ResponseEntity<ResponseStructure<Movie>> assignSeatsToMovies(int movieId,List<Integer> seatIds) {
		Movie movie=movieDao.findMovie(movieId);
		if(movie != null) {
		List<Seat> seats=sRepo.findAllById(seatIds);
			if(seats != null) {
				movie.setSeatList(seats);
				ResponseStructure<Movie> structure=new ResponseStructure<Movie>();
				structure.setMessage("assign seats to Movie success");
				structure.setStatus(HttpStatus .OK.value());
				structure.setData(movieDao.updateMovie(movie,movie.getMovieId()));
				return new ResponseEntity<ResponseStructure<Movie>>(structure,HttpStatus.OK);
			}
			else {
				
				throw new SeatNotFound("Seat Not Available, Seat assign failed");
			}
		}

		 throw new MovieNotFound("we can't assign seats to movies because,movie not found for the given id");
	}
	
	
	
}
