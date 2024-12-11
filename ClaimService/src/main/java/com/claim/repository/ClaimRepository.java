package com.claim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.claim.model.Claim;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Integer> {
	
}
