package com.Springboot.BookMyShowApp.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Springboot.BookMyShowApp.Entity.Admin;
import com.Springboot.BookMyShowApp.Repository.AdminRepo;

@Repository
public class AdminDao {

	@Autowired
	private AdminRepo adminRepo ;
	
	public Admin saveAdmin(Admin admin)
	{
		return adminRepo.save(admin);
	}
	
	public Admin findAdmin(int adminId)
	{
		Optional<Admin> opAdmin = adminRepo.findById(adminId) ;
		if( opAdmin.isPresent())
		{
			return opAdmin.get();
		}
		return null ;
	}
	
	public Admin deleteAdmin(int adminId)
	{
		Admin exiAdmin= findAdmin(adminId);
	    adminRepo.delete(exiAdmin);
	    return exiAdmin ;
	}
	public Admin updateAdmin(int adminId , Admin admin)
	{
		Admin exiAdmin = findAdmin(adminId);
		if(exiAdmin != null)
		{
		    admin.setAdmId(adminId);
		    return adminRepo.save(admin);
		}
		return null ;
	}
	
	public List<Admin> findAllAdmins()
	{
		return adminRepo.findAll();
	}
	
	
}
