package com.Springboot.BookMyShowApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Springboot.BookMyShowApp.Entity.Admin;

public interface AdminRepo extends JpaRepository<Admin, Integer>{

	public Admin findByadmEmail(String admEmail);
}
