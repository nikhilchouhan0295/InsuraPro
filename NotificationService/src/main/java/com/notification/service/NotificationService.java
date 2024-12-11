package com.notification.service;

//This interface defines the contract for sending policy expiry notifications
public interface NotificationService {
	
	/* Method to check if a user's policy has expired and send a notification
	 accordingly.*/
	String checkPolicyExpiryAndSendNotification(int userId,String emailId);

}
