package com.payment.api;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payment.model.Payment;
import com.payment.service.PaymentService;

@RestController
//@Transactional
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
//	@GetMapping("/paymentDetails/{policyId}")
//	public String paymentDetails(@PathVariable int policyId) {
//		
//		String  = "";
//	}
	
	@GetMapping("/test")
	public String test() {
		return "Chal rhi h";
	}
	

	@PostMapping("/makePayment/{policyId}/{modeOfPayment}/{paymentId}")
	public ResponseEntity<String> makePayment(@PathVariable int policyId,@PathVariable String modeOfPayment,@PathVariable int paymentId){
		String message = paymentService.addPayment(policyId, modeOfPayment, paymentId);
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	
	
	@GetMapping("/showPayments")
	public List<Payment> showPayments(){
		List<Payment> p = paymentService.showPayment();
		return p;
	}
}
