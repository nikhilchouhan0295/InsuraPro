package com.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*Author Name : Darshan Bambal 
*Date: 23-11-2024
*Descriptions : This class is created to take the username and password from the user as a object 
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

    private String username;
    private String password;
   
	
	

}
