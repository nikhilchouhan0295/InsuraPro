package com.policy.api;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.policy.dto.PurchasePolicyDto;
import com.policy.dto.policyDto;
import com.policy.entity.Policy;
import com.policy.entity.PurchasedPolicy;
import com.policy.exception.PolicyNotAddedException;
import com.policy.exception.UserIdNotFoundException;
import com.policy.service.PolicyService;
import com.policy.service.PurchasedPolicyService;


@RestController
public class PolicyController {
	
	@Autowired
	private PurchasedPolicyService purchasePolicyService;
	
	@Autowired
	private PolicyService policyService;

	@GetMapping("/test")
	public ResponseEntity<String> test() {
		return new ResponseEntity<>("Its Working", HttpStatus.OK);
	}
	
	@PostMapping("/addPolicy")
	public ResponseEntity<?> addPolicy(@RequestBody Policy policy){
		String message = policyService.addPolicy(policy);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
	
	@PutMapping("/updatePolicy")
	public ResponseEntity<?> updatePolicy(@RequestBody Policy policy){
		String message = policyService.updatePolicy(policy);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
	
	@DeleteMapping("/deletePolicy/{policyId}")
	public ResponseEntity<?> deletePolicy(@PathVariable int policyId){
		String message = policyService.deletePolicy(policyId);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
	
	@GetMapping("/showAllPolicies")
	public List<Policy> showAllPolicies(){
		return policyService.showAllPolicy();
	}
	
	@PostMapping("/buyPolicy")
	public ResponseEntity<?> buyPolicy(@RequestBody PurchasedPolicy purchasePolicy){
		String paymentRequest = purchasePolicyService.getPolicy(purchasePolicy);
		return new ResponseEntity<>(paymentRequest,HttpStatus.OK);
	}
	
	@GetMapping("/{policyId}")
	public int getUserId(@PathVariable int policyId){
		int userId = purchasePolicyService.findPolicyById(policyId);
		return userId;
	}
	
	@GetMapping("/amount/{policyId}")
	public int getPolicyAmount(@PathVariable int policyId){
		int policyAmount = policyService.findAmount(policyId);
		return policyAmount;
	}
	
	@GetMapping("/expiryDate/{policyId}")
	public ResponseEntity<?> settingExpirydate(@PathVariable int policyId){
		LocalDate expiryDate = purchasePolicyService.settingExpirydate(policyId);
		return new ResponseEntity<>(expiryDate, HttpStatus.OK);
	}
	
	
	@GetMapping("/getDetails/{policyId}")
	public policyDto getDetails(@PathVariable int policyId){
		return policyService.getDetails(policyId);
	}
	
	
	@GetMapping("/notification/{userId}")
	public ResponseEntity<?> notificationDetails(int userId) throws UserIdNotFoundException{
		PurchasePolicyDto purchaseDto = purchasePolicyService.notificationDetails(userId);
		return new ResponseEntity<>(purchaseDto, HttpStatus.OK);
	}

}
