package com.epam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomException {
	@ExceptionHandler(value = {UserNotFoundException.class})
	public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException customerNotFoundException){
		return new ResponseEntity<String>(customerNotFoundException.getMessage(),HttpStatus.BAD_REQUEST);
	}
}
