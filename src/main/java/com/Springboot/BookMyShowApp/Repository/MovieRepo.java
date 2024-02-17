package com.Springboot.BookMyShowApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Springboot.BookMyShowApp.Entity.Movie;

public interface MovieRepo extends JpaRepository<Movie, Integer>{

}
