package com.Springboot.BookMyShowApp.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Springboot.BookMyShowApp.Entity.Payment;
import com.Springboot.BookMyShowApp.Repository.PaymentRepo;

@Repository
public class PaymentDao {

	@Autowired
	private PaymentRepo paymentRepo ;
	
	public Payment savePayment(Payment payment)
	{
		return paymentRepo.save(payment);
	}
	
	public Payment findPayment(int paymentId)
	{
		Optional<Payment> opPayment = paymentRepo.findById(paymentId) ;
		if( opPayment.isPresent())
		{
			return opPayment.get();
		}
		return null ;
	}
	
	public Payment deletePayment(int paymentId)
	{
		Payment exiPayment= findPayment(paymentId);
		paymentRepo.delete(exiPayment);
	    return exiPayment ;
	}
	public Payment updatePayment(int paymentId , Payment payment)
	{
		Payment exiPayment = findPayment(paymentId);
		if(exiPayment != null)
		{
			payment.setPaymentId(paymentId);;
		    return paymentRepo.save(payment);
		}
		return null ;
	}
	
	public List<Payment> findAllPayment()
	{
		return paymentRepo.findAll();
	}
}
