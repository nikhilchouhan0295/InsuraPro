package com.payment.model;

import java.time.LocalDate;

import lombok.Data;


@Data
public class PaymentDto {
	
	private int userId;
	private int policyId;
	private LocalDate PaymentDate;
	private String modeOfPayment;

}
