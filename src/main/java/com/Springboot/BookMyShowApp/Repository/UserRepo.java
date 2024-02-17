package com.Springboot.BookMyShowApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Springboot.BookMyShowApp.Entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{

	public User findByuserEmail(String userEmail);
}
