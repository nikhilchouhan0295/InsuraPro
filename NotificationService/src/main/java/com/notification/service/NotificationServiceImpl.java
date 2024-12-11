package com.notification.service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.notification.exception.PolicyExpiryNotificationException;
import com.notification.exception.PolicyNotFoundException;
import com.notification.model.Notification;
import com.notification.model.NotificationDto;

import jakarta.mail.internet.MimeMessage;

@Service
public class NotificationServiceImpl implements NotificationService {

	// Automatically injects an instance of JavaMailSender for sending emails.
	@Autowired
	private JavaMailSender javaMailSender;

	// Automatically injects RestTemplate to make HTTP requests.
	@Autowired
	private RestTemplate restTemplate;

	// Automatically injects the Notification object used for sending the email.
	@Autowired
	private Notification notification;

	// Reads the sender's email address from the application properties.
	@Value("${spring.mail.username}")
	private String sender;

	// Defines the threshold in days for warning users about policy expiry.
	private static final int EXPIRY_WARNING_DAYS = 15;

	// The URL of the policy service from where policy details will be fetched.
	@Value("${policy.service.url}")
	private String policyServiceUrl;

	private LocalDate expiryDate;
	private String policyName;

	// Method to send a simple email to the recipient about the policy expiry.
	public String sendSimpleMail(Notification notification) {

		try {

			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(sender);
			helper.setTo(notification.getRecipient());
			helper.setSubject(notification.getSubject());

			// Read the HTML template for the email body and replace placeholders with
			// actual values.
			String htmlContent = readHtmlTemplate("/templates/emailContext.html");
			htmlContent = htmlContent.replace("${policyName}", policyName);
			htmlContent = htmlContent.replace("${expiryDate}", expiryDate.toString());
			helper.setText(htmlContent, true);

			// Send the email using JavaMailSender.
			javaMailSender.send(message);
			return "Mail Sent Successfully...";
		}

		catch (Exception e) {
			return e.getMessage();
		}
	}

	// Helper method to read an HTML template from a file.
	private String readHtmlTemplate(String templatePath) throws Exception {
		try (var inputStream = Objects.requireNonNull(getClass().getResourceAsStream(templatePath))) {
			return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
		}
	}

	// Method to check policy expiry and send a notification if applicable.
	public String checkPolicyExpiryAndSendNotification(int userId,String emailId) {
		try {
			String url = policyServiceUrl + userId;

			// Make a GET request to the policy service to fetch the policy details as a
			// NotificationDto.
			NotificationDto notificationDto = restTemplate.getForObject(url, NotificationDto.class);

			if (notificationDto.getUserId() != userId) {
				throw new PolicyNotFoundException("Policy details not found for user ID " + userId);

			}

			// Extract the expiry date and policy name from the received data.
			expiryDate = notificationDto.getExpiryDate();
			policyName = notificationDto.getPolicyName();

			if (expiryDate == null || !StringUtils.hasText(policyName)) {
				return "Invalid policy data retrieved for user ID " + userId;
			}

			// Calculate the number of days remaining until the policy expires.
			long daysUntilExpiry = ChronoUnit.DAYS.between(LocalDate.now(), expiryDate);

			// Check if the policy is expiring within the warning period (15 days).
			if (daysUntilExpiry <= EXPIRY_WARNING_DAYS) {
				notification.setRecipient(emailId);
				notification.setSubject("Policy Expiry Reminder:" + policyName);
				return sendSimpleMail(notification);
			} else {
				return "Policy is not expiring within the next 15 days.";
			}
		} catch (Exception e) {
			throw new PolicyExpiryNotificationException("Error checking policy expiry: " + e.getMessage());
		}
	}

}