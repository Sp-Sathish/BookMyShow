package com.Springboot.BookMyShowApp.Service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Springboot.BookMyShowApp.Dao.AdminDao;
import com.Springboot.BookMyShowApp.Dto.AdminDto;
import com.Springboot.BookMyShowApp.Entity.Admin;
import com.Springboot.BookMyShowApp.Entity.Theatre;
import com.Springboot.BookMyShowApp.ExceptionHandler.AdminNotFound;
import com.Springboot.BookMyShowApp.Repository.TheatreRepo;
import com.Springboot.BookMyShowApp.Util.ResponseStructure;
@Service
public class AdminService {
	
	@Autowired
	private AdminDao adminDao ;
	
    @Autowired
	TheatreRepo theatreRepo;
	public ResponseEntity<ResponseStructure<AdminDto>> saveAdmin(Admin admin)
	{
		AdminDto aDto = new AdminDto();
		ModelMapper mapper = new ModelMapper() ;
		mapper.map(adminDao.saveAdmin(admin), aDto) ;
		ResponseStructure<AdminDto> structure =  new ResponseStructure<AdminDto>();
		structure.setMessage("Admin save success");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(aDto);
		return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<AdminDto>> findAdmin(int adminId)
	{
		ResponseStructure<AdminDto> structure  = new ResponseStructure<AdminDto>();
		Admin exiAdmin = adminDao.findAdmin(adminId);
		if(exiAdmin != null)
		{
			AdminDto sDto =  new AdminDto() ;
			ModelMapper mapper = new ModelMapper();
			mapper.map(exiAdmin, sDto);
			structure.setMessage("Admin found success");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(sDto);
			return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.FOUND);
		}
		throw new AdminNotFound("Admin not Found With Given Id");

	}
	
	public ResponseEntity<ResponseStructure<AdminDto>> deleteAdmin(int adminId)
	{
		ResponseStructure<AdminDto> structure  = new ResponseStructure<AdminDto>();
		Admin exiAdmin = adminDao.findAdmin(adminId);
		if(exiAdmin != null)
		{
			AdminDto aDto =  new AdminDto() ;
			ModelMapper mapper = new ModelMapper();
			mapper.map(adminDao.deleteAdmin(adminId), aDto);
			structure.setMessage("Admin delete success");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(aDto);
			return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.OK);
		}
		throw new AdminNotFound("Admin not Found With Given Id");
		
	}
	
	
	public ResponseEntity<ResponseStructure<List<AdminDto>>> findAllAdmin()
	{
		List<Admin>  adminList = adminDao.findAllAdmins();
		List<AdminDto> aDtoList =new ArrayList<AdminDto>();
		if(! adminList.isEmpty())
		{
			ModelMapper mapper = new ModelMapper() ;
			for(Admin a : adminList  )
			{
				AdminDto aDto =  new AdminDto() ;
				mapper.map(a,aDto);
				aDtoList.add(aDto);
			}
			ResponseStructure<List<AdminDto>> structure  = new ResponseStructure<List<AdminDto>>();
			structure.setMessage("All Admin found success");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(aDtoList);
			return new ResponseEntity<ResponseStructure<List<AdminDto>>>(structure ,HttpStatus.FOUND);
		}
		throw new AdminNotFound("AdminList  not Found");
	}
	public ResponseEntity<ResponseStructure<AdminDto>> adminLogin(String admEmail , String admPassword)
	{
		ResponseStructure<AdminDto> structure  = new ResponseStructure<AdminDto>();
		Admin exiAdmin = adminDao.findByadmEmail(admEmail);
		
		if(exiAdmin != null)
		{
			if(exiAdmin.getAdmPassword().equalsIgnoreCase(admPassword))
			{
				AdminDto sDto =  new AdminDto() ;
				ModelMapper mapper = new ModelMapper();
				mapper.map(exiAdmin, sDto);
				structure.setMessage("Admin Login success");
				structure.setStatus(HttpStatus.FOUND.value());
				structure.setData(sDto);
				return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.FOUND);
			}
		}
		throw new AdminNotFound("Admin not Found With Given Id");
	}
	public ResponseEntity<ResponseStructure<AdminDto>> assignTheatresToAdmin(int adminId,List<Integer> theatreIds){
		AdminDto aDto=new AdminDto();
		ModelMapper mapper=new ModelMapper();
		Admin admin=adminDao.findAdmin(adminId);
		if(admin != null) {
			List<Theatre> extheatres=theatreRepo.findAllById(theatreIds);
			admin.setTheatreList(extheatres);
			mapper.map(adminDao.updateAdmin(adminId, admin), aDto);
			ResponseStructure<AdminDto> structure=new ResponseStructure<AdminDto>();
			structure.setMessage("assign theatre to Admin success");
			structure.setStatus(HttpStatus .OK.value());
			structure.setData(aDto);
			return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.OK);
		}
		throw new AdminNotFound("Admin Not Found With Id , Theatre Assign failed");
		
	}
}
