package com.claim.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.claim.model.Claim;

@Service
public interface ClaimService {
	String claim(int userId, int policyId, int claimId);
	List<Claim> showAllClaim();
}
