package com.Springboot.BookMyShowApp.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Springboot.BookMyShowApp.Dao.TheatreDao;
import com.Springboot.BookMyShowApp.Entity.Movie;
import com.Springboot.BookMyShowApp.Entity.Theatre;
import com.Springboot.BookMyShowApp.ExceptionHandler.TheatreAdminNotFound;
import com.Springboot.BookMyShowApp.ExceptionHandler.TheatreNotFound;
import com.Springboot.BookMyShowApp.Repository.MovieRepo;
import com.Springboot.BookMyShowApp.Repository.TheatreRepo;
import com.Springboot.BookMyShowApp.Util.ResponseStructure;

@Service
public class TheatreService {

	@Autowired
	private TheatreDao theatreDao ;
	@Autowired
	TheatreRepo tRepo;
	@Autowired
	MovieRepo mRepo;
	
	public ResponseEntity<ResponseStructure<Theatre>> saveATheatre(Theatre theatre)
	{
		ResponseStructure<Theatre> structure =  new ResponseStructure<Theatre>();
		structure.setMessage("Theatre save success");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(theatreDao.saveTheatre(theatre));
		return new ResponseEntity<ResponseStructure<Theatre>>(structure,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Theatre>> findTheatre(int theatreId)
	{
		ResponseStructure<Theatre> structure  = new ResponseStructure<Theatre>();
		Theatre exiTheatre = theatreDao.findTheatre(theatreId);
		if(exiTheatre != null)
		{
			
			structure.setMessage("Theatre found success");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(exiTheatre);
			return new ResponseEntity<ResponseStructure<Theatre>>(structure,HttpStatus.FOUND);
		}
		throw new TheatreNotFound("Theatre not found with given id");

	}
	public ResponseEntity<ResponseStructure<Theatre>> deleteTheatre(int theatreId)
	{
		ResponseStructure<Theatre> structure  = new ResponseStructure<Theatre>();
		Theatre exiTheatre = theatreDao.findTheatre(theatreId);
		if(exiTheatre != null)
		{
			structure.setMessage("Theatre delete success");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(exiTheatre);
			return new ResponseEntity<ResponseStructure<Theatre>>(structure,HttpStatus.OK);
		}
		throw new TheatreNotFound("Theatre not found with given id");
	}
	
	public ResponseEntity<ResponseStructure<List<Theatre>>> findAllTheatre()
	{
	
			ResponseStructure<List<Theatre>> structure  = new ResponseStructure<List<Theatre>>();
			structure.setMessage("All Theatre found success");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(theatreDao.findAllTheatres());
			return new ResponseEntity<ResponseStructure<List<Theatre>>>(structure ,HttpStatus.FOUND);
		
		
	}
	public ResponseEntity<ResponseStructure<Theatre>> assignMoviesToTheatre(int theatreId,List<Integer> movieIds) {
		Theatre theatre=theatreDao.findTheatre(theatreId);
		if(theatre != null) {
		List<Movie> exmovies=mRepo.findAllById(movieIds);
		theatre.setMovieList(exmovies);
		ResponseStructure<Theatre> structure=new ResponseStructure<Theatre>();
		structure.setMessage("assign movies to theatre success");
		structure.setStatus(HttpStatus .OK.value());
		structure.setData(theatreDao.updateTheatre(theatre, theatreId));
		return new ResponseEntity<ResponseStructure<Theatre>>(structure,HttpStatus.OK);
		}
	
	  throw new TheatreNotFound("we can't able to assign movie to the theatre because, theatre not found with given id");
	}
	
}


