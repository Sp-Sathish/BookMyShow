package com.Springboot.BookMyShowApp.Service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Springboot.BookMyShowApp.Dao.TheatreAdminDao;
import com.Springboot.BookMyShowApp.Dao.TheatreDao;
import com.Springboot.BookMyShowApp.Dto.TheatreAdminDto;
import com.Springboot.BookMyShowApp.Entity.Theatre;
import com.Springboot.BookMyShowApp.Entity.TheatreAdmin;
import com.Springboot.BookMyShowApp.ExceptionHandler.TheatreAdminNotFound;
import com.Springboot.BookMyShowApp.ExceptionHandler.TheatreNotFound;
import com.Springboot.BookMyShowApp.Util.ResponseStructure;

@Service
public class TheatreAdminService {

	@Autowired
	private TheatreAdminDao theatreAdminDao ;
	@Autowired
	TheatreDao tDao;
	
	public ResponseEntity<ResponseStructure<TheatreAdminDto>> saveTheatreAdmin(TheatreAdmin theatreAdmin)
	{
		TheatreAdminDto taDto = new TheatreAdminDto();
		ModelMapper mapper = new ModelMapper() ;
		mapper.map(theatreAdminDao.saveTheatreAdmin(theatreAdmin), taDto) ;
		ResponseStructure<TheatreAdminDto> structure =  new ResponseStructure<TheatreAdminDto>();
		structure.setMessage("TheatreAdmin save success");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(taDto);
		return new ResponseEntity<ResponseStructure<TheatreAdminDto>>(structure,HttpStatus.CREATED);
	}
	
	
	public ResponseEntity<ResponseStructure<TheatreAdminDto>> findTheatreAdmin(int theatreAdminId)
	{
		ResponseStructure<TheatreAdminDto> structure  = new ResponseStructure<TheatreAdminDto>();
		TheatreAdmin exiThAdmin = theatreAdminDao.findTheatreAdmin(theatreAdminId);
		if(exiThAdmin != null)
		{
			TheatreAdminDto asDto =  new TheatreAdminDto() ;
			ModelMapper mapper = new ModelMapper();
			mapper.map(exiThAdmin, asDto);
			structure.setMessage("TheatreAdmin found success");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(asDto);
			return new ResponseEntity<ResponseStructure<TheatreAdminDto>>(structure,HttpStatus.FOUND);
		}
		throw new TheatreAdminNotFound("TheatreAdmin not found");	
	}
	
	public ResponseEntity<ResponseStructure<TheatreAdminDto>> deleteTheatreAdmin(int theatreAdminId)
	{
		ResponseStructure<TheatreAdminDto> structure  = new ResponseStructure<TheatreAdminDto>();
		TheatreAdmin exiThAdmin = theatreAdminDao.findTheatreAdmin(theatreAdminId);
		if(exiThAdmin != null)
		{
			TheatreAdminDto taDto =  new TheatreAdminDto() ;
			ModelMapper mapper = new ModelMapper();
			mapper.map(theatreAdminDao.deleteTheatreAdmin(theatreAdminId), taDto);
			structure.setMessage("TheatreAdmin delete success");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(taDto);
			return new ResponseEntity<ResponseStructure<TheatreAdminDto>>(structure,HttpStatus.OK);
		}
		throw new TheatreAdminNotFound("TheatreAdmin not found");
	}
	
	
	public ResponseEntity<ResponseStructure<List<TheatreAdminDto>>> findAllTheatreAdmin()
	{
		List<TheatreAdmin>  thAdminList = theatreAdminDao.findAllTheatreAdmin();
		List<TheatreAdminDto> taDtoList =new ArrayList<TheatreAdminDto>();
		if(! thAdminList.isEmpty())
		{
			ModelMapper mapper = new ModelMapper() ;
			for(TheatreAdmin a : thAdminList  )
			{
				TheatreAdminDto taDto =  new TheatreAdminDto() ;
				mapper.map(a,taDto);
				taDtoList.add(taDto);
			}
			ResponseStructure<List<TheatreAdminDto>> structure  = new ResponseStructure<List<TheatreAdminDto>>();
			structure.setMessage("All TheatreAdmin found success");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(taDtoList);
			return new ResponseEntity<ResponseStructure<List<TheatreAdminDto>>>(structure ,HttpStatus.FOUND);
		}
		throw new TheatreAdminNotFound("TheatreAdmin List Empty");
		
	}
	public ResponseEntity<ResponseStructure<TheatreAdminDto>> assignTheatreToTheatreAdmin(int theatreAdminId,int theatreId){
		TheatreAdminDto taDto=new TheatreAdminDto();
		ModelMapper mapper=new ModelMapper();
		TheatreAdmin thAdmin=theatreAdminDao.findTheatreAdmin(theatreAdminId);
		Theatre theatre=tDao.findTheatre(theatreId);
		if(thAdmin != null) {
			if(theatre != null) {
				thAdmin.setAdminTheatre(theatre);
				mapper.map(theatreAdminDao.updateTheatreAdmin(theatreAdminId, thAdmin), taDto);
				ResponseStructure<TheatreAdminDto> structure=new ResponseStructure<TheatreAdminDto>();
				structure.setMessage("assign theatre to Theatre Admin success");
				structure.setStatus(HttpStatus .OK.value());
				structure.setData(taDto);
				return new ResponseEntity<ResponseStructure<TheatreAdminDto>>(structure,HttpStatus.OK);
			}
			else {
				
				throw new TheatreNotFound("theatre not assigned to the theatre admin because,theatre not found for the given id");
			}
			
		}
		throw new TheatreAdminNotFound("we can't assign theatre to theatre admin because,theatre Admin not found for the given id");
	}

	
	
}
