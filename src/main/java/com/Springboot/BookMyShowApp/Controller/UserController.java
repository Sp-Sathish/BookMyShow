package com.Springboot.BookMyShowApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.Springboot.BookMyShowApp.Dto.UserDto;
import com.Springboot.BookMyShowApp.Entity.User;
import com.Springboot.BookMyShowApp.Service.UserService;
import com.Springboot.BookMyShowApp.Util.ResponseStructure;

@RestController
@RequestMapping("User")
public class UserController 
{
	@Autowired
	private UserService userService ;
	@PostMapping
	public ResponseEntity<ResponseStructure<UserDto>>saveUser(@RequestBody User user )
	{
		return userService.saveUser(user);
	}
	@GetMapping
	public ResponseEntity<ResponseStructure<UserDto>>findUser(@RequestParam int userId )
	{
		return userService.findUser(userId);
	}
	@DeleteMapping
	public ResponseEntity<ResponseStructure<UserDto>>deleteUser(@RequestParam int userId )
	{
		return userService.deleteUser(userId);
	}
	@GetMapping("AllUsers")
	public ResponseEntity<ResponseStructure<List<UserDto>>> findAllAdmins()
	{
		return userService.findAllUsers();
	}
	
}
