package com.payment.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Payment {
	@Id
	private long paymentId;
	private int userId;
	private int policyId;
	private int amount;
	private String modeOfPayment;
	
}
