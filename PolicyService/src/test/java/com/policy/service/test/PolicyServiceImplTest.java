package com.policy.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.policy.dto.policyDto;
import com.policy.entity.Policy;
import com.policy.entity.PurchasedPolicy;
import com.policy.exception.PolicyNotAddedException;
import com.policy.exception.PolicyNotFoundException;
import com.policy.repository.PolicyRepository;
import com.policy.repository.PurchasePolicyRepository;
import com.policy.service.PolicyServiceImpl;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
public class PolicyServiceImplTest {

    @Autowired
    private PolicyServiceImpl policyService;

    @Autowired
    private PolicyRepository policyRepository;

    @Autowired
    private PurchasePolicyRepository purchasePolicyRepository;

    private Policy policy;

    @BeforeEach
    public void setUp() {
        // Create a sample policy to test the methods
        policy = new Policy();
        policy.setPolicyId(1);
        policy.setPolicyName("Test Policy");
        policy.setPolicyAmount(5000);
        policyRepository.save(policy);
    }

    @Test
    public void testAddPolicy() {
        Policy newPolicy = new Policy();
        newPolicy.setPolicyId(2);
        newPolicy.setPolicyName("New Policy");
        newPolicy.setPolicyAmount(3000);

        String result = policyService.addPolicy(newPolicy);
        assertEquals("Policy Added Successfully!!", result);
    }

    @Test
    public void testUpdatePolicy() {
        policy.setPolicyAmount(6000);  // Update the policy amount
        String result = policyService.updatePolicy(policy);
        assertEquals("Policy Updated Successfully!!", result);
    }

    @Test
    public void testDeletePolicy() {
        String result = policyService.deletePolicy(policy.getPolicyId());
        assertEquals("Policy deleted Successfully", result);
    }

    @Test
    public void testFindAmount() {
        int amount = policyService.findAmount(policy.getPolicyId());
        assertEquals(6000, amount);  // Ensure the correct policy amount is returned
    }

    @Test
    public void testFindPolicyById() {
        Policy foundPolicy = policyService.findPolicyById(policy.getPolicyId());
        assertEquals(policy.getPolicyId(), foundPolicy.getPolicyId());
    }

    @Test
    public void testFindPolicyById_NotFound() {
        assertThrows(PolicyNotFoundException.class, () -> {
            policyService.findPolicyById(9999);  // This policy ID doesn't exist
        });
    }

    @Test
    public void testGetDetails() {
        PurchasedPolicy purchasedPolicy = new PurchasedPolicy();
        purchasedPolicy.setPolicyId(policy.getPolicyId());
        purchasedPolicy.setUserId(1);
        purchasePolicyRepository.save(purchasedPolicy);

        policyDto dto = policyService.getDetails(policy.getPolicyId());
        assertEquals(1, dto.getUserId());  // Check that the userId is correctly mapped
        assertEquals(5000, dto.getAmount());  // Check that the amount is correctly mapped
        assertEquals(policy.getPolicyId(), dto.getPolicyId());  // Check that the policyId is correctly mapped
    }

    @Test
    public void testAddPolicy_Failure() {
        Policy duplicatePolicy = new Policy();
        duplicatePolicy.setPolicyId(1);  // Using the existing policy ID
        duplicatePolicy.setPolicyName("Duplicate Policy");
        duplicatePolicy.setPolicyAmount(10000);

        assertThrows(PolicyNotAddedException.class, () -> {
            policyService.addPolicy(duplicatePolicy);  // This should fail because of the duplicate ID
        });
    }

}

