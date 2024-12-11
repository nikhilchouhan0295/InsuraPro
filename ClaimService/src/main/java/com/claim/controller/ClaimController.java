package com.claim.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.claim.model.Claim;
import com.claim.service.ClaimService;

@RestController
public class ClaimController {
	
	@Autowired
	private ClaimService claimService;
	
	@GetMapping("/claim/{userId}/{policyId}/{claimId}")
	public ResponseEntity<?> claim(@PathVariable int userId, @PathVariable int policyId, @PathVariable int claimId){
		String checkRequest = claimService.claim(userId, policyId, claimId);
		return new ResponseEntity<>(checkRequest,HttpStatus.OK);
	}
	
	@GetMapping("/showAllClaim")
	public List<Claim> showAllClaim(){
		List<Claim> claimList = claimService.showAllClaim();
		return claimList;
	}
}

