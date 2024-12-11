package com.notification.api;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.notification.service.NotificationService;
 
@RestController
@RequestMapping("/notification")
public class NotificationController {
	@Autowired
	private NotificationService notificationService;
 
	@GetMapping("/sendMail/{userId}/{emaiId}")
	public ResponseEntity<String> notifyUser(@PathVariable int userId, @PathVariable String emaiId) {
 
		// Calls the NotificationService to check the user's policy expiry and send a
		// notification.
		String response = notificationService.checkPolicyExpiryAndSendNotification(userId, emaiId);
 
		// Returns a ResponseEntity with the response and an HTTP status of OK (200).
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
 
}
