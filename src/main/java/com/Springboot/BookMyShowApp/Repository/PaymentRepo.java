package com.Springboot.BookMyShowApp.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.Springboot.BookMyShowApp.Entity.Payment;

public interface PaymentRepo extends JpaRepository<Payment, Integer>{

}
