package com.policy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.policy.entity.PurchasedPolicy;

@Repository
public interface PurchasePolicyRepository extends JpaRepository<PurchasedPolicy, Integer>{
	PurchasedPolicy findByPolicyId(int policyId); 
}
