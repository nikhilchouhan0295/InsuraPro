package com.user.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        jwtService = new JwtService(); // Initialize JwtService
    }

    @Test
    void testGenerateToken() {
        String username = "testUser";
        String role = "ADMIN";
        
        // Generate token using the JwtService
        String token = jwtService.generateToken(username, role);
        
        // Assert that the token is not null
        assertNotNull(token);
        assertTrue(token.startsWith("eyJhbGciOiJIUzI1NiJ9.")); // JWT tokens typically start with this
    }

    @Test
    void testValidateToken_ValidToken() {
        String username = "testUser";
        String role = "USER";
        String token = jwtService.generateToken(username, role);

        try {
            // Validate the token without any mocks
            jwtService.validateToken(token); // No exception should be thrown
        } catch (Exception e) {
            fail("Token validation should pass");
        }
    }

    @Test
    void testValidateToken_InvalidToken() {
        String invalidToken = "invalid.token";

        // Try to validate an invalid token and assert that an exception is thrown
        Exception exception = assertThrows(Exception.class, () -> {
            jwtService.validateToken(invalidToken);
        });

        // Check that the exception is related to invalid tokens
        assertTrue(exception.getMessage().contains("JWT"));
    }

    @Test
    void testCreateToken() {
        String username = "testUser";
        String role = "USER";
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", "ROLE_" + role);

        // Generate the token
        String token = jwtService.generateToken(username, role);

        // Check that the token contains the role information
        assertTrue(token.contains("ROLE_USER"));
    }
    
}
    
