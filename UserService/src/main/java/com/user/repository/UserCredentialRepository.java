package com.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.user.entity.User;
import java.util.Optional;

/*Author Name : Darshan Bambal 
*Date and Time : 22-11-2024
*Descriptions : This interface extends the JpaRepository 
* 'findByUsername()' method is use to find the User object by passing username as argument
 */
public interface UserCredentialRepository  extends JpaRepository<User,Integer> {
	
	//To find the object by username
    @Query("SELECT u FROM User u WHERE u.username = :username")
	Optional<User> findByUsername(String username);
}
