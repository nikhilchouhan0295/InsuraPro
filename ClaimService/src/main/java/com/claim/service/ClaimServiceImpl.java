package com.claim.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.claim.model.Claim;
import com.claim.model.ClaimDto;
import com.claim.repository.ClaimRepository;

@Service
public class ClaimServiceImpl implements ClaimService{
	
	@Autowired
	private ClaimRepository claimRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	public String claim(int userId, int policyId, int claimId) {
		String policyUrl = "http://paymentservice"+policyId;
		ClaimDto expiryDate = restTemplate.getForObject(policyUrl, ClaimDto.class);
		String status = "";
		
		Claim claim = new Claim();
		if(LocalDate.now().isAfter(expiryDate.getExpiryDate())) {
			status = "Expired";
			claim.setStatus("Expired");
		}
		else {
			claim.setStatus("Not Expired");
		}
		
		claim.setPolicyId(policyId);
		claim.setUserId(userId);
		claim.setStatus(status);
		claim.setClaimId(claimId);
		claimRepository.save(claim);
		
		if(claimRepository.existsById(claimId)) {
			return "Your Claim Request is received, will shortly inform you regarding claim!!" ;
			}else {
				return "your policy is expired, can not be claimed";
			}
		}

	
	@Override
	public List<Claim> showAllClaim() {
		List<Claim> claimList = claimRepository.findAll();
		return claimList;
	}

	
}
