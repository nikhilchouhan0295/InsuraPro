package com.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/*Author Name : Darshan Bambal 
*Date: 22-11-2024
*Descriptions : This class is created to create the entity 'user' in 'insurance' database 
 */
@Entity
@Data
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Pattern(regexp = "[A-Za-z ]{3,15}", message = "Please enter valid username")
    private String username;
    @Email
    private String email;
    private String password;
    @Pattern(regexp = "^(user|admin)$", message = "Role can only be user or admin")
    private String role;
}
