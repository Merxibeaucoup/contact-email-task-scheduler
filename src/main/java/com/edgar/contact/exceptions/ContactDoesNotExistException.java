package com.edgar.contact.exceptions;

public class ContactDoesNotExistException extends RuntimeException {
	
	
	private static final long serialVersionUID = 1L;

	public ContactDoesNotExistException(String message) {
		super(message);
	}

}
