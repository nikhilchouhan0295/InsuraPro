package com.payment.service;

import java.util.List;

import com.payment.model.Payment;

public interface PaymentService {
	List<Payment> showPayment();
	String addPayment(int policyId, String modeOfPayment, int paymentId);
}
