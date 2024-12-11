package com.claim.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Entity
@Data
public class Claim {
	
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "myseq")
//	@SequenceGenerator(name="myseq", sequenceName = "Claim_seq", initialValue = 1001)
	@Id
	private int claimId;
	private int userId;
	private int policyId;
	private String status;
}
