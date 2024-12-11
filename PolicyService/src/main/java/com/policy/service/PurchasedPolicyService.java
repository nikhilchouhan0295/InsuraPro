package com.policy.service;

import java.time.LocalDate;

import com.policy.dto.PurchasePolicyDto;
import com.policy.entity.PurchasedPolicy;
import com.policy.exception.UserIdNotFoundException;

public interface PurchasedPolicyService {
	String getPolicy(PurchasedPolicy purchasePolicy);
	int findPolicyById(int policyId);
	LocalDate settingExpirydate(int policyId);
	PurchasePolicyDto notificationDetails(int policyId) throws UserIdNotFoundException;
}
