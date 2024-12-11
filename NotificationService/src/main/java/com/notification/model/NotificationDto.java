package com.notification.model;

import java.time.LocalDate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class NotificationDto {

	// Represents the unique identifier for the user associated with the
	// notification.
	@NotNull(message = "User ID cannot be null")
	private int userId;

	// Represents the email address to which the notification will be sent.
	@Email(message = "Invalid email format")
	@NotEmpty(message = "Email ID cannot be empty")
	private String emailId;

	// Represents the name of the policy associated with the notification (e.g.,
	// health policy, insurance policy).
	@NotEmpty(message = "Policy name cannot be empty")
	private String policyName;

	// Represents the expiry date of the policy. Used to determine if the
	// notification should be triggered.
	@NotNull(message = "Expiry date cannot be null")
	private LocalDate expiryDate;

}
