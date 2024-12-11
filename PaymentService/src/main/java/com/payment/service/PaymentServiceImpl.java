package com.payment.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import com.payment.model.Payment;
import com.payment.model.PaymentDto;
import com.payment.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService{
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
//	public Payment enroll(int paymentId, int userId, int policyId) {
//		String userUrl="http://localhost:5000/"+userId;
//		String policyUrl = "http://localhost:8300/"+courseId;
//		Payment payment1=e1.getForObject(studentUrl, Enrollment.class);
//		Enrollment e2=rt.getForObject(courseUrl, Enrollment.class);
//	    e1.setEnrollmentId(enrollmentId);
//	    e1.set
//	    e1.setCourseId(e2.getCourseId());
//	    e1.setCourseName(e2.getCourseName());
//	    Enrollment e=enr.save(e1);
//	    return e;
//	}

//	@Override
//	public String makePayment(Payment payment) {
//		paymentRepository.save(payment);
//		return "Payment Successfully!!";
//		
//	}

	@Override
	public List<Payment> showPayment() {
		List<Payment> allPayments = paymentRepository.findAll();
		return allPayments;
	}

//	@Override
//	public String addPayment(int policyId,String modeOfPayment,int paymentId) {
//		String userUrl="http://localhost:5001/"+policyId;
//		String amountUrl = "http://localhost:5001/amount/"+policyId;
//		Payment payment1 = restTemplate.getForObject(userUrl, Payment.class);
//		Payment payment2 = restTemplate.getForObject(amountUrl, Payment.class);
//		payment1.setModeOfPayment(modeOfPayment);
//		payment1.setPolicyId(policyId);
//		payment1.setAmount(payment2.getAmount());
//		payment1.setPaymentId(paymentId);
//		paymentRepository.save(payment1);
//		return "Your Payment Will be Verified!!";
//	}
	
	@Override
	public String addPayment(int policyId, String modeOfPayment, int paymentId) {
	    // URLs to fetch payment and amount information
	    String userUrl = "http://2-POLICY-SERVICE/" + policyId;
	    String amountUrl = "http://2-POLICY-SERVICE/amount/" + policyId;
	    String dateUrl = "http://2-POLICY-SERVICE/expiryDate/"+policyId;
	    
	    PaymentDto payment1 = null;
	    PaymentDto payment2 = null;
	    PaymentDto payment3 = null;

	    try {
	        // Fetch payment details associated with the policy
	        payment1 = restTemplate.getForObject(userUrl, PaymentDto.class);
	        
	        // Fetch payment amount for the policy
	        payment2 = restTemplate.getForObject(amountUrl, PaymentDto.class);
	        
	        payment3 = restTemplate.getForObject(dateUrl, PaymentDto.class);
	        
	        System.out.println(payment1.toString());
	        System.out.println(payment2.toString());
	        System.out.println(payment3.toString());
	        
	        // Check if the payment objects are valid
	        if (payment1 == null || payment2 == null || payment3==null) {
	            return "Error: Payment details could not be fetched. Please try again later.";
	        }

	        // Set the relevant fields in payment1
//	        payment1.setModeOfPayment(modeOfPayment);
//	        payment1.setPolicyId(policyId);
//	        payment1.setAmount(payment2.getAmount());  // Set amount from payment2
//	        payment1.setPaymentId(paymentId);

	        // Save the updated payment to the repository
//	        paymentRepository.save(payment1);

	        return "Your Payment Will be Verified!!";

	    } catch (HttpClientErrorException | ResourceAccessException e) {
	        // Handle specific errors (e.g., HTTP errors or network issues)
	        return "Error: Could not reach payment service. Please check the service and try again later.";
	    } catch (Exception e) {
	        // Catch any other unexpected errors
	        return "Error: An unexpected error occurred. Please try again later.";
	    }
	}



}
