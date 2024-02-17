package com.Springboot.BookMyShowApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Springboot.BookMyShowApp.Entity.Booking;

public interface BookingRepo extends JpaRepository<Booking, Integer>{

}
