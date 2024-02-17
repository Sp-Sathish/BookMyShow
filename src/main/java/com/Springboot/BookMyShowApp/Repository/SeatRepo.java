package com.Springboot.BookMyShowApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Springboot.BookMyShowApp.Entity.Seat;

public interface SeatRepo extends JpaRepository<Seat, Integer> {

}
