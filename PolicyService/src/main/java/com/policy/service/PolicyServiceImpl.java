package com.policy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.policy.dto.policyDto;
import com.policy.entity.Policy;
import com.policy.entity.PurchasedPolicy;
import com.policy.exception.PolicyNotAddedException;
import com.policy.exception.PolicyNotFoundException;
import com.policy.repository.PolicyRepository;
import com.policy.repository.PurchasePolicyRepository;

@Service
public class PolicyServiceImpl implements PolicyService{
	
	@Autowired
	private PolicyRepository policyRepository;
	
	@Autowired
	private PurchasePolicyRepository purchasePolicyRepository;
	
	@Override
	public String addPolicy(Policy policy){
		policy = policyRepository.save(policy);
		boolean added = policyRepository.existsById(policy.getPolicyId());
		if(added) {
			return "Policy Added Successfully!!";
		}
		else {
			throw new PolicyNotAddedException("Policy Not Added");
		}
	}

	@Override
	public List<Policy> showAllPolicy() {
		return policyRepository.findAll();
	}

	@Override
	public String updatePolicy(Policy policy) {
		boolean updated = policyRepository.existsById(policy.getPolicyId());
		if(updated) {
			policyRepository.save(policy);
			return "Policy Updated Successfully!!"; 
	    }
		return "Policy Not Updated,Please add Again";
	}

	@Override
	public String deletePolicy(int policyId) {
		policyRepository.deleteById(policyId);
		Policy p = policyRepository.findById(policyId).orElse(null);
		if(p==null) {
			return "Policy deleted Successfully";
		}
		return "Not deleted, Please try again";
	}
	
	

	@Override
	public int findAmount(int policyId) {
		Policy policy = policyRepository.findById(policyId).orElseThrow(()-> new RuntimeException("ID is not Available!!"));
		return policy.getPolicyAmount();
	}

	@Override
	public Policy findPolicyById(int policyId){
		List<Policy> policyList = policyRepository.findAll();
		for(Policy policy:policyList) {
			if(policy.getPolicyId()==policyId) {
				return policy;
			}
		}
		throw new PolicyNotFoundException("Policy Not Found!!");
	}

	
	
	
	
	@Override
	public policyDto getDetails(int policyID) {
		PurchasedPolicy purchasedpolicy =  purchasePolicyRepository.findById(policyID).orElse(null);
		policyDto policydto=new policyDto();
		policydto.setUserId(purchasedpolicy.getUserId());
		policydto.setAmount(findAmount(policyID));
		policydto.setPolicyId(policyID);
		return policydto;
	}



}
