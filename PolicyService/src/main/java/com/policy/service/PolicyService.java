package com.policy.service;

import java.util.List;

import com.policy.dto.policyDto;
import com.policy.entity.Policy;
import com.policy.exception.PolicyNotAddedException;
import com.policy.exception.PolicyNotFoundException;

public interface PolicyService {
	String addPolicy(Policy policy);
	List<Policy> showAllPolicy();
	String updatePolicy(Policy policy);
	String deletePolicy(int policyId);
	int findAmount(int policyId);
	Policy findPolicyById(int policyId);
	policyDto getDetails(int policyID);
	
}
