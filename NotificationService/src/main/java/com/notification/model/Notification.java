package com.notification.model;

import java.util.Date;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Notification {

	// Represents the recipient of the notification (e.g., email address).
	private String recipient;

	// Represents the body content of the notification (e.g., the message content).
	private String msgBody;

	// Represents the subject of the notification (e.g., subject line for emails).
	private String subject;

}
