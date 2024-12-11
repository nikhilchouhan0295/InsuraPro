package com.notification.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

// This class will handle exceptions globally across your entire Spring Boot application
@ControllerAdvice
public class GlobalExceptionHandler {

	// Handle general exceptions
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGeneralException(Exception ex) {
		return new ResponseEntity<>("An unexpected error occurred: " + ex.getMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// Handle specific exception if policy details are not found
	@ExceptionHandler(PolicyNotFoundException.class)
	public ResponseEntity<String> handlePolicyNotFoundException(PolicyNotFoundException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex) {
		return new ResponseEntity<>("Constraint violation: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	// Handle NullPointerException if it occurs
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<String> handleNullPointerException(NullPointerException ex) {
		return new ResponseEntity<>("Null pointer exception: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	// Handle custom exception related to policy expiry notifications
	@ExceptionHandler(PolicyExpiryNotificationException.class)
	public ResponseEntity<String> handlePolicyExpiryNotificationException(PolicyExpiryNotificationException ex) {
		return new ResponseEntity<>("Error sending policy expiry notification", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// Handle any other exceptions related to the NotificationDto class
	@ExceptionHandler(NotificationDtoException.class)
	public ResponseEntity<String> handleNotificationDtoException(NotificationDtoException ex) {
		return new ResponseEntity<>("Error processing NotificationDto: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
