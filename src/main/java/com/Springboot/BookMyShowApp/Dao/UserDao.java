package com.Springboot.BookMyShowApp.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Springboot.BookMyShowApp.Entity.User;
import com.Springboot.BookMyShowApp.Repository.UserRepo;
@Repository
public class UserDao {

	@Autowired
	private UserRepo userRepo ;
	
	public User saveUser(User user)
	{
		return userRepo.save(user);
	}
	
	public User findUser(int userId)
	{
		Optional<User> opUser = userRepo.findById(userId) ;
		if( opUser.isPresent())
		{
			return opUser.get();
		}
		return null ;
	}
	
	public User deleteUser(int userId)
	{
		User exiUser= findUser(userId);
		userRepo.delete(exiUser);
	    return exiUser ;
	}
	public User updateUser(int userId , User user)
	{
		User exiUser = findUser(userId);
		if(exiUser != null)
		{
		    user.setUserId(userId);
		    return userRepo.save(user);
		}
		return null ;
	}
	
	public List<User> findAllUser()
	{
		return userRepo.findAll();
	}
}
