package com.policy.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Policy {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int policyId;
	@NotNull(message="Policy Cannot be null")
	private String policyName;
	private int policyAmount;
	private int duration;
	private String policyType;
	@Override
	public String toString() {
		return "Policy [policyId=" + policyId + ", policyName=" + policyName + ", policyAmount=" + policyAmount
				+ ", duration=" + duration + ", policyType=" + policyType + "]";
	}
	
}
