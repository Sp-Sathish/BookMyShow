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
import com.Springboot.BookMyShowApp.Dto.AdminDto;
import com.Springboot.BookMyShowApp.Entity.Admin;
import com.Springboot.BookMyShowApp.Service.AdminService;
import com.Springboot.BookMyShowApp.Util.ResponseStructure;

@RestController
@RequestMapping("admin")
public class AdminController 
{
	@Autowired
	private AdminService adminService ;
	@PostMapping
	public ResponseEntity<ResponseStructure<AdminDto>> saveAdmin(@RequestBody Admin admin)
	{
		return adminService.saveAdmin(admin);
	}
	@GetMapping
	public ResponseEntity<ResponseStructure<AdminDto>> findAdmin(@RequestParam int adminId)
	{
		return adminService.findAdmin(adminId);
	}
	@DeleteMapping
	public ResponseEntity<ResponseStructure<AdminDto>> deleteAdmin(@RequestParam int adminId)
	{
		return adminService.deleteAdmin(adminId);
	}
	
	@GetMapping("AllAdmins")
	public ResponseEntity<ResponseStructure<List<AdminDto>>> findAllAdmins()
	{
		return adminService.findAllAdmin();
	}
}
