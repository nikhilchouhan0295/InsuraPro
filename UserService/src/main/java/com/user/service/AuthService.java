package com.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.user.entity.User;
import com.user.repository.UserCredentialRepository;

/*Author Name : Darshan Bambal 
*Date : 22-11-2024 
*Descriptions : This is the service class which have following methds
*	saveUser() method to save the user details to the user table
*	generateToken() method creates Token for specific user
*	validateToken() method validates the the Token given by the user 
*	getById() method is used to get the user object by using the userId of the user
 */

@Service
public class AuthService {

    @Autowired
    private UserCredentialRepository repository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public String saveUser(User credential) {
        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
        repository.save(credential);
        return "user added to the system";
    }

    public String generateToken(String username,String role) {
        return jwtService.generateToken(username,role);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }
    
    public User getById(int id) {
    	return repository.findById(id).orElseThrow(()-> new RuntimeException("User not found"));
    }

}
