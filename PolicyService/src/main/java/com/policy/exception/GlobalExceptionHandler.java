package com.policy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(PolicyNotAddedException.class)
	public ResponseEntity<?> handlePolicyNotAddedException(PolicyNotAddedException ex){
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(PolicyNotFoundException.class)
	public ResponseEntity<?> handlePolicyNotFoundException(PolicyNotFoundException policyEx){
		return new ResponseEntity<>(policyEx.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserIdNotFoundException.class)
	public ResponseEntity<?> handleUserIdNotFoundException(UserIdNotFoundException e){
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception e){
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
    //create exception class
	//exception response class

}

