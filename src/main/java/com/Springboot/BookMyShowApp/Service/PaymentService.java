package com.Springboot.BookMyShowApp.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Springboot.BookMyShowApp.Dao.PaymentDao;
import com.Springboot.BookMyShowApp.Entity.Payment;
import com.Springboot.BookMyShowApp.ExceptionHandler.PaymentNotFound;
import com.Springboot.BookMyShowApp.Util.ResponseStructure;

@Service
public class PaymentService {

	@Autowired
	private PaymentDao paymentDao ;
	
	
	public ResponseEntity<ResponseStructure<Payment>> savePayment(Payment payment)
	{
		ResponseStructure<Payment> structure =  new ResponseStructure<Payment>();
		structure.setMessage("Payment save success");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(paymentDao.savePayment(payment));
		return new ResponseEntity<ResponseStructure<Payment>>(structure,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Payment>> findPayment(int paymentId)
	{
		ResponseStructure<Payment> structure  = new ResponseStructure<Payment>();
		Payment exiPayment = paymentDao.findPayment(paymentId);
		if(exiPayment != null)
		{
			
			structure.setMessage("Payment found success");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(exiPayment);
			return new ResponseEntity<ResponseStructure<Payment>>(structure,HttpStatus.FOUND);
		}
		throw new PaymentNotFound("Payment not found with given id");

	}
	public ResponseEntity<ResponseStructure<Payment>> deletePayment(int paymentId)
	{
		ResponseStructure<Payment> structure  = new ResponseStructure<Payment>();
		Payment exiPayment = paymentDao.findPayment(paymentId);
		if(exiPayment != null)
		{
			structure.setMessage("Payment delete success");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(exiPayment);
			return new ResponseEntity<ResponseStructure<Payment>>(structure,HttpStatus.OK);
		}
		throw new PaymentNotFound("Payment not found with given id");
	}
	
	public ResponseEntity<ResponseStructure<List<Payment>>> findAllPayment()
	{
	
			ResponseStructure<List<Payment>> structure  = new ResponseStructure<List<Payment>>();
			structure.setMessage("All Payment found success");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(paymentDao.findAllPayment());
			return new ResponseEntity<ResponseStructure<List<Payment>>>(structure ,HttpStatus.FOUND);
		
		
	}
}
