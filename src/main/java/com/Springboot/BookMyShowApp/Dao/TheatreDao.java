package com.Springboot.BookMyShowApp.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Springboot.BookMyShowApp.Entity.Booking;
import com.Springboot.BookMyShowApp.Entity.Theatre;
import com.Springboot.BookMyShowApp.Repository.TheatreRepo;

@Repository
public class TheatreDao {

	@Autowired
	private TheatreRepo theatreRepo ;
	
	public Theatre saveTheatre (Theatre theatre)
	{
		return theatreRepo.save(theatre); 
	}
	
	public Theatre findTheatre(int theatreId)
	{
		Optional<Theatre> opTheatre = theatreRepo.findById(theatreId) ;
		if( opTheatre.isPresent())
		{
			return opTheatre.get();
		}
		return null ;
	}
	
	public Theatre deleteTheatre(int theatreId)
	{
		Theatre  exiTheatre = findTheatre(theatreId);
	    theatreRepo.delete(exiTheatre);
	    return exiTheatre ;
	}
	public List<Theatre> findAllTheatres()
	{
		return theatreRepo.findAll();
	}
	
	
	public Theatre updateTheatre(Theatre theatre , int theatreId)
	{
		Theatre exTheatre = findTheatre(theatreId);
		if(exTheatre != null){
			theatre.setTheatreId(theatreId);
			return theatreRepo.save(theatre);
		}
		return null;
		
	}
}
