package com.Springboot.BookMyShowApp.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Springboot.BookMyShowApp.Entity.Movie;
import com.Springboot.BookMyShowApp.Repository.MovieRepo;

@Repository
public class MovieDao {

	@Autowired
	private MovieRepo movieRepo ;
	
	public Movie saveMovie (Movie movie)
	{
		return movieRepo.save(movie); 
	}
	
	public Movie findMovie(int movieId)
	{
		Optional<Movie> opMovie = movieRepo.findById(movieId) ;
		if( opMovie.isPresent())
		{
			return opMovie.get();
		}
		return null ;
	}
	
	public Movie deleteMovie(int movieId)
	{
		Movie  exiMovie = findMovie(movieId);
	    movieRepo.delete(exiMovie);
	    return exiMovie ;
	}
	public List<Movie> findAllMovies()
	{
		return movieRepo.findAll();
	}
	
	public Movie updateMovie(Movie movie , int movieId)
	{
		Movie exMovie = findMovie(movieId);
		if(exMovie != null){
			movie.setMovieId(movieId);
			return movieRepo.save(movie);
		}
		return null;
		
	}
	
}
