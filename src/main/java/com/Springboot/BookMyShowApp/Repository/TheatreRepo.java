package com.Springboot.BookMyShowApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Springboot.BookMyShowApp.Entity.Theatre;

public interface TheatreRepo extends JpaRepository<Theatre, Integer>{

}
