package com.Springboot.BookMyShowApp.Service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Springboot.BookMyShowApp.Dao.UserDao;
import com.Springboot.BookMyShowApp.Dto.UserDto;
import com.Springboot.BookMyShowApp.Entity.User;
import com.Springboot.BookMyShowApp.ExceptionHandler.UserNotFound;
import com.Springboot.BookMyShowApp.Util.ResponseStructure;

@Service
public class UserService {

	@Autowired
	private UserDao userDao ;
	
	public ResponseEntity<ResponseStructure<UserDto>> saveUser(User user)
	{
		UserDto uDto = new UserDto();
		ModelMapper mapper = new ModelMapper() ;
		mapper.map(userDao.saveUser(user), uDto) ;
		ResponseStructure<UserDto> structure =  new ResponseStructure<UserDto>();
		structure.setMessage("User save success");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(uDto);
		return new ResponseEntity<ResponseStructure<UserDto>>(structure,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<UserDto>>findUser(int userId)
	{
		ResponseStructure<UserDto> structure  = new ResponseStructure<UserDto>();
		User exiUser = userDao.findUser(userId);
		if(exiUser != null)
		{
			UserDto uDto =  new UserDto() ;
			ModelMapper mapper = new ModelMapper();
			mapper.map(exiUser, uDto);
			structure.setMessage("User found success");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(uDto);
			return new ResponseEntity<ResponseStructure<UserDto>>(structure,HttpStatus.FOUND);
		}
		throw new UserNotFound("User not found with given id");
	}
	
	public ResponseEntity<ResponseStructure<UserDto>>deleteUser(int userId)
	{
		ResponseStructure<UserDto> structure  = new ResponseStructure<UserDto>();
		User exiUSer = userDao.findUser(userId);
		if(exiUSer != null)
		{
			UserDto uDto = new  UserDto() ;
			ModelMapper mapper = new ModelMapper();
			mapper.map(userDao.deleteUser(userId), uDto);
			structure.setMessage("USer delete success");
			structure.setData(uDto);
			structure.setStatus(HttpStatus.OK.value() );
			return new ResponseEntity<ResponseStructure<UserDto>>(structure, HttpStatus.OK);
		}
		throw new UserNotFound("User not found with given id");
	}
	public ResponseEntity<ResponseStructure<List<UserDto>>>findAllUsers()
	{
		List<User> userList = userDao.findAllUser();
		List<UserDto> uDtoList = new ArrayList<UserDto>();
		if(! userList.isEmpty())
		{
			ModelMapper mapper = new ModelMapper() ;
			for(User u : userList  )
			{
				UserDto uDto =  new UserDto() ;
				mapper.map(u,uDto);
				uDtoList.add(uDto);
			}
			ResponseStructure<List<UserDto>> structure  = new ResponseStructure<List<UserDto>>();
			structure.setMessage("All User Found success");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(uDtoList);
			return new ResponseEntity<ResponseStructure<List<UserDto>>>(structure ,HttpStatus.FOUND);
		}
		throw new UserNotFound("UserList not found");
	}
	public ResponseEntity<ResponseStructure<UserDto>> userLogin(String userEmail , String userPassword)
	{
		ResponseStructure<UserDto> structure  = new ResponseStructure<UserDto>();
		User exiUser = userDao.findByuserEmail(userEmail);
		
		if(exiUser != null)
		{
			if(exiUser.getUserPassword().equalsIgnoreCase(userPassword))
			{
				UserDto uDto =  new UserDto() ;
				ModelMapper mapper = new ModelMapper();
				mapper.map(exiUser, uDto);
				structure.setMessage("User Login success");
				structure.setStatus(HttpStatus.FOUND.value());
				structure.setData(uDto);
				return new ResponseEntity<ResponseStructure<UserDto>>(structure,HttpStatus.FOUND);
			}
		}
		throw new UserNotFound("Check Your Email and password");
	}
	
	
}
