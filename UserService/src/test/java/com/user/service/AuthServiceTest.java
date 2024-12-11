package com.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.user.entity.User;
import com.user.repository.UserCredentialRepository;
import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional// Ensures that database changes are rolled back after each test
public class AuthServiceTest {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserCredentialRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    private User user;

    @BeforeEach
    public void setUp() {
        // Initialize a dummy user for testing
        user = new User();
        user.setUsername("testuser");
        user.setPassword("testpassword"); // Plain password
    }

    @Test
    public void testSaveUser() {
        // Act
        String result = authService.saveUser(user);

        // Assert
        assertEquals("user added to the system", result);

        // Verify if the password was actually encoded and saved in the repository
        Optional<User> savedUser = repository.findById(user.getId());
        assertTrue(savedUser.isPresent());
        assertTrue(passwordEncoder.matches("testpassword", savedUser.get().getPassword()));
    }

    @Test
    public void testGenerateToken() {
        // Arrange
        String username = "testuser";
        String role = "admin";
        String expectedToken = "dummyToken"; // Here you can set your actual JWT token logic or expect a real token

        // Act
        String token = authService.generateToken(username, role);

        // Assert
        assertNotNull(token);
        // If you need to assert the actual token content, you will need to configure the JwtService accordingly
        // For simplicity, we just verify that the token is generated
    }


    @Test
    public void testGetByIdUserExists() {
        // Arrange
        // Save the user to the repository
        repository.save(user);

        // Act
        User foundUser = authService.getById(user.getId());

        // Assert
        assertNotNull(foundUser);
        assertEquals(user.getId(), foundUser.getId());
    }

    @Test
    public void testGetByIdUserNotFound() {
        // Act & Assert
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            authService.getById(user.getId());
        });
        assertEquals("User not found", thrown.getMessage());
    }
}