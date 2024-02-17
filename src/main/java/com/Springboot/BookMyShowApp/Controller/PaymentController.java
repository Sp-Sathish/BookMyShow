package com.Springboot.BookMyShowApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Springboot.BookMyShowApp.Entity.Payment;
import com.Springboot.BookMyShowApp.Service.PaymentService;
import com.Springboot.BookMyShowApp.Util.ResponseStructure;

@RestController
@RequestMapping("payment")
public class PaymentController {

	@Autowired
	private PaymentService paymentService ;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Payment>> savePayment(@RequestBody Payment payment)
	{
		return paymentService.savePayment(payment);
	}
	@GetMapping
	public ResponseEntity<ResponseStructure<Payment>> findPayment(@RequestParam int paymentId)
	{
		return paymentService.findPayment(paymentId);
	}
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Payment>> deletePayment(@RequestParam int paymentId)
	{
		return paymentService.deletePayment(paymentId);
	}
	
	@GetMapping("AllPayment")
	public ResponseEntity<ResponseStructure<List<Payment>>> findAllPayment()
	{
		return paymentService.findAllPayment();
	}
}
