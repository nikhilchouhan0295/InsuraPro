package com.policy.service;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.policy.dto.PurchasePolicyDto;
import com.policy.entity.Policy;
import com.policy.entity.PurchasedPolicy;
import com.policy.exception.UserIdNotFoundException;
import com.policy.repository.PolicyRepository;
import com.policy.repository.PurchasePolicyRepository;

@Service
public class PurchasedPolicyServiceImpl implements PurchasedPolicyService{
	
	@Autowired
	private PurchasePolicyRepository purchasePolicyRepo;
	
	
	@Autowired
	private PolicyRepository policyRepository;
	
	
//	@Autowired
//	private PurchasedPolicy policypurchased;
	
	
//	@Autowired
//	private Policy policy;

	@Override
	public String  getPolicy(PurchasedPolicy purchasePolicy) {
	   purchasePolicyRepo.save(purchasePolicy);
	   return "For Making the payment\\nPlease visit on http://localhost:5002/makePayment";
	}

	@Override
	public int findPolicyById(int policyId) {
		PurchasedPolicy purchasedPolicy = purchasePolicyRepo.findByPolicyId(policyId);
		return purchasedPolicy.getUserId();
	}

	@Override
	public LocalDate settingExpirydate(int policyId) {
		LocalDate currDate = LocalDate.now();
		LocalDate expiryDate = currDate.plusYears(1);
//		LocalDate newExpiryDate = purchasePolicyRepo.save();
		
		return expiryDate;
	}
	
	@Override
	public PurchasePolicyDto notificationDetails(int userId) throws UserIdNotFoundException {
		PurchasedPolicy purchasedpolicy=purchasePolicyRepo.findById(userId).orElseThrow(()-> new UserIdNotFoundException("UserId not found"));
		Policy policy= policyRepository.findById(purchasedpolicy.getPolicyId()).orElse(null);
		PurchasePolicyDto purchasePolicyDto = new PurchasePolicyDto();
		purchasePolicyDto.setUserId(purchasedpolicy.getUserId());
		purchasePolicyDto.setPolicyName(policy.getPolicyName());
		purchasePolicyDto.setExpiryDate(purchasedpolicy.getExpiryDate());
		return purchasePolicyDto;
	}


}
