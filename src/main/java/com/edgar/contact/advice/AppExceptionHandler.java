package com.edgar.contact.advice;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.edgar.contact.exceptions.ContactAlreadyExistException;
import com.edgar.contact.exceptions.ContactDoesNotExistException;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ContactDoesNotExistException.class)
	public ResponseEntity<Object> handleContactNotFound(ContactDoesNotExistException ex, WebRequest request) {

		return new ResponseEntity<>(new ApiError(ex.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now()),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ContactAlreadyExistException.class)
	public ResponseEntity<Object> handleContactAlreadyExists(ContactAlreadyExistException ex, WebRequest request) {

		return new ResponseEntity<>(new ApiError(ex.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()),
				HttpStatus.BAD_REQUEST);
	}

}
