package com.policy.dto;

import java.time.LocalDate;

import org.springframework.stereotype.Component;
 
import lombok.Data;
 

@Data
public class PurchasePolicyDto {
 
	private int userId;
	private String policyName;
	private LocalDate expiryDate;
}
