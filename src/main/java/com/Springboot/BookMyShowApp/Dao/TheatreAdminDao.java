package com.Springboot.BookMyShowApp.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Springboot.BookMyShowApp.Entity.TheatreAdmin;
import com.Springboot.BookMyShowApp.Repository.TheatreAdminRepo;
@Repository
public class TheatreAdminDao {

	@Autowired
	private TheatreAdminRepo thAdRepo ;
	
	public TheatreAdmin saveTheatreAdmin(TheatreAdmin theatreAdmin)
	{
		return thAdRepo.save(theatreAdmin);
	}
	
	public TheatreAdmin findTheatreAdmin(int theatreAdminId)
	{
		Optional<TheatreAdmin> opAdmin = thAdRepo.findById(theatreAdminId) ;
		if( opAdmin.isPresent())
		{
			return opAdmin.get();
		}
		return null ;
	}
	
	public TheatreAdmin deleteTheatreAdmin(int theatreAdminId)
	{
		TheatreAdmin exiTheatreAdmin= findTheatreAdmin(theatreAdminId);
	    thAdRepo.delete(exiTheatreAdmin);
	    return exiTheatreAdmin ;
	}
	public TheatreAdmin updateTheatreAdmin(int theatreAdminId , TheatreAdmin theatreAdmin)
	{
		TheatreAdmin exiAdmin = findTheatreAdmin(theatreAdminId);
		if(exiAdmin != null)
		{
		    theatreAdmin.setTheatreAdmId(theatreAdminId);;
		    return thAdRepo.save(theatreAdmin);
		}
		return null ;
	}
	
	public List<TheatreAdmin> findAllTheatreAdmin()
	{
		return thAdRepo.findAll();
	}
}
